package org.example.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import MyCustom.MyDialog;
import com.toedter.calendar.JDateChooser;
import org.example.BUS.*;
import org.example.DTO.CTPhieuNhap;
import org.example.DTO.NhanVien;
import org.example.DTO.PhieuNhap;
import org.example.DTO.SanPham;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
        

public class QL_NhapHang extends JFrame {

	private JPanel contentPane;
	private JTable tbl_KhoHang;
	private DefaultTableModel model_KhoHang = new DefaultTableModel();
	private JTextField txt_maSP;
	private JTextField txt_tenSP;
	private JTextField txt_donGia;
	private JTextField txt_timKiem;
	private JTable tbl_NhapHang;
	private DefaultTableModel model_NhapHang = new DefaultTableModel();
	private JTextField txt_NhanVien;
	public static JTextField txt_NCC;
	private JTextField txt_tongTien;
	private JTable tbl_PN;
	private DefaultTableModel model_PN = new DefaultTableModel();
	private JTextField txt_maPN;
	private JTextField txt_maNCC;
	private JTextField txt_maNV;
	private JTextField txt_ngayLap;
	private JTextField txt_tongTienPN;
	private JTextField txt_Min;
	private JTextField txt_Max;
	private JTable tbl_CTPN;
	private DefaultTableModel model_CTPN = new DefaultTableModel();
	private JTextField txt_maSPCT;
	private JTextField txt_maPNCT;
	private JTextField txt_soluongCT;
	private JTextField txt_donGiaCT;
	private JTextField txt_thanhTienCT;
	private JSpinner spi_soLuong;
	private int tongTien=0;
	private JButton btnNewButton, btn_chon;
        private DLG_ThemSP dlg_themSP;
        private JLabel lb_VND1, lb_VND2;
        private LocalDate today=LocalDate.now();
	JComboBox cbx_timkiem;
        JDateChooser ngay_BD_find,ngay_KT_find;
	SanPhamBUS spBUS = new SanPhamBUS();
	PhieuNhapBUS pnBUS = new PhieuNhapBUS();
	CTPhieuNhapBUS ctpnBUS = new CTPhieuNhapBUS();
	
