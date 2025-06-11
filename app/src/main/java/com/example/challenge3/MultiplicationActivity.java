package com.example.challenge3;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MultiplicationActivity extends AppCompatActivity {

    TextView txtStatus, txtQuestion;
    EditText edtAnswer;
    Button btnOK, btnNext;
    int score = 0, life = 3, timer = 60;
    int correctAnswer;
    CountDownTimer countDownTimer;

    void generateQuestion() {
        int a = new Random().nextInt(10);
        int b = new Random().nextInt(10);
        correctAnswer = a * b;
        txtQuestion.setText(a + " × " + b + " = ?");
        edtAnswer.setText("");
    }

    void updateStatus() {
        txtStatus.setText("Score: " + score + "   Life: " + life + "   Timer: " + timer);
    }

    void endGame() {
        Intent intent = new Intent(MultiplicationActivity.this, ResultActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game); // dùng lại layout của GameActivity

        txtStatus = findViewById(R.id.txtStatus);
        txtQuestion = findViewById(R.id.txtQuestion);
        edtAnswer = findViewById(R.id.edtAnswer);
        btnOK = findViewById(R.id.btnOK);
        btnNext = findViewById(R.id.btnNext);

        updateStatus();
        generateQuestion();

        countDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer--;
                updateStatus();
            }

            public void onFinish() {
                endGame();
            }
        }.start();

        btnOK.setOnClickListener(view -> {
            String input = edtAnswer.getText().toString();
            if (!input.isEmpty()) {
                int ans = Integer.parseInt(input);
                if (ans == correctAnswer) {
                    score += 10;
                    generateQuestion();
                } else {
                    life--;
                    if (life == 0) {
                        countDownTimer.cancel();
                        endGame();
                        return;
                    }
                }
                updateStatus();
            }
        });

        btnNext.setOnClickListener(view -> generateQuestion());
    }
}