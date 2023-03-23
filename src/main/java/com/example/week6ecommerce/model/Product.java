package com.example.week6ecommerce.model;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private int category;
    private String image;

    public Product(String name, int category, double price,int quantity, String image) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.image = image;
    }

    public Product(int id, String name,int category, double price, int quantity,  String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.image = image;
    }

    public Product(){
    }
}
