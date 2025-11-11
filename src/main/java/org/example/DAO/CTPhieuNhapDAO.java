package org.example.DAO;

import org.example.DTO.CTPhieuNhap;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class CTPhieuNhapDAO {

    public ArrayList<CTPhieuNhap> getListCTPhieuNhap() {
        ArrayList<CTPhieuNhap> dsctpn = new ArrayList<>();
        try {
            String sql = "SELECT * FROM CTPhieuNhap";
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CTPhieuNhap ctpn = new CTPhieuNhap();
                ctpn.setMaPN(rs.getInt(1));
                ctpn.setMaSP(rs.getInt(2));
                ctpn.setSoLuong(rs.getInt(3));
                ctpn.setDonGia(rs.getInt(4));
                ctpn.setThanhTien(rs.getInt(5));
                dsctpn.add(ctpn);
            }
        } catch (SQLException ex) {
            return null;
        }
        return dsctpn;
    }

    public ArrayList<CTPhieuNhap> getListCTPhieuNhapTheoMaPN(int maPN) {
        ArrayList<CTPhieuNhap> dsctpn = new ArrayList<>();
        try {
            String sql = "SELECT * FROM CTPhieuNhap WHERE MaPN=" + maPN;
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CTPhieuNhap ctpn = new CTPhieuNhap();
                ctpn.setMaPN(rs.getInt(1));
                ctpn.setMaSP(rs.getInt(2));
                ctpn.setSoLuong(rs.getInt(3));
                ctpn.setDonGia(rs.getInt(4));
                ctpn.setThanhTien(rs.getInt(5));
                dsctpn.add(ctpn);
            }
        } catch (SQLException ex) {
            return null;
        }
        return dsctpn;
    }

    public ArrayList<CTPhieuNhap> getListCTPhieuNhapTheoMaSP(int maSP) {
        ArrayList<CTPhieuNhap> dsctpn = new ArrayList<>();
        try {
            String sql = "SELECT * FROM CTPhieuNhap WHERE MaSP=" + maSP;
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CTPhieuNhap ctpn = new CTPhieuNhap();
                ctpn.setMaPN(rs.getInt(1));
                ctpn.setMaSP(rs.getInt(2));
                ctpn.setSoLuong(rs.getInt(3));
                ctpn.setDonGia(rs.getInt(4));
                ctpn.setThanhTien(rs.getInt(5));
                dsctpn.add(ctpn);
            }
        } catch (SQLException ex) {
            return null;
        }
        return dsctpn;
    }

    public boolean addCTPhieuNhap(CTPhieuNhap ctpn) {
        boolean result = false;
        try {
            // Phải Update số lượng SP trong kho
            int giaban=(int)(ctpn.getDonGia()*1.1);//gia ban = 110% gia nhap
            String sqlUpdateSP = "UPDATE SanPham SET SoLuong = SoLuong + ?, donGia = ?  WHERE MaSP = ?";
            PreparedStatement pre = MyConnect.conn.prepareCall(sqlUpdateSP);
            pre.setInt(1, ctpn.getSoLuong());
            pre.setInt(2, giaban);
            pre.setInt(3, ctpn.getMaSP());
            pre.executeUpdate();

            String sql = "INSERT INTO CTPhieuNhap VALUES(?,?,?,?,?)";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setInt(1, ctpn.getMaPN());
            prep.setInt(2, ctpn.getMaSP());
            prep.setInt(3, ctpn.getSoLuong());
            prep.setInt(4, ctpn.getDonGia());
            prep.setInt(5, ctpn.getThanhTien());
            result = prep.executeUpdate() > 0;
        } catch (SQLException e) {
        	System.out.println(e);
            return false;
        }
        return result;
    }
    
    public boolean addCTPhieuNhap_old(CTPhieuNhap ctpn) {
        boolean result = false;
        try {
            // Phải Update số lượng SP trong kho
            String sqlUpdateSP = "UPDATE SanPham SET SoLuong = SoLuong + ?  WHERE MaSP = ?";
            PreparedStatement pre = MyConnect.conn.prepareCall(sqlUpdateSP);
            pre.setInt(1, ctpn.getSoLuong());
            pre.setInt(2, ctpn.getMaSP());
            pre.executeUpdate();

            String sql = "INSERT INTO CTPhieuNhap VALUES(?,?,?,?,?)";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setInt(1, ctpn.getMaPN());
            prep.setInt(2, ctpn.getMaSP());
            prep.setInt(3, ctpn.getSoLuong());
            prep.setInt(4, ctpn.getDonGia());
            prep.setInt(5, ctpn.getThanhTien());
            result = prep.executeUpdate() > 0;
        } catch (SQLException e) {
        	System.out.println(e);
            return false;
        }
        return result;
    }

    public boolean deleteCTPhieuNhap(int maPN) {//Không được xóa phiếu nhập
        boolean result = false;
        try {
            String sql = "DELETE FROM CTPhieuNhap WHERE MaPN=" + maPN;
            Statement stmt = MyConnect.conn.createStatement();
            result = stmt.executeUpdate(sql) > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean deleteCTPhieuNhap(int maPN, int maSP) {
        boolean result = false;
        try {
            String sql = "DELETE FROM CTPhieuNhap WHERE MaPN=" + maPN + " AND MaSP=" + maSP;
            Statement stmt = MyConnect.conn.createStatement();
            result = stmt.executeUpdate(sql) > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean updateCTPhieuNhap(int maPN, CTPhieuNhap ctpn) {
        boolean result = false;
        try {
            String sql = "UPDATE CTPhieuNhap SET MaPN=?, MaSP=?, SoLuong=?, DonGia=?, ThanhTien=? WHERE MaPN=?";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setInt(1, ctpn.getMaPN());
            prep.setInt(2, ctpn.getMaSP());
            prep.setInt(3, ctpn.getSoLuong());
            prep.setInt(4, ctpn.getDonGia());
            prep.setInt(5, ctpn.getThanhTien());
            prep.setInt(6, maPN);
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
}
