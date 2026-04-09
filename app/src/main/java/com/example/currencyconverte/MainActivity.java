package com.example.currencyconverte;

import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    Spinner fromSpinner, toSpinner;
    TextView result;
    Button convertBtn, themeBtn;

    String[] currencies = {"INR", "USD", "EUR", "JPY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amount);
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        result = findViewById(R.id.result);
        convertBtn = findViewById(R.id.convertBtn);
        themeBtn = findViewById(R.id.themeBtn);

        // Spinner setup
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                currencies
        );

        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        // Convert button
        convertBtn.setOnClickListener(v -> {

            String amtText = amount.getText().toString();

            if (amtText.isEmpty()) {
                result.setText("Enter amount");
                return;
            }

            double amt = Double.parseDouble(amtText);

            String from = fromSpinner.getSelectedItem().toString();
            String to = toSpinner.getSelectedItem().toString();

            double rate = 1;

            if(from.equals("INR") && to.equals("USD")) rate = 0.012;
            else if(from.equals("USD") && to.equals("INR")) rate = 83;

            else if(from.equals("INR") && to.equals("EUR")) rate = 0.011;
            else if(from.equals("EUR") && to.equals("INR")) rate = 90;

            else if(from.equals("INR") && to.equals("JPY")) rate = 1.8;
            else if(from.equals("JPY") && to.equals("INR")) rate = 0.55;

            else if(from.equals("USD") && to.equals("EUR")) rate = 0.92;
            else if(from.equals("EUR") && to.equals("USD")) rate = 1.08;

            else if(from.equals("USD") && to.equals("JPY")) rate = 150;
            else if(from.equals("JPY") && to.equals("USD")) rate = 0.0067;

            double res = amt * rate;

            result.setText("Result: " + res);
        });

        // 🌙 DARK MODE BUTTON
        themeBtn.setOnClickListener(v -> {
            int current = AppCompatDelegate.getDefaultNightMode();

            if (current == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });
    }
}