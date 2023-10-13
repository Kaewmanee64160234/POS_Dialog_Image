/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.werapan.databaseproject.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author werapan
 */
public class Product {
    private int id;
    private String    name;
    private float    price;
    private String    size;
    private String    sweet_level;
    private String    type;
    private int    category_id;

    public Product(int id, String name, float price, String size, String sweet_level, String type, int category_id) {
        this.id = id;
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.sweet_level = sweet_level;
        this.type = type;
        this.category_id = category_id;
    }

    public Product() {
          this.id = -1;
        this.id = -1;
        this.name = "";
        this.price = 0;
        this.size = "";
        this.sweet_level = "";
        this.type = "";
        this.category_id = -1;
    }
    
    

    public Product(String name, float price, String size, String sweet_level, String type, int category_id) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.sweet_level = sweet_level;
        this.type = type;
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSweet_level() {
        return sweet_level;
    }

    public void setSweet_level(String sweet_level) {
        this.sweet_level = sweet_level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", size=" + size + ", sweet_level=" + sweet_level + ", type=" + type + ", category_id=" + category_id + '}';
    }

    
    public static Product fromRS(ResultSet rs) {
        Product product = new Product();
        try {
            product.setId(rs.getInt("product_id"));
            product.setName(rs.getString("product_name"));
            product.setPrice(rs.getFloat("product_price"));
            product.setSize(rs.getString("product_size"));
            product.setSweet_level(rs.getString("product_sweet_level"));
            product.setType(rs.getString("product_type"));
            product.setCategory_id(rs.getInt("category_id"));
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return product;
    }
}
