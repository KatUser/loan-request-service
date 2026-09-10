package ru.loans.loan_request_service.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.loans.loan_request_service.model.LoanRequest;
import ru.loans.loan_request_service.repository.LoanRequestRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LoanRequestServiceTest {

    @Mock
    private LoanRequest loanRequest = mock(LoanRequest.class);

    @Mock
    private LoanRequestRepository loanRequestRepository = mock(LoanRequestRepository.class);

    @InjectMocks
    private LoanRequestService loanRequestService;

    @Test
    void whenSaveLoanRequestSuccessfully() {
        when(loanRequestRepository.saveLoanRequest(any(LoanRequest.class))).thenReturn(1);
        assertTrue(loanRequestService.save(loanRequest));
    }

    @Test
    void whenFailedToSaveLoanRequest() {
        when(loanRequestRepository.saveLoanRequest(any(LoanRequest.class))).thenReturn(0);
        assertFalse(loanRequestService.save(loanRequest));
    }

}