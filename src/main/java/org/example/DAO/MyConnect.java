package org.example.DAO;

//import com.mysql.jdbc.Driver;

import MyCustom.MyDialog;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyConnect {
    
//    public static Connection conn = null;
//    private String severName="localhost:81";
//    private String dbName="quanlynhasach";
//    private String userName="root";
//    private String password="";
//    private String url="jdbc:sqlserver://localhost:1433;databaseName=QUANLYNHASACH;encrypt=true;trustServerCertificate=true;trustStore=/path/to/truststore;trustStorePassword=mypassword";
//
//    public MyConnect() {
//    	
//    	//docFileText();
//    	
//        String strConnect = "jdbc:mysql://" + severName + "/" + dbName + "?useUnicode=true&characterEncoding=utf-8";
//        Properties pro = new Properties();
//        pro.put("user", userName);
//        pro.put("password", password);
//        try {
//            com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
//            conn = driver.connect(strConnect, pro);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            new MyDialog("Không kết nối được tới CSDL!", MyDialog.ERROR_DIALOG);
//            System.exit(0);
//        }
//    }
    public static Connection conn=null;
    public String serverName="localhost:3306";
    public String userName="root";
    public String password="123456";
    public String dbName="QUANLYNHASACH";
    public MyConnect(){
        String url = "jdbc:mysql://"+serverName+"/"+dbName+"?useUnicode=true&characterEncoding=UTF-8&connectionCollation=utf8mb4_unicode_ci";
        try{
            conn=DriverManager.getConnection(url, userName, password);
        }catch( SQLException ex){
            ex.printStackTrace();
            new MyDialog("Không kết nối được tới CSDL!",MyDialog.ERROR_DIALOG);
        }
    }
    public MyConnect(String severName, String dbName, String userName, String password) {
        this.serverName = severName;
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
        String url = "jdbc:mysql://"+serverName+"/"+dbName+"?useUnicode=true&characterEncoding=UTF-8&connectionCollation=utf8mb4_unicode_ci";
        try{
            conn=DriverManager.getConnection(url, userName, password);
        }catch( SQLException ex){
            ex.printStackTrace();
            new MyDialog("Không kết nối được tới CSDL!",MyDialog.ERROR_DIALOG);
        }
    }

//    public MyConnect() {//sqlserver
//        try {
//            conn=DriverManager.getConnection(url, userName, password);
//        } catch (SQLException ex) {
//            Logger.getLogger(MyConnect.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public Connection open(){
//        try {
//            conn=DriverManager.getConnection("", userName, password);
//        } catch (SQLException ex) {
//            Logger.getLogger(MyConnect.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return conn;
//    }
    

//	private void docFileText() {
//		
//		serverName = "";
//		dbName = "";
//		userName = "";
//		password = "";
//
//		try {
//			FileInputStream fis = new FileInputStream("Connect.txt");
//			InputStreamReader isr = new InputStreamReader(fis);
//			BufferedReader br = new BufferedReader(isr);
//
//			serverName = br.readLine();
//			dbName = br.readLine();
//			userName = br.readLine();
//			password = br.readLine();
//
//			if (password == null) {
//				password = "";
//			}
//
//		} catch (Exception e) {
//		}
//	}
        public static void main(String[]args){
            Connection connect=new MyConnect().conn;
            if(conn!=null)
                System.out.print("Kết nối thành công");
        }
}
