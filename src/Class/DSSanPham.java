/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Triss
 */
public class DSSanPham {
    public Connection con;
    
    
    public ArrayList<SanPham> layDanhSachSanPham()
    {
        ArrayList<SanPham> ds = new ArrayList<>();
        try {
            
            
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            //KetNoi();
            String sql = "Select * FROM SANPHAM WHERE TRANGTHAI=0";
            Statement st = conn.ketNoi().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                SanPham n = new SanPham();
                n.MaSP = rs.getString("MASP");
                n.TenSP = rs.getString("TENSP");
                n.KichThuoc = rs.getString("KICHTHUOC");
                n.MauSac = rs.getString("MAUSAC");
                n.ChatLieu = rs.getString("CHATLIEU");
                n.HinhAnh = rs.getString("HINHANH");
                n.GiaBan = rs.getFloat("GIABAN");
                n.GiaBanSi = rs.getFloat("GIABANSI");
                n.MaNCC = rs.getString("MANCC");
                n.MaLoai = rs.getString("MALOAI");
                ds.add(n);
 
            }
            //n.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DSSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    public ArrayList<SanPham> timKiemSanPham(String giatri) {
    ArrayList<SanPham> ds = new ArrayList<>();
    try {
        KetNoi conn = new KetNoi();
        conn.ketNoi();
        CallableStatement st = conn.ketNoi().prepareCall("{call timKiemSanPham (?)}");
        st.setString(1, giatri);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            SanPham n = new SanPham();
            n.MaSP = rs.getString("MASP");
                n.TenSP = rs.getString("TENSP");
                n.KichThuoc = rs.getString("KICHTHUOC");
                n.MauSac = rs.getString("MAUSAC");
                n.ChatLieu = rs.getString("CHATLIEU");
                n.HinhAnh = rs.getString("HINHANH");
                n.GiaBan = rs.getFloat("GIABAN");
                n.GiaBanSi = rs.getFloat("GIABANSI");
                n.MaNCC = rs.getString("MANCC");
                n.MaLoai = rs.getString("MALOAI");
            ds.add(n);
 
        }
        conn.ketNoi().close();
    } catch (SQLException ex) {
        Logger.getLogger(DSSanPham.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ds;
    }
    public String SinhMaSanPham() {
    String maSP= ""; // Default value

    try (Connection connection = new KetNoi().ketNoi();
         CallableStatement st = connection.prepareCall("{call sinhma_masplonnhat}")) { // Call the stored procedure

        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            String ma = rs.getString("MASP"); // Assuming the column name is "MAKH"
            String so = ma.substring(2);
            maSP = "SP" + String.format("%03d", Integer.parseInt(so) + 1);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DSSanPham.class.getName()).log(Level.SEVERE, "Error generating customer code", ex);
        // Optional: Throw a custom exception
    }

    return maSP;
}

    //Lấy GIABANSI của sản phẩm
    public float layGiaBanSi(String maSP) {
    float giaBanSi = 0; // Default value

    try (Connection connection = new KetNoi().ketNoi();
         CallableStatement st = connection.prepareCall("{call layGiaBanSi (?)}")) { // Call the stored procedure
        st.setString(1, maSP);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            giaBanSi = rs.getFloat("GIABANSI");
        }
    } catch (SQLException ex) {
        Logger.getLogger(DSSanPham.class.getName()).log(Level.SEVERE, "Error getting wholesale price", ex);
        // Optional: Throw a custom exception
    }

    return giaBanSi;
}

    //Lấy GIABAN của sản phẩm
    public float layGiaBan(String maSP) {
    float giaBan = 0; // Default value

    try (Connection connection = new KetNoi().ketNoi();
         CallableStatement st = connection.prepareCall("{call layGiaBan (?)}")) { // Call the stored procedure
        st.setString(1, maSP);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            giaBan = rs.getFloat("GIABAN");
        }
    } catch (SQLException ex) {
        Logger.getLogger(DSSanPham.class.getName()).log(Level.SEVERE, "Error getting retail price", ex);
        // Optional: Throw a custom exception
    }

    return giaBan;
}
    //Thêm sản phẩm
    public void themSanPham(SanPham sp) {
 try {
        KetNoi conn = new KetNoi();
        conn.ketNoi();
    CallableStatement st = conn.ketNoi().prepareCall("{call insert_SanPham (?,?,?,?,?,?,?,?,?,?)}");
    st.setString(1, sp.MaSP);
    st.setString(2, sp.TenSP);
    st.setString(3, sp.KichThuoc);
    st.setString(4, sp.MauSac);
    st.setString(5, sp.ChatLieu);
    st.setString(6, sp.HinhAnh);
    st.setFloat(7, sp.GiaBan);
    st.setFloat(8, sp.GiaBanSi);
    st.setString(9, sp.MaNCC);
    st.setString(10, sp.MaLoai);
    st.executeUpdate();
    JOptionPane.showMessageDialog(null, "Thêm thành công");
    conn.ketNoi().close();
    } catch (SQLException ex) {
        Logger.getLogger(DSSanPham.class.getName()).log(Level.SEVERE, null, ex);
    }
   }

    //Sửa sản phẩm
    public void capNhatSanPham(SanPham sp) {
    try {
        KetNoi conn = new KetNoi();
        conn.ketNoi();
        CallableStatement st = conn.ketNoi().prepareCall("{call update_SanPham (?,?,?,?,?,?,?,?,?,?)}");
        st.setString(1, sp.MaSP);
        st.setString(2, sp.TenSP);
        st.setString(3, sp.KichThuoc);
        st.setString(4, sp.MauSac);
        st.setString(5, sp.ChatLieu);
        st.setString(6, sp.HinhAnh);
        st.setFloat(7, sp.GiaBan);
        st.setFloat(8, sp.GiaBanSi);
        st.setString(9, sp.MaNCC);
        st.setString(10, sp.MaLoai);
        st.executeUpdate();
        JOptionPane.showMessageDialog(null, "Sửa thành công");
        conn.ketNoi().close();
    } catch (SQLException ex) {
        Logger.getLogger(DSSanPham.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    //Xóa sản phẩm
    public void xoaSanPham(SanPham sp) {
    try {
        KetNoi conn = new KetNoi();
        conn.ketNoi();
        CallableStatement st = conn.ketNoi().prepareCall("{call delete_sanpham (?)}");
        st.setString(1, sp.MaSP);
        st.executeUpdate();
        JOptionPane.showMessageDialog(null, "Xóa thành công");
        conn.ketNoi().close();
    } catch (SQLException ex) {
        Logger.getLogger(DSSanPham.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
