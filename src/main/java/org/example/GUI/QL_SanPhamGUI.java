package org.example.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import MyCustom.MyDialog;
import MyCustom.MyFileChooser;
import org.example.BUS.LoaiSanPhamBUS;
import org.example.BUS.NhaCungCapBUS;
import org.example.BUS.SanPhamBUS;
import org.example.BUS.TacGiaBUS;
import org.example.DTO.LoaiSanPham;
import org.example.DTO.NhaCungCap;
import org.example.DTO.SanPham;
import org.example.DTO.TacGia;

import java.awt.LayoutManager;

import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.EtchedBorder;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.MouseListener;
import javax.crypto.AEADBadTagException;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.DefaultMenuLayout;

public class QL_SanPhamGUI extends JFrame {
        JLabel lb_nhap_find;
        JTextField txt_nhap_find;
        JLabel lb_giatien_find1;
        JTextField txt_giatien1;
        JLabel lb_giatien_find2;
        JTextField txt_giatien2;
        JButton btn_findSP;
	JPanel contentPane;
	JTextField txt_MaLoai,txt_TenLoai,txt_MaSP,txt_TenSP,txt_DonGia,txt_maNCC,txt_maTG,txt_tenTG;
	JTable table_LoaiSP,table_SP,table_TG;
	JTextArea txt_Mota;
	final JFileChooser fileDialog = new JFileChooser();
	DefaultTableModel model_SP = new DefaultTableModel();
	DefaultTableModel model_LSP = new DefaultTableModel();
        DefaultTableModel model_LTG = new DefaultTableModel();
        NhaCungCapBUS nccBus=new NhaCungCapBUS();
	SanPhamBUS spBUS = new SanPhamBUS();
	LoaiSanPhamBUS lspBUS = new LoaiSanPhamBUS();
        TacGiaBUS tgBus=new TacGiaBUS();
	JComboBox cbx_LocSP,cbx_loai,cbx_tg,cbx_find,cbx_LocSP_TG,cbx_NCC;
	File fileAnhSP;
	JLabel lbl_HinhAnh;
	JButton btn_ThemLoai,btn_SuaLoai, btn_XoaLoai, btn_ThemSP,btn_XoaSP,btn_SuaSP,
                btn_themHinhAnh,btn_themTG,btn_suaTG,btn_xoaTG,btn_ClearSP;
	JSpinner spi_SoLuong;
        JButton ql_loaiSP_Button;
        JDialog dialog_loaiSP;
	private JButton btn_menu;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					new MyConnect();					
//					QL_SanPhamGUI frame = new QL_SanPhamGUI();
//					frame.showWindows();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public QL_SanPhamGUI() {
                //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addControls();
		addEvents();
	}
    private void addControls() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1178, 771);
    	this.setLocationRelativeTo(null); //hiển thị giữa mà hình
    	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_SanPham = new JLabel("Quản Lý Sản Phẩm");
		lbl_SanPham.setForeground(Color.BLACK);
		lbl_SanPham.setFont(new Font("Tahoma", Font.BOLD, 23));
		lbl_SanPham.setBounds(470, 10, 300, 42);
		contentPane.add(lbl_SanPham);
		//-----------------------------------
		JPanel panel = new JPanel();//pane loai sp
		panel.setForeground(Color.BLACK);
		panel.setBackground(SystemColor.control);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Thông tin thể loại", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(10, 10, 597, 183);//62
		//contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbl_MaLoai = new JLabel("Mã loại sản phẩm :");
		lbl_MaLoai.setForeground(Color.BLACK);
		lbl_MaLoai.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_MaLoai.setBounds(25, 29, 168, 22);
		panel.add(lbl_MaLoai);
		
		JLabel lbl_TenLoai = new JLabel("Tên loại sản phẩm :");
		lbl_TenLoai.setForeground(Color.BLACK);
		lbl_TenLoai.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_TenLoai.setBounds(25, 66, 168, 22);
		panel.add(lbl_TenLoai);
		
		txt_MaLoai = new JTextField();
		txt_MaLoai.setBounds(202, 22, 234, 31);
		panel.add(txt_MaLoai);
		txt_MaLoai.setColumns(10);
                txt_MaLoai.setEnabled(false);
		
		txt_TenLoai = new JTextField();
		txt_TenLoai.setColumns(10);
		txt_TenLoai.setBounds(202, 63, 234, 30);
		panel.add(txt_TenLoai);
		
		btn_ThemLoai = new JButton("Thêm");
		btn_ThemLoai.setBounds(125, 152, 85, 21);
		panel.add(btn_ThemLoai);
		
		btn_SuaLoai = new JButton("Sửa");
		btn_SuaLoai.setBounds(250, 152, 85, 21);
		panel.add(btn_SuaLoai);
		
		btn_XoaLoai = new JButton("Xóa");
		btn_XoaLoai.setBounds(378, 152, 85, 21);
		panel.add(btn_XoaLoai);
		
		JLabel lbl_MaNCC = new JLabel("Mã nhà cung cấp :");
		lbl_MaNCC.setForeground(Color.BLACK);
		lbl_MaNCC.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_MaNCC.setBounds(25, 103, 168, 22);
		panel.add(lbl_MaNCC);
		
                cbx_NCC=new JComboBox();
                cbx_NCC.setMaximumRowCount(15);
                cbx_NCC.setFont(new Font("Tahoma",Font.PLAIN,22));
                cbx_NCC.setBounds(202, 103, 234, 35);
                panel.add(cbx_NCC);
                
		/*txt_maNCC = new JTextField();
		txt_maNCC.setColumns(10);
                txt_maNCC.setEnabled(false);
		txt_maNCC.setBounds(202, 103, 234, 39);
		panel.add(txt_maNCC);*/
		//-----------------------------------
		JPanel panel_1 = new JPanel();//pane hien thi bang loai san pham
		panel_1.setForeground(Color.BLACK);
		panel_1.setBackground(SystemColor.control);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh sách loại sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_1.setBounds(617, 10, 463, 183);
		//contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 443, 153);
		panel_1.add(scrollPane);
		
		table_LoaiSP = new JTable();
		table_LoaiSP.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Mã Loại", "Tên Loại", "Mã NCC"
			}
		));
		scrollPane.setViewportView(table_LoaiSP);

                //----------------------------------------------
                JPanel panel_tg = new JPanel();//panel tacgia
		panel_tg.setForeground(Color.BLACK);
		panel_tg.setBackground(SystemColor.control);
		panel_tg.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Thông tin tác giả", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_tg.setBounds(10, 203, 597, 183);//62
		//contentPane.add(panel);
		panel_tg.setLayout(null);
		
		JLabel lbl_MaTG1 = new JLabel("Mã Tác Giả :");
		lbl_MaTG1.setForeground(Color.BLACK);
		lbl_MaTG1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_MaTG1.setBounds(25, 29, 168, 22);
		panel_tg.add(lbl_MaTG1);
		
		JLabel lbl_TenTG = new JLabel("Tên Tác Giả :");
		lbl_TenTG.setForeground(Color.BLACK);
		lbl_TenTG.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_TenTG.setBounds(25, 66, 168, 22);
		panel_tg.add(lbl_TenTG);
		
		txt_maTG = new JTextField();
		txt_maTG.setBounds(202, 22, 234, 31);
		panel_tg.add(txt_maTG);
                txt_maTG.setEnabled(false);
		txt_maTG.setColumns(10);
		
		txt_tenTG = new JTextField();
		txt_tenTG.setColumns(10);
		txt_tenTG.setBounds(202, 63, 234, 30);
		panel_tg.add(txt_tenTG);
		
		btn_themTG = new JButton("Thêm");
		btn_themTG.setBounds(125, 152, 85, 21);
		panel_tg.add(btn_themTG);
		
		btn_suaTG = new JButton("Sửa");
		btn_suaTG.setBounds(250, 152, 85, 21);
		panel_tg.add(btn_suaTG);
		
		btn_xoaTG = new JButton("Xóa");
		btn_xoaTG.setBounds(378, 152, 85, 21);
		panel_tg.add(btn_xoaTG);
		
		//-----------------------------------
		JPanel panel_tg2 = new JPanel();//pane hien thi bang loai san pham
		panel_tg2.setForeground(Color.BLACK);
		panel_tg2.setBackground(SystemColor.control);
		panel_tg2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh sách tác giả", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_tg2.setBounds(617, 203, 463, 183);
		//contentPane.add(panel_1);
		panel_tg2.setLayout(null);
		
		JScrollPane scrollPane_tg = new JScrollPane();
		scrollPane_tg.setBounds(10, 20, 443, 153);
		panel_tg2.add(scrollPane_tg);
		
		table_TG = new JTable();
		table_TG.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Mã TG", "Tên TG"
			}
		));
		scrollPane_tg.setViewportView(table_TG);
		//-----------------Dialog quan ly tac gia va loai sp------------------
                    dialog_loaiSP=new JDialog(this, "Quản lý loại sản phẩm");
                    dialog_loaiSP.setLocationRelativeTo(null); //hiển thị giữa mà hình
                    dialog_loaiSP.setBounds(100, 100, 1090, 463);
                    dialog_loaiSP.setLayout(null);
                    dialog_loaiSP.add(panel);
                    dialog_loaiSP.add(panel_1);
                    dialog_loaiSP.add(panel_tg);
                    dialog_loaiSP.add(panel_tg2);
                //----------------------------------------------
                    JButton exit_ql_loaiSP=new JButton("Thoát");
                    exit_ql_loaiSP.setBounds(500, 393, 85, 21);
                    dialog_loaiSP.add(exit_ql_loaiSP);
                    exit_ql_loaiSP.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent event){
                            dialog_loaiSP.setVisible(false);
                        }
                    });
                
                //----------------------------------------------
		JPanel panel_2 = new JPanel();//panel them sua xoa san phamm
		panel_2.setBackground(UIManager.getColor("Button.background"));
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Thông tin sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_2.setBounds(10, 62, 500, 375);//266
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lbl_MaSP = new JLabel("Mã sản phẩm :");
		lbl_MaSP.setForeground(Color.BLACK);
		lbl_MaSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_MaSP.setBounds(212, 8, 106, 25);
		panel_2.add(lbl_MaSP);
		
		lbl_HinhAnh = new JLabel();
		lbl_HinhAnh.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_HinhAnh.setBounds(10, 40, 192, 181);
		lbl_HinhAnh.setIcon(new ImageIcon("Image/maytinh.jpg"));
		panel_2.add(lbl_HinhAnh);
		
		txt_MaSP = new JTextField();
		txt_MaSP.setBounds(328, 10, 106, 25);
		panel_2.add(txt_MaSP);
		txt_MaSP.setColumns(10);
                txt_MaSP.setEnabled(false);
		
		JLabel lbl_TênSP = new JLabel("Tên sản phẩm :");
		lbl_TênSP.setForeground(Color.BLACK);
		lbl_TênSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_TênSP.setBounds(212, 43, 118, 25);
		panel_2.add(lbl_TênSP);
		
		txt_TenSP = new JTextField();
		txt_TenSP.setColumns(10);
		txt_TenSP.setBounds(328, 45, 162, 25);//(328,45,163,39)
		panel_2.add(txt_TenSP);
		
		JLabel lbl_LoaiSP = new JLabel("Loại sản phẩm :");
		lbl_LoaiSP.setForeground(Color.BLACK);
		lbl_LoaiSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_LoaiSP.setBounds(212, 78, 118, 25);
		panel_2.add(lbl_LoaiSP);
		
		JLabel lbl_ThemHinhAnh = new JLabel("Hình ảnh :");
		lbl_ThemHinhAnh.setForeground(Color.BLACK);
		lbl_ThemHinhAnh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_ThemHinhAnh.setBounds(212, 113, 84, 25);
		panel_2.add(lbl_ThemHinhAnh);
		
		JLabel lbl_SoLuong = new JLabel("Số lượng :");
		lbl_SoLuong.setForeground(Color.BLACK);
		lbl_SoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_SoLuong.setBounds(212, 148, 84, 25);
		panel_2.add(lbl_SoLuong);
		
		JLabel lbl_DonGia = new JLabel("Đơn giá :");
		lbl_DonGia.setForeground(Color.BLACK);
		lbl_DonGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_DonGia.setBounds(212, 183, 76, 25);
		panel_2.add(lbl_DonGia);
                
                JLabel lbl_MaTG = new JLabel("Tác giả :");
		lbl_MaTG.setForeground(Color.BLACK);
		lbl_MaTG.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_MaTG.setBounds(212, 218, 76, 25);
		panel_2.add(lbl_MaTG);
		
		JLabel lbl_MoTa = new JLabel("Mô tả :");
		lbl_MoTa.setForeground(Color.BLACK);
		lbl_MoTa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_MoTa.setBounds(212, 263, 62, 25);
		panel_2.add(lbl_MoTa);
		
		spi_SoLuong = new JSpinner();
		spi_SoLuong.setModel(new SpinnerNumberModel((int)0,(int)0, null, (int)1));
		spi_SoLuong.setBounds(328, 150, 62, 25);
		panel_2.add(spi_SoLuong);
                spi_SoLuong.setEnabled(false);
		
		txt_DonGia = new JTextField();
		txt_DonGia.setColumns(10);
                txt_DonGia.setEnabled(false);
		txt_DonGia.setBounds(328, 185, 125, 25);
		panel_2.add(txt_DonGia);
		
		JLabel lblNewLabel_2 = new JLabel("VNĐ");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(458, 192, 32, 13);
		panel_2.add(lblNewLabel_2);
		
                JScrollPane sc_mota= new JScrollPane();
                sc_mota.setBounds(328, 264, 162, 50);
		txt_Mota = new JTextArea();
		//txt_Mota.setSize(162, 50);
                txt_Mota.setLineWrap(true);
                txt_Mota.setWrapStyleWord(true);
                sc_mota.setViewportView(txt_Mota);
		panel_2.add(sc_mota);
		
		btn_themHinhAnh = new JButton("Chọn file ảnh");
		btn_themHinhAnh.setHorizontalAlignment(SwingConstants.LEFT);
		btn_themHinhAnh.setBounds(328, 115, 118, 25);
		panel_2.add(btn_themHinhAnh);
		
		btn_ThemSP = new JButton("Thêm");		
		btn_ThemSP.setBounds(10, 334, 85, 21);
		panel_2.add(btn_ThemSP);
		
		btn_SuaSP = new JButton("Sữa");
		btn_SuaSP.setBounds(115, 334, 85, 21);
                btn_SuaSP.setEnabled(false);
		panel_2.add(btn_SuaSP);
		
		btn_XoaSP = new JButton("Xóa");
		btn_XoaSP.setBounds(212, 334, 85, 21);
                btn_XoaSP.setEnabled(false);
		panel_2.add(btn_XoaSP);
		
                btn_ClearSP = new JButton("Clear");
		btn_ClearSP.setBounds(312, 334, 85, 21);
		panel_2.add(btn_ClearSP);
                
                //--------------------------------
                
                ql_loaiSP_Button=new JButton("Quản lý loại SP và TG");
                ql_loaiSP_Button.setBounds(10, 303, 200, 21);
                panel_2.add(ql_loaiSP_Button);
                    
                    
		cbx_loai = new JComboBox();
		cbx_loai.setMaximumRowCount(15);
		cbx_loai.setFont(new Font("Tahoma", Font.PLAIN, 19));
		cbx_loai.setBackground(Color.WHITE);
		cbx_loai.setBounds(328, 80, 120, 25);
		panel_2.add(cbx_loai);
                
                cbx_tg = new JComboBox();
		cbx_tg.setMaximumRowCount(15);
		cbx_tg.setFont(new Font("Tahoma", Font.PLAIN, 19));
		cbx_tg.setBackground(Color.WHITE);
		cbx_tg.setBounds(328, 220, 120, 25);
		panel_2.add(cbx_tg);
                
		//--------------------------------
		JPanel panel_3 = new JPanel();//panel hien thi bang san pham
		panel_3.setBackground(SystemColor.control);
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh sách sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_3.setBounds(520, 108, 630, 330);//311,116
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 21, 610, 299);
		panel_3.add(scrollPane_1);
		
		
		table_SP = new JTable();
		table_SP.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null,null},
				{null, null, null, null, null, null, null,null},
				{null, null, null, null, null, null, null,null},
			},
			new String[] {
				"Mã sản phẩm", "Tên SP", "Số lượng", "Đơn giá", "Loại", "Hình ảnh","Tác giả", "Mô tả"
			}
		));
		scrollPane_1.setViewportView(table_SP);
		//-------------------------------------------------
		JLabel lblNewLabel_1 = new JLabel("Lọc SP theo: Loại");
		lblNewLabel_1.setForeground(UIManager.getColor("Button.focus"));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(520, 62, 180, 35);//266
		contentPane.add(lblNewLabel_1);
                
		cbx_LocSP = new JComboBox();
                
		cbx_LocSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String comboBox = "" + cbx_LocSP.getItemAt(cbx_LocSP.getSelectedIndex());
				if(comboBox.equalsIgnoreCase("All")) {
					    loadingSanPham();
					    return;
				}
                                else{
                                    locSPTheoLoai();
                                }
			}
		});
		cbx_LocSP.setMaximumRowCount(15);
		cbx_LocSP.setBackground(new Color(255, 255, 255));
		cbx_LocSP.setFont(new Font("Tahoma", Font.PLAIN, 17));
		//cbx_LocSP.setModel(DefaultComboBoxModel cbxModel = new DefaultComboBoxModel());
		cbx_LocSP.setBounds(700, 69, 120, 22);//273
		contentPane.add(cbx_LocSP);
                
                JLabel lblNewLabel_3 = new JLabel("Tác giả");
		lblNewLabel_3.setForeground(UIManager.getColor("Button.focus"));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(850, 62, 85, 35);//266
		contentPane.add(lblNewLabel_3);
                
                cbx_LocSP_TG = new JComboBox();
                
		cbx_LocSP_TG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String comboBox = "" + cbx_LocSP_TG.getItemAt(cbx_LocSP_TG.getSelectedIndex());
				if(comboBox.equalsIgnoreCase("All")) {
					    loadingSanPham();
					    return;
				}
                                else{
                                    locSPTheoTG();
                                }
			}
		});
		cbx_LocSP_TG.setMaximumRowCount(15);
		cbx_LocSP_TG.setBackground(new Color(255, 255, 255));
		cbx_LocSP_TG.setFont(new Font("Tahoma", Font.PLAIN, 17));
		//cbx_LocSP.setModel(DefaultComboBoxModel cbxModel = new DefaultComboBoxModel());
		cbx_LocSP_TG.setBounds(945, 69, 120, 22);//273
		contentPane.add(cbx_LocSP_TG);
                
                //--------------------Panel tim kiem SP---------------------------
                JPanel panel_find =new JPanel();
                panel_find.setBackground(UIManager.getColor("Button.background"));
		panel_find.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Tìm kiếm sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_find.setBounds(10, 448, 1140, 200);//266
		contentPane.add(panel_find);
		panel_find.setLayout(null);
                
                JLabel lb_timkiem=new JLabel("Tìm kiếm theo: ");
                lb_timkiem.setForeground(Color.black);
                lb_timkiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
                lb_timkiem.setBounds(420, 20, 120, 25);
                panel_find.add(lb_timkiem);
                
                cbx_find=new JComboBox();
                cbx_find.addItem("Tất cả");
                cbx_find.addItem("Mã Sản Phẩm");
                cbx_find.addItem("Tên Sản Phẩm");
                cbx_find.addItem("Giá tiền");
                cbx_find.setForeground(Color.BLACK);
                cbx_find.setBackground(new Color(255, 255, 255));
                cbx_find.setFont(new Font("Tahoma",Font.PLAIN,14));
                cbx_find.setBounds(530, 20, 150, 25);
                panel_find.add(cbx_find);
                
                lb_nhap_find=new JLabel("Nhập: ");
                lb_nhap_find.setForeground(Color.BLACK);
                lb_nhap_find.setFont(new Font("Tahoma",Font.PLAIN,14));
                lb_nhap_find.setBounds(420, 65, 120, 25);
                panel_find.add(lb_nhap_find);
                lb_nhap_find.setEnabled(false);
                
                txt_nhap_find=new JTextField();
                txt_nhap_find.setColumns(10);
                txt_nhap_find.setBounds(530,65,150,25);
                panel_find.add(txt_nhap_find);
                txt_nhap_find.setEnabled(false);
                
                lb_giatien_find1=new JLabel("Giá tiền từ: ");
                lb_giatien_find1.setForeground(Color.BLACK);
                lb_giatien_find1.setFont(new Font("Tahoma",Font.PLAIN,14));
                lb_giatien_find1.setBounds(250, 110, 100, 25);
                panel_find.add(lb_giatien_find1);
                lb_giatien_find1.setEnabled(false);
                
                txt_giatien1=new JTextField();
                txt_giatien1.setColumns(10);
                txt_giatien1.setBounds(370,110,150,25);
                panel_find.add(txt_giatien1);
                txt_giatien1.setEnabled(false);
                
                lb_giatien_find2=new JLabel("Đến: ");
                lb_giatien_find2.setForeground(Color.BLACK);
                lb_giatien_find2.setFont(new Font("Tahoma",Font.PLAIN,14));
                lb_giatien_find2.setBounds(600, 110, 120, 25);
                panel_find.add(lb_giatien_find2);
                lb_giatien_find2.setEnabled(false);
                
                txt_giatien2=new JTextField();
                txt_giatien2.setColumns(10);
                txt_giatien2.setBounds(720,110,150,25);
                panel_find.add(txt_giatien2);
                txt_giatien2.setEnabled(false);
                
                btn_findSP=new JButton("Tìm");
                btn_findSP.setForeground(Color.BLACK);
                btn_findSP.setFont(new Font("Tahoma",Font.PLAIN,18));
                btn_findSP.setBounds(570,160,75,25);
                panel_find.add(btn_findSP);
                btn_findSP.setEnabled(false);
                
		//----------------------------------------------------------------
		btn_menu = new JButton("Trang Chính");
		btn_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFormGUI MF = new MainFormGUI();
				MF.setVisible(true);
				dispose();
			}
		});
		btn_menu.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_menu.setBounds(450, 672, 216, 33);
		btn_menu.setIcon(new ImageIcon("Icon/home.png"));
		contentPane.add(btn_menu);
		//-----------------------------------------------------------------
		loadingSanPham();
		loadingLoaiSP();
                loadingTG();
		loadingComBox();
		//Căn giữa table
		for(int x=0;x<table_SP.getColumnCount();x++){
	         table_SP.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
	        }
		for(int x=0;x<table_LoaiSP.getColumnCount();x++){
	         table_LoaiSP.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
	        }
                
                dialog_loaiSP.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        if(!table_LoaiSP.contains(e.getPoint())){
                            table_LoaiSP.clearSelection();
                            txt_MaLoai.setText("");
                            txt_TenLoai.setText("");
                            cbx_NCC.setSelectedIndex(0);
                        }
                        if(!table_TG.contains(e.getPoint())){
                            table_TG.clearSelection();
                            txt_maTG.setText("");
                            txt_tenTG.setText("");
                        }
                    }
                });
	}
    
    private void addEvents() {
    	btn_ThemSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themSP();       		        
			}
		});
    	
    	btn_SuaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suaSP();
			}
		});
    	
    	btn_XoaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaSP();
			}
		});
        
    	btn_ClearSP.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clearSP();
            }
        });
        cbx_find.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                select_cbx_find();
            }
        });
        btn_findSP.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    findSP();
                }
        });
    	ql_loaiSP_Button.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent event){
                            dialog_loaiSP.setVisible(true);
                        }
        });
        
    	btn_ThemLoai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themLoai();	
			}
		});	
		btn_SuaLoai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suaLoai();
			}
		});	
		btn_XoaLoai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 xoaLoai();
			}
		});
                btn_themTG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themTG();	
			}
		});	
		btn_suaTG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suaTG();
			}
		});	
		btn_xoaTG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 xoaTG();
			}
		});
		btn_themHinhAnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themHinhAnh();
			}
		});
		table_LoaiSP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickTableLoai();
			}
		});
                table_TG.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickTableTG();
			}
		});
		table_SP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickTableSanPham();
			}
		});
    }
    private void findSP(){
        switch(cbx_find.getSelectedIndex()){
            case 1 ->{
                String maSP=txt_nhap_find.getText();
                if(maSP==null || maSP.equals("")){
                    new MyDialog("Vui lòng nhập mã SP cần tìm!",MyDialog.ERROR_DIALOG);
                    return;
                }
                model_SP.setRowCount(0);
                SanPham sp=spBUS.getSanPham(maSP);
                model_SP.addRow(new Object[]{sp.getMaSP(),sp.getTenSP(),sp.getSoLuong(),sp.getDonGia(),
                    sp.getMaLoai(),sp.getHinhAnh(),sp.getMaTG(),sp.getMoTa()});
                table_SP.setModel(model_SP);
                break;
            }
            case 2 ->{
                String tenSP=txt_nhap_find.getText();
                if(tenSP==null || tenSP.equals("")){
                    new MyDialog("Vui lòng nhập tên SP cần tìm!",MyDialog.ERROR_DIALOG);
                    return;
                }
                model_SP.setRowCount(0);
                for(SanPham sp:spBUS.getSanPhamTheoTen(tenSP)){
                    model_SP.addRow(new Object[]{sp.getMaSP(),sp.getTenSP(),sp.getSoLuong(),sp.getDonGia(),
                    sp.getMaLoai(),sp.getHinhAnh(),sp.getMaTG(),sp.getMoTa()});
                }
                table_SP.setModel(model_SP);
                break;
            }
            case 3 ->{
                String giatien1=txt_giatien1.getText();
                String giatien2=txt_giatien2.getText();
                if(giatien1==null || giatien2==null || giatien1.equals("")||giatien2.equals("")){
                    new MyDialog("Vui lòng nhập khoảng giá sản phẩm cần tìm!",MyDialog.ERROR_DIALOG);
                    return;
                }
                model_SP.setRowCount(0);
                for(SanPham sp:spBUS.getSanPhamDonGia(giatien1,giatien2)){
                    model_SP.addRow(new Object[]{sp.getMaSP(),sp.getTenSP(),sp.getSoLuong(),sp.getDonGia(),
                    sp.getMaLoai(),sp.getHinhAnh(),sp.getMaTG(),sp.getMoTa()});
                }
                table_SP.setModel(model_SP);
                break;
            }
        }
    }
    private void select_cbx_find(){
        switch(cbx_find.getSelectedIndex()){
                        case 0 ->{
                            loadingSanPham();
                            txt_nhap_find.setEnabled(false);
                            btn_findSP.setEnabled(false);
                            txt_giatien1.setEnabled(false);
                            txt_giatien2.setEnabled(false);
                            lb_nhap_find.setEnabled(false);
                            lb_giatien_find1.setEnabled(false);
                            lb_giatien_find2.setEnabled(false);
                            txt_nhap_find.setText("");
                            txt_giatien1.setText("");
                            txt_giatien2.setText("");
                            break;
                        }
                        case 1 ->{
                            lb_nhap_find.setText("Nhập Mã TG: ");
                            txt_nhap_find.setEnabled(true);
                            btn_findSP.setEnabled(true);
                            txt_giatien1.setEnabled(false);
                            txt_giatien2.setEnabled(false);
                            lb_nhap_find.setEnabled(true);
                            lb_giatien_find1.setEnabled(false);
                            lb_giatien_find2.setEnabled(false);
                            txt_giatien1.setText("");
                            txt_giatien2.setText("");
                            break;
                        }
                        case 2 ->{
                            lb_nhap_find.setText("Nhập Tên TG: ");
                            txt_nhap_find.setEnabled(true);
                            btn_findSP.setEnabled(true);
                            txt_giatien1.setEnabled(false);
                            txt_giatien2.setEnabled(false);
                            lb_nhap_find.setEnabled(true);
                            lb_giatien_find1.setEnabled(false);
                            lb_giatien_find2.setEnabled(false);
                            txt_giatien1.setText("");
                            txt_giatien2.setText("");
                            break;
                        }
                        case 3 ->{
                            lb_nhap_find.setText("Nhập: ");
                            txt_nhap_find.setEnabled(false);
                            btn_findSP.setEnabled(true);
                            txt_giatien1.setEnabled(true);
                            txt_giatien2.setEnabled(true);
                            lb_nhap_find.setEnabled(false);
                            lb_giatien_find1.setEnabled(true);
                            lb_giatien_find2.setEnabled(true);
                            txt_nhap_find.setText("");
                            break;
                        }
                    }
    }
    private void loadingLoaiSP() {
		lspBUS.docDanhSachLoai();
		model_LSP.setRowCount(0);
		ArrayList<LoaiSanPham> dslsp = lspBUS.getDanhSachLoai();
		Vector header = new Vector();
        header.add("Mã Loại ");
        header.add("Ten Loại ");
        header.add("Mã NCC");        
        
        if (model_LSP.getRowCount()==0){ 
        	model_LSP=new DefaultTableModel(header,0){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        } 
		for( LoaiSanPham lsp : dslsp) {
		        Vector row = new Vector();
		        row.add(lsp.getMaLoai());
		        row.add(lsp.getTenLoai());		      
		        row.add(lsp.getMaNCC());		        
		        model_LSP.addRow(row);
		}
                table_LoaiSP.setModel(model_LSP);
    }
    
    private void loadingTG() {
		tgBus.docDanhSachTG();
		model_LTG.setRowCount(0);
		ArrayList<TacGia> dsltg = tgBus.getDanhSachTG();
		Vector header = new Vector();
        header.add("Mã TG ");
        header.add("Tên TG ");      
        
        if (model_LTG.getRowCount()==0){ 
        	model_LTG=new DefaultTableModel(header,0){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        } 
		for( TacGia ltg : dsltg) {
		        Vector row = new Vector();
		        row.add(ltg.getMaTG());
		        row.add(ltg.getTenTG());		        
		        model_LTG.addRow(row);
		}
                table_TG.setModel(model_LTG);
    }
    
    private void loadingSanPham() {
    	spBUS.docListSanPham();
		model_SP.setRowCount(0);
		ArrayList<SanPham> dssp = spBUS.getListSanPham();
		Vector header = new Vector();
        header.add("Mã SP");
        header.add("Tên SP");
        header.add("Số Lượng");
        header.add("Đơn Giá");
        header.add("Loại");
        header.add("Hình ảnh");
        header.add("Tác giả");
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
		        Vector row = new Vector();
		        row.add(sp.getMaSP());
		        row.add(sp.getTenSP());		      
		        row.add(sp.getSoLuong());		       
		        row.add(sp.getDonGia());		      
		        String tenLoai = lspBUS.getTenLoai(sp.getMaLoai());
		        row.add(tenLoai);
		        row.add(sp.getHinhAnh());
                        String tenTG= tgBus.getTenTG(sp.getMaTG());
                        row.add(tenTG);
		        row.add(sp.getMoTa());
		        model_SP.addRow(row); 
		}
                table_SP.setModel(model_SP);
		
    }
	private void loadingComBox() {
		cbx_LocSP.removeAllItems();
                cbx_LocSP_TG.removeAllItems();
		cbx_loai.removeAllItems();
                cbx_tg.removeAllItems();
                cbx_NCC.removeAllItems();
        ArrayList<LoaiSanPham> dsl = lspBUS.getDanhSachLoai();
        cbx_LocSP.addItem("All");
        for (LoaiSanPham loai : dsl) {
        	cbx_LocSP.addItem(loai.getMaLoai()+"-"+loai.getTenLoai());
        }
        
        cbx_loai.addItem("Chọn loại");
        for (LoaiSanPham loai : dsl) {       	
        	cbx_loai.addItem(loai.getMaLoai()+" - "+loai.getTenLoai());
        }
        
        ArrayList<TacGia> dstg = tgBus.getDanhSachTG();
        cbx_LocSP_TG.addItem("All");
        for (TacGia tacgia : dstg) {
        	cbx_LocSP_TG.addItem(tacgia.getMaTG()+"-"+tacgia.getTenTG());
        }
        cbx_tg.addItem("Chọn TG");
        for (TacGia tg : dstg) {       	
        	cbx_tg.addItem(tg.getMaTG()+" - "+tg.getTenTG());
        }
        ArrayList<NhaCungCap> dsncc = nccBus.getListNhaCungCap();
        cbx_NCC.addItem("Chọn NCC");
        for (NhaCungCap ncc : dsncc) {       	
        	cbx_NCC.addItem(ncc.getMaNCC()+" - "+ncc.getTenNCC());
        }
    }
        
	private void loadAnh(String anh) {
        lbl_HinhAnh.setIcon(getAnhSP(anh));
    }
	
	private void themHinhAnh() {
		JFileChooser fileChooser = new MyFileChooser("Image/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Tệp hình ảnh", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileAnhSP = fileChooser.getSelectedFile();
            lbl_HinhAnh.setIcon(getAnhSP(fileAnhSP.getPath()));
        }
	}
	
	
	
	private void luuFileAnh() {
        BufferedImage bImage = null;
        try {
            File initialImage = new File(fileAnhSP.getPath());
            bImage = ImageIO.read(initialImage);

            ImageIO.write(bImage, "png", new File("image/" + fileAnhSP.getName()));

        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }


    private ImageIcon getAnhSP(String src) {
        src = src.trim().equals("") ? "default.png" : src;
        //Xử lý ảnh
        BufferedImage img = null;
        System.out.println(src);
        try (InputStream is = QL_SanPhamGUI.class.getResourceAsStream("/image/"+src)){
            if(is==null){
                throw new RuntimeException("Không tìm thấy ảnh");
            }
            img = ImageIO.read(is);
            fileAnhSP = new File(src);
        } catch (IOException e) {
            fileAnhSP = new File("image/default.png");
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        }
        return null;
    }
    
    private void themLoai() {
    	String ml = txt_MaLoai.getText();
		String tl = txt_TenLoai.getText();
		String ncc = txt_maNCC.getText();
		if (lspBUS.themLoai(ml, tl, ncc)) {
			loadingLoaiSP();
        }							
		loadingComBox();			
    }
    private void suaLoai() {
    	String maloai = txt_MaLoai.getText();
        String ten = txt_TenLoai.getText();
        String  mancc= txt_maNCC.getText();
        if (lspBUS.suaLoai(maloai, ten,mancc)) {
        	loadingLoaiSP();
        }
        loadingComBox();
    }
    private void xoaLoai(){
        String ma = txt_MaLoai.getText();
        for(LoaiSanPham loai: lspBUS.getDanhSachLoai()){
            if((loai.getMaLoai()+"").equals(ma)){
                JOptionPane.showMessageDialog(null, "Không thể xóa, trong kho vẫn còn sản phẩm thuộc thể loại này!");
                return;
            }
        }
    	MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if (dlg.OK_OPTION == dlg.getAction()) {
            if (lspBUS.xoaLoai(ma)) {
                loadingLoaiSP();
                txt_MaLoai.setText("");
                txt_TenLoai.setText("");
                txt_maNCC.setText("");
            }
        }
        loadingComBox();
        loadingSanPham();
    }
    
    private void themTG() {
	String ttg = txt_tenTG.getText();
	if (tgBus.themTG(ttg)) {
            loadingTG();
        }							
	loadingComBox();			
    }
    private void suaTG() {
    	String matg = txt_maTG.getText();
        String ten = txt_tenTG.getText();
        if (tgBus.suaTG(matg, ten)) {
        	   loadingTG();
        }
        loadingComBox();
    }
    private void xoaTG() {
        String ma = txt_maTG.getText();
        for(SanPham sp : spBUS.getListSanPham() ){
            if((sp.getMaTG()+"").equals(ma)){
                JOptionPane.showMessageDialog(null, "Không thể xóa, trong kho vẫn còn sản phẩm của tác giả này!");
                return;
            }
        }
        
    	MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if (dlg.OK_OPTION == dlg.getAction()) {
            if (tgBus.xoaTG(ma)) {
                loadingTG();
                txt_maTG.setText("");
                txt_tenTG.setText("");
            }
        }
        loadingComBox();
        loadingSanPham();
    }
    
    private void themSP() {
         String anh;
         try { 
              //24/03/2024
             // Kiểm tra fileAnhSP có khác null không
             if (fileAnhSP == null) {
                 throw new NullPointerException("fileAnhSP is null"); // Ném ngoại lệ nếu null
             }
             anh = fileAnhSP.getName(); // Lấy tên file ảnh
         } catch (NullPointerException e) {
             JOptionPane.showMessageDialog(null, "Vui lòng chọn ảnh!");
             System.out.println(e); // In ra thông tin lỗi
             return; // Kết thúc phương thức nếu không có ảnh
         } 
         
        // Lấy giá trị từ combobox 24/09/2024 chính nếu lấy index đầu tiên của tác giả/ loại
            int checkComBoBox1Index = cbx_loai.getSelectedIndex();
            int checkComBoBox2Index = cbx_tg.getSelectedIndex();

            // Kiểm tra xem có chọn index 0 trong combobox loại không
            if (checkComBoBox1Index == 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn mã loại sản phẩm!");
                return; // Kết thúc phương thức nếu không hợp lệ
            }

            // Kiểm tra xem có chọn index 0 trong combobox tác giả không
            if (checkComBoBox2Index == 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn mã tác giả!");
                return; // Kết thúc phương thức nếu không hợp lệ
            }
        String comboBox1 = "" + cbx_loai.getItemAt(cbx_loai.getSelectedIndex());
        String comboBox2 = "" + cbx_tg.getItemAt(cbx_tg.getSelectedIndex()); 
        
        
       boolean flag= spBUS.themSanPham(txt_TenSP.getText(),comboBox1,anh,comboBox2,txt_Mota.getText());
        if(flag) {       	
        spBUS.docListSanPham();
        loadingSanPham();
        luuFileAnh();
        }		        		        
    }
    private void themSP1() {
    	String anh = fileAnhSP.getName();
        String comboBox1 = "" + cbx_loai.getItemAt(cbx_loai.getSelectedIndex());
        String comboBox2 = "" + cbx_tg.getItemAt(cbx_tg.getSelectedIndex()); 
       boolean flag= spBUS.themSanPham1(
        		txt_MaSP.getText(),
        		txt_TenSP.getText(),		        		
                comboBox1,		                
                String.valueOf(spi_SoLuong.getValue()),		                
                txt_DonGia.getText(),
                anh,
                comboBox2,
                txt_Mota.getText());
        if(flag) {       	
        spBUS.docListSanPham();
        loadingSanPham();
        luuFileAnh();
        }		        		        
    }
    private void suaSP() {
    	String anh = fileAnhSP.getName();
		String comboBox1 = "" + cbx_loai.getItemAt(cbx_loai.getSelectedIndex());
                String comboBox2 = "" + cbx_tg.getItemAt(cbx_tg.getSelectedIndex());
        boolean flag = spBUS.suaSanPham(
        		txt_MaSP.getText(),
        		txt_TenSP.getText(),		        		
                        comboBox1,		                
                        txt_DonGia.getText(),
                        anh,
                        comboBox2,
                        txt_Mota.getText());
        spBUS.docListSanPham();
        loadingSanPham();
        luuFileAnh();
    }
    private void suaSP1() {
    	String anh = fileAnhSP.getName();
		String comboBox1 = "" + cbx_loai.getItemAt(cbx_loai.getSelectedIndex());
                String comboBox2 = "" + cbx_tg.getItemAt(cbx_tg.getSelectedIndex());
        boolean flag = spBUS.suaSanPham1(
        		txt_MaSP.getText(),
        		txt_TenSP.getText(),		        		
                comboBox1,		                
                String.valueOf(spi_SoLuong.getValue()),		                
                txt_DonGia.getText(),
                anh,
                comboBox2,
                txt_Mota.getText());
        spBUS.docListSanPham();
        loadingSanPham();
        luuFileAnh();
    }
    private void xoaSP() {
        if(((int)spi_SoLuong.getValue())> 0){
            JOptionPane.showMessageDialog(null, "Sản phầm vẫn tồn kho, không thể xóa!");
            return;
        }
    	MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if (dlg.OK_OPTION == dlg.getAction()) {
            boolean flag = spBUS.xoaSanPham(txt_MaSP.getText());
            if (flag) {
                loadingSanPham();
            }
        }
    }
    private void clearSP(){
        btn_ThemSP.setEnabled(true);
        btn_XoaSP.setEnabled(false);
        btn_SuaSP.setEnabled(false);
        txt_MaSP.setText("");
        txt_TenSP.setText("");
        txt_Mota.setText("");
        txt_DonGia.setText("");
        spi_SoLuong.setValue(0);
        lbl_HinhAnh.setIcon(null);
        cbx_loai.setSelectedIndex(0);
        cbx_tg.setSelectedIndex(0);
        txt_TenSP.setEnabled(true);
        cbx_loai.setEnabled(true);
        cbx_tg.setEnabled(true);
        table_SP.clearSelection();
    }
    private void clickTableLoai() {
    	int row = table_LoaiSP.getSelectedRow();
		if (row > -1) {
            String maLoai = table_LoaiSP.getValueAt(row, 0) + "";
            String tenLoai = table_LoaiSP.getValueAt(row, 1) + "";
            String maNCC = table_LoaiSP.getValueAt(row, 2) + "";
            txt_MaLoai.setText(maLoai);
            txt_TenLoai.setText(tenLoai);
            cbx_NCC.setSelectedItem(maNCC+" - "+nccBus.getTenNCC(maNCC) );
//            txt_maNCC.setText(maNCC);
        }
    }
    private void clickTableTG(){
        int row =table_TG.getSelectedRow();
        if(row>-1){
            txt_maTG.setText(table_TG.getValueAt(row, 0)+"");
            txt_tenTG.setText(table_TG.getValueAt(row, 1)+"");
        }
    }
    private void clickTableSanPham() {
    	int row = table_SP.getSelectedRow();
        if (row > -1) {
            String ma = table_SP.getValueAt(row, 0) + "";
            String ten = table_SP.getValueAt(row, 1) + "";
            String soLuong = table_SP.getValueAt(row, 2) + "";
            String donGia = table_SP.getValueAt(row, 3) + "";		            	
            String loai = table_SP.getValueAt(row, 4) + "";
            String anh = table_SP.getValueAt(row, 5) + "";
            String tacgia=table_SP.getValueAt(row, 6)+"";
            String mota = table_SP.getValueAt(row, 7) + "";
            //lấy dữ liệu lên textfiled
            txt_MaSP.setText(ma);
            txt_TenSP.setText(ten);
            txt_DonGia.setText(donGia);		           
            txt_Mota.setText(mota);
            spi_SoLuong.setValue(Integer.parseInt(soLuong));
            txt_TenSP.setEnabled(false);
            String[] index1 = loai.split(" ");		            
            cbx_loai.setSelectedIndex(Integer.parseInt(index1[0]));
            cbx_loai.setEnabled(false);
            String[] index2 = tacgia.split(" ");		            
            cbx_tg.setSelectedIndex(Integer.parseInt(index2[0]));
            cbx_tg.setEnabled(false);
            loadAnh(anh);
            btn_ThemSP.setEnabled(false);
            btn_XoaSP.setEnabled(true);
            btn_SuaSP.setEnabled(true);
        }
    }
    private void locSPTheoLoai() {
        if(cbx_LocSP_TG.getItemCount()>0){
            cbx_LocSP_TG.setSelectedIndex(0);
        }
    	String cbx_values = "" + cbx_LocSP.getItemAt(cbx_LocSP.getSelectedIndex());
        String maLoai[]=cbx_values.split("-");
    	spBUS.docListSanPham();
	model_SP.setRowCount(0);
            ArrayList<SanPham> dsSP=spBUS.getSanPhamTheoLoai(maLoai[0]);
            if(dsSP!=null){
		for( SanPham sp : dsSP) {
			
		        Vector row = new Vector();
		        row.add(sp.getMaSP());
		        row.add(sp.getTenSP());		      
		        row.add(sp.getSoLuong());		       
		        row.add(sp.getDonGia());		      
		        String tenLoai = lspBUS.getTenLoai(sp.getMaLoai());
		        row.add(tenLoai);
		        row.add(sp.getHinhAnh());
                        String tenTG= tgBus.getTenTG(sp.getMaTG());
                        row.add(tenTG);
		        row.add(sp.getMoTa());
		        model_SP.addRow(row);
		}
                table_SP.setModel(model_SP);
            }
    }
    private void locSPTheoTG() {
        if(cbx_LocSP.getItemCount()>0){
            cbx_LocSP.setSelectedIndex(0);
        }
    	String cbx_values = "" + cbx_LocSP_TG.getItemAt(cbx_LocSP_TG.getSelectedIndex());
        String maTG[]=cbx_values.split("-");
    	spBUS.docListSanPham();
        ArrayList<SanPham> dsSP=spBUS.getSanPhamTheoLoai(maTG[0]);
            if(dsSP!=null){
		model_SP.setRowCount(0);
		for( SanPham sp : dsSP) {
		        Vector row = new Vector();
		        row.add(sp.getMaSP());
		        row.add(sp.getTenSP());		      
		        row.add(sp.getSoLuong());		       
		        row.add(sp.getDonGia());		      
		        String tenLoai = lspBUS.getTenLoai(sp.getMaLoai());
		        row.add(tenLoai);
		        row.add(sp.getHinhAnh());
                        String tenTG= tgBus.getTenTG(sp.getMaTG());
                        row.add(tenTG);
		        row.add(sp.getMoTa());
		        model_SP.addRow(row);
		}
                table_SP.setModel(model_SP);
            }
    }
   private void showWindows() {	
	   	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1174, 688);
		this.setLocationRelativeTo(null); // hiển thị giữa mà hình
		this.setVisible(true); // hiển thị lên màn hình desktop
   }
}