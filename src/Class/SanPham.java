/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author Triss
 */
public class SanPham {
    public String MaSP;
    public String TenSP;
    public String KichThuoc;
    public String MauSac;
    public String ChatLieu;
    public String HinhAnh;
    public float GiaBan;
    public float GiaBanSi;
    public String MaNCC;
    public String MaLoai;
    public SanPham(){}

    public SanPham(String MaSP, String TenSP, String KichThuoc, String MauSac, String ChatLieu, String HinhAnh, float GiaBan, float GiaBanSi, String MaNCC, String MaLoai) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.KichThuoc = KichThuoc;
        this.MauSac = MauSac;
        this.ChatLieu = ChatLieu;
        this.HinhAnh = HinhAnh;
        this.GiaBan = GiaBan;
        this.GiaBanSi = GiaBanSi;
        this.MaNCC = MaNCC;
        this.MaLoai = MaLoai;
    }
    
    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getKichThuoc() {
        return KichThuoc;
    }

    public void setKichThuoc(String KichThuoc) {
        this.KichThuoc = KichThuoc;
    }

    public String getMauSac() {
        return MauSac;
    }

    public void setMauSac(String MauSac) {
        this.MauSac = MauSac;
    }

    public String getChatLieu() {
        return ChatLieu;
    }

    public void setChatLieu(String ChatLieu) {
        this.ChatLieu = ChatLieu;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public float getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(float GiaBan) {
        this.GiaBan = GiaBan;
    }

    public float getGiaBanSi() {
        return GiaBanSi;
    }

    public void setGiaBanSi(float GiaBanSi) {
        this.GiaBanSi = GiaBanSi;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }
    
}
