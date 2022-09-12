package com.project.e_learning.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.project.e_learning.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {
private ActivityResultBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            int score = extra.getInt("SCORE");
            int totalQuestion = extra.getInt("TOTAL");
            int correctAnswer = extra.getInt("CORRECT");
            int incorrectAnswer = extra.getInt("INCORRECT");
            int attempt = extra.getInt("ATTEMPT");
            String Subject = extra.getString("SUBJECT");
            binding.score.setText(String.format("SCORE : %d", score));
            binding.Subject.setText(Subject);
            binding.correct.setText(correctAnswer);
            binding.incorrect.setText(incorrectAnswer);
            binding.attempted.setText(attempt);
            float x1 = (correctAnswer * 100) / totalQuestion;
            if (x1 < 40)
                binding.you.setText("You Need Improvement");
            else if (x1 < 70)
                binding.you.setText("You are an average Player");
            else if (x1 < 90)
                binding.you.setText("You are an above average Player");
            else if (x1 > 90)
                binding.you.setText("You are a brilliant Player");
        }
    }
}