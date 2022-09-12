package com.project.e_learning.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.project.e_learning.Database.MyDatabase;
import com.project.e_learning.Model.Common;
import com.project.e_learning.Model.User;
import com.project.e_learning.R;
import com.project.e_learning.databinding.FragmentLoginBinding;
import com.project.e_learning.ui.activities.DashboardActivity;


public class LoginFragment extends Fragment {
    private FragmentLoginBinding Fbinding;
    private MyDatabase mydb;
    private User user;

    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance() {

        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Fbinding = FragmentLoginBinding.inflate(getLayoutInflater(),container,false);
        Fbinding.tvForgetPassword.setOnClickListener(view -> onViewClicked());
        Fbinding.btnLogin.setOnClickListener(view -> tryToLogin());
        instantiateViews();
        return Fbinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void onViewClicked() {
        //replaceFragmentWithAnimation(new ForgetPasswordFragment(), TAG_FRAG_FORGET_PASS);
    }

    public boolean checkEmailValidation(String email) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Fbinding.inputEmailLayout.setError(null);
            return true;
        } else {
            Fbinding.inputEmailLayout.setError(getString(R.string.error_invalid_email));
            return false;
        }
    }

    public boolean checkPasswordValidation(String password) {

        int passLength = Fbinding.etPassword.getText().toString().length();
        if (passLength >= 8) {
            Fbinding.inputPasswordLayout.setError(null);
            return true;
        } else {
            Fbinding.inputPasswordLayout.setError(getString(R.string.error_short_password));
            return false;
        }
    }

    public void tryToLogin() {

        String email = Fbinding.etUserCredentials.getText().toString();
        String password = Fbinding.etPassword.getText().toString();

//        if (!NetworkUtils.isConnected(getActivity())) {
//            //NetworkUtils.showNoNetworkDialog(getActivity(),"","");
//            return;
//        }

        if (checkEmailValidation(email) && checkPasswordValidation(password)) {
           switch (mydb.checklogin(email,password)){
                case "Login Successfully":{
                    Common.currentUser = mydb.getcurrentuserdata(email);
                    Common.currentUsername = Common.currentUser.getFirstname() + " " + Common.currentUser.getLastname();
                    startActivity(new Intent(getContext(), DashboardActivity.class));
                }
               case "Password don't match":{
                   Toast.makeText(getContext(), "Password don't match", Toast.LENGTH_SHORT).show();
               }
               case "Email don't match":{
                   Toast.makeText(getContext(), "Email don't match", Toast.LENGTH_SHORT).show();
               }
               default:
                   break;
            }

        }
    }

    public void instantiateViews() {
        mydb = new MyDatabase(getContext());
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    }