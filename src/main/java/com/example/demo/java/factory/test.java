package com.example.demo.java.factory;

public class test {
    public static void main(String[] args) {
        try {
            Class.forName("com.example.demo.java.factory.ff");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
