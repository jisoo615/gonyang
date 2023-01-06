package com.gonyang.diary.exception;

import com.gonyang.diary.constant.CustomErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException{
    private final CustomErrorCode customErrorCode;

}
