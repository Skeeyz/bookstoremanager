package org.example.GUI;

import MyCustom.MyFileChooser;
import org.example.BUS.LoaiSanPhamBUS;
import org.example.BUS.SanPhamBUS;
import org.example.BUS.TacGiaBUS;
import org.example.DAO.MyConnect;
import org.example.DTO.LoaiSanPham;
import org.example.DTO.TacGia;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;

import java.util.ArrayList;
public class DLG_ThemSP extends JFrame {
    //Danh sach thuoc tinh
    private JPanel pn_Main;
    private JLabel lb_TenDLG,lb_TenSP, lb_LoaiSP, lb_TG, lb_Image, lb_MoTa, lb_ImageView;
    private JTextField t_TenSP;
    private JTextArea t_MoTa;
    private JButton bt_SelectImage, bt_Confirm, bt_Cancel, bt_Refresh;
    private JComboBox cb_LoaiSP, cb_TG;
    private File fileImageSP=new File("");
    private SanPhamBUS spBUS = new SanPhamBUS();
    private LoaiSanPhamBUS loaiSPBUS = new LoaiSanPhamBUS();
    private TacGiaBUS tgBUS = new TacGiaBUS();
    private DefaultComboBoxModel model_CB_LoaiSP, model_CB_TG;
    
