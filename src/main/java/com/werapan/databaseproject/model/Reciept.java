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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author werapan
 */
public class Reciept {

    private int id;
    private Timestamp createdDate;
    private float totalPrice;
    private float cash;
    private int totalQyt;
    private float change;
    private int userId;
    private int customerId;
    private Customer customer;
    private User user;
    private ArrayList<RecieptDetail> recieptDetails = new ArrayList();

    public Reciept(int receipt_id, Timestamp createdDate, float cash, float totalPrice, int totalQyt,
            float change, int userId, int customerId) {
        this.id = receipt_id;
        this.createdDate = createdDate;
        this.totalPrice = totalPrice;
        this.cash = cash;
        this.totalQyt = totalQyt;
        this.change = change;
        this.userId = userId;
        this.customerId = customerId;
    }

    public Reciept(float cash, float change,
            int userId, int customerId) {
        this.id = -1;

        this.createdDate = null;
        this.totalPrice = 0;
        this.cash = cash;
        this.totalQyt = 0;
        this.change = change;
        this.userId = userId;
        this.customerId = customerId;
    }

    public Reciept() {
        this.customerId = -1;
        this.createdDate = null;
        this.totalPrice = 0;
        this.cash = 0;
        this.totalQyt = 0;
        this.change = 0;
        this.userId = 0;
        this.customerId = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    public int getTotalQyt() {
        return totalQyt;
    }

    public void setTotalQyt(int totalQyt) {
        this.totalQyt = totalQyt;
    }

    public float getChange() {
        return change;
    }

    public void setChange(float change) {
        this.change = change;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.customerId = customer.getId();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.userId = user.getId();
    }

    @Override
    public String toString() {
        return "Reciept{" + "id=" + id + ", createdDate=" + createdDate + ", totalPrice=" + totalPrice + ", cash=" + cash + ", totalQyt=" + totalQyt + ", change=" + change + ", userId=" + userId + ", customerId=" + customerId + ", customer=" + customer + ", user=" + user + ", recieptDetails=" + recieptDetails + '}';
    }

    public ArrayList<RecieptDetail> getRecieptDetails() {
        return recieptDetails;
    }

    public void setRecieptDetails(ArrayList recieptDetails) {
        this.recieptDetails = recieptDetails;
    }

    public void addReceiptDetail(Product product,int qty) {
         RecieptDetail rd = new RecieptDetail(product.getId(), product.getName(), product.getPrice(), 1,
                        product.getPrice() * 1, -1);
        recieptDetails.add(rd);
        calculateTotal();
    }

    public void removeReceiptDetail(RecieptDetail receiptDateil) {
        recieptDetails.remove(receiptDateil);
        calculateTotal();
    }

    private void calculateTotal() {
        int total_qty = 0;
        float total = 0.0f;
        for (RecieptDetail rd : recieptDetails) {

            total += rd.getTotal_price();
            total_qty += rd.getQty();
        }
        this.totalPrice = total;
        this.totalQyt = total_qty;
    }

    public static Reciept fromRS(ResultSet rs) {
        Reciept reciept = new Reciept();
        try {
            reciept.setId(rs.getInt("receipt_id"));
            reciept.setCreatedDate(rs.getTimestamp("receipt_date"));
            reciept.setTotalPrice(rs.getFloat("total_price"));
            reciept.setCash(rs.getFloat("receipt_cash"));
            reciept.setTotalQyt(rs.getInt("receipt_total_qyt"));
            reciept.setChange(rs.getFloat("receipt_change"));
            reciept.setUserId(rs.getInt("user_id"));
            reciept.setCustomerId(rs.getInt("customer_id"));
            CustomerDao cusDao = new CustomerDao();
            UserDao ud = new UserDao();
            Customer cus = cusDao.get(reciept.getCustomerId());
            User user = ud.get(reciept.getUserId());
            reciept.setCustomerId(0);
            reciept.setUser(user);
            reciept.setCustomer(cus);

        } catch (SQLException ex) {
            Logger.getLogger(Reciept.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return reciept;
    }
}
