package ru.loans.loan_request_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.loans.loan_request_service.dto.LoanRequestDto;
import ru.loans.loan_request_service.mapper.LoanRequestMapper;
import ru.loans.loan_request_service.service.LoanRequestService;

@Validated
@Slf4j
@Service
@RestController
@RequestMapping("/api/loanrequest")
@RequiredArgsConstructor
public class LoanRequestController {

    private final LoanRequestService loanRequestService;

    private final LoanRequestMapper loanRequestMapper;

    @PostMapping(
            value = "/register",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LoanRequestDto> saveLoanRequestDto(@Valid @RequestBody LoanRequestDto loanRequestDto) {
        var loanRequest = loanRequestMapper.convertToLoanRequest(loanRequestDto);
        var booleanSaved = loanRequestService.save(loanRequest);
        if (booleanSaved) {
            log.info("Saving loan request");
            return ResponseEntity.status(HttpStatus.CREATED).body(loanRequestDto);
        }
        throw new RuntimeException();

    }
}
