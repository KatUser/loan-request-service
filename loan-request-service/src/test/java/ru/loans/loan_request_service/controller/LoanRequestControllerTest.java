package ru.loans.loan_request_service.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.loans.loan_request_service.dto.LoanRequestDto;
import ru.loans.loan_request_service.dto.TermsDto;
import ru.loans.loan_request_service.mapper.LoanRequestMapper;
import ru.loans.loan_request_service.model.LoanRequest;
import ru.loans.loan_request_service.model.Terms;
import ru.loans.loan_request_service.service.LoanRequestService;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.loans.loan_request_service.model.LoanRequestStatus.NEW;


@SpringBootTest
class LoanRequestControllerTest {

    @Mock
    private LoanRequestService loanRequestService = mock(LoanRequestService.class);

    @Mock
    private LoanRequestMapper loanRequestMapper = mock(LoanRequestMapper.class);

    private LoanRequestController loanRequestController;

    @BeforeEach
    void setup() {
        loanRequestController = new LoanRequestController(loanRequestService, loanRequestMapper);
    }

    @Test
    void whenSaveLoanRequestDto() {
        var termsDto = new TermsDto(1L, true);
        var loanRequestDto = new LoanRequestDto(termsDto);
        var terms = new Terms(1L, true);
        var loanRequest = new LoanRequest(1L, NEW, terms);
        when(loanRequestMapper.convertToLoanRequest(loanRequestDto)).thenReturn(loanRequest);
        when(loanRequestService.save(loanRequest)).thenReturn(true);
        assertEquals(HttpStatus.CREATED, loanRequestController.saveLoanRequestDto(loanRequestDto).getStatusCode());

    }

    @Test
    void whenSaveLoanRequestDtoAndCheckResponseBody() {
        var termsDto = new TermsDto(1L, true);
        var loanRequestDto = new LoanRequestDto(termsDto);
        var terms = new Terms(1L, true);
        var loanRequest = new LoanRequest(1L, NEW, terms);
        when(loanRequestMapper.convertToLoanRequest(loanRequestDto)).thenReturn(loanRequest);
        when(loanRequestService.save(loanRequest)).thenReturn(true);
        ResponseEntity<LoanRequestDto> responseEntity = loanRequestController.saveLoanRequestDto(loanRequestDto);
        assertNotNull(responseEntity);
        assertEquals(loanRequestDto, responseEntity.getBody());
    }

    @Test
    void whenFailToSaveLoanRequestDto() {
        var termsDto = new TermsDto(1L, true);
        var loanRequestDto = new LoanRequestDto(termsDto);
        var terms = new Terms(1L, true);
        var loanRequest = new LoanRequest(1L, NEW, terms);
        when(loanRequestMapper.convertToLoanRequest(any(LoanRequestDto.class))).thenReturn(loanRequest);
        when(loanRequestService.save(any(LoanRequest.class))).thenReturn(false);
        assertThrows(RuntimeException.class,
                () -> loanRequestController.saveLoanRequestDto(loanRequestDto)
        );
    }

}