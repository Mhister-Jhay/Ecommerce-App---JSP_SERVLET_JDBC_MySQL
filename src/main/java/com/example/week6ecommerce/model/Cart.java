package com.example.week6ecommerce.model;

import lombok.Data;

@Data
public class Cart {
    private int product_id;
    private String image;
    private String name;
    private double price;
    private int quantity;

    public Cart(int product_id,String image, String name, double price, int quantity) {
        this.product_id = product_id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
