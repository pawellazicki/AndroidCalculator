package main.calculator;

import android.os.Build;
import android.text.InputFilter;
import android.widget.TextView;

import main.calculator.Calculations.Operator;

public class SimpleCalculatorController {
    private boolean toClear = false;
    private Operator operator;
    private TextView textViewCalculations;
    private boolean removeNumberClick = false;
    private String operationsString = "+-*/";
    private boolean dimensionsSet = false;
    private int maxTextLen = 0;

    SimpleCalculatorController(TextView textView){
        operator = new Operator();
        textViewCalculations =textView;
    }

    void operate(char operation){
        if (!dimensionsSet)
            setTextDimensions();
        if(textViewCalculations.length() < maxTextLen || (operation == '=' && textViewCalculations.length() == maxTextLen)) {
            removeNumberClick = false;

            toClear = false;
            String result = operator.operation(operation);
            if (checkOperation(result, operation))
                appendText(String.valueOf(operation));  // display operation
            else if (!result.equals("")) {
                displayText(result);                    // display number
                if (operation != '=')
                    appendText(String.valueOf(operation));
            }
        }
    }

    void removeNumber(){
        if(removeNumberClick){
            clear();
            removeNumberClick = false;
            return;
        }
        removeNumberClick = true;
        displayRemovedNumber(operator.removeNumber());
    }

    void increaseNumber(int add) {
        if (!dimensionsSet)
            setTextDimensions();
        checkTextToClear();
        if(textViewCalculations.length() < maxTextLen) {
            removeNumberClick = false;

            if (!(operator.getStrNumber().equals("0") && add == 0)) {
                appendText(String.valueOf(add));
                operator.appendNumber(String.valueOf(add));
            }
        }
    }

    void addDot(String dot){
        if (!dimensionsSet)
            setTextDimensions();
        if(textViewCalculations.length() < maxTextLen) {
            removeNumberClick = false;

            checkTextToClear();
            appendText(operator.appendNumber(dot));
        }
    }

    void setPlusMinus(){
        if (!dimensionsSet)
            setTextDimensions();
        if(textViewCalculations.length() < maxTextLen) {
            removeNumberClick = false;

            if (operator.isRightNumReady()) {
                displayPlusMinus(operator.setPlusMinus());
            }
        }
    }

    private boolean checkOperation(String result, char operation){
        if(result.length() > 1 && result.charAt(0) == '-')
            return result.substring(1).contains(String.valueOf(operation));
        else
            return result.contains(String.valueOf(operation));
    }

    private void appendText(String text){
        if(canAppend(text))
            textViewCalculations.setText(textViewCalculations.getText() + text);
    }

    private boolean canAppend(String text){
        char lastCh = '0';
        if(textViewCalculations.length() > 0) {
            lastCh = textViewCalculations.getText().toString().charAt(textViewCalculations.length() - 1);
//        if(textViewCalculations.length() == 0) {
//            if(textViewCalculations.getText().toString().endsWith("+") && text.equals("-")) {
//                textViewCalculations.setText(textViewCalculations.getText().toString().substring(0, textViewCalculations.length() - 1));
//                return true;
//            }
//            else if(textViewCalculations.getText().toString().endsWith("-") && text.equals("+")) {
//                textViewCalculations.setText(textViewCalculations.getText().toString().substring(0, textViewCalculations.length() - 1));
//                return true;
//            }
//        }
//        if(textViewCalculations.getText().toString().endsWith("+") ||
//                textViewCalculations.getText().toString().endsWith("-") ||
//                textViewCalculations.getText().toString().endsWith("*") ||
//                textViewCalculations.getText().toString().endsWith("/")) {
            if (!Character.isDigit(lastCh) && lastCh != '.') {
                if (textViewCalculations.getText().toString().endsWith(text))
                    return false;
                else {
                    if (!Character.isDigit(text.charAt(0)))
                        textViewCalculations.setText(textViewCalculations.getText().toString().substring(0, textViewCalculations.length() - 1));
                    return true;
                }
            }
        }
        return true;
    }

