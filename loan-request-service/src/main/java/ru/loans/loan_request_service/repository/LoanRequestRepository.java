package ru.loans.loan_request_service.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.loans.loan_request_service.model.LoanRequest;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Types;
import java.util.HashMap;


@Repository
@AllArgsConstructor
public class LoanRequestRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int saveLoanRequest(LoanRequest loanRequest) {
        String sql = "INSERT INTO loan_request (status, terms) VALUES (:status, :terms)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("status", loanRequest.getStatus(), Types.VARCHAR);
        params.addValue("terms", loanRequest.getTerms(), Types.VARCHAR);

        return namedParameterJdbcTemplate.update(sql, params);
    }

    public void clearDataBase() {
        String sql = String.format("DELETE FROM %s", "loan_request");

        namedParameterJdbcTemplate.update(sql, new HashMap<>());
    }
}
