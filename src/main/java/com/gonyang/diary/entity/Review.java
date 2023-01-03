package com.gonyang.diary.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@ToString(exclude = {"reviewPets", "product"})
public class Review {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")// hibernate가 제공하는 기본gnerator가 아니라 나만의 generator를 사용하고 싶을때
    private String id;
    private String title;
    private String content;
    private int score;

    @OneToMany(mappedBy = "review")
    private List<ReviewPet> reviewPets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}
