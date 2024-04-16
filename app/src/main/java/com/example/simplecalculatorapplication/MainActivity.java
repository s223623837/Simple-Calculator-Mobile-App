package com.example.simplecalculatorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inputEditText;
    Button addButton, subtractButton, multiplyButton, divideButton, calculateButton;
    String currentInput = ""; // Variable to store current input string
    double currentValue = 0; // Variable to store current numeric value
    OperationType currentOperation = null; // Variable to store current operation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        inputEditText = findViewById(R.id.inputEditText);
        addButton = findViewById(R.id.addButton);
        subtractButton = findViewById(R.id.subtractButton);
        multiplyButton = findViewById(R.id.multiplyButton);
        divideButton = findViewById(R.id.divideButton);
        calculateButton = findViewById(R.id.equalButton);

        // Set click listeners for digit buttons (1-9) and decimal button
        setDigitButtonListeners();

        // Set click listeners for operation buttons
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperation(OperationType.ADD);
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperation(OperationType.SUBTRACT);
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperation(OperationType.MULTIPLY);
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperation(OperationType.DIVIDE);
            }
        });

        // Set click listener for the Calculate button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    // Method to set click listeners for digit buttons (1-9) and decimal button
    private void setDigitButtonListeners() {
        // Array of button IDs for digits (1-9)
        int[] digitButtonIds = {R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6,
                R.id.button7, R.id.button8, R.id.button9};

        for (int id : digitButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String digit = ((Button) v).getText().toString();
                    appendInput(digit);
                }
            });
        }

        // Decimal button
        Button decimalButton = findViewById(R.id.decimalButton);
        decimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendInput(".");
            }
        });

        // Zero button
        Button zeroButton = findViewById(R.id.button0);
        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendInput("0");
            }
        });
    }

    // Method to append input (digit or decimal) to the current input string
    private void appendInput(String value) {
        currentInput += value;
        inputEditText.setText(currentInput);
    }

    // Method to handle operation button clicks
    private void handleOperation(OperationType operation) {
        if (!currentInput.isEmpty()) {
            // Convert current input string to double and store as currentValue
            currentValue = Double.parseDouble(currentInput);
            currentInput = ""; // Clear current input string
            currentOperation = operation; // Store the selected operation
        } else {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to perform calculation based on the stored operation type
    private void calculateResult() {
        if (currentOperation != null && !currentInput.isEmpty()) {
            double secondValue = Double.parseDouble(currentInput);
            double result = 0;

            // Perform calculation based on the current operation
            switch (currentOperation) {
                case ADD:
                    result = currentValue + secondValue;
                    break;
                case SUBTRACT:
                    result = currentValue - secondValue;
                    break;
                case MULTIPLY:
                    result = currentValue * secondValue;
                    break;
                case DIVIDE:
                    if (secondValue != 0) {
                        result = currentValue / secondValue;
                    } else {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
            }

            // Display the result using a Toast message
            Toast.makeText(this, "Result: " + result, Toast.LENGTH_SHORT).show();
            currentInput = ""; // Clear current input string
            inputEditText.setText(""); // Clear input field
        } else {
            Toast.makeText(this, "Please select an operation and enter a number", Toast.LENGTH_SHORT).show();
        }
    }

    // Enum to define different operation types
    private enum OperationType {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE
    }
}
