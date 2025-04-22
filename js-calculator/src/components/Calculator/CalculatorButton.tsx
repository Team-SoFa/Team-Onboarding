// src/components/Calculator/CalculatorButton.tsx
import React from 'react';

interface CalculatorButtonProps {
  label: string;
  onClick: () => void;
  className?: string;
}

const CalculatorButton: React.FC<CalculatorButtonProps> = ({ 
  label, 
  onClick, 
  className = ""
}) => {
  return (
    <button 
      className={`p-3 text-lg rounded-md border border-gray-200 bg-gray-50 hover:bg-gray-100 active:bg-gray-200 transition-colors ${className}`}
      onClick={onClick}
    >
      {label}
    </button>
  );
};

export default CalculatorButton;