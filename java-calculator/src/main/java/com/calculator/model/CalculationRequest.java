package com.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculationRequest {
    @NotNull(message = "첫 번째 숫자는 필수 입력값입니다")
    private Double num1;

    @NotNull(message = "두 번째 숫자는 필수 입력값입니다")
    private Double num2;

    @NotNull(message = "연산자는 필수 입력값입니다")
    private String operation;
}