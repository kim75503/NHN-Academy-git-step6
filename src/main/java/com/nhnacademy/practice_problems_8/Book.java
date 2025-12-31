package com.nhnacademy.practice_problems_8;

public class Book {
   private String title;
    private int price;

    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public void setPrice(int price) {
        this.price = price;      
    }

    public String getInfo(){
        return this.title + " - " +price+"원";
    }

}
