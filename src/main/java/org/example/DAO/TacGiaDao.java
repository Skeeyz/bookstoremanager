package org.example.DAO;

import org.example.DTO.TacGia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class TacGiaDao {
	public ArrayList<TacGia> getListTacGia() {
        try {
            ArrayList<TacGia> dstg = new ArrayList<>();
            String sql = "SELECT * FROM TacGia";
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TacGia tg=new TacGia();
                tg.setMaTG(rs.getInt(1));
                tg.setTenTG(rs.getString(2));
                dstg.add(tg);
            }
            rs.close();
            stmt.close();
            return dstg;
        } catch (SQLException ex) {
            return null;
        }
    }

    public TacGia getTacGia(int maTG) {
        TacGia tg = null;
        try {
            String sql = "SELECT * FROM TacGia WHERE maTG=" + maTG;
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tg = new TacGia();
                tg.setMaTG(rs.getInt(1));
                tg.setTenTG(rs.getString(2));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            return null;
        }
        return tg;
    }

    public boolean themTG(String tg) {
        boolean result = false;
        try {
            String sql = "INSERT INTO TacGia VALUES( ?)";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setString(2, tg);
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean updateTG(TacGia tg) {
        boolean result = false;
        try {
            String sql = "UPDATE TacGia SET tenTG=? WHERE maTG=?";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setString(1, tg.getTenTG());
            prep.setInt(2, tg.getMaTG());
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }
    
    public boolean xoaTG(int maTG) {
        boolean result = false;
        try {
            String sql = "DELETE FROM TacGia WHERE maTG=?";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setInt(1, maTG);
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }

}
