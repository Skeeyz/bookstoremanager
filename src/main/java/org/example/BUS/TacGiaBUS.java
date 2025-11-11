package org.example.BUS;

import org.example.DAO.TacGiaDao;
import org.example.DTO.TacGia;

import java.util.ArrayList;

import javax.swing.JOptionPane;



public class TacGiaBUS {
	private TacGiaDao tacGiaDao = new TacGiaDao();
	private ArrayList<TacGia> listTG = null;

	public TacGiaBUS() {
		docDanhSachTG();
	}

	public void docDanhSachTG() {
		this.listTG = tacGiaDao.getListTacGia();
	}

	public ArrayList<TacGia> getDanhSachTG() {
		if (listTG == null) {
			docDanhSachTG();
		}
		return this.listTG;
	}

	public String getTenTG(int ma) {
		for (TacGia tg : listTG) {
			if (tg.getMaTG()== ma) {
				return tg.getMaTG()+ " - " + tg.getTenTG();
			}
		}
		return "";
	}

	public boolean themTG( String tenTG) {
		if (tenTG.trim().equals("")) {			
			JOptionPane.showMessageDialog(null, "Không được để trống tên tác giả !");
			return false;
		}
		for(TacGia tg : listTG) {
			if(String.valueOf(tg.getTenTG()).equals(tenTG)) {
				JOptionPane.showMessageDialog(null, "Tên tác giả đã tồn tại, vui lòng kiểm tra lại!");
				return false;
			}
		}
		if (tacGiaDao.themTG(tenTG)) {
			JOptionPane.showMessageDialog(null, "Thêm thành công !");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Thêm thất bại !");
			return false;
		}
	}

	public boolean xoaTG(String ma) {
		if (ma.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa chọn tác giả để xóa !");
			return false;
		}
		int maTG = Integer.parseInt(ma);
		if (tacGiaDao.xoaTG(maTG)) {
			JOptionPane.showMessageDialog(null, "Xóa thành công !");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Xóa thất bại !");
			return false;
		}
	}

	public boolean suaTG(String matacgia, String ten) {
		if (matacgia.trim().equals("") || ten.trim().equals("")) {			
			JOptionPane.showMessageDialog(null, "Không được để trống thông tin !");
			return false;
		}
//		for(LoaiSanPham loai : listLoai) {
//			if(String.valueOf(loai.getMaLoai()).equals(maloai)) {
//				JOptionPane.showMessageDialog(null, "Mã sản phẩm đã tồn tại !");
//				return false;
//			}
//		}
		int maTG = Integer.parseInt(matacgia);
                TacGia tg=new TacGia(maTG, ten);
		if (tacGiaDao.updateTG(tg)) {
			JOptionPane.showMessageDialog(null, "Sửa thành công !");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Sữa thất bại !");
			return false;
		}
	}
}
