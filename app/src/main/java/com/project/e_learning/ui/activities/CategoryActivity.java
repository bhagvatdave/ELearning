package com.project.e_learning.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.project.e_learning.databinding.ActivityCategoryBinding;

public class CategoryActivity extends AppCompatActivity {
    private ActivityCategoryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}