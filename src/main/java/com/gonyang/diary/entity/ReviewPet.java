package com.gonyang.diary.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@ToString(exclude = {"pet", "review"})
public class ReviewPet {
    // 리뷰 하나에 고양이 여러마리, 고양이 하나에 리뷰 여러개
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")// hibernate가 제공하는 기본gnerator가 아니라 나만의 generator를 사용하고 싶을때
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    public ReviewPet(Pet pet, Review review){// 양쪽 테이블에 하나씩 넣어줘야함
        this.pet = pet;
        pet.getReviewPets().add(this);
        this.review = review;
        review.getReviewPets().add(this);
    }

}
