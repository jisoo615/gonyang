package com.gonyang.diary.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
    private String message;
    private int status;

    public ErrorDto(int status, String message){
        this.status = status;
        this.message = message;
    }
}
