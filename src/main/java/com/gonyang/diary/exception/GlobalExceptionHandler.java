package com.gonyang.diary.exception;

import com.gonyang.diary.constant.CustomErrorCode;
import com.gonyang.diary.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.gonyang.diary.constant.CustomErrorCode.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ CustomException.class })
    protected ResponseEntity handleCustomException(CustomException ex){
        return new ResponseEntity(
                ErrorDto.builder()
                        .status(ex.getCustomErrorCode().getStatus())
                        .message(ex.getCustomErrorCode().getMessage())
                        .build()
        , HttpStatus.valueOf(ex.getCustomErrorCode().getStatus()));
    }

    @ExceptionHandler({ Exception.class })
    protected ResponseEntity handleException(Exception ex){
        return new ResponseEntity(
                ErrorDto.builder()
                        .status(INTERNAL_SERVER_ERROR.getStatus())
                        .message(INTERNAL_SERVER_ERROR.getMessage())
                        .build()
                , HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
