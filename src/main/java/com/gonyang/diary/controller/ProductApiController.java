package com.gonyang.diary.controller;

import com.gonyang.diary.dto.ProductDto;
import com.gonyang.diary.dto.ResponseDto;
import com.gonyang.diary.entity.Product;
import com.gonyang.diary.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class ProductApiController {
    final private ProductService productService;

    @GetMapping("product/{categoryId}")
    public ResponseEntity<?> getProduct(@PathVariable String categoryId){
        List<Product> entities = productService.retrieve(categoryId);
        List<ProductDto> dtos = entities.stream().map(ProductDto::new).collect(Collectors.toList());
        ResponseDto<ProductDto> response = ResponseDto.<ProductDto>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("product")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto dto){
        try{
            System.out.println(dto);

            List<Product> entities = productService.create(dto);
            System.out.println(entities.get(0));

            List<ProductDto> dtos = entities.stream().map(ProductDto::new).collect(Collectors.toList());
            System.out.println(dtos.get(0));

            ResponseDto<ProductDto> response = ResponseDto.<ProductDto>builder().data(dtos).build();
            System.out.println(response);
            return ResponseEntity.ok().body(dtos);
        }catch (Exception e){
            String error = e.getMessage();
            ResponseDto<ProductDto> response = ResponseDto.<ProductDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("product")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto dto){
        Product entity = ProductDto.toEntity(dto);
        List<Product> entites = productService.update(entity);
        List<ProductDto> dtos = entites.stream().map(ProductDto::new).collect(Collectors.toList());
        ResponseDto<ProductDto> response = ResponseDto.<ProductDto>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("product")
    public ResponseEntity<?> deleteTodo(@RequestBody ProductDto dto){
        try{
            Product entity = ProductDto.toEntity(dto);
            List<Product> entities = productService.delete(entity);
            List<ProductDto> dtos = entities.stream().map(ProductDto::new).collect(Collectors.toList());
            ResponseDto<ProductDto> repponse = ResponseDto.<ProductDto>builder().data(dtos).build();
            return ResponseEntity.ok().body(repponse);
        }catch (Exception e){
            String error = e.getMessage();
            ResponseDto<ProductDto> repponse = ResponseDto.<ProductDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(repponse);
        }
    }

    // naver product search

}
