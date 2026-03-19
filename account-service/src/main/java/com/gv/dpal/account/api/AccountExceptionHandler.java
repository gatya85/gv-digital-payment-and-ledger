package com.gv.dpal.account.api;

import com.gv.dpal.account.exception.AccountDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AccountExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex){
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(ApiResponseStatus.ERROR)
                .message(ex.getMessage())
                .correlationId(MDC.get("correlationId"))
                .build();
        log.debug(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(AccountDoesNotExistException.class)
    public ResponseEntity<ApiResponse<Void>> handleAccountNotFound(AccountDoesNotExistException ex) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(ApiResponseStatus.ERROR)
                .message(ex.getMessage())
                .data(null)
                .correlationId(MDC.get("correlationId"))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
