package org.example.DAO;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.example.DTO.KhachHang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class KhachHangDAO {

    public ArrayList<KhachHang> getListKhachHang() {
        try {
            String sql = "SELECT * FROM KhachHang WHERE TinhTrang=1";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<KhachHang> dskh = new ArrayList<>();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getInt(1));
                kh.setHoKH(rs.getString(2));
                kh.setTenKH(rs.getString(3));
                kh.setGioitinh(rs.getString(4));
                kh.setTongchitieu(rs.getInt(5));
                dskh.add(kh);
            }
            return dskh;
        } catch (SQLException ex) {
        }
        return null;
    }

    public KhachHang getKhachHang(int maKH) {
        KhachHang kh = null;
        try {
            String sql = "SELECT * FROM KhachHang WHERE MaKH=? AND TinhTrang=1";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setInt(1, maKH);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                kh = new KhachHang();
                kh.setMaKH(rs.getInt(1));
                kh.setHoKH(rs.getString(2));
                kh.setTenKH(rs.getString(3));
                kh.setGioitinh(rs.getString(4));
                kh.setTongchitieu(rs.getInt(5));
            }
        } catch (SQLException ex) {
            return null;
        }
        return kh;
    }

    public boolean addKhachHang(KhachHang kh) {
        boolean result = false;
        try {
            String sql = "INSERT INTO KhachHang(Ho,Ten,gioiTinh,tongChiTieu,tinhTrang) VALUES(?,?,?,0,1)";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setString(1, kh.getHoKH());
            prep.setString(2, kh.getTenKH());
            prep.setString(3, kh.getGioitinh());         
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean deleteKhachHang(int maKH) {
        boolean result = false;
        try {
            String sql = "UPDATE KhachHang SET TinhTrang=0 WHERE MaKH=?";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setInt(1, maKH);
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean updateKhachHang( KhachHang kh) {
    	boolean result = false;
        try {
            String sql = "UPDATE KhachHang SET Ho=?, Ten=?, GioiTinh=?, TongChiTieu=? WHERE MaKH=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, kh.getHoKH());
            pre.setString(2, kh.getTenKH());
            pre.setString(3, kh.getGioitinh());
            pre.setInt(4, kh.getTongchitieu());
            pre.setInt(5, kh.getMaKH());
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean updateTongChiTieu(int maKH, int tongChiTieu) {
        boolean result = false;
        try {
            String sql = "UPDATE KhachHang SET TongChiTieu=" + tongChiTieu + " WHERE MaKH=" + maKH;
            Statement stmt = MyConnect.conn.createStatement();
            result = stmt.executeUpdate(sql) > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
    public boolean nhapExcel(KhachHang kh) {
        try {
            String sql = "DELETE * FROM KhachHang; " +
                    "INSERT INTO KhachHang(maKH, Ho, Ten, gioiTinh, tongchitieu) " +
                    "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, kh.getMaKH());
            pre.setString(2, kh.getHoKH());
            pre.setString(3, kh.getTenKH());
            pre.setString(4, kh.getGioitinh());
            pre.setInt(5, kh.getTongchitieu());
            return true;
        } catch (SQLException ex) {
        }
        return false;
    }
}
