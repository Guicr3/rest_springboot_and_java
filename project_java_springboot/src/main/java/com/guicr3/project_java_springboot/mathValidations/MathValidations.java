package com.guicr3.project_java_springboot.mathValidations;

import com.guicr3.project_java_springboot.exception.UnsupportedMathOperationException;
import org.springframework.stereotype.Service;

@Service
public class MathValidations {
    public Double convertToDouble(String strNumber) {
        //if(strNumber == null || strNumber.isEmpty()) throw new UnsupportedMathOperationException("Please set a numeric value!");
        String number = strNumber.replace(",",".");
        return Double.parseDouble(number);
    }

    public boolean isNumeric(String strNumber){
        if(strNumber == null || strNumber.isEmpty()) throw new UnsupportedMathOperationException("Please set a numeric value!");
        String number = strNumber.replace(",",".");
        return (number.matches("[-+]?[0-9]*\\.?[0-9]+"));
    }
}
