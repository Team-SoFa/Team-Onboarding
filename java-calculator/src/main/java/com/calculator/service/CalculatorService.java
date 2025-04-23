package com.calculator.service;

import com.calculator.exception.CalculatorException;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    /**
     * 두 수를 더합니다.
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * 뺄셈 기능 (미구현)
     * TODO: 이슈로 할당 예정
     */
    public double subtract(double a, double b) {
        return a-b;
    }

    /**
     * 곱셈 기능 (미구현)
     * TODO: 이슈로 할당 예정
     */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * 나누기 기능 (미구현)
     * TODO: 이슈로 할당 예정
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new CalculatorException("0으로 나눌 수 없습니다.");
        }
        // 미구현 상태로 예외 발생
        throw new UnsupportedOperationException("나누기 기능은 아직 구현되지 않았습니다.");
    }

    /**
     * 연산 수행
     */
    public double calculate(double a, double b, String operation) {
        switch (operation.toLowerCase()) {
            case "add":
                return add(a, b);
            case "subtract":
                return subtract(a, b);
            case "multiply":
                return multiply(a, b);
            case "divide":
                return divide(a, b);
            default:
                throw new CalculatorException("지원하지 않는 연산입니다: " + operation);
        }
    }
}