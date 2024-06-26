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
public class DanhMuc {
    public String maloai;
    public String Tenloai;
    public String mach;
    public DanhMuc(){}

    public DanhMuc(String maloai, String Tenloai, String mach) {
        this.maloai = maloai;
        this.Tenloai = Tenloai;
        this.mach = mach;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return Tenloai;
    }

    public void setTenloai(String Tenloai) {
        this.Tenloai = Tenloai;
    }

    public String getMach() {
        return mach;
    }

    public void setMach(String mach) {
        this.mach = mach;
    }

    
}