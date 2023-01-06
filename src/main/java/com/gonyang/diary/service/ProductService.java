package com.gonyang.diary.service;

import com.gonyang.diary.constant.CustomErrorCode;
import com.gonyang.diary.dto.ProductDto;
import com.gonyang.diary.entity.Category;
import com.gonyang.diary.entity.Product;
import com.gonyang.diary.exception.CustomException;
import com.gonyang.diary.repository.CategoryRepository;
import com.gonyang.diary.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    final private ProductRepository productRepository;
    final private CategoryRepository categoryRepository;

    public List<Product> retrieve(String categoryId){
        if(categoryId==null){
            throw new CustomException(CustomErrorCode.INTERNAL_SERVER_ERROR);
        }
        if(categoryRepository.findById(categoryId).isEmpty()){
            throw new CustomException(CustomErrorCode.CATEGORY_NOT_FOUND);
        }
        return productRepository.findAllByCategoryId(categoryId);
    }

    public List<Product> create(ProductDto dto){
        Product entity = ProductDto.toEntity(dto);

        productRepository.findProductByNaverProductId(dto.getNaverProductId())
                .ifPresent(ex -> { throw new CustomException(CustomErrorCode.ALREADY_SAVED_PRODUCT);} );

        Category categoryEntity = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()-> new CustomException(CustomErrorCode.CATEGORY_NOT_FOUND));
        entity.setCategory(categoryEntity);
        entity.setReviews(new ArrayList<>());

        log.info("create entity {}", entity);
        validate(entity);
        productRepository.save(entity);
        log.info("Entity id : {}", entity.getId());
        return productRepository.findAll();
    }

    public List<Product> update(Product entity){
        validate(entity);

        Optional<Product> original = productRepository.findProductByNaverProductId(entity.getNaverProductId());
        if(original.isEmpty()) throw new CustomException(CustomErrorCode.PRODUCT_NOT_FOUND);

        Product old = original.get();
        old.setTitle(entity.getTitle());
        old.setPrice(entity.getPrice());
        old.setNaverLink(entity.getNaverLink());
        old.setNaverImage(entity.getNaverImage());
// 카테고리 바꾸는 건 updateCategory로 따로 만들기

        productRepository.save(old);
        return retrieve(old.getCategory().getId());
    }

    public List<Product> delete(Product entity){
        validate(entity);
        try{
            productRepository.delete(entity);
        }catch (Exception e){
            log.error("error deleting entity", entity.getId(), e);
            throw new RuntimeException("error deleting entity"+entity.getId());
        }

        return retrieve(entity.getCategory().getId());
    }


    private void validate(Product entity){
        if(entity == null) {
            log.warn("Entity cannot be null");
            throw new CustomException(CustomErrorCode.INVALID_PARAMETER);
        }
    }

}
