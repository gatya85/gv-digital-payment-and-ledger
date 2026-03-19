package com.gv.dpal.account.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_idempotency_record")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdempotencyRecord extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idempotency_key", nullable = false, length = 128)
    private String idempotencyKey;

    @Column(name = "http_status", nullable = false)
    private Integer httpStatus;

    @Column(name = "response_body", nullable = false, columnDefinition = "json")
    private String responseBody;

}
