package main.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import main.calculator.Calculations.Operations;

public class CalculatorActivity extends AppCompatActivity {
    private boolean toClear = false;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonSum;
    private Button buttonAdd;
    private Button buttonDiv;
    private Button buttonMinus;
    private Button buttonMult;
    private Button buttonCA;
    private Button buttonDot;
    private Button buttonBack;
    private Operations operations;
    private TextView textViewCalculations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        setButtons();
        operations = new Operations();
    }

    private void setButtons(){
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonSum = findViewById(R.id.buttonSum);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonMult = findViewById(R.id.buttonMult);
        buttonDot = findViewById(R.id.buttonDot);
        buttonCA = findViewById(R.id.buttonCA);
        buttonBack = findViewById(R.id.buttonBack);

        textViewCalculations = findViewById(R.id.textViewCalculations);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseNumber(0);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseNumber(1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseNumber(2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseNumber(3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseNumber(4);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseNumber(5);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseNumber(6);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseNumber(7);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseNumber(8);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseNumber(9);
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDot(".");
            }
        });

        buttonSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayResultNumber('=');
                operations.clear();
                toClear = true;
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayResultNumber('+');
                appendText("+");
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayResultNumber('-');
                appendText("-");
            }
        });

        buttonMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayResultNumber('*');
                appendText("*");
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayResultNumber('/');
                appendText("/");
            }
        });

        buttonCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operations.clear();
                displayText("");
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    private double operate(char operation){
        checkTextToClear();
        return operations.operation(operation);
    }

    private void increaseNumber(int add) {
        checkTextToClear();
        if (!(operations.getStrNumber().equals("0") && add == 0)) {
            appendText(String.valueOf(add));
            operations.appendNumber(String.valueOf(add));
        }
    }

    private void addDot(String dot){
        checkTextToClear();
        appendText(operations.appendNumber(dot));
    }

    private void checkTextToClear(){
        if(toClear){
            operations.clear();
            displayText("");
            toClear = false;
        }
    }

    private void displayResultNumber(char operation){
        if(textViewCalculations.length() > 0) {
            double result = operate(operation);
            int toCompare = (int) (result);
            if (Double.compare(toCompare, result) == 0)
                displayText(String.valueOf(toCompare));
            else
                displayText(String.valueOf(result));
        }
    }

    private void displayText(String text){
        textViewCalculations.setText(text);
    }

    private void appendText(String text){
        if(canAppend(text))
            textViewCalculations.setText(textViewCalculations.getText() + text);
    }

    private boolean canAppend(String text){
        String check = "+-*/";
        if(textViewCalculations.length() == 0 && (text.contains("+") ||
                text.contains("*") || text.contains("/")))
            return false;
        if(textViewCalculations.getText().toString().endsWith("+") ||
                textViewCalculations.getText().toString().endsWith("-") ||
                textViewCalculations.getText().toString().endsWith("*") ||
                textViewCalculations.getText().toString().endsWith("/"))
            if(textViewCalculations.getText().toString().endsWith(text))
                return false;
            else{
                if(check.contains(text))
                    textViewCalculations.setText(textViewCalculations.getText().toString().substring(0, textViewCalculations.length() - 1));
                return true;
            }
        return true;
//        if(textViewCalculations.getText().toString().equals("-"))
//        if(textViewCalculations.getText().length() > 0)
//            return true;
//        else
//            return !check.contains(text);
    }

    private void back(){
        checkTextToClear();
        String prevCh = "";
        if(textViewCalculations.length() > 0) {
            String operationsStr = "+-/*";
            if(textViewCalculations.length() > 1)
                prevCh = String.valueOf(textViewCalculations.getText().charAt(textViewCalculations.getText().length() - 2));
            operations.delete();
            String lastCh = String.valueOf(textViewCalculations.getText().charAt(textViewCalculations.getText().length() - 1));
            if (!operationsStr.contains(lastCh)) {
                if (lastCh.equals(".")) {
                    if (prevCh.equals("0")) {
                        textViewCalculations.setText(textViewCalculations.getText().toString().substring(0, textViewCalculations.length() - 2));
                        operations.delete();
                    }
                    else
                        textViewCalculations.setText(textViewCalculations.getText().toString().substring(0, textViewCalculations.length() - 1));
                } else
                    if(textViewCalculations.length()> 1) {
                        if(prevCh.equals(".")) {
                            textViewCalculations.setText(textViewCalculations.getText().toString().substring(0, textViewCalculations.length() - 2));
                            operations.delete();
                        }
                        else
                            textViewCalculations.setText(textViewCalculations.getText().toString().substring(0, textViewCalculations.length() - 1));
                    } else
                        textViewCalculations.setText(textViewCalculations.getText().toString().substring(0, textViewCalculations.length() - 1));
            }
        }

    }
}
