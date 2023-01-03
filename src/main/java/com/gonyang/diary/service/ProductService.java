package com.gonyang.diary.service;

import com.gonyang.diary.dto.ProductDto;
import com.gonyang.diary.entity.Category;
import com.gonyang.diary.entity.Product;
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
        return productRepository.findAllByCategoryId(categoryId);
    }

    public List<Product> create(ProductDto dto){
        Product entity = ProductDto.toEntity(dto);
        System.out.println(entity);
        Category categoryEntity = categoryRepository.findById(dto.getCategoryId()).get();
        System.out.println(categoryEntity);
        entity.setCategory(categoryEntity);
        entity.setReviews(new ArrayList<>());
        System.out.println(entity);
        log.info("create entity {}", entity);
        validate(entity);
        System.out.println("valiate pass: "+entity);
        productRepository.save(entity);
        log.info("Entity id : {}", entity.getId());
        return productRepository.findAll();
    }

    public List<Product> update(Product entity){
        validate(entity);

        Optional<Product> original = productRepository.findById(entity.getId());

        original.ifPresent(product -> {
            product.setTitle(entity.getTitle());
            product.setNaverLink(entity.getNaverLink());
            product.setNaverProductId(entity.getNaverProductId());
            product.setCategory(entity.getCategory());

            productRepository.save(product);
        });
        return retrieve(entity.getCategory().getId());
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
        System.out.println(entity);
        log.info("validate entity: {}", entity);
        if(entity == null) {
            System.out.println(entity);

            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }
        System.out.println(entity);

    }

}
