package ru.loans.loan_request_service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerLoggingAspect {
    @Pointcut("execution (* ru.loans.loan_request_service.controller.LoanRequestController..*(..))")
    public void saveLoanRequestDto() {}

    @Around("saveLoanRequestDto()")
    public Object logAroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        String saveLoanRequestDto = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        log.info("Вызов метода: {} с аргументами: {}", saveLoanRequestDto, args);

        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;
            log.info("Метод {} успешно выполнен за {} мс. Результат: {}", saveLoanRequestDto, executionTime, result);
            return result;
        } catch (Throwable e) {
            log.error("Ошибка при выполнении метода : {}", saveLoanRequestDto, e);
            throw e;
        }
    }

}
