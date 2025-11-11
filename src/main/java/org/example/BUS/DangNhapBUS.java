package org.example.BUS;

import MyCustom.MyDialog;
import MyCustom.XulyInput;
import org.example.DAO.DangNhapDAO;
import org.example.DTO.TaiKhoan;

import java.io.*;

public class DangNhapBUS {

   
    public static TaiKhoan taiKhoanLogin = null;

    public TaiKhoan getTaiKhoanDangNhap(String user, String password, boolean selected) {
    	 if (user.equals("")) {
            new MyDialog("Tên đăng nhập không được để trống!", MyDialog.ERROR_DIALOG);
            return null;
         }else if(user.length()<5 || user.length()>50){
             new MyDialog("Tên đăng nhập không hợp lệ, tên đăng nhập phải có từ 5-50 kí tự!", MyDialog.ERROR_DIALOG);
            return null;
         }else if(!new XulyInput().khongkitudacbiet(user)){
             new MyDialog("Tên đăng nhập không hợp lệ, tên đăng nhập không được chứa kí tự đặc biệt!", MyDialog.ERROR_DIALOG);
            return null;
         }
         if (password.equals("")) {
            new MyDialog("Mật khẩu không được để trống!", MyDialog.ERROR_DIALOG);
            return null;
         }else if(password.length()<5 || password.length()>50){
             new MyDialog("Mật khẩu không hợp lệ, mật khẩu phải có từ 5-50 kí tự!", MyDialog.ERROR_DIALOG);
            return null;
         }
        TaiKhoan tk = new TaiKhoan();
        tk.setTenDangNhap(user);
        tk.setMatKhau(password);

        DangNhapDAO dangNhapDAO = new DangNhapDAO();
        TaiKhoan account = dangNhapDAO.dangNhap(tk);
        taiKhoanLogin = account;

        if (account == null) {
            new MyDialog("Sai thông tin đăng nhập hoặc tài khoản đã bị khoá!", MyDialog.ERROR_DIALOG);
        } else {
            PhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
            phanQuyenBUS.kiemTraQuyen(account.getQuyen());
            xuLyGhiNhoDangNhap(user, password, selected);
            new MyDialog("Đăng nhập thành công!", MyDialog.SUCCESS_DIALOG);
        }
        return account;
    }

    public String getTaiKhoanGhiNho() {
        try {
            FileInputStream fis = new FileInputStream("remember.dat");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            br.close();
            return line;
        }catch (Exception e) {
        	System.out.println(e);
        }
        return "";
    }

    private void xuLyGhiNhoDangNhap(String user, String password, boolean selected) {
        try {
            if (!selected) {
                user = "";
                password = "";
            }
            FileWriter fw = new FileWriter("remember.dat");
            fw.write(user + " | " + password);
            fw.close();
        } catch (Exception e) {
        	System.out.println(e);
        }
    }

  

}
