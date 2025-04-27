// src/components/Calculator/Calculator.tsx
import { useState } from "react";
import CalculatorDisplay from "./CalculatorDisplay";
import CalculatorButton from "./CalculatorButton";
import { add, multiply } from "../../utils/calculator";

const Calculator: React.FC = () => {
  const [display, setDisplay] = useState<string>("0");
  const [firstOperand, setFirstOperand] = useState<number | null>(null);
  const [waitingForSecondOperand, setWaitingForSecondOperand] =
    useState<boolean>(false);
  const [operator, setOperator] = useState<null | "+" | "x">(null);

  const clearDisplay = (): void => {
    setDisplay("0");
    setFirstOperand(null);
    setWaitingForSecondOperand(false);
  };

  const inputDigit = (digit: string): void => {
    if (waitingForSecondOperand) {
      setDisplay(digit);
      setWaitingForSecondOperand(false);
    } else {
      setDisplay(display === "0" ? digit : display + digit);
    }
  };

  const inputDecimal = (): void => {
    if (waitingForSecondOperand) {
      setDisplay("0.");
      setWaitingForSecondOperand(false);
      return;
    }

    if (!display.includes(".")) {
      setDisplay(display + ".");
    }
  };

  // 덧셈 기능 구현 (utils 사용)
  const performAddition = (): void => {
    const inputValue = parseFloat(display);

    if (firstOperand === null) {
      setFirstOperand(inputValue);
    } else {
      const result = add(firstOperand, inputValue);
      setDisplay(String(result));
      setFirstOperand(result);
    }

    setWaitingForSecondOperand(true);
    setOperator("+");
  };

  // 곱셈 기능 구현 (utils 사용)
  const performMultiplication = (): void => {
    const inputValue = parseFloat(display);

    if (firstOperand === null) {
      setFirstOperand(inputValue);
    } else {
      const result = multiply(firstOperand, inputValue);
      setDisplay(String(result));
      setFirstOperand(result);
    }

    setWaitingForSecondOperand(true);
    setOperator("x");
  };

  const handleEquals = (): void => {
    if (firstOperand === null) return;

    const inputValue = parseFloat(display);
    let result;
    if (operator === "+") {
      result = add(firstOperand, inputValue);
    } else if (operator === "x") {
      result = multiply(firstOperand, inputValue);
    }

    setDisplay(String(result));
    setFirstOperand(null);
    setWaitingForSecondOperand(true);
  };

  return (
    <div className="w-72 border border-gray-300 rounded-lg p-4 shadow-md bg-white mx-auto">
      <CalculatorDisplay value={display} />
      <div className="grid grid-cols-4 gap-2 mt-4">
        <CalculatorButton
          label="C"
          onClick={clearDisplay}
          className="bg-red-500 text-white"
        />
        <div className="col-span-1"></div>
        <div className="col-span-1"></div>
        <div className="col-span-1"></div>

        <CalculatorButton label="7" onClick={() => inputDigit("7")} />
        <CalculatorButton label="8" onClick={() => inputDigit("8")} />
        <CalculatorButton label="9" onClick={() => inputDigit("9")} />

        <CalculatorButton
          label="x"
          onClick={performMultiplication}
          className="bg-sky-500 text-white"
        />

        <CalculatorButton label="4" onClick={() => inputDigit("4")} />
        <CalculatorButton label="5" onClick={() => inputDigit("5")} />
        <CalculatorButton label="6" onClick={() => inputDigit("6")} />

        <div className="col-span-1"></div>

        <CalculatorButton label="1" onClick={() => inputDigit("1")} />
        <CalculatorButton label="2" onClick={() => inputDigit("2")} />
        <CalculatorButton label="3" onClick={() => inputDigit("3")} />

        <CalculatorButton
          label="+"
          onClick={performAddition}
          className="bg-sky-500 text-white"
        />

        <CalculatorButton
          label="0"
          onClick={() => inputDigit("0")}
          className="col-span-1"
        />
        <CalculatorButton label="." onClick={inputDecimal} />
        <CalculatorButton
          label="="
          onClick={handleEquals}
          className="bg-green-500 text-white"
        />
        <div className="col-span-1"></div>
      </div>
    </div>
  );
};

export default Calculator;
