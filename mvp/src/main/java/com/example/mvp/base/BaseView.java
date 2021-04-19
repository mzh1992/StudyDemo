package com.example.mvp.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseView<P extends BasePresenter, CONTRACT> extends AppCompatActivity {
    protected P p;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p = getPresenter();
        p.bindView(this);
    }

    public abstract CONTRACT getContract();

    public abstract P getPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        p.unbindView();
    }
}
