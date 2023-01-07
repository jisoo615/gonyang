package com.gonyang.diary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gonyang.diary.dto.ProductDto;
import static org.assertj.core.api.Assertions.assertThat;

import com.gonyang.diary.entity.Category;
import com.gonyang.diary.repository.CategoryRepository;
import com.gonyang.diary.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import  static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductApiController.class)
public class ProductApiControllerTest {
    @Autowired MockMvc mvc;
    @MockBean ProductService productService;
    @MockBean CategoryRepository categoryRepository;

    @Test
    @DisplayName("상품 저장후, 같은 카테고리 상품 목록 조회")
    void test() throws Exception{
        given(categoryRepository.save(Category.builder().id("001").title("낚싯대").products(new ArrayList<>()).build()));

        ObjectMapper objectMapper = new ObjectMapper();
        ProductDto productRequestDto = ProductDto.builder()
                .title("테스트 장난감 1")
                .price(3000)
                .naverLink("https://naver.com/search?djskjfsskfjs")
                .naverProductId(999999L)
                .categoryId("001")
                .build();
        String jsonStr = objectMapper.writeValueAsString(productRequestDto);
        System.out.println(jsonStr);
        ResultActions resultActions = mvc.perform(
                        post("/api/product")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonStr)
                                .characterEncoding("UTF-8")
                )
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.data[0].title").value("테스트 장난감 1"))
                .andExpect(jsonPath("$.data[0].categoryId").value("001"))
                .andExpect(jsonPath("$.data[0].naverCategoryId").value(999999L))
                .andExpect(jsonPath("$.data[0].id").value(String.class));

    }

}
