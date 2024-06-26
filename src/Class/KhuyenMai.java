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
public class KhuyenMai {
    public String MaKM;
    public String TenKM;
    public String MaSP;
    public int SoLuongKM;
    public float GiaKM;
    public float PhanTramKM;
    public Date NGAYBATDAU ;
    public Date NGAYKETHUC;
    
    public KhuyenMai(){}

    public KhuyenMai(String MaKM, String TenKM, String MaSP, float GiaKM, int SoLuongKM, float PhanTramKM, Date NGAYBATDAU, Date NGAYKETHUC) {
        this.MaKM = MaKM;
        this.TenKM = TenKM;
        this.MaSP = MaSP;
        this.GiaKM = GiaKM;
        this.SoLuongKM = SoLuongKM;
        this.PhanTramKM = PhanTramKM;
        this.NGAYBATDAU = NGAYBATDAU;
        this.NGAYKETHUC = NGAYKETHUC;
    }

    public String getMaKM() {
        return MaKM;
    }

    public void setMaKM(String MaKM) {
        this.MaKM = MaKM;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String TenKM) {
        this.TenKM = TenKM;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public int getSoLuongKM() {
        return SoLuongKM;
    }

    public void setSoLuongKM(int SoLuongKM) {
        this.SoLuongKM = SoLuongKM;
    }

    public float getGiaKM() {
        return GiaKM;
    }

    public void setGiaKM(float GiaKM) {
        this.GiaKM = GiaKM;
    }

    public float getPhanTramKM() {
        return PhanTramKM;
    }

    public void setPhanTramKM(float PhanTramKM) {
        this.PhanTramKM = PhanTramKM;
    }

    public Date getNGAYBATDAU() {
        return NGAYBATDAU;
    }

    public void setNGAYBATDAU(Date NGAYBATDAU) {
        this.NGAYBATDAU = NGAYBATDAU;
    }

    public Date getNGAYKETHUC() {
        return NGAYKETHUC;
    }

    public void setNGAYKETHUC(Date NGAYKETHUC) {
        this.NGAYKETHUC = NGAYKETHUC;
    }

    
    
}
