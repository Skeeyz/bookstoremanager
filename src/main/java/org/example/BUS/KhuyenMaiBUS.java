package org.example.BUS;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import javax.swing.JOptionPane;

import MyCustom.MyDialog;
import MyCustom.XulyInput;
import org.example.DAO.KhuyenMaiDAO;
import org.example.DTO.KhuyenMai;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhuyenMaiBUS {
    private ArrayList<KhuyenMai> listKhuyenMai = null;
    private KhuyenMaiDAO KMDAO = new KhuyenMaiDAO();
    private XulyInput xulyInput= new XulyInput();
    private LocalDate now=LocalDate.now();
    private Date daynow;
    public KhuyenMaiBUS() {
        docDanhSach();
        try {
            daynow=new SimpleDateFormat("yyyy-MM-dd").parse(now.toString());
        } catch (ParseException ex) {
            Logger.getLogger(KhuyenMaiBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void docDanhSach() {
        this.listKhuyenMai = KMDAO.getDanhSachKhuyenMai();
        
    }

    public ArrayList<KhuyenMai> getDanhSachKhuyenMai() {
        if (this.listKhuyenMai == null)
            docDanhSach();
        return this.listKhuyenMai;
    }

    public boolean themKhuyenMai(String ten, Date ngayBD, Date ngayKT, String phanTramGiam, String dieuKien) {
        if (ten.equals("")) {
            new MyDialog("Không được để trống tên chương trình khuyến mãi!", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if (ngayBD.compareTo(daynow)<0|| ngayBD.compareTo(daynow) == 0) {
            new MyDialog("Ngày bắt đầu phải lớn hơn ngày hiện tại", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            new MyDialog("Ngày kết thúc phải lớn hơn ngày bắt đầu!", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if(phanTramGiam.equals("")|| !xulyInput.dataso(phanTramGiam.trim())){
            new MyDialog("Phần trăm giảm không được bỏ trống và phải nhập kí tự số!", MyDialog.ERROR_DIALOG);
            return false;
        }
        int temp1=Integer.parseInt(phanTramGiam.trim());
        if(temp1<1 || temp1>100){
            new MyDialog("Phần trăm giảm giá phải thỏa: 0 < x <101 ", MyDialog.ERROR_DIALOG);
            return false;
        }
        if(dieuKien.equals("")|| !xulyInput.dataso(dieuKien.trim())){
            new MyDialog("Điều kiện không được bỏ trống và phải nhập kí tự số!!", MyDialog.ERROR_DIALOG);
            return false;
        }
        int temp2=Integer.parseInt(dieuKien.trim());
        if(temp2<20000){
            new MyDialog("Điều kiện hóa đơn phải thỏa: x >= 20,000 ", MyDialog.ERROR_DIALOG);
            return false;
        }
        MyDialog dlg = new MyDialog("Bạn chắc chắn muốn thêm chương trình khuyến mãi này?", MyDialog.WARNING_DIALOG);
        if(dlg.getAction() == MyDialog.CANCEL_OPTION)
            return false;
        boolean flag = false;
        try {           
            KhuyenMai km = new KhuyenMai();
            km.setTenKM(ten);
            km.setNgayBD(ngayBD);
            km.setNgayKT(ngayKT);
            km.setPhanTramGiam(temp1);
            km.setDieuKien(temp2);
            flag = KMDAO.themKhuyenMai(km);
        } catch (Exception e) { 
            new MyDialog("Thêm mới thất bại!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (flag) {
            new MyDialog("Thêm mới thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Thêm mới thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
//    public boolean themKhuyenMai1(int ma, String ten, Date ngayBD, Date ngayKT) {
//        if (ten.equals("")) {
//            new MyDialog("Hãy nhập tên chương trình khuyến mãi!", MyDialog.ERROR_DIALOG);
//            return false;
//        }
//        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
//            new MyDialog("Ngày kết thúc không hợp lệ!", MyDialog.ERROR_DIALOG);
//            return false;
//        }
//        boolean flag = false;
//        try {           
//            KhuyenMai km = new KhuyenMai();
//            km.setMaKM(ma);
//            km.setTenKM(ten);
//            km.setNgayBD(ngayBD);
//            km.setNgayKT(ngayKT);
//
//            flag = KMDAO.themKhuyenMai1(km);
//        } catch (Exception e) {            
//            return false;
//        }
//        if (flag) {
//            new MyDialog("Thêm mới thành công!", MyDialog.SUCCESS_DIALOG);
//        } else {
//            new MyDialog("Thêm mới thất bại!", MyDialog.ERROR_DIALOG);
//        }
//        return flag;
//    }
    public boolean suaKhuyenMai(String ma, String ten, Date ngayBD, Date ngayKT, String phanTramGiam, String dieuKien) {     
        if (ma.equals("")) {
            new MyDialog("Chưa chọn mã để sửa!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ten.equals("")) {
            new MyDialog("Không được để trống tên chương trình khuyến mãi!", MyDialog.ERROR_DIALOG);
            return false;
        }
        Date nowday= new Date();
        if (ngayBD.compareTo(nowday)<0|| ngayBD.compareTo(nowday) == 0) {
            new MyDialog("Ngày bắt đầu phải lớn hơn ngày hiện tại", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            new MyDialog("Ngày kết thúc phải lớn hơn ngày bắt đầu!", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        int temp1=Integer.parseInt(phanTramGiam.trim());
        if(temp1<1 || temp1>100){
            new MyDialog("Phần trăm giảm giá không hợp lệ! 0<x<100 ", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        int temp2=Integer.parseInt(dieuKien.trim());
        if(temp2<20000){
            new MyDialog("Điều kiện hóa đơn không hợp lệ! x>=20000 ", MyDialog.ERROR_DIALOG);
            return false;
        }
        MyDialog dlg = new MyDialog("Bạn chắc chắn muốn sửa thông tin của chương trình khuyến mãi này?", MyDialog.WARNING_DIALOG);
        if(dlg.getAction() == MyDialog.CANCEL_OPTION)
            return false;
        boolean flag = false;
        try {
            int maKM = Integer.parseInt(ma);            
            KhuyenMai km = new KhuyenMai();
            km.setMaKM(maKM);
            km.setTenKM(ten);           
            km.setNgayBD(ngayBD);
            km.setNgayKT(ngayKT);
            km.setPhanTramGiam(temp1);
            km.setDieuKien(temp2);
            flag = KMDAO.suaKhuyenMai(km);
        } catch (Exception e) {
            new MyDialog("Thông nhập chưa hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (flag) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
    public boolean xoaKhuyenMai(String ma) {
    	if (ma.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa chọn loại để xóa !");
			return false;
		}
		int maKM = Integer.parseInt(ma);
		if (KMDAO.xoaKhuyenMai(maKM)) {
			JOptionPane.showMessageDialog(null, "Xóa thành công !");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Xóa thất bại !");
			return false;
		}
    }
    public KhuyenMai getKMById(String ma){
	int maKM = Integer.parseInt(ma);
        return KMDAO.getKMById(maKM);
    }
}
