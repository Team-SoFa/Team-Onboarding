/**
 * 계산기 핵심 로직을 담당하는 유틸리티 함수들
 */

/**
 * 두 수를 더하는 함수
 */
export const add = (a: number, b: number): number => {
    return a + b;
  };
  
  /**
   * 첫 번째 수에서 두 번째 수를 빼는 함수
   * 나중에 Issue #1에서 구현할 예정
   */
  export const subtract = (a: number, b: number): number => {
    // TODO: Issue #1 - 뺄셈 기능 구현
    throw new Error('뺄셈 기능이 아직 구현되지 않았습니다');
  };
  
  /**
   * 두 수를 곱하는 함수
   * 나중에 Issue #2에서 구현할 예정
   */
  export const multiply = (a: number, b: number): number => {
    // TODO: Issue #2 - 곱셈 기능 구현
    throw new Error('곱셈 기능이 아직 구현되지 않았습니다');
  };
  
  /**
   * 첫 번째 수를 두 번째 수로 나누는 함수
   * 나중에 Issue #3에서 구현할 예정
   */
  export const divide = (a: number, b: number): number => {
    // TODO: Issue #3 - 나눗셈 기능 구현
    throw new Error('나눗셈 기능이 아직 구현되지 않았습니다');
  };
  
  /**
   * 입력값이 유효한 숫자인지 확인
   */
  export const isValidNumber = (value: string): boolean => {
    return !isNaN(parseFloat(value)) && isFinite(Number(value));
  };
  
  /**
   * 계산 수행 함수
   */
  export const performCalculation = (
    firstOperand: number,
    secondOperand: number,
    operator: string
  ): number => {
    switch (operator) {
      case '+':
        return add(firstOperand, secondOperand);
      case '-':
        return subtract(firstOperand, secondOperand);
      case '*':
        return multiply(firstOperand, secondOperand);
      case '/':
        return divide(firstOperand, secondOperand);
      default:
        return secondOperand;
    }
  };