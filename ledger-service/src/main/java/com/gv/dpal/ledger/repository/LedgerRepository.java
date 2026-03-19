package com.gv.dpal.ledger.repository;

import com.gv.dpal.ledger.model.TopUpSaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerRepository extends JpaRepository<TopUpSaga, Long> {
}
