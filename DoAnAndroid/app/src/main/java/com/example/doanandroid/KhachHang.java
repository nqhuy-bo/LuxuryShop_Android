package com.example.doanandroid;

import java.io.Serializable;

public class KhachHang implements Serializable {
    private String hoTen,soDienThoai,matKhau;

    public KhachHang() {
    }

    public KhachHang(String hoTen, String soDienThoai, String matKhau) {
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
