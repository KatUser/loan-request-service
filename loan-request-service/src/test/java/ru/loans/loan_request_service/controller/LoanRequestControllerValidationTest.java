package ru.loans.loan_request_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.TestConfiguration;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.loans.loan_request_service.dto.LoanRequestDto;
import ru.loans.loan_request_service.dto.TermsDto;
import ru.loans.loan_request_service.mapper.LoanRequestMapper;
import ru.loans.loan_request_service.model.LoanRequest;
import ru.loans.loan_request_service.model.LoanRequestStatus;
import ru.loans.loan_request_service.model.Terms;
import ru.loans.loan_request_service.repository.LoanRequestRepository;
import ru.loans.loan_request_service.service.LoanRequestService;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(LoanRequestController.class)
public class LoanRequestControllerValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private LoanRequestService loanRequestService;

    @MockitoBean
    private LoanRequestRepository loanRequestRepository;

    @MockitoBean
    private LoanRequestMapper loanRequestMapper;

    @TestConfiguration
    static class ObjectMapperTestConfig {
        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("validLoanRequestDto")
    void whenDataIsValid(LoanRequestDto validDto) throws Exception {

        when(loanRequestMapper.convertToLoanRequest(validDto))
                .thenReturn(new LoanRequest(1L, LoanRequestStatus.NEW, new Terms(1L, true)));

        when(loanRequestService.save(any())).thenReturn(true);

        mockMvc.perform(post("/api/loanrequest/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validDto)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    static Stream<Arguments> validLoanRequestDto() {
        return Stream.of(
                Arguments.of(new LoanRequestDto(new TermsDto(1000L, true))),
                Arguments.of(new LoanRequestDto(new TermsDto(1000000L, false))));

    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidLoanRequestDto")
    void whenDataIsInValid(LoanRequestDto invalidDto) throws Exception {

        mockMvc.perform(post("/api/loanrequest/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest());
    }

    static Stream<Arguments> invalidLoanRequestDto() {
        return Stream.of(
                Arguments.of(new LoanRequestDto(new TermsDto(1L, null))),
                Arguments.of(new LoanRequestDto(new TermsDto(-1L, true))),
                Arguments.of(new LoanRequestDto(new TermsDto(null, false))));

    }
}
