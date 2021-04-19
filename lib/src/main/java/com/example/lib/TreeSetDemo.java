package com.example.lib;

import java.util.TreeSet;

/**
 * 存储结构是红黑树
 */
public class TreeSetDemo {
    public static void main(String[] args) {

        TreeSet<Person> personTreeSet = new TreeSet<>();
        personTreeSet.add(new Person("Jack", 15));
        personTreeSet.add(new Person("Tom", 17));
        personTreeSet.add(new Person("May", 12));
        personTreeSet.add(new Person("Tony", 19));
        personTreeSet.add(new Person("Cat", 14));

        System.out.println("size is:" + personTreeSet.size());

    }
}
