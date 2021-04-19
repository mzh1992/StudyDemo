package com.example.lib;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetDemo {

    public static void main(String[] args) {
        HashSet<Person> hashSet = new HashSet<>();
        hashSet.add(new Person("xiaoming", 15));
        hashSet.add(new Person("xiaobai", 15));
        hashSet.add(new Person("xiaodong", 15));
        hashSet.add(new Person("xiaonan", 15));
        hashSet.add(new Person("xiaobei", 15));

        System.out.println("size is:" + hashSet.size());
        System.out.println(hashSet.toString());

        hashSet.add(new Person("xiaoming", 15));

        System.out.println("size is:" + hashSet.size());
        System.out.println(hashSet.toString());
    }
}
