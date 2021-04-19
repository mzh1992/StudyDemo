package com.example.amsdemo;

import android.app.Application;

import com.example.amsdemo.core.AmsManager;

import java.lang.reflect.InvocationTargetException;

public class AmsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            AmsManager.hookAMSService(this);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        try {
            AmsManager.hookActivityThread(this);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
