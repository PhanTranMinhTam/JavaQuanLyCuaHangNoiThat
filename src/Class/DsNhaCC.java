/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * 
 */


public class DsNhaCC {
    
    public Connection con;
    
    public ArrayList<NhaCungCap> layDanhSachNCC()
    {
        ArrayList<NhaCungCap> ds = new ArrayList<>();
        try {
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            //KetNoi();
            String sql = "Select * FROM NHACUNGCAP WHERE TRANGTHAI=0";
            Statement st = conn.ketNoi().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                NhaCungCap n = new NhaCungCap();
                n.MaNCC = rs.getString("MANCC");
                n.TenNCC = rs.getString("TENNCC");
                n.DiaChi = rs.getString("DIACHI");
                n.SDT = rs.getString("SDT");
                n.Email = rs.getString("EMAIL");
                n.maCH = rs.getString("MACH");
                n.maloai = rs.getString("MALOAI");
                ds.add(n);
 
            }
            //n.con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DsNhaCC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    
    public ArrayList<NhaCungCap> timKiemNhaCungCap(String giatri) {
    ArrayList<NhaCungCap> ds = new ArrayList<>();
    try {
        KetNoi conn = new KetNoi();
        conn.ketNoi();
        CallableStatement st = conn.ketNoi().prepareCall("{call timKiemNCC (?)}");
        st.setString(1, giatri);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            NhaCungCap n = new NhaCungCap();
            n.MaNCC = rs.getString("MaNCC"); // Đảm bảo tên cột trong cơ sở dữ liệu chính xác
            n.TenNCC = rs.getString("TenNCC");
            n.DiaChi = rs.getString("DiaChi");
            n.SDT = rs.getString("SDT");
            n.Email = rs.getString("Email"); // Đảm bảo tên cột trong cơ sở dữ liệu chính xác
            n.maCH = rs.getString("MACH");
            n.maloai = rs.getString("MALOAI");
            ds.add(n);
        }
        conn.ketNoi().close();
    } catch (SQLException ex) {
        Logger.getLogger(DsNhaCC.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ds;
    }
    public ArrayList<NhaCungCap> DanhSachNCC_TheoSDT(String dienthoai) {
        ArrayList<NhaCungCap> ds = new ArrayList<>();
        try {
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            CallableStatement st = conn.ketNoi().prepareCall("{call DanhSachNCCTheo_SDT (?)}");
            st.setString(1, dienthoai);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                NhaCungCap n = new NhaCungCap();
                n.MaNCC = rs.getString("MANCC"); // Đảm bảo tên cột trong cơ sở dữ liệu chính xác
                n.TenNCC = rs.getString("TENNCC");
                n.DiaChi = rs.getString("DIACHI");
                n.SDT = rs.getString("SDT");
                n.Email = rs.getString("EMAIL"); // Đảm bảo tên cột trong cơ sở dữ liệu chính xác
                ds.add(n);
            }
            conn.ketNoi().close();
        } catch (SQLException ex) {
            Logger.getLogger(DsNhaCC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
     public String SinhMaNCC(String mach) {
     String mancc = ""; // Giá trị mặc định

    try (Connection connection = new KetNoi().ketNoi();
        CallableStatement st = connection.prepareCall("{call sinhma_mancclonnhat(?)}")) {
        st.setString(1, mach);
        ResultSet rs = st.executeQuery();
        if (rs.next()) { // Kiểm tra xem ResultSet có kết quả không
            String ma = rs.getString("MANCC");
            String so = ma.substring(2,ma.length());
            mancc = mach + String.format("%02d", Integer.parseInt(so) + 1); // Lấy giá trị của cột "mancc"
            
        }
    } catch (SQLException ex) {
        Logger.getLogger(DsNhaCC.class.getName()).log(Level.SEVERE, "Lỗi khi sinh mã nhà cung cấp", ex);
        // Xử lý lỗi
    }

    return mancc;
}
    public boolean themNCC(NhaCungCap nhacungcap) {
    try {
        KetNoi conn = new KetNoi();
        conn.ketNoi();
        CallableStatement st = conn.ketNoi().prepareCall("{call insert_NHACUNGCAP (?,?,?,?,?,?,?)}");
        st.setString(1, nhacungcap.MaNCC);
        st.setString(2, nhacungcap.TenNCC);
        st.setString(3, nhacungcap.DiaChi);
        st.setString(4, nhacungcap.SDT);
        st.setString(5, nhacungcap.Email);
        st.setString(6, nhacungcap.maCH);
        st.setString(7, nhacungcap.maloai);
        st.executeUpdate();
        conn.ketNoi().close();
        return true; // Trả về true nếu thêm thành công
    } catch (SQLException ex) {
        Logger.getLogger(DsNhaCC.class.getName()).log(Level.SEVERE, null, ex);
        return false; // Trả về false nếu có lỗi xảy ra
    }
}

    
    public boolean capNhatNCC(NhaCungCap nhacungcap) {
    try {
        KetNoi conn = new KetNoi();
        conn.ketNoi();
        CallableStatement st = conn.ketNoi().prepareCall("{call update_NHACUNGCAP (?,?,?,?,?,?,?)}");
        st.setString(1, nhacungcap.MaNCC);
        st.setString(2, nhacungcap.TenNCC);
        st.setString(3, nhacungcap.DiaChi);
        st.setString(4, nhacungcap.SDT);
        st.setString(5, nhacungcap.Email);
        st.setString(6, nhacungcap.maCH);
        st.setString(7, nhacungcap.maloai);
        int rowsAffected = st.executeUpdate();
        conn.ketNoi().close();
        return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng được cập nhật
    } catch (SQLException ex) {
        Logger.getLogger(DsNhaCC.class.getName()).log(Level.SEVERE, null, ex);
        return false; // Trả về false nếu có lỗi xảy ra
    }
}
    public void xoaNCC(NhaCungCap nhacungcap)
    {
         try {
             KetNoi conn = new KetNoi();
             conn.ketNoi();
             CallableStatement st = conn.ketNoi().prepareCall("{call delete_NHACUNGCAP (?)}");
             st.setString(1,nhacungcap.getMaNCC());
             st.executeUpdate();
             JOptionPane.showMessageDialog(null, "Xóa thành công");
             conn.ketNoi().close();
         } catch (SQLException ex) {
             Logger.getLogger(DsNhaCC.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    public Object DanhSachNCCTheo_SDT(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


