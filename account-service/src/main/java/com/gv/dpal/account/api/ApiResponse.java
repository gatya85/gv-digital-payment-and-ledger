package com.gv.dpal.account.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private ApiResponseStatus status;
    private String message;
    private T data;

    private String correlationId;
}
