package com.gonyang.diary.repository;

import com.gonyang.diary.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<Pet, String> {
}
