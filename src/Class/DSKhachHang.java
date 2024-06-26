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
 * @author Phan Tran Minh Tam
 */
public class DSKhachHang {
    public Connection con;
    
    
    public ArrayList<KhachHang> layDanhSachKhachHang()
    {
        ArrayList<KhachHang> ds = new ArrayList<>();
        try {
            
            
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            //KetNoi();
            String sql = "Select * FROM KHACHHANG";
            Statement st = conn.ketNoi().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                KhachHang n = new KhachHang();
                n.maKH = rs.getString("MAKH");
                n.tenKH = rs.getString("TENKH");
                n.DiaChi = rs.getString("DIACHI");
                n.SDT = rs.getString("SDT");
                n.Email = rs.getString("EMAIL");
                ds.add(n);
 
            }
            //n.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DSKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    public ArrayList<KhachHang> timKiemKhachHang(String giatri) {
    ArrayList<KhachHang> ds = new ArrayList<>();
    try {
        KetNoi conn = new KetNoi();
        conn.ketNoi();
        CallableStatement st = conn.ketNoi().prepareCall("{call timKiemKhachHang (?)}");
        st.setString(1, giatri);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            KhachHang n = new KhachHang();
            n.maKH = rs.getString("MAKH"); // Đảm bảo tên cột trong cơ sở dữ liệu chính xác
            n.tenKH = rs.getString("TENKH");
            n.DiaChi = rs.getString("DIACHI");
            n.SDT = rs.getString("SDT");
            n.Email = rs.getString("EMAIL"); // Đảm bảo tên cột trong cơ sở dữ liệu chính xác
            ds.add(n);
        }
        conn.ketNoi().close();
    } catch (SQLException ex) {
        Logger.getLogger(DSKhachHang.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ds;
    }
    
    public ArrayList<KhachHang> DanhSachKhachHang_TheoSDT(String dienthoai) {
        ArrayList<KhachHang> ds = new ArrayList<>();
        try {
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            CallableStatement st = conn.ketNoi().prepareCall("{call DanhSachKHTheo_SDT (?)}");
            st.setString(1, dienthoai);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                KhachHang n = new KhachHang();
                n.maKH = rs.getString("MAKH"); // Đảm bảo tên cột trong cơ sở dữ liệu chính xác
                n.tenKH = rs.getString("TENKH");
                n.DiaChi = rs.getString("DIACHI");
                n.SDT = rs.getString("SDT");
                n.Email = rs.getString("EMAIL"); // Đảm bảo tên cột trong cơ sở dữ liệu chính xác
                ds.add(n);
            }
            conn.ketNoi().close();
        } catch (SQLException ex) {
            Logger.getLogger(DSKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    
    public String SinhMaKhachHang() {
    String maloai = ""; // Default value

    try (Connection connection = new KetNoi().ketNoi();
         CallableStatement st = connection.prepareCall("{call sinhma_makhlonnhat}")) { // Call the stored procedure

        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            String ma = rs.getString("MAKH"); // Assuming the column name is "MAKH"
            String so = ma.substring(2);
            maloai = "KH" + String.format("%03d", Integer.parseInt(so) + 1);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DSKhachHang.class.getName()).log(Level.SEVERE, "Error generating customer code", ex);
        // Optional: Throw a custom exception
    }

    return maloai;
}
    public boolean themKH(KhachHang khachhang) {
    try {
        KetNoi conn = new KetNoi();
        conn.ketNoi();
        CallableStatement st = conn.ketNoi().prepareCall("{call insert_KhachHang (?,?,?,?,?)}");
        st.setString(1, khachhang.maKH);
        st.setString(2, khachhang.tenKH);
        st.setString(3, khachhang.DiaChi);
        st.setString(4, khachhang.SDT);
        st.setString(5, khachhang.Email);
        st.executeUpdate();
        conn.ketNoi().close();
        return true; // Trả về true nếu thêm thành công
    } catch (SQLException ex) {
        Logger.getLogger(DSKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        return false; // Trả về false nếu có lỗi xảy ra
    }
}

    
    public boolean capNhatKH(KhachHang khachhang) {
    try {
        KetNoi conn = new KetNoi();
        conn.ketNoi();
        CallableStatement st = conn.ketNoi().prepareCall("{call update_KhachHang (?,?,?,?,?)}");
        st.setString(1, khachhang.maKH);
        st.setString(2, khachhang.tenKH);
        st.setString(3, khachhang.DiaChi);
        st.setString(4, khachhang.SDT);
        st.setString(5, khachhang.Email);
        int rowsAffected = st.executeUpdate();
        conn.ketNoi().close();
        return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng được cập nhật
    } catch (SQLException ex) {
        Logger.getLogger(DSKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        return false; // Trả về false nếu có lỗi xảy ra
    }
}
}
