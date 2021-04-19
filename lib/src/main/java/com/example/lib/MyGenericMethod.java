package com.example.lib;

public class MyGenericMethod {


    public <T, M, K> void show(T t, M m, K k) {
        System.out.println(t);
        System.out.println(m);
        System.out.println(k);
    }

}
