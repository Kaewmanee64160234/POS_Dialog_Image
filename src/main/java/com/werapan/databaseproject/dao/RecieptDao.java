/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.werapan.databaseproject.dao;

import com.werapan.databaseproject.helper.DatabaseHelper;
import com.werapan.databaseproject.model.Reciept;
import com.werapan.databaseproject.model.RecieptDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author werapan
 */
public class RecieptDao implements Dao<Reciept> {



    @Override
    public Reciept get(int id) {
        Reciept receipt = null;
        String sql = "SELECT * FROM  receipt  WHERE receipt_id=?";
        Connection conn = DatabaseHelper.getConnect();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                receipt = Reciept.fromRS(rs);
                RecieptDetailDao rdd = new RecieptDetailDao();
                ArrayList<RecieptDetail> recieptDetails = (ArrayList<RecieptDetail>)
                rdd.getAll("recipt_id="+receipt.getId()," recipt_detail_id");
                receipt.setRecieptDetails(recieptDetails);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return receipt;
    }

    @Override
    public List<Reciept> getAll() {
        ArrayList<Reciept> list = new ArrayList();
        String sql = "SELECT * FROM receipt";
        Connection conn = DatabaseHelper.getConnect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Reciept receipt = Reciept.fromRS(rs);
                list.add(receipt);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Reciept> getAll(String where, String order) {
        ArrayList<Reciept> list = new ArrayList();
        String sql = "SELECT * FROM receipt where " + where + " ORDER BY" + order;
        Connection conn = DatabaseHelper.getConnect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Reciept receipt = Reciept.fromRS(rs);
                list.add(receipt);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public List<Reciept> getAll(String order) {
        ArrayList<Reciept> list = new ArrayList();
        String sql = "SELECT * FROM receipt  ORDER BY" + order;
        Connection conn = DatabaseHelper.getConnect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Reciept receipt = Reciept.fromRS(rs);
                list.add(receipt);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public Reciept save(Reciept obj) {

        String sql = "INSERT INTO receipt ("
                + //
                "                        receipt_date,\r\n"
                + //
                "                        total_price,\r\n"
                + //
                "                        receipt_cash,\r\n"
                + //
                "                        receipt_total_qyt,\r\n"
                + //
                "                        receipt_change,\r\n"
                + //
                "                        user_id,\r\n"
                + //
                "                        customer_id\r\n"
                + //
                "                    )"
                + "VALUES( ?,?,?,?,?,?,?)";
        Connection conn = DatabaseHelper.getConnect();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setTimestamp(1, obj.getCreatedDate());
            stmt.setFloat(2, obj.getTotalPrice());
            stmt.setFloat(3, obj.getCash());
            stmt.setInt(4, obj.getTotalQyt());
            stmt.setFloat(5, obj.getChange());
            stmt.setInt(6, obj.getUserId());
            stmt.setInt(7, obj.getCustomerId());

            // System.out.println(stmt);
            stmt.executeUpdate();
            int id = DatabaseHelper.getInsertedId(stmt);
            obj.setId(id);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return obj;
    }

    @Override
    public Reciept update(Reciept obj) {
        String sql = "UPDATE receipt "
                 + "SET receipt_date = ?,total_price = ?,receipt_cash = ?,receipt_total_qyt = ?,receipt_change = ?,user_id = ?,customer_id = ?"+
                " WHERE receipt_id = ?";
        Connection conn = DatabaseHelper.getConnect();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setTimestamp(1, obj.getCreatedDate());
            stmt.setFloat(2, obj.getTotalPrice());
            stmt.setFloat(3, obj.getCash());
            stmt.setInt(3, obj.getId());
            stmt.setInt(4, obj.getTotalQyt());
            stmt.setFloat(5, obj.getChange());
            stmt.setInt(6, obj.getUserId());
       
            stmt.setInt(7, obj.getCustomerId());

            stmt.setInt(8, obj.getId());

            // System.out.println(stmt);
            int ret = stmt.executeUpdate();
            System.out.println(ret);
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int delete(Reciept obj) {
        String sql = "DELETE FROM receipt WHERE receipt_id=?";
        Connection conn = DatabaseHelper.getConnect();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            int ret = stmt.executeUpdate();
            return ret;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }

}
