package com.gonyang.diary.dto;

import com.gonyang.diary.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private String id;
    private String title;
    private String categoryId;
    private Long naverProductId;
    private String naverLink;
    private String naverImage;
    private int price;

    public ProductDto(Product entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.price = entity.getPrice();
        this.categoryId = entity.getCategory().getId();
        this.naverImage = entity.getNaverImage();
        this.naverLink = entity.getNaverLink();
        this.naverProductId = entity.getNaverProductId();
    }

    public static Product toEntity(ProductDto dto){
        return Product.builder()
                .id(dto.id)
                .title(dto.title)
                .naverImage(dto.naverImage)
                .naverLink(dto.naverLink)
                .naverProductId(dto.naverProductId)
                .price(dto.price)
                .build();
    }

}
