package ru.loans.loan_request_service.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {

    @EqualsAndHashCode.Include
    private Long id;

    private LoanRequestStatus status;

    private Terms terms;
}
