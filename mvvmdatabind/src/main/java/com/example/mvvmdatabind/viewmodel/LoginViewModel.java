package com.example.mvvmdatabind.viewmodel;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mvvmdatabind.bean.UserInfo;
import com.example.mvvmdatabind.databinding.ActivityMainBindingImpl;

public class LoginViewModel {
    public UserInfo userInfo;

    public TextWatcher pwdInputListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            userInfo.pwd.set(String.valueOf(s));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public TextWatcher nameInputListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            userInfo.name.set(String.valueOf(s));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public View.OnClickListener loginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if ("123".equals(userInfo.name.get()) && "456".equals(userInfo.pwd.get())) {
                Log.i("mzh", "success...");
                Toast.makeText(binding.getRoot().getContext(), "success", Toast.LENGTH_SHORT).show();
            } else {
                Log.i("mzh", "fail...");
            }
        }
    };
    private final ActivityMainBindingImpl binding;

    public LoginViewModel(ActivityMainBindingImpl binding) {
        this.binding = binding;
        this.binding.setLoginViewModel(this);
        userInfo = new UserInfo();
    }
}
