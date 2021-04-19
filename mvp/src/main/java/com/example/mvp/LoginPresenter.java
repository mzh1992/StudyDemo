package com.example.mvp;


import com.example.mvp.base.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginModel, LoginActivity, LoginContract.Presenter> {
    @Override
    public LoginContract.Presenter getContract() {
        return new LoginContract.Presenter<UserInfo>() {
            @Override
            public void requestLogin(String userName, String pwd) {
                model.getContract().executeLogin(userName, pwd);
            }

            @Override
            public void responseResult(UserInfo userInfo) {
                if (getView() != null) {
                    getView().getContract().handleResult(userInfo);
                }
            }
        };
    }

    @Override
    public LoginModel getModel() {
        return new LoginModel(this);
    }
}
