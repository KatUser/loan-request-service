package ru.loans.loan_request_service.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.loans.loan_request_service.model.LoanRequest;
import ru.loans.loan_request_service.model.LoanRequestStatus;
import ru.loans.loan_request_service.repository.LoanRequestRepository;

@Service
@AllArgsConstructor
public class LoanRequestService {

    @Autowired
    private final LoanRequestRepository loanRequestRepository;

    public boolean save(LoanRequest loanRequest) {
        if (checkLoanRequestTermsDto(loanRequest)) {
            loanRequest.setStatus(LoanRequestStatus.NEW);
            int res = loanRequestRepository.saveLoanRequest(loanRequest);
            return res > 0;
        }
        return false;
    }


    private boolean checkLoanRequestTermsDto(LoanRequest loanRequest) {
        return loanRequest.getTerms().getAmount() > 0
                && loanRequest.getTerms().getIsRepeater() != null;
    }
}
