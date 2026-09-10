package ru.loans.loan_request_service.dto;

import lombok.Builder;

@Builder
public record LoanRequestDto(
        TermsDto termsDto
) {
}
