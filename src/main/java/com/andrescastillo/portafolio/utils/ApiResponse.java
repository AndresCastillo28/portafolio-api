package com.andrescastillo.portafolio.utils;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private T data;
    private ApiError error;
    private int statusCode;

    public static <T> ApiResponse<T> success(int statusCode, T data) {
        return new ApiResponse<>(true, data, null, statusCode);
    }

    public static <T> ApiResponse<T> error(int errorCode, String errorMessage) {
        return new ApiResponse<>(false, null, new ApiError(errorCode, errorMessage), errorCode);
    }

    @Data
    @AllArgsConstructor
    public static class ApiError {
        private int code;
        private String message;
    }
}
