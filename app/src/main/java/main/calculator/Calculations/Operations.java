package main.calculator.Calculations;

public class Operations {
    private double result = 0;
    private StringNumber stringNumber = new StringNumber();
    private char lastOperation = '0';

    public double operation(char operation){
        if(lastOperation == '0') {
            lastOperation = operation;
            result += stringNumber.getDouble();
            if(operation == '-'){
                stringNumber.setMinus();
                lastOperation = '+';
            }
        }
        else {
            if(operation == '=') {
                doOperation(lastOperation);
                //clear();
            }
            else {
                doOperation(lastOperation);
                lastOperation = operation;
                if(operation == '-'){
                    stringNumber.setMinus();
                    lastOperation = '+';
                }
            }
        }
        return result;
    }

    public String appendNumber(String num){
        return stringNumber.append(num);
    }

    public void clear(){
        result = 0;
        stringNumber.clear();
        lastOperation = '0';
    }

    private void doOperation(char operation){
        switch (operation){
            case '+':
            case '-':
                addition();
                break;
            case '*':
                multiplication();
            case '/':
                division();
                break;
        }
    }

    private void addition(){
        if(stringNumber.isReady()){
            double toadd= stringNumber.getDouble();
            result = result + toadd;
        }
    }

    private void multiplication(){
        if(stringNumber.isReady()){
            if(result == 0)
                result = 1;
            result = result * stringNumber.getDouble();
        }
    }

    private void division(){
        if(stringNumber.isReady()){
            result = result / stringNumber.getDouble();
        }
    }
}