	private ArrayList<Vector> dsPhieuNhap = new ArrayList<>();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					new MyConnect();
//					QL_NhapHang frame = new QL_NhapHang();
//					frame.setVisible(true);
//					frame.loadingKhoHang();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public QL_NhapHang() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(120, 120, 1200, 800);
		this.setLocationRelativeTo(null); //hiển thị giữa mà hình
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 20));
		tabbedPane.setBounds(10, 10, 1176, 753);
		contentPane.add(tabbedPane);
		
		JPanel pn_NhapHang = new JPanel();
		tabbedPane.addTab("Nhập Hàng", null, pn_NhapHang, null);
		pn_NhapHang.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 610, 642);
		pn_NhapHang.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 350, 610, 288);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 590, 266);
		panel_2.add(scrollPane);
		
		tbl_KhoHang = new JTable();
		tbl_KhoHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickTableKhoHang();
			}
		});
		scrollPane.setViewportView(tbl_KhoHang);
		tbl_KhoHang.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Mã Sản Phẩm", "Tên Sản Phẩm", "Hiện Còn"
			}
		));
		
		JLabel lblNewLabel = new JLabel("Kho Hàng");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(194, 10, 215, 24);
		panel.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Thông tin thể loại", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(0, 40, 610, 300);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mã SP :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 31, 77, 31);
		panel_3.add(lblNewLabel_1);
		
		txt_maSP = new JTextField();
		txt_maSP.setDisabledTextColor(Color.BLACK);
		txt_maSP.setEnabled(false);
		txt_maSP.setFont(new Font("Tahoma", Font.BOLD, 17));
		txt_maSP.setHorizontalAlignment(SwingConstants.CENTER);
		txt_maSP.setBounds(93, 31, 159, 31);
		panel_3.add(txt_maSP);
		txt_maSP.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên SP :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 70, 77, 31);
		panel_3.add(lblNewLabel_1_1);
		
		txt_tenSP = new JTextField();
		txt_tenSP.setDisabledTextColor(Color.BLACK);
		txt_tenSP.setEnabled(false);
		txt_tenSP.setFont(new Font("Tahoma", Font.BOLD, 17));
		txt_tenSP.setHorizontalAlignment(SwingConstants.CENTER);
		txt_tenSP.setColumns(10);
		txt_tenSP.setBounds(93, 72, 237, 31);
		panel_3.add(txt_tenSP);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Số lượng :");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(361, 31, 110, 31);
		panel_3.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Đơn giá :");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_2.setBounds(361, 70, 77, 31);
		panel_3.add(lblNewLabel_1_1_2);
		
		spi_soLuong = new JSpinner();
		spi_soLuong.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spi_soLuong.setFont(new Font("Tahoma", Font.BOLD, 16));
		spi_soLuong.setBounds(461, 29, 95, 31);
		panel_3.add(spi_soLuong);
		
		txt_donGia = new JTextField();
		txt_donGia.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txt_donGia.setText("1");
		txt_donGia.setHorizontalAlignment(SwingConstants.CENTER);
		txt_donGia.setColumns(10);
		txt_donGia.setBounds(458, 72, 142, 31);
		panel_3.add(txt_donGia);
		
		JLabel lblNewLabel_2 = new JLabel("Tìm kiếm :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(108, 238, 116, 31);
		panel_3.add(lblNewLabel_2);
		
		txt_timKiem = new JTextField();
		txt_timKiem.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        liveSearch();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                         liveSearch();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                         liveSearch();
                    }
                });
		
                txt_timKiem.setColumns(10);
		txt_timKiem.setBounds(221, 238, 237, 31);
		panel_3.add(txt_timKiem);
		
		JButton btn_themSP = new JButton("Thêm SP");
		btn_themSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dlg_themSP==null){
                                    dlg_themSP=new DLG_ThemSP();
                                }else{
                                    dlg_themSP.setVisible(true);
                                }
			}
		});
		btn_themSP.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_themSP.setBounds(75, 180, 150, 30);
		panel_3.add(btn_themSP);
                
		txt_timKiem.setColumns(10);
		txt_timKiem.setBounds(221, 238, 237, 31);
		panel_3.add(txt_timKiem);
		
		btn_chon = new JButton("Chọn nhập");
		btn_chon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chonSanPhamNhap();
			}
		});
		btn_chon.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_chon.setBounds(235, 120, 159, 39);
                btn_chon.setEnabled(false);
		panel_3.add(btn_chon);
                
                //Hien thi nut Refresh
                JButton btn_Refresh = new JButton("Bỏ chọn");
                btn_Refresh.setFont(new Font("Tahoma",Font.BOLD,18));
                btn_Refresh.setBounds(400, 180, 150, 30);
                panel_3.add(btn_Refresh);
                
                btn_Refresh.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        refreshData();
                    }
                });
                
                //
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(630, 10, 531, 642);
		pn_NhapHang.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btn_NhapHang = new JButton("Xác Nhận");
		btn_NhapHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//thêm phiếu nhập vào csdl
                            if(tbl_KhoHang.getSelectedRow()==-1){
                                new MyDialog("Vui lòng chọn sản phẩm cần nhập!", MyDialog.ERROR_DIALOG);
                                return;
                            }
                            if(!txt_NCC.getText().equals("")){
                                String maNV[] = txt_NhanVien.getText().split(" - ");
                                String maNCC[] = txt_NCC.getText().split(" - ");
                                pnBUS.themPhieuNhap(maNCC[0], maNV[0], tongTien);
                                //Nhập hàng
                                nhapHang();
                                //thêm chi tiết phiếu nhập vào cơ sở dữ liệu
                                 for (Vector vec : dsPhieuNhap) {
                                     String maSP = vec.get(0) + "";
                                     String soLuong = vec.get(2) + "";
                                     String donGia = vec.get(3) + "";
                                     String thanhTien = vec.get(4) + "";		       
                                     ctpnBUS.luuCTPhieuNhap(maSP,soLuong,donGia,thanhTien);
                                 }
                                 //loading Table
                                 loadingPhieuNhap();
                                 loadingCTPhieuNhap();
                                 loadingKhoHang();

                                 //Xóa thông tin nhập hàng cũ
                                 txt_maSP.setText("");
                                 txt_tenSP.setText("");
                                 txt_donGia.setText("1");
                                 spi_soLuong.setValue(1);

                                 txt_NCC.setText("");
                                 txt_tongTien.setText("");
                                 dsPhieuNhap.clear();
                                 
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhà cung cấp!");
                            }
			}
		});
		btn_NhapHang.setFont(new Font("Dialog", Font.BOLD, 20));
		btn_NhapHang.setBounds(279, 572, 146, 34);
		panel_1.add(btn_NhapHang);
		
		JLabel lblNhpHng = new JLabel("Nhập Hàng");
		lblNhpHng.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhpHng.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNhpHng.setBounds(180, 10, 215, 24);
		panel_1.add(lblNhpHng);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Hàng chờ nhập", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_4.setBounds(10, 266, 511, 288);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 28, 491, 247);
		panel_4.add(scrollPane_1);
		
		tbl_NhapHang = new JTable();
		tbl_NhapHang.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Thành tiền"
			}
		));
		scrollPane_1.setViewportView(tbl_NhapHang);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Thông tin phiếu nhập", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(10, 44, 511, 212);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nhân Viên :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(78, 24, 105, 31);
		panel_5.add(lblNewLabel_1_2);
		
		txt_NhanVien = new JTextField();
		txt_NhanVien.setDisabledTextColor(Color.BLACK);
		txt_NhanVien.setEnabled(false);
		txt_NhanVien.setFont(new Font("Tahoma", Font.BOLD, 17));
		txt_NhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		txt_NhanVien.setColumns(10);
		txt_NhanVien.setBounds(193, 26, 202, 31);
		panel_5.add(txt_NhanVien);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Nhà Cung Cấp :");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2_1.setBounds(51, 78, 157, 31);
		panel_5.add(lblNewLabel_1_2_1);
		
		txt_NCC = new JTextField();
		txt_NCC.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txt_NCC.setHorizontalAlignment(SwingConstants.CENTER);
		txt_NCC.setColumns(10);
		txt_NCC.setBounds(193, 80, 202, 31);
                txt_NCC.setEnabled(false);
		panel_5.add(txt_NCC);
		
		JButton btn_timNCC = new JButton("Tìm");
		btn_timNCC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DLG_ChonNhaCungCap cncc = new DLG_ChonNhaCungCap();
				cncc.setVisible(true);
			}
		});
		btn_timNCC.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_timNCC.setBounds(405, 78, 85, 31);
		panel_5.add(btn_timNCC);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Tổng Tiền :");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2_1_1.setBounds(78, 135, 105, 31);
		panel_5.add(lblNewLabel_1_2_1_1);
		
		txt_tongTien = new JTextField();
		txt_tongTien.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txt_tongTien.setHorizontalAlignment(SwingConstants.CENTER);
		txt_tongTien.setColumns(10);
		txt_tongTien.setBounds(193, 137, 202, 31);
                txt_tongTien.setEnabled(false);
		panel_5.add(txt_tongTien);
		
		JLabel lblNewLabel_5 = new JLabel("VNĐ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setBounds(405, 142, 45, 24);
		panel_5.add(lblNewLabel_5);
		
		JButton btn_Xoa = new JButton("Xóa");
		btn_Xoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaSPNhap();
			}
		});
		btn_Xoa.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_Xoa.setBounds(123, 572, 146, 34);
		panel_1.add(btn_Xoa);
		
		JButton btn_home = new JButton("Trở Về Trang Chính");
		btn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFormGUI MF = new MainFormGUI();
				MF.setVisible(true);
				dispose();
			}
		});
		btn_home.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_home.setBounds(502, 657, 265, 47);
		pn_NhapHang.add(btn_home);
		
		JPanel pn_PhieuNhap = new JPanel();
		tabbedPane.addTab("Phiếu Nhập", null, pn_PhieuNhap, null);
		pn_PhieuNhap.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_6.setBounds(10, 10, 632, 637);
		pn_PhieuNhap.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Phiếu Nhập");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(198, 10, 160, 27);
		panel_6.add(lblNewLabel_3);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(10, 286, 612, 341);
		panel_6.add(panel_8);
		panel_8.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 10, 592, 321);
		panel_8.add(scrollPane_2);
		
		tbl_PN = new JTable();
		tbl_PN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickTablePhieuNhap();
			}
		});
		tbl_PN.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Mã PN", "Mã NCC", "Mã NV", "Ngày Lập", "Tổng tiền"
			}
		));
		scrollPane_2.setViewportView(tbl_PN);
		
		JLabel lblNewLabel_4 = new JLabel("Mã PN :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4.setBounds(10, 54, 82, 27);
		panel_6.add(lblNewLabel_4);
		
		txt_maPN = new JTextField();
		txt_maPN.setHorizontalAlignment(SwingConstants.CENTER);
		txt_maPN.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_maPN.setBounds(102, 56, 127, 26);
                txt_maPN.setEnabled(false);
		panel_6.add(txt_maPN);
		txt_maPN.setColumns(10);
		
		JLabel lblNewLabel_4_1 = new JLabel("Mã NCC :");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4_1.setBounds(10, 91, 82, 27);
		panel_6.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("Mã NV :");
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4_2.setBounds(10, 128, 82, 27);
		panel_6.add(lblNewLabel_4_2);
		
		txt_maNCC = new JTextField();
		txt_maNCC.setHorizontalAlignment(SwingConstants.CENTER);
		txt_maNCC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_maNCC.setColumns(10);
		txt_maNCC.setBounds(102, 93, 127, 26);
                txt_maNCC.setEnabled(false);
		panel_6.add(txt_maNCC);
		
		txt_maNV = new JTextField();
		txt_maNV.setHorizontalAlignment(SwingConstants.CENTER);
		txt_maNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_maNV.setColumns(10);
		txt_maNV.setBounds(102, 130, 127, 26);
                txt_maNV.setEnabled(false);
		panel_6.add(txt_maNV);
		
		JLabel lblNewLabel_4_3 = new JLabel("Ngày lập :");
		lblNewLabel_4_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4_3.setBounds(275, 54, 103, 27);
		panel_6.add(lblNewLabel_4_3);
		
		txt_ngayLap = new JTextField();
		txt_ngayLap.setHorizontalAlignment(SwingConstants.CENTER);
		txt_ngayLap.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_ngayLap.setColumns(10);
                txt_ngayLap.setEnabled(false);
		txt_ngayLap.setBounds(388, 56, 127, 26);
		panel_6.add(txt_ngayLap);
		
		JLabel lblNewLabel_4_3_1 = new JLabel("Tổng tiền :");
		lblNewLabel_4_3_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4_3_1.setBounds(275, 91, 103, 27);
		panel_6.add(lblNewLabel_4_3_1);
		
		txt_tongTienPN = new JTextField();
		txt_tongTienPN.setHorizontalAlignment(SwingConstants.CENTER);
		txt_tongTienPN.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_tongTienPN.setColumns(10);
                txt_tongTienPN.setEnabled(false);
		txt_tongTienPN.setBounds(386, 93, 127, 26);
		panel_6.add(txt_tongTienPN);
		
		JLabel lblNewLabel_4_2_1 = new JLabel("Từ :");
		lblNewLabel_4_2_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4_2_1.setBounds(170, 196, 40, 27);
		panel_6.add(lblNewLabel_4_2_1);
		
		JLabel lblNewLabel_4_2_2 = new JLabel("Đến :");
		lblNewLabel_4_2_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4_2_2.setBounds(400, 196, 50, 27);
		panel_6.add(lblNewLabel_4_2_2);
		
		txt_Min = new JTextField();
		txt_Min.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_Min.setColumns(10);
		txt_Min.setBounds(210, 196, 127, 26);
                txt_Min.setEnabled(false);
		panel_6.add(txt_Min);
		
		txt_Max = new JTextField();
		txt_Max.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_Max.setColumns(10);
		txt_Max.setBounds(450, 196, 127, 26);
                txt_Max.setEnabled(false);
		panel_6.add(txt_Max);
                
                ngay_BD_find=new JDateChooser();
                ngay_BD_find.setDateFormatString("dd/MM/yyyy");
                ngay_BD_find.getDateEditor().getUiComponent().setFont(new Font("Tahoma",Font.PLAIN,16));
                ngay_BD_find.setBounds(210, 196, 140, 26);
                ngay_BD_find.setVisible(false);
                panel_6.add(ngay_BD_find);
                
                ngay_KT_find=new JDateChooser();
                ngay_KT_find.setDateFormatString("dd/MM/yyyy");
                ngay_KT_find.getDateEditor().getUiComponent().setFont(new Font("Tahoma",Font.PLAIN,16));
		ngay_KT_find.setBounds(450, 196, 140, 26);
                ngay_KT_find.setVisible(false);
                panel_6.add(ngay_KT_find);
                
            try {
                ngay_BD_find.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(today.toString()));
                ngay_KT_find.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(today.toString()));
            } catch (ParseException ex) {
                Logger.getLogger(QL_NhapHang.class.getName()).log(Level.SEVERE, null, ex);
            }
                
		cbx_timkiem = new JComboBox();
		cbx_timkiem.setFont(new Font("Tahoma", Font.BOLD, 18));
		cbx_timkiem.setModel(new DefaultComboBoxModel(new String[] {"All", "Theo giá", "Theo ngày"}));
		cbx_timkiem.setBounds(23, 196, 127, 27);
		panel_6.add(cbx_timkiem);
		cbx_timkiem.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        cbx_method_find();
                    }
                });
                
                
		JButton btn_timPN = new JButton("Tìm");
		btn_timPN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemPN();
			}
		});
		btn_timPN.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_timPN.setBounds(260, 245, 118, 31);
		panel_6.add(btn_timPN);
		
		lb_VND1 = new JLabel("VND");
		lb_VND1.setHorizontalAlignment(SwingConstants.CENTER);
		lb_VND1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lb_VND1.setBounds(340, 196, 26, 26);
                lb_VND1.setVisible(false);
		panel_6.add(lb_VND1);
		
		lb_VND2 = new JLabel("VND");
		lb_VND2.setHorizontalAlignment(SwingConstants.CENTER);
		lb_VND2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lb_VND2.setBounds(580, 196, 26, 26);
                lb_VND2.setVisible(false);
		panel_6.add(lb_VND2);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_7.setBounds(652, 10, 509, 637);
		pn_PhieuNhap.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_3_1 = new JLabel("Chi Tiết Phiếu Nhập");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3_1.setBounds(192, 10, 199, 27);
		panel_7.add(lblNewLabel_3_1);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(10, 291, 489, 336);
		panel_7.add(panel_9);
		panel_9.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 10, 469, 316);
		panel_9.add(scrollPane_3);
		
		tbl_CTPN = new JTable();
		tbl_CTPN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickTableCTPN();
			}
		});
		tbl_CTPN.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Mã PN", "Mã SP", "Số Lượng","Đơn Giá", "Thành tiền"
			}
		){
                    @Override
                    public boolean isCellEditable(int row,int column){
                        return false;
                    }
                });
		scrollPane_3.setViewportView(tbl_CTPN);
		
		JLabel lblNewLabel_4_4 = new JLabel("Sản phẩm :");
		lblNewLabel_4_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4_4.setBounds(131, 104, 116, 27);
		panel_7.add(lblNewLabel_4_4);
		
		txt_maSPCT = new JTextField();
		txt_maSPCT.setHorizontalAlignment(SwingConstants.CENTER);
		txt_maSPCT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_maSPCT.setColumns(10);
                txt_maSPCT.setEnabled(false);
		txt_maSPCT.setBounds(246, 106, 186, 26);
		panel_7.add(txt_maSPCT);
		
		JLabel lblNewLabel_4_4_1 = new JLabel("Mã PN :");
		lblNewLabel_4_4_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4_4_1.setBounds(131, 67, 90, 27);
		panel_7.add(lblNewLabel_4_4_1);
		
		txt_maPNCT = new JTextField();
		txt_maPNCT.setHorizontalAlignment(SwingConstants.CENTER);
		txt_maPNCT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_maPNCT.setColumns(10);
		txt_maPNCT.setBounds(246, 67, 186, 26);
                txt_maPNCT.setEnabled(false);
		panel_7.add(txt_maPNCT);
		
		txt_soluongCT = new JTextField();
		txt_soluongCT.setHorizontalAlignment(SwingConstants.CENTER);
		txt_soluongCT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_soluongCT.setColumns(10);
		txt_soluongCT.setBounds(246, 141, 186, 26);
                txt_soluongCT.setEnabled(false);
		panel_7.add(txt_soluongCT);
		
		txt_donGiaCT = new JTextField();
		txt_donGiaCT.setHorizontalAlignment(SwingConstants.CENTER);
		txt_donGiaCT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_donGiaCT.setColumns(10);
		txt_donGiaCT.setBounds(246, 177, 186, 26);
                txt_donGiaCT.setEnabled(false);
		panel_7.add(txt_donGiaCT);
		
		txt_thanhTienCT = new JTextField();
		txt_thanhTienCT.setHorizontalAlignment(SwingConstants.CENTER);
		txt_thanhTienCT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_thanhTienCT.setColumns(10);
		txt_thanhTienCT.setBounds(246, 213, 186, 26);
                txt_thanhTienCT.setEnabled(false);
		panel_7.add(txt_thanhTienCT);
		
		JLabel lblNewLabel_4_4_2 = new JLabel("Số lượng :");
		lblNewLabel_4_4_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4_4_2.setBounds(131, 141, 116, 27);
		panel_7.add(lblNewLabel_4_4_2);
		
		JLabel lblNewLabel_4_4_3 = new JLabel("Đơn giá :");
		lblNewLabel_4_4_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4_4_3.setBounds(131, 177, 116, 27);
		panel_7.add(lblNewLabel_4_4_3);
		
		JLabel lblNewLabel_4_4_4 = new JLabel("Thành tiền :");
		lblNewLabel_4_4_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4_4_4.setBounds(131, 213, 116, 27);
		panel_7.add(lblNewLabel_4_4_4);
		
		btnNewButton = new JButton("Làm mới");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadingCTPhieuNhap();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton.setBounds(394, 270, 105, 21);
		panel_7.add(btnNewButton);
		
		JButton btn_home1 = new JButton("Trở Về Trang Chính");
		btn_home1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFormGUI MF = new MainFormGUI();
				MF.setVisible(true);
				dispose();
			}
		});
		btn_home1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_home1.setBounds(502, 657, 265, 47);
		pn_PhieuNhap.add(btn_home1);
		
		getTenNhanVien();
		loadingKhoHang();
		loadingPhieuNhap();
		loadingCTPhieuNhap();
	}
	private void loadingKhoHang() {
		spBUS.docListSanPham();
		model_KhoHang.setRowCount(0);
		ArrayList<SanPham>dssp = spBUS.getListSanPham();
		Vector header = new Vector();
            header.add("Mã SP");
            header.add("Tên SP");
            header.add("Còn lại");
        
            if (model_KhoHang.getRowCount()==0){ 
                    model_KhoHang=new DefaultTableModel(header,0){
                        @Override
                        public boolean isCellEditable(int row,int column){
                            return false;
                        }
                    };
            } 
                    for( SanPham sp : dssp) {
                            Vector row = new Vector();
                            row.add(sp.getMaSP());
                            row.add(sp.getTenSP());		      
                            row.add(sp.getSoLuong());		      
                            model_KhoHang.addRow(row); 
                            tbl_KhoHang.setModel(model_KhoHang);
                    }
	}
	private void clickTableKhoHang() {
		int row = tbl_KhoHang.getSelectedRow();
        if (row > -1) {
            String ma = tbl_KhoHang.getValueAt(row, 0) + "";
            String ten = tbl_KhoHang.getValueAt(row, 1) + "";                     	            	         
            //lấy dữ liệu lên textfiled           
            txt_maSP.setText(ma);
            txt_tenSP.setText(ten);
            txt_donGia.setText("0");
            btn_chon.setEnabled(true);
        }
	}
	private void liveSearch() {
		String text = txt_timKiem.getText();
		model_KhoHang.setRowCount(0);
		ArrayList<SanPham> dssp = spBUS.getListSanPham();
		Vector header = new Vector();
                header.add("Mã SP");
                header.add("Tên SP");
                header.add("Còn lại");
        
        if (model_KhoHang.getRowCount()==0){ 
        	model_KhoHang=new DefaultTableModel(header,0);
        } 
		for (SanPham sp : dssp) {
			if (String.valueOf(sp.getMaSP()).equalsIgnoreCase(text) || sp.getTenSP().contains(text) ||String.valueOf(sp.getSoLuong()).equalsIgnoreCase(text)) {
				Vector row = new Vector();
				row.add(sp.getMaSP());
                                row.add(sp.getTenSP());		      
                                row.add(sp.getSoLuong());	
				model_KhoHang.addRow(row);
				tbl_KhoHang.setModel(model_KhoHang);
			}
		}			
	}
	private void chonSanPhamNhap() {
        if(tbl_KhoHang.getSelectedRow()==-1){
            new MyDialog("Vui lòng chọn sản phẩm cần nhập!", MyDialog.ERROR_DIALOG);
            return;
        }
	tbl_NhapHang.removeAll();
	int tongTien=0;
	int row = tbl_KhoHang.getSelectedRow();
        
        String ma = txt_maSP.getText();      
        SanPham sp= spBUS.getSanPham(ma);       
        int soLuong = Integer.parseInt(spi_soLuong.getValue() + "");
        if(soLuong>500){
            new MyDialog("Số lượng quá lớn, số lương tối đa là 500. Vui lòng xem xét lại!",MyDialog.ERROR_DIALOG);
            return;
        }
        int giaNhap;
        String temp=txt_donGia.getText();
        if(temp.equals("")){
            new MyDialog("Đơn giá không được để trống, vui lòng nhập!",MyDialog.ERROR_DIALOG);
            return;
        }
        try{
            giaNhap = Integer.parseInt(temp);
            if(giaNhap<1000){
                new MyDialog("Đơn giá quá nhỏ, vui lòng xem xét lại!",MyDialog.ERROR_DIALOG);
                return;
            }else if(giaNhap>10000000){
                new MyDialog("Đơn giá quá lớn, vui lòng xem xét lại!",MyDialog.ERROR_DIALOG);
                return;
            }
        }catch(NumberFormatException e){
            new MyDialog("Đơn giá chỉ được nhập số, vui lòng nhập lại!",MyDialog.ERROR_DIALOG);
            return;
        }

    
        Vector header = new Vector();
        header.add("Mã SP ");
        header.add("Tên SP ");
        header.add("Số lượng");  
        header.add("Đơn giá"); 
        header.add("Thành tiền"); 
        
        if (model_NhapHang.getRowCount()==0){
        	model_NhapHang=new DefaultTableModel(header,0);
        } 
      
		for (int i = 0; i < tbl_NhapHang.getRowCount(); i++) {
			String maTbl = tbl_NhapHang.getValueAt(i, 0) + "";
			if (ma.equals(maTbl)) {
				int soLuongAdd = Integer.parseInt(tbl_NhapHang.getValueAt(i, 2) + "");
				soLuongAdd += soLuong;
				int donGiaSP = giaNhap;
                                tbl_NhapHang.setValueAt(donGiaSP,i,3);
				tbl_NhapHang.setValueAt(soLuongAdd, i, 2);
				tbl_NhapHang.setValueAt((soLuongAdd * donGiaSP), i, 4);
				return;
			}
		}
        
		Vector vec = new Vector();
		vec.add(ma);
		vec.add(sp.getTenSP());
		vec.add(soLuong);
		vec.add(giaNhap);
		vec.add((soLuong * giaNhap));
		model_NhapHang.addRow(vec);
		tbl_NhapHang.setModel(model_NhapHang);	
		
		//hiển thị tổng tiền sau khi chọn
		txt_tongTien.setText(String.valueOf(tinhTongTien()));
	}
	private int tinhTongTien() {
		tongTien=0;
		for (int i = 0; i < tbl_NhapHang.getRowCount(); i++) {
			String thanhTien =  tbl_NhapHang.getValueAt(i, 4)+"";
			tongTien += Integer.parseInt(thanhTien);
		}
		return tongTien;
	}
	private void xoaSPNhap() {
		int row = tbl_NhapHang.getSelectedRow();
        if (row > -1) {
            model_NhapHang.removeRow(row);
        }
        txt_tongTien.setText(String.valueOf(tinhTongTien()));
	}
	private void getTenNhanVien() {
		DangNhapBUS DN = new DangNhapBUS();
		int maNV = DN.taiKhoanLogin.getMaNhanVien();
		if(maNV==0)
			txt_NhanVien.setText("Admin !");
		NhanVienBUS NV = new NhanVienBUS();
		ArrayList<NhanVien> dsnv = NV.getDanhSachNhanVien();
		for(NhanVien nv : dsnv) {
			if(nv.getMaNV() == maNV) {
				txt_NhanVien.setText(nv.getMaNV()+" - "+nv.getHoNV()+" "+nv.getTenNV());
			}
		}
	}
	private void loadingPhieuNhap() {
                txt_NCC.setText("");
		pnBUS.docDanhSach();
		model_PN.setRowCount(0);
		ArrayList<PhieuNhap> dspn = pnBUS.getListPhieuNhap();
		Vector header = new Vector();
        header.add("Mã PN");
        header.add("Mã NCC");
        header.add("Mã NV");
        header.add("Ngày lập");
        header.add("Tổng Tiền");
        
        if (model_PN.getRowCount()==0){ 
        	model_PN=new DefaultTableModel(header,0){
                    @Override
                    public boolean isCellEditable(int row, int column){
                        return false;
                    }
                };
        }
        if(dspn==null) return;
		for( PhieuNhap pn : dspn) {
		        Vector row = new Vector();
		        row.add(pn.getMaPN());
		        row.add(pn.getMaNCC());		      
		        row.add(pn.getMaNV());	
		        row.add(pn.getNgayLap());		      
		        row.add(pn.getTongTien());	
		        model_PN.addRow(row); 
		        tbl_PN.setModel(model_PN);
		}
	}
	private void loadingCTPhieuNhap() {
		ctpnBUS.docDanhSach();
		model_CTPN.setRowCount(0);
		ArrayList<CTPhieuNhap> dspn = ctpnBUS.getListPhieuNhap();
		Vector header = new Vector();
                header.add("Mã PN");
                header.add("Mã SP");
                header.add("Số lượng");
                header.add("Đơn giá");
                header.add("Thành Tiền");
        
            if (model_CTPN.getRowCount()==0){ 
        	model_CTPN=new DefaultTableModel(header,0){
                    @Override
                    public boolean isCellEditable(int row,int column){
                        return false;
                    }
                };
            } 
            for( CTPhieuNhap pn : dspn) {
                Vector row = new Vector();
                row.add(pn.getMaPN());
                row.add(pn.getMaSP());		      
		row.add(pn.getSoLuong());	
		row.add(pn.getDonGia());		      
		row.add(pn.getThanhTien());	
		model_CTPN.addRow(row); 
		tbl_CTPN.setModel(model_CTPN);
            }
            txt_maPNCT.setText("");
            txt_maSPCT.setText("");
            txt_soluongCT.setText("");
            txt_donGiaCT.setText("");
            txt_thanhTienCT.setText("");
	}
	private void clickTablePhieuNhap() {
		int row = tbl_PN.getSelectedRow();
        if (row > -1) {
            String maPN = tbl_PN.getValueAt(row, 0) + "";
            String maNCC = tbl_PN.getValueAt(row, 1) + ""; 
            String maNV = tbl_PN.getValueAt(row, 2) + "";
            String ngaylap = tbl_PN.getValueAt(row, 3) + "";   
            String tongTien = tbl_PN.getValueAt(row, 4) + "";     
            //lấy dữ liệu lên textfiled           
            txt_maPN.setText(maPN);
            txt_maNCC.setText(maNCC);  
            txt_maNV.setText(maNV);  
            txt_ngayLap.setText(ngaylap);
            txt_tongTienPN.setText(tongTien);
            
            // Lọc CTPN
            model_CTPN.setRowCount(0);
            ArrayList<CTPhieuNhap> ds = ctpnBUS.getListPhieuNhap();
	for (CTPhieuNhap ctpn : ds) {
				if (ctpn.getMaPN() == Integer.parseInt(maPN)) {
					Vector header = new Vector();
			        header.add("Mã PN");
			        header.add("Mã SP");
			        header.add("Số lượng");
			        header.add("Đơn giá");
			        header.add("Thành Tiền");

					if (model_CTPN.getRowCount() == 0) {
						model_CTPN = new DefaultTableModel(header, 0){
                                                    @Override
                                                    public boolean isCellEditable(int row, int column){
                                                        return false;
                                                    }
                                                };
					}

					Vector vec = new Vector();
					vec.add(ctpn.getMaPN());
					vec.add(ctpn.getMaSP());
					vec.add(ctpn.getSoLuong());
					vec.add(ctpn.getDonGia());
					vec.add(ctpn.getThanhTien());
					model_CTPN.addRow(vec);
					tbl_CTPN.setModel(model_CTPN);
				}
			}		for (CTPhieuNhap ctpn : ds) {
				if (ctpn.getMaPN() == Integer.parseInt(maPN)) {
					Vector header = new Vector();
			        header.add("Mã PN");
			        header.add("Mã SP");
			        header.add("Số lượng");
			        header.add("Đơn giá");
			        header.add("Thành Tiền");

					if (model_CTPN.getRowCount() == 0) {
						model_CTPN = new DefaultTableModel(header, 0){
                                                    @Override
                                                    public boolean isCellEditable(int row, int column){
                                                        return false;
                                                    }
                                                };
					}

					Vector vec = new Vector();
					vec.add(ctpn.getMaPN());
					vec.add(ctpn.getMaSP());
					vec.add(ctpn.getSoLuong());
					vec.add(ctpn.getDonGia());
					vec.add(ctpn.getThanhTien());
					model_CTPN.addRow(vec);
					tbl_CTPN.setModel(model_CTPN);
				}
			}
        }
	}
	private void clickTableCTPN() {
		int row = tbl_CTPN.getSelectedRow();
        if (row > -1) {
            String maPN = tbl_CTPN.getValueAt(row, 0) + "";
            String maSP = tbl_CTPN.getValueAt(row, 1) + ""; 
            String soluong = tbl_CTPN.getValueAt(row, 2) + "";
            String dongia = tbl_CTPN.getValueAt(row, 3) + "";   
            String thanhTien = tbl_CTPN.getValueAt(row, 4) + "";     
            //lấy dữ liệu lên textfiled              
            txt_maPNCT.setText(maPN);
            String tenSP = spBUS.getTenSP(Integer.parseInt(maSP));
            txt_maSPCT.setText(tenSP);  
            txt_soluongCT.setText(soluong);  
            txt_donGiaCT.setText(dongia);
            txt_thanhTienCT.setText(thanhTien);
        }
	}
	private void timKiemPN() {
		String cbx_value = cbx_timkiem.getSelectedItem()+"";
		model_PN.setRowCount(0);
		ArrayList<PhieuNhap> dspn = pnBUS.getListPhieuNhap();
		Vector header = new Vector();
	        header.add("Mã PN");
	        header.add("Mã NCC");
	        header.add("Mã NV");
	        header.add("Ngày lập");
	        header.add("Tổng Tiền");

			if (model_PN.getRowCount() == 0) {
				model_PN = new DefaultTableModel(header, 0);
			}
			if(cbx_value.equalsIgnoreCase("Theo giá")) {
                                String start = txt_Min.getText();
                                String end = txt_Max.getText();
                                if(start.equals(""))
                                        start = "0";
                                int giaMax = Integer.MAX_VALUE;
                                if(end.equals(""))
                                        end = giaMax+"";
				ArrayList<PhieuNhap> listPhieuNhap = pnBUS.getListPhieuNhapTheoGia(start,end);
				if(listPhieuNhap!=null) {
					for (PhieuNhap pn : listPhieuNhap) {
						Vector row = new Vector();
						row.add(pn.getMaPN());
						row.add(pn.getMaNCC());
						row.add(pn.getMaNV());
						row.add(pn.getNgayLap());
						row.add(pn.getTongTien());						

						model_PN.addRow(row);						
					}
					tbl_PN.setModel(model_PN);
				}
			}
			if(cbx_value.equalsIgnoreCase("Theo ngày")) {
				ArrayList<PhieuNhap> listPhieuNhap = pnBUS.getListPhieuNhapTheoNgay(ngay_BD_find.getDate(),ngay_KT_find.getDate());
				if(listPhieuNhap!=null) {
					for (PhieuNhap pn : listPhieuNhap) {
						Vector row = new Vector();
						row.add(pn.getMaPN());
						row.add(pn.getMaNCC());
						row.add(pn.getMaNV());
						row.add(pn.getNgayLap());
						row.add(pn.getTongTien());	

						model_PN.addRow(row);
						System.out.println(row);
					}
					tbl_PN.setModel(model_PN);
				}
			}
	}
        
        private void cbx_method_find(){
            if(cbx_timkiem.getSelectedItem().equals("All")){
                txt_Min.setVisible(true);
                txt_Max.setVisible(true);
                txt_Min.setEnabled(false);
                txt_Max.setEnabled(false);
                ngay_BD_find.setVisible(false);
                ngay_KT_find.setVisible(false);
                lb_VND1.setVisible(false);
                lb_VND2.setVisible(false);
                loadingPhieuNhap();
            }else if(cbx_timkiem.getSelectedItem().equals("Theo giá")){
                txt_Min.setVisible(true);
                txt_Max.setVisible(true);
                txt_Min.setEnabled(true);
                txt_Max.setEnabled(true);
                ngay_BD_find.setVisible(false);
                ngay_KT_find.setVisible(false);
                lb_VND1.setVisible(true);
                lb_VND2.setVisible(true);
            }else{
                txt_Min.setVisible(false);
                txt_Max.setVisible(false);
                ngay_BD_find.setVisible(true);
                ngay_KT_find.setVisible(true);
                lb_VND1.setVisible(false);
                lb_VND2.setVisible(false);
            }
        }
        
        private void refreshData(){
            txt_maSP.setText("");
            txt_tenSP.setText("");
            txt_donGia.setText("0");
            spi_soLuong.setValue(1);
            btn_chon.setEnabled(false);
            loadingKhoHang();
        }    
	private void nhapHang() {
        int row = tbl_NhapHang.getRowCount();
        if (row == 0) {
        	JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm trước khi nhập hàng !!!");
        	return;
        }
        if(txt_NCC.getText().equals("")) {
        	JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp !!!");
        	return;
        }
        String nhacungcap = txt_NCC.getText();
        String nhanVien = txt_NhanVien.getText();        
       
        for (int i = 0; i < row; i++) {
            Vector vec = new Vector();
            vec.add(tbl_NhapHang.getValueAt(i, 0));
            vec.add(tbl_NhapHang.getValueAt(i, 1));
            vec.add(tbl_NhapHang.getValueAt(i, 2));
            vec.add(tbl_NhapHang.getValueAt(i, 3));
            vec.add(tbl_NhapHang.getValueAt(i, 4));           
            dsPhieuNhap.add(vec);
        }

        DLG_PhieuNhap hoaDon = new DLG_PhieuNhap();
        hoaDon.setVisible(true);
        hoaDon.xuLyHienThiPhieuNhap(dsPhieuNhap, tongTien, nhanVien, nhacungcap);
        model_NhapHang.setRowCount(0);
	}
}
