package ru.loans.loan_request_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class Terms {

    private Long amount;
    private Boolean isRepeater;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Terms terms = (Terms) o;
        return Objects.equals(amount, terms.amount) && Objects.equals(isRepeater, terms.isRepeater);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, isRepeater);
    }

    @Override
    public String toString() {
        return "{" +
                "amount=" + amount +
                ", isRepeater=" + isRepeater +
                '}';
    }
}
