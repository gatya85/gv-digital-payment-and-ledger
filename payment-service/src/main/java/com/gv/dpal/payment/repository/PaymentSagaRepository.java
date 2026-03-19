package com.gv.dpal.payment.repository;

import com.gv.dpal.payment.model.saga.PaymentSaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSagaRepository extends JpaRepository<PaymentSaga, Long> {
}
