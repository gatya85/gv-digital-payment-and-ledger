package com.gv.dpal.fraud.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {

    private ApiResponseStatus status;
    private String message;
    private T data;

    private String correlationId;
}
