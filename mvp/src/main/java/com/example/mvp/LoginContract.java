package com.example.mvp;


//
public interface LoginContract {

    public interface Model {
        void executeLogin(String userName, String pwd);
    }

    public interface View<T extends BaseEntity> {
        void handleResult(T t);
    }

    public interface Presenter<T extends BaseEntity> {
        //接收View层的任务，可以自己处理，也可以让Model层处理
        void requestLogin(String userName, String pwd);

        //响应Model的结果，通知View进行刷新
        void responseResult(T t);
    }

}
