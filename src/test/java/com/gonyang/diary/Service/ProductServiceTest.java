package com.gonyang.diary.Service;

import com.gonyang.diary.entity.Category;
import com.gonyang.diary.repository.CategoryRepository;
import com.gonyang.diary.repository.ProductRepository;
import com.gonyang.diary.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

}
