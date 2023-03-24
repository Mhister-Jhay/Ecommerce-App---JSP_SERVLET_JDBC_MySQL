package com.example.week6ecommerce.model;
import java.time.LocalDate;
import java.util.Date;
import lombok.Data;

@Data
public class Order {
    private int product_id;
    private String image;
    private String name;
    private double price;
    private int quantity;
    private Date date;

    public Order(int product_id, String image, String name, double price, int quantity, Date date) {
        this.product_id = product_id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }
}
