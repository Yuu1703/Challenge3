package com.example.challenge3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView txtCongrat, txtFinalScore;
    Button btnPlayAgain, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtCongrat = findViewById(R.id.txtCongrat);
        txtFinalScore = findViewById(R.id.txtFinalScore);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnExit = findViewById(R.id.btnExit);

        // Nhận điểm từ GameActivity
        int score = getIntent().getIntExtra("score", 0);
        txtFinalScore.setText("Score: " + score);

        // Nhấn Play Again
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, GameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Nhấn Exit
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // kết thúc ResultActivity để không quay lại bằng nút back
            }
        });
    }
}