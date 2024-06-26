/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Phan Tran Minh Tam
 */
public class CuaHang {
    public String mach;
    public String Tench;
    public String Ghichu;
    
    CuaHang() {}
    public CuaHang(String mach, String Tench, String Ghichu) {
        this.mach = mach;
        this.Tench = Tench;
        this.Ghichu = Ghichu;
    }

    public String getMach() {
        return mach;
    }

    public void setMach(String mach) {
        this.mach = mach;
    }

    public String getTench() {
        return Tench;
    }

    public void setTench(String Tench) {
        this.Tench = Tench;
    }

    public String getGhichu() {
        return Ghichu;
    }

    public void setGhichu(String Ghichu) {
        this.Ghichu = Ghichu;
    }
    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    /**
     *
     * @author Phan Tran Minh Tam
     */
  
}
