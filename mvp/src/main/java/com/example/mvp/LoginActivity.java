package com.example.mvp;


import android.os.Bundle;
import android.widget.Toast;

import com.example.mvp.base.BaseView;

public class LoginActivity extends BaseView<LoginPresenter, LoginContract.View> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.test).setOnClickListener(v -> login());
    }

    @Override
    public LoginContract.View getContract() {
        return (LoginContract.View<UserInfo>) userInfo -> Toast.makeText(LoginActivity.this, userInfo.toString(), Toast.LENGTH_SHORT).show();
    }


    public void login() {
        p.getContract().requestLogin("123", "456");
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }
}