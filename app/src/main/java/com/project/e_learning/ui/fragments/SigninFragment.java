package com.project.e_learning.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.project.e_learning.Database.MyDatabase;
import com.project.e_learning.Model.User;
import com.project.e_learning.R;
import com.project.e_learning.databinding.FragmentSigninBinding;
import com.project.e_learning.ui.activities.BaseActivity;

public class SigninFragment extends Fragment {
  private FragmentSigninBinding Fbinding;
  private BaseActivity base;
  private MyDatabase mydb;
    private User user;


    public SigninFragment() {
    }

    public static SigninFragment newInstance() {
        return new SigninFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Fbinding = FragmentSigninBinding.inflate(getLayoutInflater(),container, false);
        mydb =new MyDatabase(getContext());
        base = BaseActivity.getInstance();
        Fbinding.btnRegister.setOnClickListener(view -> {
            if(valid()){
                User u = new User(Fbinding.etFirstName.getText().toString(),
                                  Fbinding.etLastName.getText().toString(),
                                  Fbinding.etEmail.getText().toString(),
                                  Fbinding.etPassword.getText().toString());
                mydb.insertuserdata(u);
                base.replaceFragmentWithAnimation(getActivity(),LoginFragment.newInstance(),R.id.fragment_holder);
            }
        });
        return Fbinding.getRoot();
    }

    private boolean valid() {

        if (Fbinding.etFirstName.getText() == null || Fbinding.etFirstName.getText().toString().trim().isEmpty()) {
            Fbinding.inputFirstName.setError(getString(R.string.invalid_firstName));
            return false;
        } else {
            Fbinding.inputFirstName.setError(null);
        }
        if (Fbinding.etLastName.getText() == null || Fbinding.etLastName.getText().toString().trim().isEmpty()) {
            Fbinding.inputLastName.setError(getString(R.string.invalid_lastName));
            return false;
        } else {
            Fbinding.inputLastName.setError(null);
        }
        if (Patterns.EMAIL_ADDRESS.matcher(Fbinding.etEmail.getText().toString().trim()).matches()) {
            Fbinding.inputEmail.setError(null);
        } else {
            Fbinding.inputEmail.setError(getString(R.string.error_invalid_email));
            return false;
        }
        if (Fbinding.etPassword.getText() == null || Fbinding.etPassword.getText().toString().trim().isEmpty()) {
            Fbinding.inputPassword.setError(getString(R.string.error_short_password));
            return false;
        } else {
            Fbinding.inputPassword.setError(null);
        }
        if(((Fbinding.etPassword.getText().toString().compareTo(Fbinding.etConfirmPassword.getText().toString()))!=0) || (Fbinding.etConfirmPassword.getText() == null || Fbinding.etConfirmPassword.getText().toString().trim().isEmpty()))
        {
            Toast.makeText(getContext(), R.string.invalid_password2, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}