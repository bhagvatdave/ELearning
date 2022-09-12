package com.project.e_learning.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;

import com.project.e_learning.R;

import java.util.List;

public class BaseActivity extends AppCompatActivity {
    private static BaseActivity baseActivity;

    public BaseActivity() {}

    public static BaseActivity getInstance(){
        if(baseActivity==null){
            synchronized (BaseActivity.class){
                if(baseActivity==null){
                    baseActivity=new BaseActivity();
                }
            }
        }
        return baseActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public void replaceFragmentWithAnimation(FragmentActivity activity, Fragment fragment, Integer containerId) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.commit();
    }

    public Fragment getVisibleFragment(FragmentActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments)
            if (fragment != null && fragment.isVisible()) return fragment;
        return null;
    }
}