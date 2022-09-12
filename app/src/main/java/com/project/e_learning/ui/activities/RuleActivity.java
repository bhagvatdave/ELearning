package com.project.e_learning.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.project.e_learning.databinding.ActivityRuleBinding;

public class RuleActivity extends AppCompatActivity {
private ActivityRuleBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRuleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}