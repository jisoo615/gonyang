package com.gonyang.diary.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@ToString(exclude = "products")
public class Category {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")// hibernate가 제공하는 기본gnerator가 아니라 나만의 generator를 사용하고 싶을때
    private String id;

    private String title;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
