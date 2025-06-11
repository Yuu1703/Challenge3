package com.example.challenge3;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView txtStatus, txtQuestion;
    EditText edtAnswer;
    Button btnOK, btnNext;
    int score = 0, life = 3, timer = 60;
    int correctAnswer;
    CountDownTimer countDownTimer;

    void generateQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(50);
        int b = rand.nextInt(50);
        correctAnswer = a + b;
        txtQuestion.setText(a + " + " + b + " = ?");
        edtAnswer.setText("");
    }

    void updateStatus() {
        txtStatus.setText("Score: " + score + "   Life: " + life + "   Timer: " + timer);
    }

    void endGame() {
        Intent intent = new Intent(GameActivity.this, ResultActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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
                int userAnswer = Integer.parseInt(input);
                if (userAnswer == correctAnswer) {
                    score += 10;
                    generateQuestion(); // ✅ Thêm dòng này để qua câu tiếp theo
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