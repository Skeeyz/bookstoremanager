package org.example.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import MyCustom.MyDialog;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.SystemColor;

import com.toedter.calendar.JDateChooser;
import org.example.BUS.*;
import org.example.DTO.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QL_BanHang extends JFrame {

	private JPanel contentPane;
	private JPanel panel_BanHang;
	private JPanel panel_HoaDon;
	private JTextField txt_TongTien;
	public JTextField txt_HoKH;//
	public JTextField txt_maKH;//
	public JTextField txt_tenKH;//
	public JTextField txt_gioiTinh;//
	private JTextField txt_tenNV ;
	private JTextField txt_MaSP;
	private JTextField txt_TenSP;
	private JTextField txt_donGia;
	private JTable tbl_gioHang;
	private JTable tbl_SP;
	public JTextField txt_GiamGia ;//
	int tongphantram=0, tongTien=0,tongTienSauKM=0;
	private JButton btn_TimMaGG;
	private JButton btn_ThanhToan, btn_TimHD ;
	private JButton btn_tim;
	private JButton btn_menu;
	private JSpinner spi_soLuong;
	private JComboBox cbx_LoaiSP;
        private int maHD_Chuathanhtoan;
        private JDateChooser ngay_BD_find;
        private JDateChooser ngay_KT_find;
	JLabel lbl_hinhAnh, lb_VND1, lb_VND2;
	File fileAnhSP;
	final JFileChooser fileDialog = new JFileChooser();
	DefaultTableModel model_SP = new DefaultTableModel();
	DefaultTableModel model_gioHang = new DefaultTableModel();
	SanPhamBUS spBUS = new SanPhamBUS();
	LoaiSanPhamBUS lspBUS = new LoaiSanPhamBUS();
        KhuyenMaiBUS kmBUS= new KhuyenMaiBUS();
	private JTextField txt_giaMin;
	private JTextField txt_giaMax;
	static String temp ="";
//	private DLG_ChonKhuyenMai ckm;
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	
	ArrayList<Vector> dsGioHang = new ArrayList<>();
	ArrayList<Integer> dsma = new ArrayList<Integer>();
	
	
	
	
	
	private JTable table_HD;
	private JTextField txt_maHD2;
	private JTextField txt_maKH2;
	private JTextField txt_NhanVien2;
	private JTextField txt_NgayLap2;
	private JTextField txt_tongTien;
	private JTextField txt_ghichu;
	private JTextField txt_Min;
	private JTextField txt_Max;
	private JTable table_CTHD;
	private JTextField txt_maCTHD;
	private JTextField txt_maSPCTHD;
	private JTextField txt_DGCTHD;
	private JTextField txt_maSLCTHD;
	private JTextField txt_thanhtienCTHD;
	private HoaDonBUS hoaDonBUS = new HoaDonBUS();
	private CTHoaDonBUS CThoaDonBUS = new CTHoaDonBUS();
	private KhachHangBUS khBUS = new KhachHangBUS();
        private QL_BanHang thisFrame=this;
	DLG_ChonKhachHang KH;
        DLG_ChonKhuyenMai KM;
	DefaultTableModel model_HD = new DefaultTableModel();
	DefaultTableModel model_CTHD = new DefaultTableModel();
	JComboBox cbx_timkiem;
	JButton btn_menu1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					new MyConnect();
//					QL_BanHangTEST frame = new QL_BanHangTEST();
//					frame.setVisible(true);
//					frame.loadingSanPham();
//					frame.loadingComBox();
//					frame.loadingHoaDon();
//					frame.loadingCTHD();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
	public QL_BanHang() {
		//new MyConnect();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(120, 120, 1200, 800);
		this.setLocationRelativeTo(null); //hiển thị giữa mà hình
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 20));
		tabbedPane.setBounds(10, 10, 1166, 743);
		contentPane.add(tabbedPane);
		
		JPanel panel_BanHang = new JPanel();
		panel_BanHang.setBackground(SystemColor.control);
		tabbedPane.addTab("Bán Hàng", null, panel_BanHang, null);
		panel_BanHang.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Lọc Sản Phẩm Theo Loại:");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 49, 174, 33);
		panel_BanHang.add(lblNewLabel_1);
		
		cbx_LoaiSP = new JComboBox();
		cbx_LoaiSP.setModel(new DefaultComboBoxModel(new String[] {"All"}));
		cbx_LoaiSP.setBounds(194, 56, 96, 21);
		panel_BanHang.add(cbx_LoaiSP);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(SystemColor.control);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh S\u00E1ch S\u1EA3n Ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(10, 169, 462, 472);
		panel_BanHang.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 22, 442, 440);
		panel.add(scrollPane_1);
		
		tbl_SP = new JTable();
		tbl_SP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickTableSanPham();
			}
		});
		tbl_SP.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Mã SP", "Tên SP", "Còn lại", "Đơn giá", "Loại", "Hình ảnh", "Mô tả"
			}
		));
		scrollPane_1.setViewportView(tbl_SP);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(0, 0, 0));
		panel_1.setBackground(SystemColor.control);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Thông tin khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_1.setBounds(482, 14, 635, 627);
		panel_BanHang.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.control);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Thông tin khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_2.setBounds(10, 20, 624, 110);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Họ");
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_4.setBounds(104, 20, 38, 13);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Mã KH:");
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_5.setBounds(91, 43, 45, 13);
		panel_2.add(lblNewLabel_5);
		
		txt_HoKH = new JTextField();
		txt_HoKH.setBounds(146, 17, 183, 25);
                txt_HoKH.setEditable(false);
		panel_2.add(txt_HoKH);
		txt_HoKH.setColumns(10);
		
		txt_maKH = new JTextField();
		txt_maKH.setBounds(146, 40, 183, 25);
                txt_maKH.setEditable(false);
		panel_2.add(txt_maKH);
		txt_maKH.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Tên ");
		lblNewLabel_7.setForeground(Color.BLACK);
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7.setBounds(376, 20, 45, 15);
		panel_2.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Giới Tính");
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_8.setBounds(358, 43, 70, 15);
		panel_2.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Nhân viên lập");
		lblNewLabel_9.setForeground(Color.BLACK);
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_9.setBounds(339, 68, 82, 15);
		panel_2.add(lblNewLabel_9);
		
		txt_tenKH = new JTextField();
		txt_tenKH.setBounds(431, 17, 183, 25);
                txt_tenKH.setEditable(false);
		panel_2.add(txt_tenKH);
		txt_tenKH.setColumns(10);
		
		txt_gioiTinh = new JTextField();
		txt_gioiTinh.setBounds(431, 40, 183, 25);
                txt_gioiTinh.setEditable(false);
		panel_2.add(txt_gioiTinh);
		txt_gioiTinh.setColumns(10);
		
		txt_tenNV = new JTextField();
		txt_tenNV.setDisabledTextColor(Color.BLACK);
		txt_tenNV.setForeground(SystemColor.windowText);
		txt_tenNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_tenNV.setBackground(SystemColor.window);
		txt_tenNV.setEnabled(false);
		txt_tenNV.setBounds(431, 65, 183, 25);
		panel_2.add(txt_tenNV);
		txt_tenNV.setColumns(10);
		
		JButton btn_TimKH = new JButton("Tìm KH");
		btn_TimKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            KH = new DLG_ChonKhachHang(thisFrame);
                            KH.setVisible(true);
			}
		});
		btn_TimKH.setBackground(new Color(255, 255, 0));
		btn_TimKH.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btn_TimKH.setBounds(194, 69, 96, 21);
		panel_2.add(btn_TimKH);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.control);
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng Tin S\u1EA3n Ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_3.setBounds(10, 132, 624, 139);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("Mã Sản Phẩm:");
		lblNewLabel_10.setForeground(Color.BLACK);
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_10.setBounds(267, 21, 81, 13);
		panel_3.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Tên Sản Phẩm:");
		lblNewLabel_11.setForeground(Color.BLACK);
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_11.setBounds(263, 44, 85, 13);
		panel_3.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Đơn Giá");
		lblNewLabel_12.setForeground(Color.BLACK);
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_12.setBounds(296, 67, 52, 13);
		panel_3.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Số Lượng:");
		lblNewLabel_13.setForeground(Color.BLACK);
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_13.setBounds(287, 90, 61, 13);
		panel_3.add(lblNewLabel_13);
		
		txt_MaSP = new JTextField();
		txt_MaSP.setBounds(358, 18, 168, 25);
                txt_MaSP.setEditable(false);
		panel_3.add(txt_MaSP);
		txt_MaSP.setColumns(10);
		
		txt_TenSP = new JTextField();
		txt_TenSP.setBounds(358, 41, 227, 25);
                txt_TenSP.setEditable(false);
		panel_3.add(txt_TenSP);
		txt_TenSP.setColumns(10);
		
		txt_donGia = new JTextField();
		txt_donGia.setBounds(358, 64, 256, 25);
                txt_donGia.setEditable(false);
		panel_3.add(txt_donGia);
		txt_donGia.setColumns(10);
		
		JButton btn_themGioHang = new JButton("Thêm vào giỏ");
		btn_themGioHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themVaoGioHang();
			}
		});
		btn_themGioHang.setBackground(new Color(255, 255, 0));
		btn_themGioHang.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btn_themGioHang.setBounds(434, 108, 124, 21);
		panel_3.add(btn_themGioHang);
		
		spi_soLuong = new JSpinner();
		spi_soLuong.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spi_soLuong.setBounds(358, 87, 96, 20);
		panel_3.add(spi_soLuong);
		
		lbl_hinhAnh = new JLabel("");
		lbl_hinhAnh.setBackground(Color.WHITE);
		lbl_hinhAnh.setBounds(85, 19, 168, 108);
		panel_3.add(lbl_hinhAnh);
		
		JPanel panel_4 = new JPanel();
		panel_4.setForeground(Color.BLACK);
		panel_4.setBackground(SystemColor.control);
		panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Gi\u1ECF H\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_4.setBounds(10, 281, 624, 213);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 604, 183);
		panel_4.add(scrollPane);
		
		tbl_gioHang = new JTable();
		tbl_gioHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickTableGioHang();
			}
		});
		tbl_gioHang.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã SP", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"
			}
		));
		tbl_gioHang.setFont(new Font("Times New Roman", Font.BOLD, 12));
		scrollPane.setViewportView(tbl_gioHang);
		
		JLabel lblNewLabel_2 = new JLabel("Tổng tiền      :");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_2.setBounds(310, 504, 97, 13);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Mã giảm giá :");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_3.setBounds(310, 572, 81, 13);
		panel_1.add(lblNewLabel_3);
		
		btn_ThanhToan = new JButton("Thanh Toán");
		btn_ThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txt_maKH.getText().equals("")||txt_maKH.getText().equals("0")) {
					new MyDialog("Vui lòng chọn khách hàng trước khi thanh toán !!!", MyDialog.ERROR_DIALOG);
					return;
				}
                                int status=JOptionPane.showConfirmDialog(panel_HoaDon,"Xác nhận thanh toán!","Thông báo",JOptionPane.YES_NO_OPTION);
                                if(status==JOptionPane.NO_OPTION)
                                    return;
				// thêm hóa đơn vào csdl
				String maNV[] = txt_tenNV.getText().split(" - ");
				String tt[] = txt_TongTien.getText().split("  ");
				hoaDonBUS.luuHoaDon(txt_maKH.getText(), maNV[0], tt[0], "Đã thanh toán");
				// thanh toán
				thanhToan();
				// thêm chi tiết hóa đơn vào cơ sở dữ liệu
				for (Vector vec : dsGioHang) {
					String maSP = vec.get(0) + "";
					String soLuong = vec.get(2) + "";
					String donGia = vec.get(3) + "";
					String thanhTien = vec.get(4) + "";
					CThoaDonBUS.addCTHoaDon(maSP, soLuong, donGia, thanhTien);
				}
				// update tổng chi tiêu khách hàng
				//khBUS.updateTongChiTieu(txt_maKH.getText(), txt_TongTien.getText());
                                loadingHoaDon();
                                setKhachHang("", "", "", "");
                                setKhuyenMai("", "");
                                clearForm();
			}
		});
		btn_ThanhToan.setBackground(new Color(255, 255, 0));
		btn_ThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btn_ThanhToan.setBounds(146, 554, 97, 49);
		panel_1.add(btn_ThanhToan);
                
                /*btn_ThanhToan = new JButton("Thanh Toán");//testThanhToan
		btn_ThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txt_maKH.getText().equals("")||txt_maKH.getText().equals("0")) {
					new MyDialog("Vui lòng chọn khách hàng trước khi thanh toán !!!", MyDialog.ERROR_DIALOG);
					return;
				}
				// thêm hóa đơn vào csdl
				String maNV[] = txt_tenNV.getText().split(" - ");
				String tt[] = txt_TongTien.getText().split("  ");
				hoaDonBUS.luuHoaDon(txt_maKH.getText(), maNV[0], tt[0], "Đã thanh toán");
				// thanh toán
				thanhToan();
				// thêm chi tiết hóa đơn vào cơ sở dữ liệu
				for (Vector vec : dsGioHang) {
					String maSP = vec.get(0) + "";
					String soLuong = vec.get(2) + "";
					String donGia = vec.get(3) + "";
					String thanhTien = vec.get(4) + "";
					CThoaDonBUS.addCTHoaDon(maSP, soLuong, donGia, thanhTien);
				}
				// update tổng chi tiêu khách hàng
				//khBUS.updateTongChiTieu(txt_maKH.getText(), txt_TongTien.getText());
                                loadingHoaDon();
				txt_GiamGia.setText("");
				txt_tongTien.setText("");
				tongTien = 0;
				tongTienSauKM = 0;
				dsma.clear();
				dsGioHang.clear();
			}
		});
		btn_ThanhToan.setBackground(new Color(255, 255, 0));
		btn_ThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btn_ThanhToan.setBounds(146, 554, 97, 49);
		panel_1.add(btn_ThanhToan);//TestThanhToan*/
		
		txt_TongTien = new JTextField();
		txt_TongTien.setBounds(405, 501, 187, 25);
		panel_1.add(txt_TongTien);
		txt_TongTien.setColumns(10);
                txt_TongTien.setEnabled(false);
		
		txt_GiamGia = new JTextField();			
		txt_GiamGia.setBounds(405, 566, 153, 25);
		panel_1.add(txt_GiamGia);
		txt_GiamGia.setColumns(10);
                txt_GiamGia.setEnabled(false);
		txt_GiamGia.getDocument().addDocumentListener(new DocumentListener() {		
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				tinhTongTien();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				tinhTongTien();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				tinhTongTien();
			}
		});
		
		btn_TimMaGG = new JButton("Tìm");
		btn_TimMaGG.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(txt_TongTien.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm !");
					return;
				}
                                KM = new DLG_ChonKhuyenMai(thisFrame);
				KM.setVisible(true);		
			}
		});
		btn_TimMaGG.setBounds(568, 568, 57, 21);
		panel_1.add(btn_TimMaGG);
                
                JLabel lbl_maHDChuaTT=new JLabel("Mã hóa đơn: ");
                lbl_maHDChuaTT.setForeground(Color.BLACK);
                lbl_maHDChuaTT.setFont(new Font("Times New Roman",Font.BOLD,12));
                lbl_maHDChuaTT.setBounds(166, 500, 112, 21);
                panel_1.add(lbl_maHDChuaTT);
		
		JButton btn_xoaGioHang = new JButton("Xóa khỏi giỏ");
		btn_xoaGioHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaKhoiGioHang();
			}
		});
		btn_xoaGioHang.setBounds(20, 500, 112, 21);
		panel_1.add(btn_xoaGioHang);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tổng tiền giảm :");
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(310, 533, 97, 13);
		panel_1.add(lblNewLabel_2_1);
		
		txt_tongTienKM = new JTextField();
		txt_tongTienKM.setColumns(10);
		txt_tongTienKM.setBounds(405, 530, 187, 25);
                txt_tongTienKM.setEnabled(false);
		panel_1.add(txt_tongTienKM);
		
		
		JLabel lblNewLabel_6 = new JLabel("Giá từ :");
		lblNewLabel_6.setForeground(Color.BLACK);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_6.setBounds(10, 106, 108, 25);
		panel_BanHang.add(lblNewLabel_6);
		
		txt_giaMin = new JTextField();
		txt_giaMin.setBounds(93, 113, 123, 25);
		panel_BanHang.add(txt_giaMin);
		txt_giaMin.setColumns(10);
		
		JLabel lblNewLabel_6_1 = new JLabel("Đến :");
		lblNewLabel_6_1.setForeground(Color.BLACK);
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_6_1.setBounds(240, 106, 81, 25);
		panel_BanHang.add(lblNewLabel_6_1);
		
		txt_giaMax = new JTextField();
		txt_giaMax.setColumns(10);
		txt_giaMax.setBounds(319, 113, 123, 25);
		panel_BanHang.add(txt_giaMax);
		
		btn_tim = new JButton("Tìm");
		btn_tim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiem();
			}
		});
		btn_tim.setBounds(188, 142, 85, 21);
		panel_BanHang.add(btn_tim);
		
		btn_menu = new JButton("Trang Chính");
		btn_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                                xoaKhoiGioHangTongQuat();
				MainFormGUI MF = new MainFormGUI();
				MF.setVisible(true);
				dispose();
			}
		});
		btn_menu.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_menu.setBounds(551, 661, 216, 33);
		btn_menu.setIcon(new ImageIcon("Icon/home.png"));
		panel_BanHang.add(btn_menu);
		
		JPanel panel_HoaDon = new JPanel();
		tabbedPane.addTab("Hóa Đơn", null, panel_HoaDon, null);
		panel_HoaDon.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Danh sách hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(0, 0, 689, 635);
		panel_HoaDon.add(panel_5);
		panel_5.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(10, 260, 669, 399);
		panel_5.add(panel_6);
		panel_6.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 10, 649, 360);
		panel_6.add(scrollPane_2);
		
		table_HD = new JTable();
		table_HD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickTableHD();
			}
		});
		table_HD.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Mã HĐ", "Mã KH", "Nhân Viên", "Ngày Lập", "Tổng Tiền", "Ghi chú"
			}
		));
		scrollPane_2.setViewportView(table_HD);
		
		JLabel lblNewLabel = new JLabel("Mã HĐ :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 29, 82, 26);
		panel_5.add(lblNewLabel);
		
		JLabel lblMKh = new JLabel("Mã KH :");
		lblMKh.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMKh.setBounds(10, 65, 82, 26);
		panel_5.add(lblMKh);
		
		JLabel lblNhnVinLp = new JLabel("Nhân Viên Lập :");
		lblNhnVinLp.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNhnVinLp.setBounds(10, 101, 135, 26);
		panel_5.add(lblNhnVinLp);
		
		txt_maHD2 = new JTextField();
		txt_maHD2.setBounds(80, 29, 261, 26);
		panel_5.add(txt_maHD2);
		txt_maHD2.setColumns(10);
                txt_maHD2.setEnabled(false);
		
		txt_maKH2 = new JTextField();
		txt_maKH2.setColumns(10);
		txt_maKH2.setBounds(80, 65, 261, 26);
                txt_maKH2.setEnabled(false);
		panel_5.add(txt_maKH2);
		
		txt_NhanVien2 = new JTextField();
		txt_NhanVien2.setColumns(10);
		txt_NhanVien2.setBounds(133, 101, 208, 26);
                txt_NhanVien2.setEnabled(false);
		panel_5.add(txt_NhanVien2);
		
		JLabel lblNgyLp = new JLabel("Ngày Lập :");
		lblNgyLp.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNgyLp.setBounds(372, 29, 82, 26);
		panel_5.add(lblNgyLp);
		
		JLabel lblTngTin = new JLabel("Tổng Tiền :");
		lblTngTin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTngTin.setBounds(372, 65, 91, 26);
		panel_5.add(lblTngTin);
		
		JLabel lblGhiCh = new JLabel("Ghi chú :");
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblGhiCh.setBounds(372, 101, 82, 26);
		panel_5.add(lblGhiCh);
		
		txt_NgayLap2 = new JTextField();
		txt_NgayLap2.setColumns(10);
		txt_NgayLap2.setBounds(471, 29, 208, 26);
                txt_NgayLap2.setEnabled(false);
		panel_5.add(txt_NgayLap2);
		
		txt_tongTien = new JTextField();
		txt_tongTien.setColumns(10);
		txt_tongTien.setBounds(473, 69, 206, 26);
                txt_tongTien.setEnabled(false);
		panel_5.add(txt_tongTien);
		
		txt_ghichu = new JTextField();
		txt_ghichu.setColumns(10);
		txt_ghichu.setBounds(471, 105, 208, 26);
                txt_ghichu.setEnabled(false);
		panel_5.add(txt_ghichu);
		
		JLabel lblGiT = new JLabel("Từ :");
		lblGiT.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblGiT.setBounds(70, 188, 40, 30);
		panel_5.add(lblGiT);
		
		txt_Min = new JTextField();
		txt_Min.setColumns(10);
		txt_Min.setBounds(110, 188, 135, 30);
                txt_Min.setEnabled(false);
		panel_5.add(txt_Min);
		
		JLabel lbln = new JLabel("Đến :");
		lbln.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbln.setBounds(410, 188, 50, 30);
		panel_5.add(lbln);
		
		txt_Max = new JTextField();
		txt_Max.setColumns(10);
		txt_Max.setBounds(460, 188, 135, 30);
                txt_Max.setEnabled(false);
		panel_5.add(txt_Max);
		
		btn_TimHD = new JButton("Tìm");
                btn_TimHD.setFont(new Font("Tahoma",Font.PLAIN,18));
		btn_TimHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemHD();
			}
		});
		btn_TimHD.setBounds(325, 230, 85, 25);
                btn_TimHD.setEnabled(false);
		panel_5.add(btn_TimHD);
		
		cbx_timkiem = new JComboBox();
		cbx_timkiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		cbx_timkiem.setModel(new DefaultComboBoxModel(new String[] {"All","Theo giá", "Theo ngày"}));
		cbx_timkiem.setBounds(318, 145, 102, 32);
		panel_5.add(cbx_timkiem);
                
                cbx_timkiem.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        cbx_method_find();
                    }
                });
		
		lb_VND1 = new JLabel("VND");
		lb_VND1.setHorizontalAlignment(SwingConstants.CENTER);
		lb_VND1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lb_VND1.setBounds(248, 188, 30, 30);
                lb_VND1.setVisible(false);
		panel_5.add(lb_VND1);
                
                ngay_BD_find =new JDateChooser();
                ngay_BD_find.setDateFormatString("dd/MM/yyyy");
                ngay_BD_find.getDateEditor().getUiComponent().setFont(new Font("Tahoma",Font.PLAIN,16));
                ngay_BD_find.setBounds(110,188,165,30);//250
                ngay_BD_find.setVisible(false);
                panel_5.add(ngay_BD_find);
                
                ngay_KT_find =new JDateChooser();
                ngay_KT_find.setDateFormatString("dd/MM/yyyy");
                ngay_KT_find.getDateEditor().getUiComponent().setFont(new Font("Tahoma",Font.PLAIN,16));
                ngay_KT_find.setBounds(460,188,165,30);//600
                ngay_KT_find.setVisible(false);
                panel_5.add(ngay_KT_find);
		
                try {
                ngay_BD_find.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString()));
                ngay_KT_find.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString()));
                } catch (ParseException ex) {
                Logger.getLogger(QL_NhapHang.class.getName()).log(Level.SEVERE, null, ex);
                }
                
		lb_VND2 = new JLabel("VND");
		lb_VND2.setHorizontalAlignment(SwingConstants.CENTER);
		lb_VND2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lb_VND2.setBounds(598, 188, 30, 30);
                lb_VND2.setVisible(false);
		panel_5.add(lb_VND2);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Chi Tiết Hóa Đơn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(709, 10, 442, 619);
		panel_HoaDon.add(panel_7);
		panel_7.setLayout(null);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(10, 227, 422, 377);
		panel_7.add(panel_8);
		panel_8.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 10, 402, 357);
		panel_8.add(scrollPane_3);
		
		table_CTHD = new JTable();
		table_CTHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickTableCTHD();
			}
		});
		table_CTHD.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Mã HĐ", "Mã SP", "Số Lượng", "Đơn Giá", "Thành Tiền"
			}
		));
		scrollPane_3.setViewportView(table_CTHD);
		
		JLabel lblNewLabel_14 = new JLabel("Mã HĐ :");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_14.setBounds(78, 10, 82, 26);
		panel_7.add(lblNewLabel_14);
		
		txt_maCTHD = new JTextField();
		txt_maCTHD.setColumns(10);
		txt_maCTHD.setBounds(197, 14, 135, 26);
                txt_maCTHD.setEditable(false);
		panel_7.add(txt_maCTHD);
		
		JLabel lblNewLabel_14_1 = new JLabel("Mã SP :");
		lblNewLabel_14_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_14_1.setBounds(78, 46, 82, 26);
		panel_7.add(lblNewLabel_14_1);
		
		txt_maSPCTHD = new JTextField();
		txt_maSPCTHD.setColumns(10);
		txt_maSPCTHD.setBounds(197, 50, 135, 26);
                txt_maSPCTHD.setEditable(false);
		panel_7.add(txt_maSPCTHD);
		
		txt_DGCTHD = new JTextField();
		txt_DGCTHD.setColumns(10);
		txt_DGCTHD.setBounds(197, 122, 135, 26);
                txt_DGCTHD.setEditable(false);
		panel_7.add(txt_DGCTHD);
		
		JLabel lblNewLabel_14_1_1 = new JLabel("Số Lượng :");
		lblNewLabel_14_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_14_1_1.setBounds(78, 82, 82, 26);
		panel_7.add(lblNewLabel_14_1_1);
		
		txt_maSLCTHD = new JTextField();
		txt_maSLCTHD.setColumns(10);
		txt_maSLCTHD.setBounds(197, 86, 135, 26);
                txt_maSLCTHD.setEditable(false);
		panel_7.add(txt_maSLCTHD);
		
		JLabel lblNewLabel_14_1_1_1 = new JLabel("Đơn giá :");
		lblNewLabel_14_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_14_1_1_1.setBounds(78, 121, 82, 26);
		panel_7.add(lblNewLabel_14_1_1_1);
		
		JLabel lblNewLabel_14_1_1_1_1 = new JLabel("Thành Tiền :");
		lblNewLabel_14_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_14_1_1_1_1.setBounds(78, 157, 96, 26);
		panel_7.add(lblNewLabel_14_1_1_1_1);
		
		txt_thanhtienCTHD = new JTextField();
		txt_thanhtienCTHD.setColumns(10);
		txt_thanhtienCTHD.setBounds(197, 161, 135, 26);
                txt_thanhtienCTHD.setEditable(false);
		panel_7.add(txt_thanhtienCTHD);
		
		JButton btn_refresh = new JButton("Refesh");
		btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadingCTHD();
			}
		});
		btn_refresh.setBounds(347, 196, 85, 21);
		panel_7.add(btn_refresh);
		
		btn_menu1 = new JButton("Trang Chính");
		btn_menu1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFormGUI MF = new MainFormGUI();
				MF.setVisible(true);
				dispose();
			}
		});
		btn_menu1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_menu1.setBounds(551, 661, 216, 33);
		btn_menu1.setIcon(new ImageIcon("Icon/home.png"));
		panel_HoaDon.add(btn_menu1);
		loadingSanPham();
		loadingComBox();
		loadingHoaDon();
		loadingCTHD();
		getTenNhanVien();
	}
	private void loadAnh(String anh) {
        lbl_hinhAnh.setIcon(getAnhSP(anh));
    }
	private ImageIcon getAnhSP(String src) {
        src = src.trim().equals("") ? "default.png" : src;
        //Xử lý ảnh
        BufferedImage img = null;
        File fileImg = new File(src);

        if (!fileImg.exists()) {
            src = "default.png";
            fileImg = new File("Image/" + src);
        }

        try {
            img = ImageIO.read(fileImg);
            fileAnhSP = new File(src);
        } catch (IOException e) {
            fileAnhSP = new File("imgs/anhthe/avatar.jpg");
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        }

        return null;
    }
	private void clickTableSanPham() {
    	int row = tbl_SP.getSelectedRow();
        if (row > -1) {
            String ma = tbl_SP.getValueAt(row, 0) + "";
            String ten = tbl_SP.getValueAt(row, 1) + "";           
            String donGia = tbl_SP.getValueAt(row, 3) + "";		            	         
            String anh = tbl_SP.getValueAt(row, 5) + "";           
            //lấy dữ liệu lên textfiled           
            txt_MaSP.setText(ma);
            txt_TenSP.setText(ten);
            txt_donGia.setText(donGia);		                                    
            loadAnh("Image/" + anh);
        }
    }
	private void loadingSanPham() {
    	spBUS.docListSanPham();
		model_SP.setRowCount(0);
		ArrayList<SanPham>dssp = spBUS.getListSanPham();
		Vector header = new Vector();
        header.add("Mã SP");
        header.add("Tên SP");
        header.add("Còn lại");
        header.add("Đơn giá");
        header.add("Loại");
        header.add("Hình ảnh");
        header.add("Mô Tả");
        
        if (model_SP.getRowCount()==0){ 
        	model_SP=new DefaultTableModel(header,0){
                    @Override
                    public boolean isCellEditable(int row,int column){
                        return false;
                    }
                };
        } 
		for( SanPham sp : dssp) {
                    if(sp.getSoLuong()>0){
                        Vector row = new Vector();
		        row.add(sp.getMaSP());
		        row.add(sp.getTenSP());		      
		        row.add(sp.getSoLuong());		       
		        row.add(sp.getDonGia());		      
		        String tenLoai = lspBUS.getTenLoai(sp.getMaLoai());
		        row.add(tenLoai);
		        row.add(sp.getHinhAnh());
		        row.add(sp.getMoTa());
		        model_SP.addRow(row); 
		        tbl_SP.setModel(model_SP);
                    }
		}
	centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	for(int x=0;x<tbl_SP.getColumnCount();x++){
        tbl_SP.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
   }
    }
	DecimalFormat dcf = new DecimalFormat("###,###");
	private JTextField txt_tongTienKM;
	
	private void loadingComBox() {
		cbx_LoaiSP.removeAllItems();
        ArrayList<LoaiSanPham> dsl = lspBUS.getDanhSachLoai();
        cbx_LoaiSP.addItem("All");
        for (LoaiSanPham loai : dsl) {
        	cbx_LoaiSP.addItem(loai.getTenLoai());
        }
    }
	/*private void themVaoGioHang1() {//testthemgiohang
		tbl_gioHang.removeAll();
		int tongTien=0;
		int row = tbl_SP.getSelectedRow();
        if (row < 0) {
            return;
        }
        if(txt_MaSP.getText().equals("") || txt_TenSP.getText().equals("")||txt_donGia.getText().equals("") ) {
        	new MyDialog("Bạn chưa chọn sản phẩm !", MyDialog.ERROR_DIALOG);
        	return;
        }
        if(row==0){
            
        }
        String maNV[] = txt_tenNV.getText().split(" - ");
	String tt[] = txt_TongTien.getText().split("  ");
	hoaDonBUS.luuHoaDon(txt_maKH.getText(), maNV[0], tt[0], "Chưa thanh toán");
        String ma = txt_MaSP.getText();      
        String ten = txt_TenSP.getText();
        String donGia = txt_donGia.getText();        
        int soLuong = Integer.parseInt(spi_soLuong.getValue() + "");        
        int soLuongConLai = Integer.parseInt(tbl_SP.getValueAt(tbl_SP.getSelectedRow(), 2) + "");

        if (soLuong > soLuongConLai || soLuongConLai <= 0) {
            new MyDialog("Sản phẩm đã hết hàng", MyDialog.ERROR_DIALOG);
            return;
        }

        txt_MaSP.setText("");
        txt_TenSP.setText("");
        txt_donGia.setText("");
        spi_soLuong.setValue(1);

        int key = Integer.parseInt(ma);
    
        Vector header = new Vector();
        header.add("Mã SP ");
        header.add("Tên SP ");
        header.add("Số lượng");  
        header.add("Đơn giá"); 
        header.add("Thành tiền"); 
        
        if (model_gioHang.getRowCount()==0){ 
        	model_gioHang=new DefaultTableModel(header,0){
                    @Override
                    public boolean isCellEditable(int row, int column){
                        return false;
                    }
                };
        } 
      
		for (int i = 0; i < tbl_gioHang.getRowCount(); i++) {
			String maTbl = tbl_gioHang.getValueAt(i, 0) + "";
			if (ma.equals(maTbl)) {
				int soLuongAdd = Integer.parseInt(tbl_gioHang.getValueAt(i, 2) + "");
				soLuongAdd += soLuong;
				int donGiaSP = Integer.parseInt(donGia);

				tbl_gioHang.setValueAt(soLuongAdd, i, 2);
				tbl_gioHang.setValueAt((soLuongAdd * donGiaSP), i, 4);
				
				// cập nhật lại số lượng trong db
				spBUS.capNhatSoLuongSP(key, -soLuong);
				spBUS.docListSanPham();
				loadingSanPham();
				tinhTongTien();
				return;
			}
		}
        
		Vector vec = new Vector();
		vec.add(ma);
		vec.add(ten);
		vec.add(soLuong);
		vec.add(donGia);
		int donGiaSP = Integer.parseInt(donGia);
		vec.add((soLuong * donGiaSP));
		model_gioHang.addRow(vec);
		tbl_gioHang.setModel(model_gioHang);
		int Ma =Integer.parseInt(ma);
		dsma.add(Ma);

		// cập nhật lại số lượng trong db
		spBUS.capNhatSoLuongSP(key, -soLuong);
		spBUS.docListSanPham();
		loadingSanPham();
		//căn giữa
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for(int x=0;x<tbl_gioHang.getColumnCount();x++){
	        tbl_gioHang.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
	   }
		tinhTongTien() ;  
	}//testthemgiohang*/
        
        private void themVaoGioHang() {
		tbl_gioHang.removeAll();
		int tongTien=0;
		int row = tbl_SP.getSelectedRow();
        if (row < 0) {
            return;
        }
        
        if(txt_MaSP.getText().equals("") || txt_TenSP.getText().equals("")||txt_donGia.getText().equals("") ) {
        	new MyDialog("Bạn chưa chọn sản phẩm !", MyDialog.ERROR_DIALOG);
        	return;
        }
        
        String ma = txt_MaSP.getText();      
        String ten = txt_TenSP.getText();
        String donGia = txt_donGia.getText();        
        int soLuong = Integer.parseInt(spi_soLuong.getValue() + "");        
        int soLuongConLai = Integer.parseInt(tbl_SP.getValueAt(tbl_SP.getSelectedRow(), 2) + "");

        if (soLuong > soLuongConLai || soLuongConLai <= 0) {
            new MyDialog("Sản phẩm đã hết hàng", MyDialog.ERROR_DIALOG);
            return;
        }

        txt_MaSP.setText("");
        txt_TenSP.setText("");
        txt_donGia.setText("");
        spi_soLuong.setValue(1);

        int key = Integer.parseInt(ma);
    
        Vector header = new Vector();
        header.add("Mã SP ");
        header.add("Tên SP ");
        header.add("Số lượng");  
        header.add("Đơn giá"); 
        header.add("Thành tiền"); 
        
        if (model_gioHang.getRowCount()==0){ 
        	model_gioHang=new DefaultTableModel(header,0){
                    @Override
                    public boolean isCellEditable(int row, int column){
                        return false;
                    }
                };
        } 
      
		for (int i = 0; i < tbl_gioHang.getRowCount(); i++) {
			String maTbl = tbl_gioHang.getValueAt(i, 0) + "";
			if (ma.equals(maTbl)) {
				int soLuongAdd = Integer.parseInt(tbl_gioHang.getValueAt(i, 2) + "");
				soLuongAdd += soLuong;
				int donGiaSP = Integer.parseInt(donGia);

				tbl_gioHang.setValueAt(soLuongAdd, i, 2);
				tbl_gioHang.setValueAt((soLuongAdd * donGiaSP), i, 4);
				
				// cập nhật lại số lượng trong db
				spBUS.capNhatSoLuongSP(key, -soLuong);
				spBUS.docListSanPham();
				loadingSanPham();
				tinhTongTien();
				return;
			}
		}
        
		Vector vec = new Vector();
		vec.add(ma);
		vec.add(ten);
		vec.add(soLuong);
		vec.add(donGia);
		int donGiaSP = Integer.parseInt(donGia);
		vec.add((soLuong * donGiaSP));
		model_gioHang.addRow(vec);
		tbl_gioHang.setModel(model_gioHang);
		int Ma =Integer.parseInt(ma);
		dsma.add(Ma);

		// cập nhật lại số lượng trong db
		spBUS.capNhatSoLuongSP(key, -soLuong);
		spBUS.docListSanPham();
		loadingSanPham();
		//căn giữa
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for(int x=0;x<tbl_gioHang.getColumnCount();x++){
	        tbl_gioHang.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
	   }
		tinhTongTien() ;  
	}
	private void timKiem() {
		String cbx_value = "" + cbx_LoaiSP.getItemAt(cbx_LoaiSP.getSelectedIndex());
		String Min = txt_giaMin.getText();
		String Max = txt_giaMax.getText();
		ArrayList<SanPham> dssp = spBUS.getListSanPham();
		
		int giaMin = 0;
		int giaMax = Integer.MAX_VALUE;
		if(!Min.equals(""))
			giaMin = Integer.parseInt(Min);
		if(!Max.equals(""))
			giaMax = Integer.parseInt(Max);
		

		model_SP.setRowCount(0);
		if (cbx_value.equals("All")) {
			for (SanPham sp : dssp) {
				if (sp.getDonGia() >= giaMin && sp.getDonGia() <= giaMax) {
					Vector row = new Vector();
					row.add(sp.getMaSP());
					row.add(sp.getTenSP());
					row.add(sp.getSoLuong());
					row.add(sp.getDonGia());
					String loai = lspBUS.getTenLoai(sp.getMaLoai());
					row.add(loai);
					row.add(sp.getHinhAnh());
					row.add(sp.getMoTa());
					model_SP.addRow(row);
					tbl_SP.setModel(model_SP);
				}
			}
		} else {
			for (SanPham sp : dssp) {
				String[] tenLoai = lspBUS.getTenLoai(sp.getMaLoai()).split(" - "); // lấy tên loại
				if (tenLoai[1].equals(cbx_value) && sp.getDonGia() >= giaMin && sp.getDonGia() <= giaMax) {

					Vector row = new Vector();
					row.add(sp.getMaSP());
					row.add(sp.getTenSP());
					row.add(sp.getSoLuong());
					row.add(sp.getDonGia());
					String loai = lspBUS.getTenLoai(sp.getMaLoai());
					row.add(loai);
					row.add(sp.getHinhAnh());
					row.add(sp.getMoTa());
					model_SP.addRow(row);
					tbl_SP.setModel(model_SP);
				}
			}
		}
	}
	private void clickTableGioHang() {
        int row = tbl_gioHang.getSelectedRow();
        if (row < 0) {
            return;
        }
        String ma = tbl_gioHang.getValueAt(row, 0) + "";              
        loadAnh("Image/" + spBUS.getAnh(ma));
    }
	private void xoaKhoiGioHang() {
		int row = tbl_gioHang.getSelectedRow();
        if (row > -1) {
            int ma = Integer.parseInt(tbl_gioHang.getValueAt(row, 0) + "");
            int soLuong = Integer.parseInt(tbl_gioHang.getValueAt(row, 2) + "");
            spBUS.capNhatSoLuongSP(ma, soLuong);
            loadingSanPham();
            model_gioHang.removeRow(row);
        }
        tinhTongTien() ;
	}
	public void tinhTongTien() {
		String[] magg = txt_GiamGia.getText().split(" - ");
		if (!txt_GiamGia.getText().equals("")) {
                        KhuyenMai km = kmBUS.getKMById(magg[0]);
                        tongphantram = km.getPhanTramGiam();
			int column = 4;
			tongTien = 0;
			tongTienSauKM = 0;
			for (int j = 0; j < model_gioHang.getRowCount(); j++) {
				tongTien += (int) (model_gioHang.getValueAt(j, column));				
			}
			
			int tongTienSauKM = tongTien - (tongTien * tongphantram / 100);
			txt_TongTien.setText(String.valueOf(tongTienSauKM+"  VNĐ"));
			txt_tongTienKM.setText((tongTien - tongTienSauKM)+"  VNĐ");
		}
		else {			
			tongTien=0;	
			tongTienSauKM=0;
			int column =4;
			for (int j = 0; j < model_gioHang.getRowCount(); j++) {
				tongTien += (int) (model_gioHang.getValueAt(j, column));				
			}
			tongTienSauKM = tongTien - (tongTien*tongphantram/100);
			txt_TongTien.setText(String.valueOf(tongTienSauKM+"  VNĐ"));
			txt_tongTienKM.setText((tongTien - tongTienSauKM)+"  VNĐ");
		}
	}
	
	private void thanhToan() {
	//	ArrayList<Vector> dsGioHang = new ArrayList<>();
        int row = tbl_gioHang.getRowCount();
        if (row == 0) {
        	JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm trước khi thanh toán !!!");
        	return;
        }
        String khachHang = (txt_maKH.getText()+" - "+txt_HoKH.getText() +" "+ txt_tenKH.getText());
        String nhanVien = txt_tenNV.getText(); 
       
       // int maGG[] = Integer.parseInt(txt_GiamGia.getText().split(" - "));
        for (int i = 0; i < row; i++) {
            Vector vec = new Vector();
            vec.add(tbl_gioHang.getValueAt(i, 0));
            vec.add(tbl_gioHang.getValueAt(i, 1));
            vec.add(tbl_gioHang.getValueAt(i, 2));
            vec.add(tbl_gioHang.getValueAt(i, 3));
            vec.add(tbl_gioHang.getValueAt(i, 4));           
            dsGioHang.add(vec);
        }

        DLG_HoaDon hoaDon = new DLG_HoaDon();
        hoaDon.setVisible(true);
        hoaDon.xuLyHienThiHoaDon(dsGioHang, tongTien, nhanVien, khachHang, tongphantram);
        model_gioHang.setRowCount(0);   
      
	}
	
	
	//Các hàm của Hóa Đơn
	private void loadingHoaDon() {
		
		model_HD.setRowCount(0);
		ArrayList<HoaDon> dshd = hoaDonBUS.getListHoaDon();
		Vector header = new Vector();
		header.add("Mã HĐ");
		header.add("Mã KH");
		header.add("Nhân Viên");
		header.add("Ngày Lập");
		header.add("Tổng Tiền");
		header.add("Ghi Chú");

		if (model_HD.getRowCount() == 0) {
			model_HD = new DefaultTableModel(header, 0){
                            @Override
                            public boolean isCellEditable(int row,int column){
                                return false;
                            }
                        };
		}
                int hhl=-1;
		for (HoaDon hd : dshd) {
			Vector row = new Vector();
                        hhl=hd.getMaHD();
			row.add(hhl);
			row.add(hd.getMaKH());
			row.add(hd.getMaNV());
			row.add(hd.getNgayLap());
			row.add(hd.getTongTien());
			row.add(hd.getGhiChu());
			
			model_HD.addRow(row);
		}
		table_HD.setModel(model_HD);
                maHD_Chuathanhtoan=hhl;//fix lại sau
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int x = 0; x < table_HD.getColumnCount(); x++) {
			table_HD.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
		}
	}

	private void clickTableHD() {
		int row = table_HD.getSelectedRow();
		if (row > -1) {
			String mahd = table_HD.getValueAt(row, 0) + "";
			String makh = table_HD.getValueAt(row, 1) + "";
			String manv = table_HD.getValueAt(row, 2) + "";
			String ngaylap = table_HD.getValueAt(row, 3) + "";
			String tongtien = table_HD.getValueAt(row, 4) + "";
			String ghichu = table_HD.getValueAt(row, 5) + "";
			// lấy dữ liệu lên textfiled
			txt_maHD2.setText(mahd);
			txt_maKH2.setText(makh);
			txt_NhanVien2.setText(manv);
			txt_NgayLap2.setText(ngaylap);
			txt_tongTien.setText(tongtien);
			txt_ghichu.setText(ghichu);
			// Lọc CTHĐ
			model_CTHD.setRowCount(0);
			;
			ArrayList<CTHoaDon> ds = CThoaDonBUS.getListCTHoaDon();
			for (CTHoaDon cthd : ds) {
				if (cthd.getMaHD() == Integer.parseInt(mahd)) {
					Vector header = new Vector();
					header.add("Mã HĐ");
					header.add("Mã SP");
					header.add("Số lượng");
					header.add("Đơn giá");
					header.add("Thành tiền");

					if (model_CTHD.getRowCount() == 0) {
						model_CTHD = new DefaultTableModel(header, 0);
					}

					Vector vec = new Vector();
					vec.add(cthd.getMaHD());
					vec.add(cthd.getMaSP());
					vec.add(cthd.getSoLuong());
					vec.add(cthd.getDonGia());
					vec.add(cthd.getThanhTien());
					model_CTHD.addRow(vec);
					table_CTHD.setModel(model_CTHD);
				}
			}
		}
	}
	private void timKiemHD() {
            String cbx_value = "" + cbx_timkiem.getSelectedItem();
            model_HD.setRowCount(0);
            ArrayList<HoaDon> dshd = hoaDonBUS.getListHoaDon();
            Vector header = new Vector();
			header.add("Mã HĐ");
			header.add("Mã KH");
			header.add("Nhân Viên");
			header.add("Ngày Lập");
			header.add("Tổng Tiền");
			header.add("Ghi Chú");

			if (model_HD.getRowCount() == 0) {
				model_HD = new DefaultTableModel(header, 0);
			}
			if(cbx_value.equalsIgnoreCase("Theo giá")) {
                            String start = txt_Min.getText();
                            String end = txt_Max.getText();
                            if(start.equals(""))
                                    start = "0";
                            int giaMax = Integer.MAX_VALUE;
                            if(end.equals(""))
                                    end = giaMax+"";
				ArrayList<HoaDon> listHoaDon = hoaDonBUS.getListHoaDonTheoGia(start,end);
				if(listHoaDon!=null) {
					for (HoaDon hd : listHoaDon) {
						Vector row = new Vector();
						row.add(hd.getMaHD());
						row.add(hd.getMaKH());
						row.add(hd.getMaNV());
						row.add(hd.getNgayLap());
						row.add(hd.getTongTien());
						row.add(hd.getGhiChu());

						model_HD.addRow(row);
						System.out.println(row);
					}
					table_HD.setModel(model_HD);
				}
			}
			if(cbx_value.equalsIgnoreCase("Theo ngày")) {
                                Date start = ngay_BD_find.getDate();
                                Date end = ngay_KT_find.getDate();
                                if(start == null || end == null) return;
				ArrayList<HoaDon> listHoaDon = hoaDonBUS.getListHoaDonTheoNgay(start,end);
				if(listHoaDon!=null) {
					for (HoaDon hd : listHoaDon) {
						Vector row = new Vector();
						row.add(hd.getMaHD());
						row.add(hd.getMaKH());
						row.add(hd.getMaNV());
						row.add(hd.getNgayLap());
						row.add(hd.getTongTien());
						row.add(hd.getGhiChu());
						model_HD.addRow(row);
					}
					table_HD.setModel(model_HD);
				}
			}
	}
	private void loadingCTHD() {
		model_CTHD.setRowCount(0);
		ArrayList<CTHoaDon> dscthd = CThoaDonBUS.getListCTHoaDon();
		Vector header = new Vector();
		header.add("Mã HĐ");
		header.add("Mã SP");
		header.add("Số lượng");
		header.add("Đơn giá");
		header.add("Thành tiền");

		if (model_CTHD.getRowCount() == 0) {
			model_CTHD = new DefaultTableModel(header, 0){
                            @Override
                            public boolean isCellEditable(int row,int column){
                                return false;
                            }
                        };
		}
		for (CTHoaDon hd : dscthd) {
			Vector row = new Vector();
			row.add(hd.getMaHD());
			row.add(hd.getMaSP());
			row.add(hd.getSoLuong());
			row.add(hd.getDonGia());
			row.add(hd.getThanhTien());			
			model_CTHD.addRow(row);
		}
		table_CTHD.setModel(model_CTHD);
		//CĂN GIỮA TABLE
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int x = 0; x < table_CTHD.getColumnCount(); x++) {
			table_CTHD.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
		}
	}
	private void clickTableCTHD() {
		int row = table_CTHD.getSelectedRow();
		if (row > -1) {
			String mahd = table_CTHD.getValueAt(row, 0) + "";
			String masp = table_CTHD.getValueAt(row, 1) + "";
			String soluong = table_CTHD.getValueAt(row, 2) + "";
			String dongia = table_CTHD.getValueAt(row, 3) + "";
			String thanhtien = table_CTHD.getValueAt(row, 4) + "";
			// lấy dữ liệu lên textfiled
			txt_maCTHD.setText(mahd);
			txt_maSPCTHD.setText(masp);
			txt_maSLCTHD.setText(soluong);
			txt_DGCTHD.setText(dongia);
			txt_thanhtienCTHD.setText(thanhtien);
		}
	}
	private void getTenNhanVien() {
		DangNhapBUS DN = new DangNhapBUS();
		int maNV = DN.taiKhoanLogin.getMaNhanVien();
		if(maNV==0)
			txt_tenNV.setText("Admin !");
		NhanVienBUS NV = new NhanVienBUS();
		ArrayList<NhanVien> dsnv = NV.getDanhSachNhanVien();
		for(NhanVien nv : dsnv) {
			if(nv.getMaNV() == maNV) {
				txt_tenNV.setText(nv.getMaNV()+" - "+nv.getHoNV()+" "+nv.getTenNV());
			}
		}
	}
        private void xoaKhoiGioHangTongQuat() {
            // Lặp qua tất cả các hàng trong bảng từ dưới lên để xóa
            int rowCount = tbl_gioHang.getRowCount();// Lấy tổng số hàng trong bảng
            if(rowCount==0){
                return;
            }
            for (int row = rowCount - 1; row >= 0; row--) {
                int ma = Integer.parseInt(tbl_gioHang.getValueAt(row, 0) + "");
                int soLuong = Integer.parseInt(tbl_gioHang.getValueAt(row, 2) + "");
                spBUS.capNhatSoLuongSP(ma, soLuong); // Cập nhật số lượng sản phẩm
                model_gioHang.removeRow(row); // Xóa hàng trong bảng
            }
            loadingSanPham(); // Tải lại danh sách sản phẩm
            tinhTongTien(); // Tính lại tổng tiền
        }
        public void clearForm(){
            txt_tongTien.setText("");
            txt_TongTien.setText("");
            txt_tongTienKM.setText("");
            lbl_hinhAnh.setIcon(null);
            tongTien = 0;
            tongTienSauKM = 0;
            dsma.clear();
            dsGioHang.clear();
        }
        private void cbx_method_find(){
            if(cbx_timkiem.getSelectedItem().equals("All")){
                btn_TimHD.setEnabled(false);
                txt_Min.setVisible(true);
                txt_Max.setVisible(true);
                txt_Min.setEnabled(false);
                txt_Max.setEnabled(false);
                ngay_BD_find.setVisible(false);
                ngay_KT_find.setVisible(false);
                lb_VND1.setVisible(false);
                lb_VND2.setVisible(false);
                loadingHoaDon();
            }else if(cbx_timkiem.getSelectedItem().equals("Theo giá")){
                btn_TimHD.setEnabled(true);
                txt_Min.setVisible(true);
                txt_Max.setVisible(true);
                txt_Min.setEnabled(true);
                txt_Max.setEnabled(true);
                ngay_BD_find.setVisible(false);
                ngay_KT_find.setVisible(false);
                lb_VND1.setVisible(true);
                lb_VND2.setVisible(true);
            }else{
                btn_TimHD.setEnabled(true);
                txt_Min.setVisible(false);
                txt_Max.setVisible(false);
                ngay_BD_find.setVisible(true);
                ngay_KT_find.setVisible(true);
                lb_VND1.setVisible(false);
                lb_VND2.setVisible(false);
            }
        }
	public void setKhachHang(String ma,String ho,String ten,String gioitinh) {		
		txt_HoKH.setText(ho);
		txt_tenKH.setText(ten);
		txt_maKH.setText(ma);
		txt_gioiTinh.setText(gioitinh);		
	}
	public void setKhuyenMai(String ma, String ten) {
                if(ma.equals("")||ten.equals(""))
                    txt_GiamGia.setText("");
                else
                    txt_GiamGia.setText(ma+" - "+ten);
	}
}
