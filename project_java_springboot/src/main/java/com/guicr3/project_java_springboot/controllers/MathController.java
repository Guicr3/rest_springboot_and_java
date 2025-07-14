package com.guicr3.project_java_springboot.controllers;

import com.guicr3.project_java_springboot.exception.UnsupportedMathOperationException;
import com.guicr3.project_java_springboot.mathFunctions.FunctionsMath;
import com.guicr3.project_java_springboot.mathValidations.MathValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/math")
public class MathController {

    @Autowired
    private MathValidations validations;
    @Autowired
    private FunctionsMath functions;

    //http://localhost:8080/math/sum/6/3
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ){
        if(!validations.isNumeric(numberOne) || !validations.isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value!");
        return functions.sum(validations.convertToDouble(numberOne), validations.convertToDouble(numberTwo));
    }

    //http://localhost:8080/math/subtraction/6/3
    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ){
        if(!validations.isNumeric(numberOne) || !validations.isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value!");
        return functions.subtraction(validations.convertToDouble(numberOne), validations.convertToDouble(numberTwo));
    }

    //http://localhost:8080/math/multiplication/6/3
    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ){
        if(!validations.isNumeric(numberOne) || !validations.isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value!");
        return functions.multiplication(validations.convertToDouble(numberOne), validations.convertToDouble(numberTwo));
    }

    //http://localhost:8080/math/division/6/3
    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ){
        if(!validations.isNumeric(numberOne) || !validations.isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value!");
        return functions.division(validations.convertToDouble(numberOne), validations.convertToDouble(numberTwo));
    }

    //http://localhost:8080/math/mean/6/3
    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ){
        if(!validations.isNumeric(numberOne) || !validations.isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value!");
        return functions.mean(validations.convertToDouble(numberOne), validations.convertToDouble(numberTwo));
    }

    //http://localhost:8080/math/squareroot/81
    @RequestMapping("/squareroot/{numberOne}")
    public Double squareRoot(
            @PathVariable("numberOne") String numberOne
    ){
        if(!validations.isNumeric(numberOne)) throw new UnsupportedMathOperationException("Please set a numeric value!");
        return functions.squareroot(validations.convertToDouble(numberOne));
    }
}
