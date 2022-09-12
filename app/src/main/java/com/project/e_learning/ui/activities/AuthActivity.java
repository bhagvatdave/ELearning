package com.project.e_learning.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.project.e_learning.R;
import com.project.e_learning.databinding.ActivityAuthBinding;
import com.project.e_learning.ui.fragments.LoginFragment;
import com.project.e_learning.ui.fragments.SigninFragment;

import java.util.List;

public class AuthActivity extends BaseActivity {
private ActivityAuthBinding Abinding;
    private boolean doubleClickToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Abinding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(Abinding.getRoot());

        getSupportActionBar().hide();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_holder, LoginFragment.newInstance()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (doubleClickToExitPressedOnce) finish();
        Fragment fragment = getVisibleFragment(AuthActivity.this);
        if (fragment instanceof LoginFragment) {
            Toast.makeText(this, "Click once more to close the app", Toast.LENGTH_SHORT).show();
            doubleClickToExitPressedOnce = true;
            new Handler().postDelayed(() -> doubleClickToExitPressedOnce = false, 2750);
        } else if (fragment instanceof SigninFragment)
            replaceFragmentWithAnimation(AuthActivity.this, LoginFragment.newInstance(), R.id.fragment_holder);
        else {
            replaceFragmentWithAnimation(AuthActivity.this, LoginFragment.newInstance(), R.id.fragment_holder);
        }
    }

}