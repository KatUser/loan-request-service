package ru.loans.loan_request_service.dto;

import lombok.Builder;

@Builder
public record TermsDto(
        Long amount,
        Boolean isRepeater
) {
}
