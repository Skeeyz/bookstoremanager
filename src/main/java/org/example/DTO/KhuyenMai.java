package org.example.DTO;

import java.util.Date;

public class KhuyenMai {
    private int maKM;
    private String tenKM;
    private Date ngayBD;
    private Date ngayKT;
    private int phanTramGiam;
    private int dieuKien;
	public KhuyenMai() {
	}


	public KhuyenMai(int maKM, String tenKM, Date ngayBD, Date ngayKT, int phanTramGiam, int dieuKien) {
		this.maKM = maKM;
		this.tenKM = tenKM;
		this.ngayBD = ngayBD;
		this.ngayKT = ngayKT;
                this.phanTramGiam=phanTramGiam;
                this.dieuKien=dieuKien;
	}

    public void setPhanTramGiam(int phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public void setDieuKien(int dieuKien) {
        this.dieuKien = dieuKien;
    }

    public int getPhanTramGiam() {
        return phanTramGiam;
    }

    public int getDieuKien() {
        return dieuKien;
    }


	public int getMaKM() {
		return maKM;
	}


	public void setMaKM(int maKM) {
		this.maKM = maKM;
	}


	public String getTenKM() {
		return tenKM;
	}


	public void setTenKM(String tenKM) {
		this.tenKM = tenKM;
	}


	public Date getNgayBD() {
		return ngayBD;
	}


	public void setNgayBD(Date ngayBD) {
		this.ngayBD = ngayBD;
	}


	public Date getNgayKT() {
		return ngayKT;
	}


	public void setNgayKT(Date ngayKT) {
		this.ngayKT = ngayKT;
	}
    
    
}
