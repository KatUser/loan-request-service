package ru.loans.loan_request_service.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import ru.loans.loan_request_service.dto.LoanRequestDto;
import ru.loans.loan_request_service.mapper.LoanRequestMapper;
import ru.loans.loan_request_service.model.LoanRequest;
import ru.loans.loan_request_service.service.LoanRequestService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class LoanRequestControllerTest {

    @Mock
    private LoanRequestService loanRequestService = mock(LoanRequestService.class);

    @Mock
    private LoanRequestMapper loanRequestMapper = mock(LoanRequestMapper.class);

    @Mock
    private LoanRequest loanRequest = mock(LoanRequest.class);

    @Mock
    private LoanRequestDto loanRequestDto = mock(LoanRequestDto.class);

    private LoanRequestController loanRequestController;

    @BeforeEach
    void setup() {
        loanRequestController = new LoanRequestController(loanRequestService, loanRequestMapper);
    }

    @Test
    void whenSaveLoanRequestDto() {
        when(loanRequestMapper.convertToLoanRequest(any(LoanRequestDto.class))).thenReturn(loanRequest);
        when(loanRequestService.save(any(LoanRequest.class))).thenReturn(true);
        assertEquals(HttpStatus.OK, loanRequestController.saveLoanRequestDto(loanRequestDto).getStatusCode());
    }

    @Test
    void whenFailToSaveLoanRequestDto() {
        when(loanRequestMapper.convertToLoanRequest(any(LoanRequestDto.class))).thenReturn(loanRequest);
        when(loanRequestService.save(any(LoanRequest.class))).thenReturn(false);
        assertThrows(RuntimeException.class,
                () -> {
                    loanRequestController.saveLoanRequestDto(loanRequestDto);
                }
        );
    }

}