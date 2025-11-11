package org.example.DAO;

import org.example.DTO.SanPham;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class SanPhamDAO {
    
    public ArrayList<SanPham> getListSanPham() {
        try {
            String sql = "SELECT * FROM SanPham";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setSoLuong(rs.getInt(3));              
                sp.setDonGia(rs.getInt(4));
                sp.setMaLoai(rs.getInt(5));
                sp.setHinhAnh(rs.getString(6));
                sp.setMaTG(rs.getInt(7));
                sp.setMoTa(rs.getString(8));

                dssp.add(sp);
            }
            return dssp;
        } catch (SQLException e) {
        }

        return null;
    }

    public SanPham getSanPham(int ma) {
        try {
            String sql = "SELECT *FROM SanPham WHERE MaSP=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, ma);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                SanPham sp = new SanPham();

                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setSoLuong(rs.getInt(3));              
                sp.setDonGia(rs.getInt(4));
                sp.setMaLoai(rs.getInt(5));
                sp.setHinhAnh(rs.getString(6));
                sp.setMaTG(rs.getInt(7));
                sp.setMoTa(rs.getString(8));

                return sp;
            }
        } catch (SQLException e) {
        }

        return null;
    }

    public ArrayList<SanPham> getSanPhamTheoLoai(int maLoai) {
        try {
            String sql = "SELECT * FROM SanPham WHERE MaLoai=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maLoai);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while (rs.next()) {
                SanPham sp = new SanPham();

                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setMaLoai(rs.getInt(3));
                sp.setSoLuong(rs.getInt(4));              
                sp.setDonGia(rs.getInt(5));
                sp.setHinhAnh(rs.getString(6));
                sp.setMaTG(rs.getInt(7));
                sp.setMoTa(rs.getString(8));
                dssp.add(sp);
            }
            return dssp;
        } catch (SQLException e) {
        }

        return null;
    }
    public ArrayList<SanPham> getSanPhamTheoTG(int maTG) {
        try {
            String sql = "SELECT * FROM SanPham WHERE MaTG=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maTG);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while (rs.next()) {
                SanPham sp = new SanPham();

                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setMaLoai(rs.getInt(3));
                sp.setSoLuong(rs.getInt(4));              
                sp.setDonGia(rs.getInt(5));
                sp.setHinhAnh(rs.getString(6));
                sp.setMaTG(rs.getInt(7));
                sp.setMoTa(rs.getString(8));
                dssp.add(sp);
            }
            return dssp;
        } catch (SQLException e) {
        }

        return null;
    }

    public String getAnh(int ma) {
        try {
            String sql = "SELECT HinhAnh FROM SanPham WHERE MaSP=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, ma);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getString("HinhAnh");
            }
        } catch (SQLException e) {
        }
        return "";
    }

    public void capNhatSoLuongSP(int ma, int soLuongMat) {
        SanPham sp = getSanPham(ma);       
        int soLuong = sp.getSoLuong();       
        sp.setSoLuong(soLuong + soLuongMat);
        try {
            String sql = "UPDATE SanPham SET SoLuong= ? WHERE MaSP=" + ma;
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, sp.getSoLuong());
            pre.executeUpdate();
        } catch (SQLException e) {
        }

    }

    public boolean themSanPham(SanPham sp) {
        try {
            String sql = "INSERT INTO SanPham(tenSP,maLoai,soLuong,donGia,hinhAnh,maTG,moTa) VALUES ( ?, ?, ?, 0, ?, ?, ?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, sp.getTenSP());
            pre.setInt(2, sp.getMaLoai());
            pre.setInt(3,0);
            pre.setString(4, sp.getHinhAnh());
            pre.setInt(5, sp.getMaTG());
            pre.setString(6, sp.getMoTa());
            pre.execute();            
            return true;
        } catch (SQLException e) {
        	System.out.println(e);       	
        }
        return false;
    }
    
    public boolean themSanPham_PN(SanPham sp) {
        try {
            String sql = "INSERT INTO SanPham(MaSP,tenSP,maLoai,soLuong,donGia,hinhAnh,maTG,moTa) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,sp.getMaSP());
            pre.setString(2, sp.getTenSP());
            pre.setInt(3, sp.getMaLoai());
            pre.setInt(4, sp.getSoLuong());
            pre.setInt(5, sp.getDonGia());
            pre.setString(6, sp.getHinhAnh());
            pre.setInt(7, sp.getMaTG());
            pre.setString(8, sp.getMoTa());
            pre.execute();            
            return true;
        } catch (SQLException e) {
        	System.out.println(e);       	
        }
        return false;
    }

//    public boolean nhapSanPhamTuExcel(SanPham sp) {
//        try {
//            String sql = "DELETE * FROM sanpham; " +
//                    "INSERT INTO SanPham(TenSP, MaLoai, SoLuong, DonViTinh, HinhAnh, DonGia) "
//                    + "VALUES (?, ?, ?, ?, ?, ?)";
//            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
//            pre.setInt(1, sp.getMaSP());
//            pre.setString(2, sp.getTenSP());
//            pre.setInt(3, sp.getMaLoai());
//            pre.setInt(4, sp.getSoLuong());
//            pre.setInt(5, sp.getDonGia());
//            pre.setString(6, sp.getHinhAnh());
//
//            pre.execute();
//            return true;
//        } catch (SQLException e) {
//        }
//        return false;
//    }

    public boolean xoaSanPham(int maSP) {
        try {
            String sql = "DELETE FROM SanPham WHERE MaSP=" + maSP;
            Statement st = MyConnect.conn.createStatement();
            st.execute(sql);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean suaSanPham(SanPham sp) {
        try {
            String sql = "UPDATE SanPham SET "
                    + "hinhAnh= ?, moTa= ? "
                    + "WHERE maSP=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);            
            pre.setString(1, sp.getHinhAnh());
            pre.setString(2, sp.getMoTa());
            pre.setInt(3, sp.getMaSP());

            pre.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean suaSanPham_PN(SanPham sp) {
        try {
            String sql = "UPDATE SanPham SET "
                    + "tenSP= ?, "
                    + "maLoai= ?, soLuong= ?, donGia= ?, hinhAnh= ?, maTG= ?, moTa= ? "
                    + "WHERE maSP=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);            
            pre.setString(1, sp.getTenSP());
            pre.setInt(2, sp.getMaLoai());
            pre.setInt(3, sp.getSoLuong());
            pre.setInt(4, sp.getDonGia());
            pre.setString(5, sp.getHinhAnh());
            pre.setInt(6,sp.getMaTG());
            pre.setString(7, sp.getMoTa());
            pre.setInt(8, sp.getMaSP());

            pre.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public int getGiaSP(int maSP){
        try{
            String sql="SELECT donGia FROM SanPham WHERE maSP=?";
            PreparedStatement pre=MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maSP);
            ResultSet rs=pre.executeQuery();
            if(rs.next()){
                return rs.getInt("donGia");
            }
        }catch(SQLException e){
            
        }
        return 0;
    }
    public static void main(String[]args){
        new SanPhamDAO().getListSanPham();
    }
}
