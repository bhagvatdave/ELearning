package com.project.e_learning.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.e_learning.R;
import com.project.e_learning.databinding.FragmentHomeBinding;
import com.project.e_learning.ui.activities.CategoryActivity;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding Fbinding;
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Fbinding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        Fbinding.startquiz.setOnClickListener(view -> {
            startActivity(new Intent(getContext(), CategoryActivity.class));
        });
        Fbinding.readquestion.setOnClickListener(view -> {
            startActivity(new Intent(getContext(), CategoryActivity.class));
        });
//        Fbinding.History.setOnClickListener(view -> {
//            getContext().startActivity(new Intent(getContext()));
//        });
//        Fbinding.rules.setOnClickListener(view -> {
//            getContext().startActivity(new Intent(getContext()));
//        });
        return Fbinding.getRoot();
    }
}