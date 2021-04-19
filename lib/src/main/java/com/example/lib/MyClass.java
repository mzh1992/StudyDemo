package com.example.lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MyClass {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(61);
        list.add(34);
        list.add(631);
        list.add(56);
        list.add(65);
        list.add(21);
        list.add(16);
        list.add(85);

        System.out.println(list.toString());
        List<Integer> copyList = new ArrayList<>(list.size());
        Collections.copy(copyList,list);
    }
}