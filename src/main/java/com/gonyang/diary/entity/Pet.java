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
@ToString(exclude = {"user", "reviewPets"})
public class Pet {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")// hibernate가 제공하는 기본gnerator가 아니라 나만의 generator를 사용하고 싶을때
    // strategy = "uuid" 일때 오류로 "uuid2"로 변경
    private String id;
    private String name;
    private int age;
    private String introduction;
    private String feature;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "pet")
    private List<ReviewPet> reviewPets;

}
