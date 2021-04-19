package com.example.mvvmdatabind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;
import android.os.Handler;

import com.example.mvvmdatabind.databinding.ActivityMainBindingImpl;
import com.example.mvvmdatabind.viewmodel.LoginViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBindingImpl binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        new LoginViewModel(binding);
        startService()
    }
}