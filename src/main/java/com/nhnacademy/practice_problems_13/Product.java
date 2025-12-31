package com.nhnacademy.practice_problems_13;

public class Product {
    private String name;
    private int price;

    Product(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public int getprice(){
        return this.price;
    }
    // 생성자, getter 생략
}
