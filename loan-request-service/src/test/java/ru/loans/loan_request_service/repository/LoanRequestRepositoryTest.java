package ru.loans.loan_request_service.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import ru.loans.loan_request_service.model.LoanRequest;
import ru.loans.loan_request_service.model.LoanRequestStatus;
import ru.loans.loan_request_service.model.Terms;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ImportTestcontainers
class LoanRequestRepositoryTest {

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @AfterEach
    void clearDataBase() {
        loanRequestRepository.clearDataBase();
    }

    @Test
    void whenSaveLoanRequest() {
        var terms = new Terms(10000L, false);
        var loanRequest = new LoanRequest(null, LoanRequestStatus.NEW, terms);
        assertEquals(1, loanRequestRepository.saveLoanRequest(loanRequest));
    }

}