package com.project.e_learning.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.e_learning.Model.Common;
import com.project.e_learning.R;
import com.project.e_learning.databinding.FragmentUserBinding;

public class UserFragment extends Fragment {
    private FragmentUserBinding Fbinding;

    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance() {
        return  new UserFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Fbinding = FragmentUserBinding.inflate(getLayoutInflater(),container, false);
        Fbinding.userProfileName.setText(Common.currentUsername);
        Fbinding.UserEmail.setText(Common.currentUser.getEmail());
        return Fbinding.getRoot();
    }
}