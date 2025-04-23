package com.calculator.service;

import com.calculator.exception.CalculatorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    @DisplayName("덧셈 연산 테스트")
    void testAdd() {
        // Given
        double num1 = 10;
        double num2 = 5;

        // When
        double result = calculatorService.add(num1, num2);

        // Then
        assertEquals(15, result, "10 + 5는 15여야 합니다");
    }

    @Test
    @DisplayName("뺄셈 연산은 아직 구현되지 않았으므로 예외를 발생시켜야 함")
    void testSubtractThrowsException() {
        // Given
        double num1 = 10;
        double num2 = 5;

        // When & Then
        UnsupportedOperationException thrown = assertThrows(
                UnsupportedOperationException.class,
                () -> calculatorService.subtract(num1, num2),
                "뺄셈 기능은 아직 구현되지 않아 예외가 발생해야 합니다"
        );

        assertTrue(thrown.getMessage().contains("빼기 기능은 아직 구현되지 않았습니다"));
    }

    @Test
    @DisplayName("곱셈 연산 테스트")
    void testMultiply() {
        // Given
        double num1 = 10;
        double num2 = 5;

        // When & Then

        // When
        double result = calculatorService.multiply(num1, num2);

        // Then
        assertEquals(50, result, "10 * 5는 50여야 합니다");
    }

    @Test
    @DisplayName("나눗셈 연산 시 0으로 나누면 예외가 발생해야 함")
    void testDivideByZeroThrowsException() {
        // Given
        double num1 = 10;
        double num2 = 0;

        // When & Then
        CalculatorException thrown = assertThrows(
                CalculatorException.class,
                () -> calculatorService.divide(num1, num2),
                "0으로 나누려고 할 때 예외가 발생해야 합니다"
        );

        assertTrue(thrown.getMessage().contains("0으로 나눌 수 없습니다"));
    }

    @Test
    @DisplayName("계산 메서드는 연산자에 따라 알맞은 메서드를 호출해야 함")
    void testCalculate() {
        // Given
        double num1 = 10;
        double num2 = 5;

        // When & Then
        assertEquals(15, calculatorService.calculate(num1, num2, "add"),
                "add 연산은 덧셈 결과를 반환해야 합니다");

        // 다른 연산은 아직 구현되지 않았으므로 예외가 발생해야 함
        assertThrows(UnsupportedOperationException.class,
                () -> calculatorService.calculate(num1, num2, "subtract"));
        assertThrows(UnsupportedOperationException.class,
                () -> calculatorService.calculate(num1, num2, "multiply"));
        assertThrows(UnsupportedOperationException.class,
                () -> calculatorService.calculate(num1, num2, "divide"));

        // 지원하지 않는 연산일 경우 예외가 발생해야 함
        CalculatorException thrown = assertThrows(CalculatorException.class,
                () -> calculatorService.calculate(num1, num2, "unknown"));
        assertTrue(thrown.getMessage().contains("지원하지 않는 연산입니다"));
    }
}