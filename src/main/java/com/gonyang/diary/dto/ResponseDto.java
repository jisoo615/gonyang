package com.gonyang.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ResponseDto<T> {
    private List<T> data;
    private String error;

}
