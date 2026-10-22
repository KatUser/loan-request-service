package ru.loans.loan_request_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table("loan_request")
public class LoanRequest {

    @EqualsAndHashCode.Include
    @Id
    private Long id;

    private LoanRequestStatus status;

    private Terms terms;
}
