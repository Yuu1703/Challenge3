package com.example.challenge3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnAddition, btnSubtraction, btnMultiplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các nút từ giao diện XML
        btnAddition = findViewById(R.id.btnAddition);
        btnSubtraction = findViewById(R.id.btnSubtraction);
        btnMultiplication = findViewById(R.id.btnMultiplication);

        // Nút "Addition" → mở GameActivity (phép cộng)
        btnAddition.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        });

        // Nút "Subtraction" → mở SubtractionActivity (phép trừ)
        btnSubtraction.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SubtractionActivity.class);
            startActivity(intent);
        });

        // Nút "Multiplication" → mở MultiplicationActivity (phép nhân)
        btnMultiplication.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MultiplicationActivity.class);
            startActivity(intent);
        });
    }
}