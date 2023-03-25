package com.example.week6ecommerce.constant;

import lombok.Data;

@Data
public class Queries {
    private String registerCustomer = "INSERT INTO e_commerce.customers(first_name,last_name,phone_number,email,password) VALUES(?,?,?,?,?)";
    private String getAllUsers = "SELECT * FROM e_commerce.customers";
    private String loginCustomer = "SELECT * FROM e_commerce.customers WHERE email = ? AND password = ?";


    // Admin Duties
    private String getAllProducts = "SELECT * FROM e_commerce.products";
    private String updateProductQuantity = "UPDATE e_commerce.products SET quantity = ? WHERE id = ?";
    private String updateProductPrice = "UPDATE e_commerce.products SET price = ? WHERE id = ?";
    private String addNewProduct = "INSERT INTO e_commerce.products(name,category_id,price,quantity,image) VALUES(?,?,?,?,?)";
    private String deleteProduct = "DELETE FROM e_commerce.products WHERE id = ?";

    // Customer Duties
    private String selectProduct = "SELECT * FROM e_commerce.products WHERE id = ?";
    private String selectCartProductQuantity = "SELECT quantity FROM e_commerce.cart WHERE product_id = ? AND customer_id = ?";
    private String updateCartProductQuantity = "UPDATE e_commerce.cart SET quantity = ? WHERE product_id = ? AND customer_id = ?";
    private String addToCart = "INSERT INTO e_commerce.cart(customer_id,product_id,quantity) VALUES(?,?,?)";
    private String getCart = "SELECT p.id,p.image, p.name,p.price,c.quantity\n" +
            "FROM e_commerce.products AS p\n" +
            "INNER JOIN e_commerce.cart  AS c on p.id = c.product_id\n" +
            "WHERE c.customer_id = ?;";
    private String removeFromCart = "DELETE FROM e_commerce.cart WHERE product_id = ? AND customer_id = ?";
    private String deleteCart = "DELETE FROM e_commerce.cart WHERE customer_id = ?";
    private String addToWishList = "INSERT INTO e_commerce.wishlist(customer_id,product_id) VALUES(?,?)";
    private String viewWishList = "SELECT * FROM e_commerce.products AS p\n" +
            "INNER JOIN e_commerce.wishlist AS w on p.id = w.product_id\n" +
            "WHERE w.customer_id = ?";
    private String removeFromWishList = "DELETE FROM e_commerce.wishlist WHERE product_id = ? AND customer_id = ?";
    private String orderProduct = "INSERT INTO e_commerce.orders(customer_id,product_id,order_quantity,order_amount,order_date) VALUES(?,?,?,?,?)";
    private String viewOrder =  "SELECT p.id,p.image, p.name,p.price,o.order_quantity,o.order_date\n" +
            "FROM e_commerce.products AS p\n" +
            "INNER JOIN e_commerce.orders AS o on p.id = o.product_id\n" +
            "WHERE o.customer_id = ?;";
}
