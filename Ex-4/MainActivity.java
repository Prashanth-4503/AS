package com.example.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentInput = "";
    private String operator = "";
    private double firstOperand = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Number buttons
        Button button1 = findViewById(R.id.button_1);
        Button button2 = findViewById(R.id.button_2);
        Button button3 = findViewById(R.id.button_3);
        Button button4 = findViewById(R.id.button_4);
        Button button5 = findViewById(R.id.button_5);
        Button button6 = findViewById(R.id.button_6);
        Button button7 = findViewById(R.id.button_7);
        Button button8 = findViewById(R.id.button_8);
        Button button9 = findViewById(R.id.button_9);
        Button button0 = findViewById(R.id.button_0);

        // Operator buttons
        Button buttonAdd = findViewById(R.id.button_add);
        Button buttonSubtract = findViewById(R.id.button_subtract);
        Button buttonMultiply = findViewById(R.id.button_multiply);
        Button buttonDivide = findViewById(R.id.button_divide);

        // Other buttons
        Button buttonClear = findViewById(R.id.button_clear);
        Button buttonEqual = findViewById(R.id.button_equal);

        // Set OnClickListener for number buttons
        button1.setOnClickListener(v -> appendToInput("1"));
        button2.setOnClickListener(v -> appendToInput("2"));
        button3.setOnClickListener(v -> appendToInput("3"));
        button4.setOnClickListener(v -> appendToInput("4"));
        button5.setOnClickListener(v -> appendToInput("5"));
        button6.setOnClickListener(v -> appendToInput("6"));
        button7.setOnClickListener(v -> appendToInput("7"));
        button8.setOnClickListener(v -> appendToInput("8"));
        button9.setOnClickListener(v -> appendToInput("9"));
        button0.setOnClickListener(v -> appendToInput("0"));

        // Set OnClickListener for operator buttons
        buttonAdd.setOnClickListener(v -> setOperator("+"));
        buttonSubtract.setOnClickListener(v -> setOperator("-"));
        buttonMultiply.setOnClickListener(v -> setOperator("*"));
        buttonDivide.setOnClickListener(v -> setOperator("/"));

        // Set OnClickListener for equal and clear buttons
        buttonEqual.setOnClickListener(v -> calculateResult());
        buttonClear.setOnClickListener(v -> clearInput());
    }

    // Append digits to the input
    private void appendToInput(String input) {
        currentInput += input;
        display.setText(currentInput);
    }

    // Set the operator when clicked
    private void setOperator(String operator) {
        if (!currentInput.isEmpty()) {
            firstOperand = Double.parseDouble(currentInput);
            currentInput = "";
            this.operator = operator;
        }
    }

    // Calculate result based on the operator
    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
        }
    }

    // Clear the input
    private void clearInput() {
        currentInput = "";
        display.setText("");
    }
}
