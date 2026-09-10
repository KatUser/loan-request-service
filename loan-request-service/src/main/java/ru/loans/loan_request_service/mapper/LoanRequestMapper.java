package ru.loans.loan_request_service.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import ru.loans.loan_request_service.dto.LoanRequestDto;
import ru.loans.loan_request_service.model.LoanRequest;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanRequestMapper {

    @Mapping(source = "termsDto.isRepeater", target = "terms.isRepeater")
    @Mapping(source = "termsDto.amount", target = "terms.amount")
    LoanRequest convertToLoanRequest(LoanRequestDto loanRequestDto);

    @InheritInverseConfiguration
    LoanRequestDto converToLoanRequestDto(LoanRequest loanRequest);

}
