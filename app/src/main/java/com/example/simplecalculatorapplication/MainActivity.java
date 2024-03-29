package com.example.simplecalculatorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstValueEditText, secondValueEditText;
    Button addButton, subtractButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstValueEditText = findViewById(R.id.firstValueEditText);
        secondValueEditText = findViewById(R.id.secondValueEditText);
        addButton = findViewById(R.id.addButton);
        subtractButton = findViewById(R.id.subtractButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addValues();
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtractValues();
            }
        });
    }

    private void addValues() {
        try {
            double firstValue = Double.parseDouble(firstValueEditText.getText().toString());
            double secondValue = Double.parseDouble(secondValueEditText.getText().toString());
            double result = firstValue + secondValue;
            Toast.makeText(this, "Result of addition: " + result, Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }

    private void subtractValues() {
        try {
            double firstValue = Double.parseDouble(firstValueEditText.getText().toString());
            double secondValue = Double.parseDouble(secondValueEditText.getText().toString());
            double result = firstValue - secondValue;
            Toast.makeText(this, "Result of subtraction: " + result, Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
}