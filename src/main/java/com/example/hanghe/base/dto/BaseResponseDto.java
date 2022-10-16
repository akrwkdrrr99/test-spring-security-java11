package com.example.hanghe.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class BaseResponseDto<T> {
    private boolean success;
    private T data;
    private Error error;

    public static <T> BaseResponseDto<T> success(T data) {
        return new BaseResponseDto<>(true, data, null);
    }

    public static <T> BaseResponseDto<T> fail(String code, String message) {
        return new BaseResponseDto<>(false, null, new Error(code, message));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class Error {
        private String code;
        private String message;
    }

}
