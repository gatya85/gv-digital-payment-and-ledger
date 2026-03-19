package com.gv.dpal.ledger.api;

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
