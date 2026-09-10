package ru.loans.loan_request_service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.loans.loan_request_service.dto.LoanRequestDto;
import ru.loans.loan_request_service.dto.TermsDto;
import ru.loans.loan_request_service.model.LoanRequest;
import ru.loans.loan_request_service.model.Terms;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoanRequestMapperTest {

    @Autowired
    private LoanRequestMapper loanRequestMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenMappedToLoanRequest() {
        var terms = Terms.builder().amount(10000L).isRepeater(false).build();
        var loanRequest = LoanRequest.builder().terms(terms);
        var termsDto = TermsDto.builder().amount(10000L).isRepeater(false).build();
        var loanRequestDto = LoanRequestDto.builder().termsDto(termsDto).build();
        assertThat(loanRequestMapper.convertToLoanRequest(loanRequestDto))
                .usingRecursiveComparison().isEqualTo(loanRequest);
    }

    @Test
    void whenMappedToLoanRequestDto() {
        var terms = Terms.builder().amount(10000L).isRepeater(false).build();
        var loanRequest = LoanRequest.builder().terms(terms);
        var termsDto = TermsDto.builder().amount(10000L).isRepeater(false).build();
        var loanRequestDto = LoanRequestDto.builder().termsDto(termsDto).build();
        assertThat(loanRequestMapper.converToLoanRequestDto(loanRequest.build()))
                .usingRecursiveComparison().isEqualTo(loanRequestDto);
    }

}