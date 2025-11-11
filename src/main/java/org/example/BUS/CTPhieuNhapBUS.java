package org.example.BUS;

import org.example.DAO.CTPhieuNhapDAO;
import org.example.DAO.SanPhamDAO;
import org.example.DTO.CTPhieuNhap;

import java.util.ArrayList;


public class CTPhieuNhapBUS {

    private ArrayList<CTPhieuNhap> listPhieuNhap = null;
    private CTPhieuNhapDAO ctPhieuNhapDAO = new CTPhieuNhapDAO();
    private PhieuNhapBUS pnBUS = new PhieuNhapBUS();
    private SanPhamDAO spDao=new SanPhamDAO();
    public CTPhieuNhapBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listPhieuNhap = ctPhieuNhapDAO.getListCTPhieuNhap();
    }

    public ArrayList<CTPhieuNhap> getListPhieuNhap() {
        if (listPhieuNhap == null) {
            docDanhSach();
        }
        return listPhieuNhap;
    }
    
    public ArrayList<CTPhieuNhap> getListPhieuNhap(String maPN) {
        ArrayList<CTPhieuNhap> dsct = new ArrayList<>();
        int ma = Integer.parseInt(maPN);
        
        for(CTPhieuNhap ct: listPhieuNhap) {
            if(ct.getMaPN() == ma) {
                dsct.add(ct);
            }
        }
        
        return dsct;
    }

    public boolean luuCTPhieuNhap(String maSP,String soluong,String dongia,String thanhTien) {
    	int ma = pnBUS.getLastID();

        CTPhieuNhap ctpn = new CTPhieuNhap();
        int giaNhap=Integer.parseInt(dongia);
        int giaBan=Integer.parseInt(spDao.getGiaSP(Integer.parseInt(maSP))+"");
        ctpn.setMaPN(ma);
        ctpn.setMaSP(Integer.parseInt(maSP));
        ctpn.setDonGia(Integer.parseInt(dongia));
        ctpn.setSoLuong(Integer.parseInt(soluong));
        ctpn.setThanhTien(Integer.parseInt(thanhTien));
    	if(((int)giaNhap*1.1)>giaBan){
            return ctPhieuNhapDAO.addCTPhieuNhap(ctpn);
        }
        return ctPhieuNhapDAO.addCTPhieuNhap_old(ctpn);
        
    }
}