    public DLG_ThemSP(){
        addComponent();
        addMethod();
    }
    public void addComponent(){//Danh sach cac Component va Setup cac thuoc tinh co ban
        this.setDefaultCloseOperation(DLG_ThemSP.EXIT_ON_CLOSE);
        this.setSize(550,475);
        this.setLocationRelativeTo(null);//Can giua
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        //
        pn_Main=new JPanel();
        pn_Main.setBackground(SystemColor.control);
        pn_Main.setLayout(null);
        this.setContentPane(pn_Main);
        //
        lb_TenDLG = new JLabel("Thêm Mới Sản Phẩm");
        lb_TenDLG.setFont(new Font("Tahoma", Font.BOLD, 20));
        lb_TenDLG.setBounds(150,10,300,25);
        lb_TenDLG.setVisible(true);
        pn_Main.add(lb_TenDLG);
        //Hiển thị mục Tên SP
        lb_TenSP = new JLabel("Tên sản phẩm:");
        lb_TenSP.setFont(new Font("Tahoma",Font.PLAIN,16));
        lb_TenSP.setBounds(220,60,110,20);
        lb_TenSP.setVisible(true);
        pn_Main.add(lb_TenSP);
        //Nơi nhập tên SP
        t_TenSP =new JTextField("");
        t_TenSP.setBounds(350,60,150,22);
        t_TenSP.setVisible(true);
        pn_Main.add(t_TenSP);
        //Hiển thị mục Loại SP
        lb_LoaiSP = new JLabel("Loại sản phẩm:");
        lb_LoaiSP.setFont(new Font("Tahoma",Font.PLAIN,16));
        lb_LoaiSP.setBounds(220,110,120,20);
        lb_LoaiSP.setVisible(true);
        pn_Main.add(lb_LoaiSP);
        //Mục chọn Loại SP
        cb_LoaiSP = new JComboBox();
        cb_LoaiSP.setMaximumRowCount(15);
        cb_LoaiSP.setFont(new Font("Tahoma",Font.PLAIN,16));
        cb_LoaiSP.setBackground(Color.white);
        cb_LoaiSP.setBounds(350,110,150,22);
        pn_Main.add(cb_LoaiSP);
        //Hiển thị mục TG
        lb_TG =new JLabel("Tác giả:");
        lb_TG.setFont(new Font("Tahoma",Font.PLAIN,16));
        lb_TG.setBounds(220,160,120,20);
        lb_TG.setVisible(true);
        pn_Main.add(lb_TG);
        //
        cb_TG = new JComboBox();
        cb_TG.setFont(new Font("Tahoma",Font.PLAIN,16));
        cb_TG.setBackground(Color.white);
        cb_TG.setBounds(350,160,150,22);
        cb_TG.setVisible(true);
        pn_Main.add(cb_TG);
        //Hiển thị mục URL Image
        lb_Image = new JLabel("URL Image:");
        lb_Image.setFont(new Font("Tahoma",Font.PLAIN,16));
        lb_Image.setBounds(220,210,120,20);
        lb_Image.setVisible(true);
        pn_Main.add(lb_Image);
        //
        bt_SelectImage = new JButton("Chọn hình ảnh");
        bt_SelectImage.setFont(new Font("Tahammo",Font.PLAIN,16));
        bt_SelectImage.setBounds(350,210,150,22);
        bt_SelectImage.setVisible(true);
        pn_Main.add(bt_SelectImage);
        //Hiển thị mục mô tả
        lb_MoTa = new JLabel("Mô tả:");
        lb_MoTa.setFont(new Font("Tahoma",Font.PLAIN,16));
        lb_MoTa.setBounds(30,270,60,20);
        lb_MoTa.setVisible(true);
        pn_Main.add(lb_MoTa);
        //Nơi nhập mô tả về SP
        t_MoTa = new JTextArea("");
        t_MoTa.setFont(new Font("Tahoma",Font.PLAIN,16));
        t_MoTa.setBounds(95,270,405,70);
        pn_Main.add(t_MoTa);
        //Nơi hiển thị hình ảnh của SP
        lb_ImageView = new JLabel();
        lb_ImageView.setBounds(30,60,180,200);//*
        try{
            lb_ImageView.setIcon(new ImageIcon(ImageIO.read(new File("Image/default.png")).getScaledInstance(180, 200, Image.SCALE_SMOOTH)));
        }catch(Exception e){}
        
        lb_ImageView.setVisible(true);
        pn_Main.add(lb_ImageView);
        //
        bt_Confirm = new JButton("Xác nhận");
        bt_Confirm.setFont(new Font("Tahoma",Font.PLAIN,16));
        bt_Confirm.setBounds(220,350,100,30);
        pn_Main.add(bt_Confirm);
        //
        bt_Cancel = new JButton("Thoát");
        bt_Cancel.setFont(new Font("Tahoma",Font.PLAIN,16));
        bt_Cancel.setBounds(30,400,100,25);
        pn_Main.add(bt_Cancel);
        //
        bt_Refresh = new JButton("Làm mới");
        bt_Refresh.setFont(new Font("Tahoma",Font.PLAIN,16));
        bt_Refresh.setBounds(30,400,100,25);
        //pn_Main.add(bt_Refresh);
        //
        loadComboBox();
        this.setVisible(true);
        
    }
    private void addMethod(){//Danh sach cac chuc nang
        bt_SelectImage.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                selectImage();
            }
        });
        bt_Confirm.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                themSP();
            }
        });
        bt_Cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
    }
    private ImageIcon getandformatImage(String url){
        if(url.trim().equals("")){
            url="Default.png";
        }
        BufferedImage img = null;
        File fileImg = new File(url);

        if (!fileImg.exists()) {
            url = "default.png";
            fileImg = new File("Image/" + url);
        }

        try {
            img = ImageIO.read(fileImg);
            fileImageSP = new File(url);
        } catch (IOException e) {
             
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        }
        return null;
    }
    private void selectImage(){
        JFileChooser fileChooser=new MyFileChooser("Image/");
        FileNameExtensionFilter filter= new FileNameExtensionFilter("Tệp hình ảnh","jpg","png","jpeg");
        fileChooser.setFileFilter(filter);
        if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
            fileImageSP = fileChooser.getSelectedFile();
            loadImage(fileImageSP.getPath());
        }
    }
    private void loadImage(String url){
        lb_ImageView.setIcon(getandformatImage(url));
    }
    private void loadComboBox(){
        ArrayList <LoaiSanPham> dsloai = loaiSPBUS.getDanhSachLoai();
        if(dsloai==null){
            cb_LoaiSP.addItem("Thêm thể loại để chọn");
            cb_LoaiSP.setEnabled(false);
        }else{
            cb_LoaiSP.addItem("Chọn thể loại");
            for(LoaiSanPham loai : dsloai){
                cb_LoaiSP.addItem(loai.getMaLoai()+" - "+loai.getTenLoai());
            }
        }
        ArrayList <TacGia> dstg = tgBUS.getDanhSachTG();
        if(dstg==null){
            cb_TG.addItem("Thêm tác giả để chọn");
            cb_TG.setEnabled(false);
        }else{
            cb_TG.addItem("Chọn tác giả");
            for(TacGia tg : dstg){
                cb_TG.addItem(tg.getMaTG()+" - "+tg.getTenTG());
            }
        }
        
    }
    private void themSP(){
        spBUS.themSanPham(t_TenSP.getText(),cb_LoaiSP.getSelectedItem()+"",fileImageSP.getName(),cb_TG.getSelectedItem()+"",t_MoTa.getText());
    }
    
    public static void main(String[]args){
        new MyConnect();
        new DLG_ThemSP();
    }
}
