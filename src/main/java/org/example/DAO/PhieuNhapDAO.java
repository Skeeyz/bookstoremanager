package org.example.DAO;

import org.example.DTO.PhieuNhap;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class PhieuNhapDAO {

    public ArrayList<PhieuNhap> getListPhieuNhap() {
        ArrayList<PhieuNhap> dspn = new ArrayList<>();
        try {
            String sql = "SELECT * FROM PhieuNhap";
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap();
                pn.setMaPN(rs.getInt(1));
                pn.setMaNCC(rs.getInt(2));
                pn.setMaNV(rs.getInt(3));
                pn.setNgayLap(rs.getDate(4));
                pn.setTongTien(rs.getInt(5));
                dspn.add(pn);
            }
        } catch (SQLException ex) {
            return null;
        }
        return dspn;
    }

    public boolean themPhieuNhap1(PhieuNhap pn) {
        boolean result = false;
        try {
            String sql = "INSERT INTO PhieuNhap(maNCC, maNV, ngayNhap, tongTien) "
                    + "VALUES(NEXT VALUE FOR phieunhap_sequence,?,?,?,?)";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setInt(1, pn.getMaNCC());
            prep.setInt(2, pn.getMaNV());
            prep.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            prep.setInt(4, pn.getTongTien());
            result = prep.executeUpdate() > 0;
        } catch (SQLException e) {
        	System.out.println(e);
            return false;
        }
        return result;
    }
    public boolean themPhieuNhap(PhieuNhap pn) {
        boolean result = false;
        try {
            String sql = "INSERT INTO PhieuNhap(maNCC, maNV, ngayNhap, tongTien) "
                    + "VALUES(?,?,?,?)";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setInt(1, pn.getMaNCC());
            prep.setInt(2, pn.getMaNV());
            prep.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            prep.setInt(4, pn.getTongTien());
            result = prep.executeUpdate() > 0;
        } catch (SQLException e) {
        	System.out.println(e);
            return false;
        }
        return result;
    }

    public PhieuNhap getPhieuNhap(int maPN) {
        PhieuNhap pn = null;
        try {
            String sql = "SELECT * FROM PhieuNhap WHERE MaPN=" + maPN;
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pn = new PhieuNhap();
                pn.setMaPN(rs.getInt(1));
                pn.setMaNCC(rs.getInt(2));
                pn.setMaNV(rs.getInt(3));
                pn.setNgayLap(rs.getDate(4));
                pn.setTongTien(rs.getInt(5));
            }
        } catch (SQLException ex) {
            return null;
        }
        return pn;
    }

    public boolean deletePhieuNhap(int maPN) {
        boolean result = false;
        try {
            String sql = "DELETE FROM PhieuNhap WHERE MaPN=" + maPN;
            Statement stmt = MyConnect.conn.createStatement();
            result = stmt.executeUpdate(sql) > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean updatePhieuNhap(int maPN, PhieuNhap pn) {
        boolean result = false;
        try {
            String sql = "UPDATE PhieuNhap SET MaPN=?, MaNCC=?, MaNV=?, NgayLap=?, TongTien=? "
                    + "WHERE MaPN=" + maPN;
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setInt(1, pn.getMaPN());
            prep.setInt(2, pn.getMaNCC());
            prep.setInt(3, pn.getMaNV());
            prep.setDate(4, new java.sql.Date(pn.getNgayLap().getTime()));
            prep.setInt(5, pn.getTongTien());
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
    
    public boolean updatePhieuNhap1(int maPN, int tongTien) {
        boolean result = false;
        try {
            String sql = "UPDATE PhieuNhap SET TongTien=? "
                    + "WHERE MaPN=?";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setInt(2, maPN);
            prep.setInt(1, tongTien);
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public int getLastID() {
        try {
            String sql = "SELECT MAX(maPN) FROM PhieuNhap";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
