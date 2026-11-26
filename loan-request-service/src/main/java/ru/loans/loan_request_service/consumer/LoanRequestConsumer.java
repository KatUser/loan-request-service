package ru.loans.loan_request_service.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.loans.loan_request_service.configuration.rabbit.RabbitConfig;
import ru.loans.loan_request_service.dto.LoanRequestDto;
import ru.loans.loan_request_service.mapper.LoanRequestMapper;
import ru.loans.loan_request_service.service.LoanRequestService;

@Slf4j
@Component
@AllArgsConstructor
public class LoanRequestConsumer {

    private final LoanRequestService loanRequestService;

    private final LoanRequestMapper loanRequestMapper;
//TODO
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void processLoanRequest(LoanRequestDto loanRequestDto) {
        try {
            log.info("Received order: " + loanRequestDto);

            var loanRequest = loanRequestMapper.convertToLoanRequest(loanRequestDto);

            var booleanSaved = loanRequestService.save(loanRequest);

            if (booleanSaved) {
                log.info("Saving loan request");
            }
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException("Received invalid message", e);
        }
    }
}
