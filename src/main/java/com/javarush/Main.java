package com.javarush;

public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);

        System.out.println(map.remove("B"));
        System.out.println(map.get("B"));

        System.out.println(map.remove("D"));

    }
}