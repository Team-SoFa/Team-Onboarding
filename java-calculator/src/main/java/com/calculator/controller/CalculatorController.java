package com.calculator.controller;

import com.calculator.exception.CalculatorException;
import com.calculator.model.CalculationRequest;
import com.calculator.model.CalculationResponse;
import com.calculator.service.CalculatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculator")
@CrossOrigin(origins = "*")
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
    @Operation(summary = "계산 수행", description = "두 숫자와 연산자를 받아 계산을 수행합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "계산 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ResponseEntity<CalculationResponse> calculate(
            @Valid @RequestBody CalculationRequest request) {
        try {
            double result = calculatorService.calculate(
                    request.getNum1(),
                    request.getNum2(),
                    request.getOperation()
            );

            CalculationResponse response = new CalculationResponse(
                    result,
                    request.getOperation(),
                    request.getNum1(),
                    request.getNum2(),
                    "계산 성공"
            );

            return ResponseEntity.ok(response);
        } catch (UnsupportedOperationException e) {
            // 아직 구현되지 않은 기능에 대한 응답
            CalculationResponse response = new CalculationResponse(
                    null,
                    request.getOperation(),
                    request.getNum1(),
                    request.getNum2(),
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        } catch (CalculatorException e) {
            // 계산기 관련 예외에 대한 응답
            CalculationResponse response = new CalculationResponse(
                    null,
                    request.getOperation(),
                    request.getNum1(),
                    request.getNum2(),
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            // 기타 예외에 대한 응답
            CalculationResponse response = new CalculationResponse(
                    null,
                    request.getOperation(),
                    request.getNum1(),
                    request.getNum2(),
                    "오류 발생: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/add")
    @Operation(summary = "덧셈 수행", description = "두 숫자를 더합니다.")
    public ResponseEntity<CalculationResponse> add(
            @RequestParam("num1") double num1,
            @RequestParam("num2") double num2) {

        double result = calculatorService.add(num1, num2);

        CalculationResponse response = new CalculationResponse(
                result,
                "add",
                num1,
                num2,
                "덧셈 성공"
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/subtract")
    @Operation(summary = "뺼셈 수행", description = "두 숫자를 뻅니다.")
    public ResponseEntity<CalculationResponse> subtract(
            @RequestParam("num1") double num1,
            @RequestParam("num2") double num2) {

        double result = calculatorService.subtract(num1, num2);

        CalculationResponse response = new CalculationResponse(
                result,
                "subtract",
                num1,
                num2,
                "뺼셈 성공"
        );

        return ResponseEntity.ok(response);
    }

    // TODO: 뺄셈, 곱셈, 나눗셈 API 엔드포인트 추가 예정
}