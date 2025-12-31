package com.nhnacademy.practice_problems_13;

import java.util.ArrayList;

public class ProductService {
    private ArrayList<Product> products = new ArrayList<>();

    public void add(Product product){
        products.add(product);
    }

    public ArrayList<Product> getAll(){
        return products;
    }

    public int count(){
        return products.size();
    }
}
