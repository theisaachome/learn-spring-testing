package com.isaachome.controller;

public class TestController {
    public static void main(String[] args) {
        int x =0; int y=10;
        try {
           y/=x;
        }catch (ArithmeticException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
