package com.example.doanandroid;

import java.io.Serializable;

public class SanPham implements Serializable {
    int MASANPHAM,MALOAI,GIA;
    String TENSANPHAM,MOTASANPHAM,HINHANH;

    public SanPham(int MASANPHAM, int MALOAI, int GIA, String TENSANPHAM, String MOTASANPHAM, String HINHANH) {
        this.MASANPHAM = MASANPHAM;
        this.MALOAI = MALOAI;
        this.GIA = GIA;
        this.TENSANPHAM = TENSANPHAM;
        this.MOTASANPHAM = MOTASANPHAM;
        this.HINHANH = HINHANH;
    }

    public SanPham() {
    }

    public int getMASANPHAM() {
        return MASANPHAM;
    }

    public void setMASANPHAM(int MASANPHAM) {
        this.MASANPHAM = MASANPHAM;
    }

    public int getMALOAI() {
        return MALOAI;
    }

    public void setMALOAI(int MALOAI) {
        this.MALOAI = MALOAI;
    }

    public int getGIA() {
        return GIA;
    }

    public void setGIA(int GIA) {
        this.GIA = GIA;
    }

    public String getTENSANPHAM() {
        return TENSANPHAM;
    }

    public void setTENSANPHAM(String TENSANPHAM) {
        this.TENSANPHAM = TENSANPHAM;
    }

    public String getMOTASANPHAM() {
        return MOTASANPHAM;
    }

    public void setMOTASANPHAM(String MOTASANPHAM) {
        this.MOTASANPHAM = MOTASANPHAM;
    }

    public String getHINHANH() {
        return HINHANH;
    }

    public void setHINHANH(String HINHANH) {
        this.HINHANH = HINHANH;
    }
}
