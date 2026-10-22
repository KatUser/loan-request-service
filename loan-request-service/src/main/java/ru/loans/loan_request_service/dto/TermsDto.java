package ru.loans.loan_request_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record TermsDto(
        @Positive(message = "Amount must be over zero")
        Long amount,
        @NotNull(message = "Repeater cannot be null")
        Boolean isRepeater
) {
}
