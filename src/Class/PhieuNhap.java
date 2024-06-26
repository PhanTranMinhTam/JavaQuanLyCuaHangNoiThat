/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.sql.Date;

/**
 *
 * @author Phan Tran Minh Tam
 */
public class PhieuNhap {

   
    public String maPhieuNhap ;
    public String maNhanVien;
    public Date NgayLap ;
    public String maNCC; 
    public String tenNCC;
    public String maSP;
    public String tenSP;
    public String mach;
    public int soLuong;
    public Float DonGia;
    
    public PhieuNhap(){}

    public PhieuNhap(String maPhieuNhap, String maNhanVien, Date NgayLap, String maNCC, String tenNCC, String maSP, String tenSP, String mach, int soLuong, Float DonGia) {
        this.maPhieuNhap = maPhieuNhap;
        this.maNhanVien = maNhanVien;
        this.NgayLap = NgayLap;
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.mach = mach;
        this.soLuong = soLuong;
        this.DonGia = DonGia;
    }

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getmach() {
        return mach;
    }

    public void setmach(String mach) {
        this.mach = mach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Float getDonGia() {
        return DonGia;
    }

    public void setDonGia(Float DonGia) {
        this.DonGia = DonGia;
    }
    
    
}
