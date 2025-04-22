package com.calculator.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.calculator.exception.CalculatorException;
import com.calculator.model.CalculationRequest;
import com.calculator.service.CalculatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CalculatorService calculatorService;

    @Test
    @DisplayName("POST /calculate 엔드포인트 테스트 - 성공 케이스")
    void testCalculateEndpoint() throws Exception {
        // Given
        CalculationRequest request = new CalculationRequest(10.0, 5.0, "add");
        when(calculatorService.calculate(10.0, 5.0, "add")).thenReturn(15.0);

        // When & Then
        mockMvc.perform(post("/api/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(15.0)))
                .andExpect(jsonPath("$.operation", is("add")))
                .andExpect(jsonPath("$.num1", is(10.0)))
                .andExpect(jsonPath("$.num2", is(5.0)))
                .andExpect(jsonPath("$.message", is("계산 성공")));
    }

    @Test
    @DisplayName("POST /calculate 엔드포인트 테스트 - 미구현 기능")
    void testCalculateEndpointWithUnimplementedOperation() throws Exception {
        // Given
        CalculationRequest request = new CalculationRequest(10.0, 5.0, "subtract");
        when(calculatorService.calculate(10.0, 5.0, "subtract"))
                .thenThrow(new UnsupportedOperationException("빼기 기능은 아직 구현되지 않았습니다."));

        // When & Then
        mockMvc.perform(post("/api/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotImplemented())
                .andExpect(jsonPath("$.result").doesNotExist())
                .andExpect(jsonPath("$.operation", is("subtract")))
                .andExpect(jsonPath("$.num1", is(10.0)))
                .andExpect(jsonPath("$.num2", is(5.0)))
                .andExpect(jsonPath("$.message", is("빼기 기능은 아직 구현되지 않았습니다.")));
    }

    @Test
    @DisplayName("POST /calculate 엔드포인트 테스트 - 잘못된 요청")
    void testCalculateEndpointWithInvalidRequest() throws Exception {
        // Given
        CalculationRequest request = new CalculationRequest(10.0, 0.0, "divide");
        when(calculatorService.calculate(10.0, 0.0, "divide"))
                .thenThrow(new CalculatorException("0으로 나눌 수 없습니다."));

        // When & Then
        mockMvc.perform(post("/api/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.result").doesNotExist())
                .andExpect(jsonPath("$.operation", is("divide")))
                .andExpect(jsonPath("$.num1", is(10.0)))
                .andExpect(jsonPath("$.num2", is(0.0)))
                .andExpect(jsonPath("$.message", is("0으로 나눌 수 없습니다.")));
    }

    @Test
    @DisplayName("GET /add 엔드포인트 테스트")
    void testAddEndpoint() throws Exception {
        // Given
        when(calculatorService.add(10.0, 5.0)).thenReturn(15.0);

        // When & Then
        mockMvc.perform(get("/api/calculator/add")
                        .param("num1", "10.0")
                        .param("num2", "5.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(15.0)))
                .andExpect(jsonPath("$.operation", is("add")))
                .andExpect(jsonPath("$.num1", is(10.0)))
                .andExpect(jsonPath("$.num2", is(5.0)))
                .andExpect(jsonPath("$.message", is("덧셈 성공")));
    }

    @Test
    @DisplayName("유효하지 않은 요청 데이터에 대한 검증")
    void testInvalidRequestData() throws Exception {
        // Given: 필수 필드가 빠진 요청
        CalculationRequest request = new CalculationRequest(null, 5.0, "add");

        // When & Then
        mockMvc.perform(post("/api/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        // Given: 유효하지 않은 연산자를 포함한 요청
        request = new CalculationRequest(10.0, 5.0, "invalid");
        when(calculatorService.calculate(anyDouble(), anyDouble(), anyString()))
                .thenThrow(new CalculatorException("지원하지 않는 연산입니다: invalid"));

        // When & Then
        mockMvc.perform(post("/api/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("지원하지 않는 연산입니다")));
    }
}