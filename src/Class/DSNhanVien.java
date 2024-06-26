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
public class DSNhanVien {
    public Connection con;
    
    
    public ArrayList<NhanVien> layDanhSachNhanVien()
    {
        ArrayList<NhanVien> ds = new ArrayList<>();
        try {
            
            
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            //KetNoi();
            String sql = "Select * FROM NHANVIEN WHERE TRANGTHAI=0";
            Statement st = conn.ketNoi().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                NhanVien n = new NhanVien();
                n.MaNV = rs.getString("MANV");
                n.TenNV = rs.getString("TENNV");
                n.TenTK = rs.getString("TENTAIKHOAN");
                n.ChucVu = rs.getString("CHUCVU");
                n.SDT = rs.getString("SDT");
                n.Email = rs.getString("EMAIL");
                n.MatKhau = rs.getString("MATKHAU");
                n.HinhAnh = rs.getString("HINHANH");
                ds.add(n);
 
            }
            //n.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DSNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
     public String SinhMaNhanVien() {
    String maloai = ""; // Default value

    try (Connection connection = new KetNoi().ketNoi();
         CallableStatement st = connection.prepareCall("{call sinhma_manvlonnhat}")) { // Call the stored procedure

        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            String ma = rs.getString("MANV"); // Assuming the column name is "MAKH"
            String so = ma.substring(2);
            maloai = "NV" + String.format("%03d", Integer.parseInt(so) + 1);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DSKhachHang.class.getName()).log(Level.SEVERE, "Error generating customer code", ex);
        // Optional: Throw a custom exception
    }

    return maloai;
}
     public ArrayList<NhanVien> timKiemNhanVien(String giatri) {
        ArrayList<NhanVien> ds = new ArrayList<>();
        try {
        KetNoi conn = new KetNoi();
        conn.ketNoi();
        CallableStatement st = conn.ketNoi().prepareCall("{call timKiemNhanVien (?)}");
        st.setString(1, giatri);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
                NhanVien n = new NhanVien();
                n.MaNV =rs.getString("MANV");
                n.TenNV = rs.getString("TENNV");
                n.TenTK = rs.getString("TENTAIKHOAN");
                n.ChucVu = rs.getString("CHUCVU");
                n.SDT =rs.getString("SDT");
                n.Email = rs.getString("EMAIL");
                n.MatKhau =rs.getString("MATKHAU");
                ds.add(n);
        }
        conn.ketNoi().close();
    } catch (SQLException ex) {
        Logger.getLogger(DSNhanVien.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ds;
    }
    
     public void themNhanVien(NhanVien nvien) {
    try {
        KetNoi conn = new KetNoi();
        conn.ketNoi();
        CallableStatement st = conn.ketNoi().prepareCall("{call insert_NhanVien (?,?,?,?,?,?,?,?)}");
        st.setString(1, nvien.MaNV);
        st.setString(2, nvien.TenNV);
        st.setString(3, nvien.TenTK);
        st.setString(4, nvien.ChucVu);
        st.setString(5, nvien.SDT);
        st.setString(6, nvien.Email);
        st.setString(7, nvien.MatKhau);
        st.setString(8, nvien.HinhAnh); // Đưa đường dẫn ảnh vào stored procedure
        st.executeUpdate();
        JOptionPane.showMessageDialog(null, "Thêm thành công");
        conn.ketNoi().close();
    } catch (SQLException ex) {
        Logger.getLogger(DSNhanVien.class.getName()).log(Level.SEVERE, null, ex); // Thay đổi DSKhuyenMai thành DSNhanVien
    }
}
     public void capNhatNhanVien(NhanVien nvien) {
    try {
        KetNoi conn = new KetNoi();
        conn.ketNoi();
        CallableStatement st = conn.ketNoi().prepareCall("{call update_NhanVien (?,?,?,?,?,?,?,?)}");
        st.setString(1, nvien.MaNV);
        st.setString(2, nvien.TenNV);
        st.setString(3, nvien.TenTK);
        st.setString(4, nvien.ChucVu);
        st.setString(5, nvien.SDT);
        st.setString(6, nvien.Email);
        st.setString(7, nvien.MatKhau);
        st.setString(8, nvien.HinhAnh);
        st.executeUpdate();
        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
        conn.ketNoi().close();
    } catch (SQLException ex) {
        Logger.getLogger(DSNhanVien.class.getName()).log(Level.SEVERE, null, ex);
    }
}
     public void xoaNhanVien(NhanVien nhanvien)
    {
  
         try {
             KetNoi conn = new KetNoi();
             conn.ketNoi();
             CallableStatement st = conn.ketNoi().prepareCall("{call delete_nhanvien (?)}");
             st.setString(1,nhanvien.getMaNV());
             st.executeUpdate();
             JOptionPane.showMessageDialog(null, "Xóa thành công");
             conn.ketNoi().close();
         } catch (SQLException ex) {
             Logger.getLogger(DSDanhMuc.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

}
