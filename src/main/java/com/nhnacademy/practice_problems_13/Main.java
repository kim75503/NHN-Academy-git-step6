package com.nhnacademy.practice_problems_13;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*
        ###종합 연습
        ####문제 13: 다음 요구사항에 맞게 ProductService 클래스를 작성하세요.

        요구사항:

        Product 목록을 저장하는 private 필드
        add(Product product) 메서드
        getAll() 메서드
        count() 메서드
        // Product 클래스는 주어짐
        public class Product {
            private String name;
            private int price;
            // 생성자, getter 생략
        }
         */

        ProductService products = new ProductService();
        Product pd1 = new Product("컴퓨터", 20000);
        Product pd2 = new Product("핸드폰", 10000);

        products.add(pd1);
        products.add(pd2);

        System.out.println(products.count()+"개");

        ArrayList<Product> PD = new ArrayList<>();

        PD = products.getAll();
        System.out.println(PD.size()+"개");




        
    }
}

