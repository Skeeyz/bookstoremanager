package org.example.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.example.BUS.DangNhapBUS;
import org.example.BUS.NhanVienBUS;
import org.example.BUS.PhanQuyenBUS;
import org.example.DTO.NhanVien;
import org.example.DTO.PhanQuyen;
import org.example.GUI.DangNhap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;

public class MainFormGUI extends JFrame {
        DangNhapBUS login = new DangNhapBUS();
	private JPanel contentPane;
	JLabel lbl_tenNV;
	JButton btn_nhaphang,btn_banhang,btn_nhanvien,btn_khachhang,btn_sanpham,btn_thongke,btn_khuyenmai;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					new MyConnect();
//					MainFormGUI frame = new MainFormGUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MainFormGUI() {
		this.setTitle("Quản Lý Của Hàng Sách v1.1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1084, 680);
		this.setLocationRelativeTo(null); //hiển thị giữa mà hình
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 1050, 31);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btn_logout = new JButton("");
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				org.example.GUI.DangNhap dn = new DangNhap();
				dn.setVisible(true);
				dispose();
			}
		});
		btn_logout.setBounds(0, 0, 48, 31);
		btn_logout.setIcon(getIconButton("/icon/logout.png"));
		btn_logout.setFocusPainted(false);
		panel.add(btn_logout);
		
		JLabel lblNewLabel = new JLabel("Xin chào ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(750, 0, 102, 31);
		panel.add(lblNewLabel);
		
		lbl_tenNV = new JLabel("Nhân viên");
		lbl_tenNV.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lbl_tenNV.setBounds(840, 0, 200, 31);
		panel.add(lbl_tenNV);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 51, 284, 582);
		contentPane.add(panel_1);
		
		btn_khuyenmai = new JButton("Khuyến Mãi");
		btn_khuyenmai.setBounds(10, 248, 264, 75);
		btn_khuyenmai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QL_KhuyenMaiGUI KM = new QL_KhuyenMaiGUI();
				KM.setVisible(true);
				dispose();
			}
		});
		btn_khuyenmai.setFont(new Font("Tahoma", Font.BOLD, 22));
		btn_khuyenmai.setIcon(getIconButton("/icon/KhuyenMai.png"));
		btn_khuyenmai.setFocusPainted(false);
		
		btn_sanpham = new JButton("Sản Phẩm");
		btn_sanpham.setBounds(10, 10, 264, 75);
		btn_sanpham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QL_SanPhamGUI SP = new  QL_SanPhamGUI();
				SP.setVisible(true);
				dispose();
			}
		});
		btn_sanpham.setFont(new Font("Tahoma", Font.BOLD, 22));
		btn_sanpham.setIcon(getIconButton("/icon/SanPham.png"));
		btn_sanpham.setFocusPainted(false);
		
		btn_nhanvien = new JButton("Nhân Viên");
		btn_nhanvien.setBounds(10, 418, 264, 65);
		btn_nhanvien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QL_NhanVien NV = new QL_NhanVien(login.taiKhoanLogin.getQuyen());
				NV.setVisible(true);
				dispose();
			}
		});
		btn_nhanvien.setFont(new Font("Tahoma", Font.BOLD, 22));
		btn_nhanvien.setForeground(new Color(0, 0, 0));
		btn_nhanvien.setIcon(getIconButton("/icon/NhanVien.png"));
		btn_nhanvien.setFocusPainted(false);
		
		btn_khachhang = new JButton("Khách Hàng");
		btn_khachhang.setBounds(10, 333, 264, 75);
		btn_khachhang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QL_KhachHang KH = new QL_KhachHang();
				KH.setVisible(true);
				dispose();
			}
		});
		btn_khachhang.setFont(new Font("Tahoma", Font.BOLD, 22));
		btn_khachhang.setIcon(getIconButton("/icon/KhachHang.png"));
		btn_khachhang.setFocusPainted(false);
		
		btn_nhaphang = new JButton("Nhập Hàng");
		btn_nhaphang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QL_NhapHang BH = new QL_NhapHang();
				BH.setVisible(true);
				dispose();
			}
		});
		btn_nhaphang.setBounds(10, 95, 264, 65);
		btn_nhaphang.setFont(new Font("Tahoma", Font.BOLD, 22));
		btn_nhaphang.setIcon(getIconButton("/icon/KhoHang.png"));
		btn_nhaphang.setFocusPainted(false);
		
		btn_thongke = new JButton("Thống Kê");
		btn_thongke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongKeGUI tk = new ThongKeGUI();
				tk.setVisible(true);
				dispose();
			}
		});
		btn_thongke.setBounds(10, 493, 264, 68);
		btn_thongke.setFont(new Font("Tahoma", Font.BOLD, 22));
		btn_thongke.setIcon(getIconButton("/icon/ThongKe.png"));
		btn_thongke.setFocusPainted(false);
		panel_1.setLayout(null);
		panel_1.add(btn_khuyenmai);
		panel_1.add(btn_sanpham);
		panel_1.add(btn_nhanvien);
		panel_1.add(btn_khachhang);
		panel_1.add(btn_nhaphang);
		panel_1.add(btn_thongke);
		
		btn_banhang = new JButton("Bán Hàng");
		btn_banhang.setBounds(10, 170, 264, 68);
		panel_1.add(btn_banhang);
		btn_banhang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QL_BanHang BH = new QL_BanHang();
				BH.setVisible(true);
				dispose();
			}
		});
		btn_banhang.setFont(new Font("Tahoma", Font.BOLD, 22));
		btn_banhang.setIcon(getIconButton("/icon/BanHang.png"));
		btn_banhang.setFocusPainted(false);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(304, 51, 766, 582);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lbl_hinhanh = new JLabel("image");
		lbl_hinhanh.setBounds(10, 10, 746, 562);
		lbl_hinhanh.setIcon(getIconButton("/icon/CuaHang.png"));
		panel_2.add(lbl_hinhanh);
		getTenNhanVien();
		chucNangTheoPhanQuyen();
	}

    public ImageIcon getIconButton(String url){
        URL imgUrl = MainFormGUI.class.getResource(url);
        if(imgUrl == null){
            throw new RuntimeException("Không tìm thấy icon");
        }
        return new ImageIcon(imgUrl);
    }

	private void getTenNhanVien() {
		
		int maNV = login.taiKhoanLogin.getMaNhanVien();
		if(maNV==0)
			lbl_tenNV.setText("Admin !");
		NhanVienBUS NV = new NhanVienBUS();
		ArrayList<NhanVien> dsnv = NV.getDanhSachNhanVien();
		for(NhanVien nv : dsnv) {
			if(nv.getMaNV() == maNV) {
				lbl_tenNV.setText(" "+nv.getHoNV()+" "+nv.getTenNV()+" !");
			}
		}
	}
	private void chucNangTheoPhanQuyen() {
	PhanQuyen quyen = PhanQuyenBUS.quyenTK;

        if (quyen.getNhapHang() == 0) {
        	btn_nhaphang.setVisible(false);
        }

        if (quyen.getQlSanPham() == 0) {
            
            btn_sanpham.setVisible(false);
        }

        if (quyen.getQlNhanVien() == 0) {
           btn_nhanvien.setVisible(false);
        }

        if (quyen.getQlKhachHang() == 0) {
            btn_khachhang.setVisible(false);
        }

        if (quyen.getThongKe() == 0) {
            btn_thongke.setVisible(false);
        }
	}
}
