package com.gv.dpal.account.repository;

import com.gv.dpal.account.model.IdempotencyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdempotencyRecordRepository extends JpaRepository<IdempotencyRecord,Long> {

    Optional<IdempotencyRecord> findIdempotencyRecordByIdempotencyKey(String idempotencyKey);
}
