package ru.loans.loan_request_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.loans.loan_request_service.configuration.rabbit.RabbitConfig;
import ru.loans.loan_request_service.dto.LoanRequestDto;

@Validated
@Slf4j
@Service
@RestController
@RequestMapping("/api/loanrequest")
@RequiredArgsConstructor
public class LoanRequestController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping(
            value = "/register",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> saveLoanRequestDto(@Valid @RequestBody LoanRequestDto loanRequestDto) {

        rabbitTemplate.convertSendAndReceive(
                RabbitConfig.EXCHANGE_NAME,
                RabbitConfig.ROUTING_KEY,
                loanRequestDto
        );

        return ResponseEntity.accepted().body("LoanRequest has been received for processing");

    }
}
