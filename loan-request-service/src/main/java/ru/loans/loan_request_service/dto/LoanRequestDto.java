package ru.loans.loan_request_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LoanRequestDto(
        @Valid
        @NotNull(message = "Terms must not be null")
        TermsDto termsDto
) {
}
