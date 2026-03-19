package com.gv.dpal.account.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.dpal.account.api.ApiResponse;
import com.gv.dpal.account.dto.account.CreateAccountResponse;
import com.gv.dpal.account.exception.IdempotencyRecordNotFound;
import com.gv.dpal.account.model.IdempotencyRecord;
import com.gv.dpal.account.repository.IdempotencyRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class IdempotencyService {

    private final ObjectMapper mapper;

    private final IdempotencyRecordRepository idempotencyRecordRepository;

    public ApiResponse<CreateAccountResponse> findIdempotencyResponse(String idempotencyKey) throws IdempotencyRecordNotFound {

        IdempotencyRecord record = idempotencyRecordRepository.findIdempotencyRecordByIdempotencyKey(idempotencyKey)
                .orElseThrow(() -> new IdempotencyRecordNotFound("Idempotency record not found"));

        try {
            JavaType type = mapper.getTypeFactory()
                    .constructParametricType(ApiResponse.class, CreateAccountResponse.class);

            return mapper.readValue(String.valueOf(record.getResponseBody()), type);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to deserialize stored response", e);
        }
    }

    public void saveIdempotencyResponse(String idempotencyKey, int httpStatus, ApiResponse<CreateAccountResponse> response){
        try {
            final String responseJson = mapper.writeValueAsString(response);

            IdempotencyRecord record = IdempotencyRecord.builder()
                    .idempotencyKey(idempotencyKey)
                    .httpStatus(httpStatus)
                    .responseBody(responseJson)
                    .build();

            idempotencyRecordRepository.save(record);

        } catch (JsonProcessingException e) {
            log.info(e.getMessage());
            throw new IllegalStateException("Failed to serialize idempotency response", e);
        }
    }
}
