package com.example.mvp;

import android.widget.Toast;

import com.example.mvp.base.BaseModel;


public class LoginModel extends BaseModel<LoginPresenter,LoginContract.Model> {
    public LoginModel(LoginPresenter loginPresenter) {
        super(loginPresenter);
    }

    @Override
    public LoginContract.Model getContract() {
        return new LoginContract.Model() {
            @Override
            public void executeLogin(String userName, String pwd) {
                if (userName.equals("123") && pwd.equals("456")) {
                    Toast.makeText(p.getView(), "LoginSuccess", Toast.LENGTH_SHORT).show();
                    UserInfo userInfo = new UserInfo();
                    userInfo.setCompany("Baidu");
                    userInfo.setName("mzh");
                    p.getContract().responseResult(userInfo);
                }

            }
        };
    }
}
