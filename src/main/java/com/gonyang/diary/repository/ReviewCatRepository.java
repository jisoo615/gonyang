package com.gonyang.diary.repository;

import com.gonyang.diary.entity.ReviewPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewCatRepository extends JpaRepository<ReviewPet, String> {
}
