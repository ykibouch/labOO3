package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public abstract class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String currentInput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        // Set up click listeners for digit buttons
        for (int i = 0; i <= 9; i++) {
            int id = getResources().getIdentifier("button" + i, "id", getPackageName());
            Button button = findViewById(id);
            button.setOnClickListener(v -> {
                currentInput += button.getText().toString();
                resultTextView.setText(currentInput);
            });
        }

        for (int i = 0; i <= 9; i++) {
            int id = getResources().getIdentifier("button" + i, "id", getPackageName());
            Button button = findViewById(id);
            button.setOnClickListener(v -> {
                currentInput += button.getText().toString();
                resultTextView.setText(currentInput);
            });
        }

        // Set up click listeners for operator buttons (+, -, *, /)
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> handleOperatorClick("+"));

        Button subtractButton = findViewById(R.id.subtractButton);
        subtractButton.setOnClickListener(v -> handleOperatorClick("-"));

        Button multiplyButton = findViewById(R.id.multiplyButton);
        multiplyButton.setOnClickListener(v -> handleOperatorClick("*"));

        Button divideButton = findViewById(R.id.divideButton);
        divideButton.setOnClickListener(v -> handleOperatorClick("/"));

        // Set up click listeners for the clear and equal buttons
        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(v -> clearInput());

        Button equalButton = findViewById(R.id.equalButton);
        equalButton.setOnClickListener(v -> performCalculation());
    }

    // Handle operator button click
    private void handleOperatorClick(String operator) {
        if (!currentInput.isEmpty()) {
            currentInput += " " + operator + " ";
            resultTextView.setText(currentInput);
        }
    }

    // Clear the input and operator
    private void clearInput() {
        currentInput = "";
        resultTextView.setText("");
    }

    // Perform the calculation
    private void performCalculation() {
        String currentOperator = null;
        if (!currentInput.isEmpty() && currentOperator.length() > 0) {
            String[] expressionParts = currentInput.split(" ");
            if (expressionParts.length == 3) {
                try {
                    double num1 = Double.parseDouble(expressionParts[0]);
                    double num2 = Double.parseDouble(expressionParts[2]);
                    String operator = expressionParts[1];
                    double result = 0;

                    switch (operator) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 != 0) {
                                result = num1 / num2;
                            } else {
                                resultTextView.setText("Error: Division by zero");
                                return;
                            }
                            break;
                    }

                    resultTextView.setText(String.valueOf(result));
                } catch (NumberFormatException e) {
                    resultTextView.setText("Error: Invalid input");
                }
            } else {
                resultTextView.setText("Error: Invalid expression");
            }
        }        // Implement your calculation logic here
        // You'll need to parse the expression, perform the calculation, and update resultTextView
        // Make use of the currentOperator and currentInput variables
    }

    }

    // Implement the logic for operator buttons and equalButton here
