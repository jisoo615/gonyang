package com.gonyang.diary.dto;

import com.gonyang.diary.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatDto {
    private String id;
    private String name;
    private int age;
    private String introduction;
    private String feature;

    public CatDto(Pet entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.age = entity.getAge();
        this.introduction = entity.getIntroduction();
        this.feature = entity.getFeature();
    }

    public Pet toEntity(CatDto dto){
        return Pet.builder()
                .age(dto.age)
                .feature(dto.feature)
                .id(dto.id)
                .introduction(dto.introduction)
                .name(dto.name)
                .build();
    }

}
