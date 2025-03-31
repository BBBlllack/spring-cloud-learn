package com.bbblllack.model.common;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private int code;
    private T data;
    private String message;

    public ApiResponse() {
    }

    public ApiResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200,data, "ok");
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(200,data, message);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(200,null, message);
    }
}
