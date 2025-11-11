package org.example.BUS;

import MyCustom.MyDialog;
import org.example.DAO.PhieuNhapDAO;
import org.example.DTO.PhieuNhap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PhieuNhapBUS {

    private PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAO();
    private ArrayList<PhieuNhap> listPhieuNhap = null;

    public PhieuNhapBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listPhieuNhap = phieuNhapDAO.getListPhieuNhap();
    }

    public ArrayList<PhieuNhap> getListPhieuNhap() {
        if (listPhieuNhap == null) {
            docDanhSach();
        }
        return listPhieuNhap;
    }

    public boolean themPhieuNhap(String nhaCungCap, String nhanVien, int tongTien) {
    	int maNCC = Integer.parseInt(nhaCungCap.trim());
    	int maNV =  Integer.parseInt(nhanVien);
        PhieuNhap pn = new PhieuNhap();
        pn.setMaNCC(maNCC);
        pn.setMaNV(maNV);
        pn.setTongTien(tongTien);

        return phieuNhapDAO.themPhieuNhap(pn);
    }

    public int getLastID() {
        return phieuNhapDAO.getLastID();
    }

    public PhieuNhap timPhieuNhap(String maPN) {
        int ma = Integer.parseInt(maPN);
        for (PhieuNhap pn : listPhieuNhap) {
            if (pn.getMaPN() == ma) {
                return pn;
            }
        }
        return null;
    }

    public ArrayList<PhieuNhap> getListPhieuNhapTheoGia(String giaThap, String giaCao) {
        try {
            int min = Integer.parseInt(giaThap);
            int max = Integer.parseInt(giaCao);
            if (max < min) {
                new MyDialog("Hãy nhập khoảng giá phù hợp!", MyDialog.ERROR_DIALOG);
                return null;
            }
            ArrayList<PhieuNhap> result = new ArrayList<>();
            for (PhieuNhap pn : listPhieuNhap) {
                if (pn.getTongTien() <= max && pn.getTongTien() >= min) {
                    result.add(pn);
                }
            }
            return result;
        } catch (Exception e) {
        	System.out.println(e);
        	 new MyDialog("Hãy nhập khoảng giá phù hợp!", MyDialog.ERROR_DIALOG);
        }
        return null;
    }

    public ArrayList<PhieuNhap> getListPhieuNhapTheoNgay(Date tuNgay, Date denNgay) {
            if (denNgay.before(tuNgay)) {
                new MyDialog("Vui lòng nhập khoảng ngày phù hợp, ngày bắt đầu <= ngày kết thúc!", MyDialog.ERROR_DIALOG);
                return null;
            }
            docDanhSach();
        try {
            ArrayList<PhieuNhap> result = new ArrayList<>();
            for (PhieuNhap pn : listPhieuNhap) {
                if (pn.getNgayLap().after(tuNgay) && pn.getNgayLap().before(denNgay)) {
                    result.add(pn);
                }
            }
            return result;
        } catch (Exception e) {
            new MyDialog("Hãy nhập ngày hợp lệ (dd/MM/yyyy)!", MyDialog.ERROR_DIALOG);
        }
        return null;
    }

}
