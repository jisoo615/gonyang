package com.gonyang.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewDto {
    private String review_id;
    private String title;
    private String content;
    private int score;
    private String product_id;
    private List<CatDto> catsDto;// 고양이 id 1~n개
    private ProductDto productDto;

}
