package com.gonyang.diary.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomErrorCode {
    //400 BAD_REQUEST 잘못된 요청
    INVALID_PARAMETER(400, "파라미터 값을 확인해주세요."),

    //404 NOT_FOUND 잘못된 리소스 접근
    PRODUCT_NOT_FOUND(404, "존재하지 않는 상품 ID 싱품입니다."),
    CATEGORY_NOT_FOUND(404, "존재하지 않는 카테고리 ID 입니다"),
    SAVED_PRODUCT_NOT_FOUND(404, "저장하지 않은 상품입니다."),

    //409 CONFLICT 중복된 리소스
    ALREADY_SAVED_PRODUCT(409, "이미 저장한 상품입니다."),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다. 서버 팀에 연락주세요!");

    private final int status;
    private final String message;
}
