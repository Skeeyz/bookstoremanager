package org.example.DAO;

import MyCustom.XulyInput;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import com.sun.net.httpserver.Headers;
import org.example.DTO.SanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class SanPhamAPI {
    public static void main(String[]args) throws Exception{
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/sanpham", new SanPhamHandler());
        server.setExecutor(null);
        server.start();//Url:http://localhost:8080/api/sanpham
    }
    static class SanPhamHandler implements HttpHandler{
        MyConnect conn= new MyConnect();
        SanPhamDAO dao= new SanPhamDAO();
        XulyInput v=new XulyInput();
        int status_code=200;
        int size=0;
        @Override
        public void handle(HttpExchange exchange){
            try{
                boolean status=true;
                switch(exchange.getRequestMethod()){
                    case "GET"://Mẫu: Url or Url?maSP=1 or Url?maSP=1&Fields=hinhAnh or Url?maSP=1&Fields=donGia
                        String response;// Ngoài ra có thể thay maSP bằng maLoai or maTG
                        String query = exchange.getRequestURI().getQuery();
                        if(query==null || query.isEmpty()){
                            ArrayList<SanPham> listSanPham = dao.getListSanPham();
                            if(listSanPham.isEmpty()){
                                status_code=200;
                                response ="{\"Danh sách sản phẩm trống\"}";
                            }
                            else{
                                status_code=200;
                                response = arrayListconvertToJSon(listSanPham);
                            }
                        }else{
                            Map<String,String> option = convertQuery(query);
                            if(option.size()==1){
                                if(option.getOrDefault("maSP","maTG").equals("maTG")){
                                    if(option.getOrDefault("maTG","maLoai").equals("maLoai")){
                                        if(option.getOrDefault("maLoai","Default").equals("Default")){
                                            response="{\"error\": \"Invalid Query\"}";
                                            status_code=400;
                                        }else{
                                            String maLoai=option.get("maLoai").trim();
                                            if(v.dataso(maLoai)){
                                                int id=Integer.parseInt(maLoai);
                                                ArrayList<SanPham> listSanPham = dao.getSanPhamTheoLoai(id);
                                                response = arrayListconvertToJSon(listSanPham);
                                                status_code=200;
                                            }else{
                                                response = "{\"error\": \"Invalid Value in Query\"}";
                                                status_code=400;
                                            }
                                        }
                                    }
                                    else{
                                        String maTG=option.get("maTG").trim();
                                        if(v.dataso(maTG)){
                                            int id=Integer.parseInt(maTG);
                                            ArrayList<SanPham> listSanPham = dao.getSanPhamTheoTG(id);
                                            response = arrayListconvertToJSon(listSanPham);
                                            status_code=200;
                                        }else{
                                            response = "{\"error\": \"Invalid Value in Query\"}";
                                            status_code=400;
                                        }
                                    }
                                }
                                else{
                                    String maSP=option.get("maSP").trim();
                                    if(v.dataso(maSP)){
                                        int id=Integer.parseInt(maSP);
                                        SanPham sanpham = dao.getSanPham(id);
                                        response = sanpham.toString();
                                        status_code=200;
                                    }else{
                                        response = "{\"error\": \"Invalid Value in Query\"}";
                                        status_code=400;
                                    }
                                }
                            }else{
                                if(option.getOrDefault("maSP","Default").equals("Default")){
                                    status_code=400;
                                    response = "{\"error\": \"Invalid Value in Query\"}";
                                }
                                else{
                                    if(option.getOrDefault("Fields","Default").equals("Default")){
                                        status_code=400;
                                        response = "{\"error\": \"Invalid Value in Query\"}";
                                    }
                                    else{
                                        String maSP=option.get("maSP").trim();
                                        if(v.dataso(maSP)){
                                            int id=Integer.parseInt(maSP);
                                            if(option.get("Fields").equals("hinhAnh")){
                                                String path = dao.getAnh(id);
                                                response = "{\n \"hinhAnh\": '"+path+"'\n}";
                                                status_code=200;
                                            }else if(option.get("Fields").equals("donGia")){
                                                int donGia = dao.getGiaSP(id);
                                                response = "{\n \"donGia\": '"+donGia+"'\n}";
                                                status_code=200;
                                            }else{
                                                status_code=400;
                                                response = "{\"error\": \"Invalid Value in Query\"}";
                                            }
                                        }else{
                                            status_code=400;
                                            response = "{\"error\": \"Invalid Value in Query\"}";
                                        }
                                    }
                                }
                            }
                        }
                        exchange.getResponseHeaders().set("Content-Type","application/json");
                        exchange.sendResponseHeaders(status_code, response.getBytes().length);
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                        break;
                    case "POST":// Mẫu: {\n \"tenSP\": 'SPTest',\n \"maLoai\": 7,\n \"soLuong\": 0,\n \"donGia\": 0,\n\"hinhAnh\": 'uihuih',\n \"maTG\": 9,\n \"moTa": '.....'\n}
                        String response_post;
                        String query_post = exchange.getRequestURI().getQuery();
                        if(query_post!=null){
                            status_code=405;
                            response_post="{\"error\": \"Http no Support\"}";
                        }
                        else{
                            String postdata = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))
                                .lines().reduce("", (accumulator, actual)->accumulator+actual);
                            SanPham sp1 = jsonconvertSanPham(postdata);
                            if(size==5 && sp1!=null){//Them SP binh thuong
                                if(dao.themSanPham(sp1)==false){
                                    status_code=400;
                                    response_post= "{\"error\": \"Invalid JSON format\"}";
                                }else{
                                    status_code=200;
                                    response_post= "{\"successfull\": \"Them san pham thanh cong!\"}";
                                }
                            }
                            else if(size==7 && sp1!=null){//Them SP tu phieu nhap
                                if(dao.themSanPham_PN(sp1)==false) status=false;
                                if(status==false){
                                    status_code=400;
                                    response_post= "{\"error\": \"Invalid JSON format\"}";
                                }else{
                                    status_code=200;
                                    response_post= "{\"successfull\": \"Them san pham thanh cong!\"}";
                                }
                            }else{
                                status_code=405;
                                response_post="{\"error\": \"Http no Support\"}";
                            }
                            //exchange.getRequestHeaders().set("Content-Type", "application/json");
                            exchange.sendResponseHeaders(status_code, response_post.getBytes().length);
                            OutputStream os1= exchange.getResponseBody();
                            os1.write(response_post.getBytes());
                            os1.close();
                        }
                        break;
                    case "PUT":// Mẫu: Body {\n \"tenSP\": 'SPTest',\n \"maLoai\": 7,\n \"soLuong\": 0,\n \"donGia\": 0,\n\"hinhAnh\": 'uihuih',\n \"maTG\": 9,\n \"moTa": '.....'\n}
                        String response_put;//ở phần Url: Url?maSP=1
                        String query_put = exchange.getRequestURI().getQuery();
                        if(query_put==null){
                            status_code=405;
                            response_put="{\"error\": \"Http no Support\"}";
                        }else{
                            Map<String,String> option = convertQuery(query_put);
                            if(option.size()==1){
                                if(option.getOrDefault("maSP", "Default").equals("Default")){
                                    status_code=405;
                                    response_put="{\"error\": \"Http no Support\"}";
                                }else{
                                    String putdata  = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))
                                        .lines().reduce("", (accumulator, actual)->accumulator+actual);
                                    SanPham sp2 = jsonconvertSanPham(putdata);
                                    String maSP= option.get("maSP").trim();
                                    if(v.dataso(maSP) && sp2!=null){
                                        if(sp2.getMaSP()==Integer.parseInt(maSP)){
                                            if(size==3){//Sửa bình thường
                                            if(dao.suaSanPham(sp2)==false){
                                                status_code=400;
                                                response_put= "{\"error\": \"Invalid JSON format\"}";
                                            }else{
                                                status_code=200;
                                                response_put= "{\"successfull\": \"Sua san pham thanh cong!\"}";
                                            }
                                            }else if(size==8){//Sửa trong phiếu nhập
                                                if(dao.suaSanPham_PN(sp2)==false){
                                                    status_code=400;
                                                    response_put= "{\"error\": \"Invalid JSON format\"}";
                                                }else{
                                                    status_code=200;
                                                    response_put= "{\"successfull\": \"Sua san pham thanh cong!\"}";
                                                }
                                            }else{//Không hỗ trợ
                                                status_code=400;
                                                response_put= "{\"error\": \"Invalid JSON format\"}";
                                            }
                                        }else{//maSP khong trung khop voi nhau
                                            status_code=400;
                                            response_put= "{\"error\": \"Invalid Query and JSON\"}";
                                        }
                                        
                                    }else{
                                        status_code=400;
                                        response_put= "{\"error\": \"Invalid Query\"}";
                                    }
                                }
                            }else if(option.size()==2){
                                if(option.getOrDefault("maSP", "Default").equals("Default")){
                                    status_code=405;
                                    response_put="{\"error\": \"Http no Support\"}";
                                }else{
                                    String maSP=option.get("maSP").trim();
                                    if(v.dataso(maSP)){
                                        if(option.getOrDefault("soLuong", "Default").equals("Default")){
                                            status_code=405;
                                            response_put="{\"error\": \"Http no Support\"}";
                                        }else{
                                            String soLuong=option.get("soLuong").trim();
                                            if(v.dataso(soLuong)){
                                               dao.capNhatSoLuongSP(Integer.parseInt(maSP),Integer.parseInt(soLuong));
                                               status_code=200;
                                               response_put="{\"successful\": \"Update So luong thanh cong!\"}";
                                            }else{
                                                status_code=400;
                                                response_put= "{\"error\": \"Invalid Query\"}";
                                            }
                                        }
                                    }else{
                                        status_code=400;
                                        response_put= "{\"error\": \"Invalid Query\"}";
                                    }
                                }
                            }else{
                                status_code=405;
                                response_put="{\"error\": \"Http no Support5\"}";
                            }
                        }
                        //exchange.getRequestHeaders().set("Content-Type", "application/json");
                        exchange.sendResponseHeaders(status_code, response_put.getBytes().length);
                        OutputStream os2=exchange.getResponseBody();
                        os2.write(response_put.getBytes());
                        os2.close();
                        break;
                    case "DELETE":// Mẫu data test: Url?maSP=1
                        String response_del;
                        String query_del = exchange.getRequestURI().getQuery();
                        if(query_del==null){
                            status_code=405;
                            response_del="{\"error\": \"Http no Support\"}";
                        }else{
                            Map<String,String> option = convertQuery(query_del);
                            if(option.size()==1){
                                if(option.getOrDefault("maSP", "Default").equals("Default")){
                                    status_code=405;
                                    response_del="{\"error\": \"Http no Support\"}";
                                }else{
                                    String maSP=option.get("maSP").trim();
                                    if(v.dataso(maSP)){
                                        if(dao.xoaSanPham(Integer.parseInt(maSP))){
                                            status_code=200;
                                            response_del= "{\"successfull\": \"Xoa san pham thanh cong!\"}";
                                        }else{
                                            status_code=422;
                                            response_del= "{\"error\": \"Unprocessable Entity\"}";
                                        }
                                    }else{
                                        status_code=400;
                                        response_del= "{\"error\": \"Invalid Query\"}";
                                    }
                                    
                                }
                            }
                            else{
                                status_code=405;
                                response_del="{\"error\": \"Http no Support\"}";
                            }
                        }
                        //exchange.getRequestHeaders().set("Content-Type", "application/json");
                        exchange.sendResponseHeaders(status_code, response_del.getBytes().length);
                        OutputStream os3=exchange.getResponseBody();
                        os3.write(response_del.getBytes());
                        os3.close();
                        break;
                    default:
                        exchange.sendResponseHeaders(405, -1);
                        break;
                }
            }catch(Exception e){
                e.printStackTrace();
                try {
                    exchange.sendResponseHeaders(500, -1); // Internal Server Error
                } catch (Exception ignored) {}
            }
        }
        
        public String arrayListconvertToJSon(ArrayList<SanPham> ds){
            StringBuilder json = new StringBuilder("[");
            for(int i=0;i<ds.size();i++){
                json.append("\n").append(ds.get(i).toString()).append("\n");
                if(i<ds.size()-1) json.append(',');
            }
            json.append("]");
            return json.toString();
        }
        public SanPham jsonconvertSanPham(String json){
            SanPham sp;
            json=json.replace("\n", "");
            json=json.replace("{", "");
            json=json.replace("}", "");
            json=json.replace("\"","");
            json=json.replace("'","");
            String[] list = json.split(", ");
            switch (list.length) {
                case 5:
                    size=5;
                    try{
                        String tenSP=list[0].substring(list[0].indexOf(": ")+2);
                        int maLoai=Integer.parseInt(list[1].substring(list[1].indexOf(": ")+2));
                        String hinhAnh=list[2].substring(list[2].indexOf(": "));
                        int maTG=Integer.parseInt(list[3].substring(list[3].indexOf(": ")+2));
                        String moTa=list[4].substring(list[4].indexOf(": ")+2);
                        sp= new SanPham(0, tenSP, maLoai, 0, 0, hinhAnh, maTG, moTa);
                    }
                    catch(Exception e){
                        sp=null;
                    }   break;
                case 7:
                    size=7;
                    try{
                        String tenSP=list[0].substring(list[0].indexOf(": ")+2);
                        int maLoai=Integer.parseInt(list[1].substring(list[1].indexOf(": ")+2));
                        int soLuong=Integer.parseInt(list[2].substring(list[2].indexOf(": ")+2));
                        int donGia=Integer.parseInt(list[3].substring(list[3].indexOf(": ")+2));
                        String hinhAnh=list[4].substring(list[4].indexOf(": "));
                        int maTG=Integer.parseInt(list[5].substring(list[5].indexOf(": ")+2));
                        String moTa=list[6].substring(list[6].indexOf(": ")+2);
                        sp= new SanPham(0, tenSP, maLoai, soLuong, donGia, hinhAnh, maTG, moTa);
                    }catch(Exception e){
                        sp=null;
                    }   break;
                case 8:
                    size=8;
                    try{
                        int maSP=Integer.parseInt(list[0].substring(list[0].indexOf(": ")+2));
                        String tenSP=list[1].substring(list[1].indexOf(": ")+2);
                        int maLoai=Integer.parseInt(list[2].substring(list[2].indexOf(": ")+2));
                        int soLuong=Integer.parseInt(list[3].substring(list[3].indexOf(": ")+2));
                        int donGia=Integer.parseInt(list[4].substring(list[4].indexOf(": ")+2));
                        String hinhAnh=list[5].substring(list[5].indexOf(": "));
                        int maTG=Integer.parseInt(list[6].substring(list[6].indexOf(": ")+2));
                        String moTa=list[7].substring(list[7].indexOf(": ")+2);
                        sp= new SanPham(maSP, tenSP, maLoai, soLuong, donGia, hinhAnh, maTG, moTa);
                    }catch(Exception e){
                        sp=null;
                    }   break;
                case 3:
                    size=3;
                    try{
                        int maSP=Integer.parseInt(list[0].substring(list[0].indexOf(": ")+2));
                        String hinhAnh=list[1].substring(list[1].indexOf(": ")+2);
                        String moTa=list[2].substring(list[2].indexOf(": ")+2);
                        sp= new SanPham(maSP,"", 0, 0, 0, hinhAnh,0, moTa);
                    }catch(Exception e){
                        sp=null;
                    }   break;
                default:
                    size=0;
                    sp=null;
                    break;
            }
            return sp;
        }
        public int jsonconvertToMaSP(String json){
            json=json.replace("\n", "");
            json=json.replace("{", "");
            json=json.replace("}", "");
            json=json.replace("\"","");
            int maSP=Integer.parseInt(json.substring(json.indexOf(": ")+2));
            return maSP;
        }
        public Map<String,String> convertQuery(String query){
            Map<String,String> option = new HashMap<>();
            String pairs[]=query.split("&");
            for(String pair:pairs){
                String keys[]=pair.split("=",2);
                String key=URLDecoder.decode(keys[0], StandardCharsets.UTF_8);
                String value=keys.length>1?URLDecoder.decode(keys[1], StandardCharsets.UTF_8):"";
                option.put(key, value);
            }
            return option;
        }
    }
}
