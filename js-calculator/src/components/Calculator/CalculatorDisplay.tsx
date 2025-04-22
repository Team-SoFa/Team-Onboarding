// src/components/Calculator/CalculatorDisplay.tsx
import React from 'react';

interface CalculatorDisplayProps {
  value: string;
}

const CalculatorDisplay: React.FC<CalculatorDisplayProps> = ({ value }) => {
  return (
    <div className="bg-gray-100 border border-gray-200 rounded-md p-2 text-right text-2xl h-12 flex items-center justify-end overflow-hidden">
      {value}
    </div>
  );
};

export default CalculatorDisplay;