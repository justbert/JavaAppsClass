/*
File name: CalculatorModel.java
Author: Justin Bertrand, 040 592 786
Course: CST8221 – JAP, Lab Section: 
Assignment: Assignment 1, Part 2
Date: October 30th, 2015
Professor: Svillen Ranev
Purpose: This class builds the GUI of a Calculator
Class list: CalculatorView, Controller, CalculatorModel
*/

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Class which builds the GUI of a Calculator
 * @author Justin Bertrand
 * @version 1.0
 * @see java.lang.Float
 * @see java.lang.Double
 * @see java.lang.Integer
 * @see java.lang.Long
 * @see java.lang.String
 * @see java.lang.ArithmeticException
 * @since 1.8_60
 */
public class CalculatorModel {
    
    /**
     * String for when an error occurs.
     */
    public static final String ERROR_STRING = "--";
    
    /**
     * The first operand in the equation
     */
    private String operand1;
    
    /**
     * The second operand of the equation.
     */
    private String operand2;
    
    /**
     * The operator of the equation.
     */
    private String operator;
    
    /**
     * The mode of the calculations. Either floating point or integral.
     */
    private String mode;
    
    /**
     * The precision of the floating point result.
     */
    private String precision;
    
    /**
     * The result of an integer operation.
     */
    private Long intResult;
    
    /**
     * The result of the floating point operation.
     */
    private Double floatResult;
    
    /**
     * Boolean indicating if an error has occured.
     */
    private boolean errorState;

    /**
     * Default constructor initializing variables.
     */
    public CalculatorModel() {
        errorState = false;
        operand1 = null;
        operand2 = null;
        operator = null;
        mode = "float";
        precision = "doublePrecision";
    }
    
    /**
     * Setter for the first operand.
     * @param op1 The first operand in the equation.
     */
    public void setOperand1(String op1) {
        operand1 = op1;
    }
    
    /**
     * Setter for the second operand.
     * @param op2 The second operand in the equation.
     */
    public void setOperand2(String op2) {
        operand2 = op2;
    }
    
    /**
     * The setter for the operator.
     * @param op The operator of the equation.
     */
    public void setOperator(String op) {
        operator = op;
    }
    
    /**
     * Setter for the mode of the equation.
     * @param mode Can be integer or float.
     */
    public void setMode(String mode) {
        this.mode = mode;
    }
    
    /**
     * Setter for the precision of the result of the floating point operations. 
     * @param prec The precision.
     */
    public void setPrecision(String prec) {
        precision = prec;
    }
    
    /**
     * Setter for the error state.
     * @param es True for an error having occured, false for not.
     */
    public void setErrorState(boolean es) {
        errorState = es;
    }
    
    /**
     * Getter for the error state.
     * @return The error state.
     */
    public boolean getErrorState() {
        return errorState;
    }
    
    /**
     * Getter for the result. Returns a result based on the mode, either
     * integer or floating point. If floating point, will also format
     * based on the precision set.
     * @return A string formatted depending on the mode and the precision.
     * @see java.lang.String
     * @see java.lang.Float
     * @see java.lang.Integer
     * @see CalculatorView
     * @see java.text.DecimalFormat
     */
    public String getResult() {
        String result;
        
        if(mode.equalsIgnoreCase("integer")) {
            if(intResult > Integer.MAX_VALUE) {
                errorState = true;
                return ERROR_STRING;
            }
            
            result = Integer.toString(intResult.intValue());
        } else {
            if(floatResult > Float.MAX_VALUE) {
                errorState = true;
                return ERROR_STRING; 
            }
                NumberFormat formatter;
            switch(precision) {
                case "scientificPrecision":
                    formatter = new DecimalFormat("0.######E00");
                    break;
                case "doublePrecision":
                    formatter = new DecimalFormat("0.00");
                    break;
                case "singlePrecision":  
                    formatter = new DecimalFormat("0.0");
                default:
                    errorState = true;
                    return ERROR_STRING;
            }
            
            result = formatter.format(floatResult.floatValue());
        }
        
        if(result.length() > CalculatorView.DISPLAY_COLUMNS) {
            errorState = true;
            return ERROR_STRING;   
        } else {
            return result;
        }
    }
    
    /**
     * Method which performs the calculation of the equation formed with the 
     * first operand, the operator, and the second operand.
     * @see java.lang.String
     * @see java.lang.Float
     * @see java.lang.Double
     * @see java.lang.Integer
     * @see java.lang.Long
     * @see java.lang.ArithmeticException
     */
    public void performCalculations() {
        
        if(mode.equalsIgnoreCase("integer")) {
            switch(operator) {
                case "+":
                    intResult = Long.parseLong(operand1) + Long.parseLong(operand2);
                    break;
                case "-":
                    intResult = Long.parseLong(operand1) - Long.parseLong(operand2);
                    break;
                case "/":
                    if(Long.parseLong(operand2) == 0.0) {
                        errorState = true;
                    } else {
                        intResult = Long.parseLong(operand1) / Long.parseLong(operand2);
                    }
                    break;
                case "*":
                    intResult = Long.parseLong(operand1) * Long.parseLong(operand2);
            }
        } else {
            switch(operator) {
                case "+":
                    floatResult = Double.parseDouble(operand1) + Double.parseDouble(operand2);
                    break;
                case "-":
                    floatResult = Double.parseDouble(operand1) - Double.parseDouble(operand2);
                    break;
                case "/":
                    try {
                        floatResult = Double.parseDouble(operand1) / Double.parseDouble(operand2);
                    } catch (ArithmeticException e) {
                        errorState = true;
                    }
                case "*":
                    floatResult = Double.parseDouble(operand1) * Double.parseDouble(operand2);
            }
        }       
    }
}
