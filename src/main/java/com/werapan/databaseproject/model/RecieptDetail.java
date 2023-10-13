/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.werapan.databaseproject.model;

import com.werapan.databaseproject.dao.CustomerDao;
import com.werapan.databaseproject.dao.UserDao;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author werapan
 */
public class RecieptDetail {

    private int id;
    private int product_id;
    private String product_name;
    private float product_price;
    private int qty;
    private float total_price;
    private int recipt_id;

    public RecieptDetail(int id, int product_id, String product_name, float product_price, int qty, float total_price, int recipt_id) {
        this.id = id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.qty = qty;
        this.total_price = total_price;
        this.recipt_id = recipt_id;
    }

    public RecieptDetail(int product_id, String product_name, float product_price, int qty, float total_price, int recipt_id) {
        this.id = -1;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.qty = qty;
        this.total_price = total_price;
        this.recipt_id = recipt_id;
    }

    public RecieptDetail() {
        this.id = -1;
        this.product_id = -1;
        this.product_name = "";
        this.product_price = 0;
        this.qty = 0;
        this.total_price = 0;
        this.recipt_id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public float getProduct_price() {
        return product_price;
    }

    public void setProduct_price(float product_price) {
        this.product_price = product_price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public int getRecipt_id() {
        return recipt_id;
    }

    public void setRecipt_id(int recipt_id) {
        this.recipt_id = recipt_id;
    }

    @Override
    public String toString() {
        return "RecieptDetail{" + "id=" + id + ", product_id=" + product_id + ", product_name=" + product_name + ", product_price=" + product_price + ", qty=" + qty + ", total_price=" + total_price + ", recipt_id=" + recipt_id + '}';
    }

    public static RecieptDetail fromRS(ResultSet rs) {
        RecieptDetail reciept = new RecieptDetail();
        try {
            reciept.setId(rs.getInt("recipt_detail_id"));
            reciept.setProduct_id(rs.getInt("product_id"));
            reciept.setProduct_name(rs.getString("product_name"));
            reciept.setProduct_price(rs.getFloat("product_price"));
            reciept.setQty(rs.getInt("qty"));
            reciept.setTotal_price(rs.getFloat("total_price"));
            reciept.setRecipt_id(rs.getInt("recipt_id"));

        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return reciept;
    }

}