    void back(){
        String prevCh = "";
        if(textViewCalculations.length() > 0) {
            if(textViewCalculations.length() > 1)
                prevCh = String.valueOf(textViewCalculations.getText().charAt(textViewCalculations.getText().length() - 2));
            operator.delete();
            char lastCh = textViewCalculations.getText().charAt(textViewCalculations.getText().length() - 1);
            if (Character.isDigit(lastCh) || lastCh == '.') {
                if (lastCh == '.') {
                    if (prevCh.equals("0")) {
                        textViewCalculations.setText(textViewCalculations.getText().toString().substring(0, textViewCalculations.length() - 2));
                        operator.delete();
                    }
                    else
                        textViewCalculations.setText(textViewCalculations.getText().toString().substring(0, textViewCalculations.length() - 1));
                }
                else if(textViewCalculations.length()> 1) {
                    if(prevCh.equals(".")) {
                        textViewCalculations.setText(textViewCalculations.getText().toString().substring(0, textViewCalculations.length() - 2));
                        operator.delete();
                    }
                    else {
                        textViewCalculations.setText(textViewCalculations.getText().toString().substring(0, textViewCalculations.length() - 1));
                        //operator.clear();
                    }
                }
                else {
                    textViewCalculations.setText(textViewCalculations.getText().toString().substring(0, textViewCalculations.length() - 1));
                    operator.clear();
                }
            }
        }
        else{
            operator.clear();
        }
    }

    private void checkTextToClear(){
        if(toClear){
            clear();
            toClear = false;
        }
    }

    private void displayRemovedNumber(boolean wasMinus){
        String text = textViewCalculations.getText().toString();
        if(wasMinus){
            int index = 0;
            for (int i = 0; i < text.length(); ++i) {
                if(i > index && text.charAt(i) != '-' && !Character.isDigit(text.charAt(i)) && text.charAt(i) != '.')
                    index = i;
            }
            if(text.contains("--"))
                index = text.lastIndexOf("--");
            if(index > 0)
                textViewCalculations.setText(text.substring(0, index + 1));
            else {
                textViewCalculations.setText("");
                operator.clear();
            }
        }
        else{
            int index = 0;
            for (int i = 0; i < text.length(); ++i) {
                if(i > index && !Character.isDigit(text.charAt(i)) && text.charAt(i) != '.')
                    index = i;
            }
            if(index > 0)
                textViewCalculations.setText(text.substring(0, index + 1));
            else {
                textViewCalculations.setText("");
                operator.clear();
            }
        }
    }

    private void displayPlusMinus(String sign){
        String text = textViewCalculations.getText().toString();
        if(sign.equals("+"))
            textViewCalculations.setText(text.substring(0, text.lastIndexOf("-")) + operator.getStrNumber());
        else {
            int index = 0;
            for (int i = 0; i < text.length(); ++i) {
                if(i > index && !Character.isDigit(text.charAt(i)) && text.charAt(i) != '.')
                    index = i;
            }
            if(index > 0)
                textViewCalculations.setText(text.substring(0, index + 1) + sign + operator.getStrNumber());
            else
                textViewCalculations.setText(sign + operator.getStrNumber());
        }
    }

    private void displayText(String text){
        textViewCalculations.setText(text);
    }

    void clear(){
        operator.clear();
        displayText("");
    }

    void setToClear(){
        toClear = true;
    }

    private void setTextDimensions(){
        if(Build.VERSION.SDK_INT < 26) {
            textViewCalculations.setTextSize(textViewCalculations.getHeight() / 4);
            maxTextLen = (int) (textViewCalculations.getWidth() * 1.8 / textViewCalculations.getTextSize());
            textViewCalculations.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxTextLen)});
        }
        else {
            maxTextLen = (int) (textViewCalculations.getWidth() * 0.5 / textViewCalculations.getAutoSizeMinTextSize());
            textViewCalculations.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxTextLen)});
        }
        dimensionsSet = true;
    }
}
