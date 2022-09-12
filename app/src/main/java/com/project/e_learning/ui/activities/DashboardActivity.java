package com.project.e_learning.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.e_learning.R;
import com.project.e_learning.databinding.ActivityDashboardBinding;
import com.project.e_learning.ui.fragments.HomeFragment;
import com.project.e_learning.ui.fragments.UserFragment;

public class DashboardActivity extends BaseActivity {
private ActivityDashboardBinding binding;
    private BaseActivity base;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        base = BaseActivity.getInstance();
        binding.navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:{
                        base.replaceFragmentWithAnimation(DashboardActivity.this,HomeFragment.newInstance(),R.id.frame_layout);
                        return true;}
                    case R.id.ranking:{
                        //base.replaceFragmentWithAnimation(DashboardActivity.this,HomeFragment.newInstance(),R.id.frame_layout);
                        return true;}
                    case R.id.user:{
                        base.replaceFragmentWithAnimation(DashboardActivity.this, UserFragment.newInstance(),R.id.frame_layout);
                        return true;}
                }
                return false;
            }
        });
    }
}