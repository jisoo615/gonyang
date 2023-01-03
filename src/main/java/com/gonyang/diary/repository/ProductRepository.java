package com.gonyang.diary.repository;

import com.gonyang.diary.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByCategoryId(String categoryId);
}
