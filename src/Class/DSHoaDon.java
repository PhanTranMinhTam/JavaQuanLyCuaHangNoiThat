/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.CallableStatement;
import javax.swing.JOptionPane;



/**
 *
 * @author Phan Tran Minh Tam
 */
public class DSHoaDon {
    public Connection con;
    
    
    public ArrayList<HoaDon> layDanhSachHoaDon()
    {
        ArrayList<HoaDon> ds = new ArrayList<>();
        try {
            
            
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            //KetNoi();
            String sql = "SELECT CT_HOADON.MAHD, CT_HOADON.MASP, SANPHAM.TENSP, CT_HOADON.GIABAN, CT_HOADON.SOLUONG, HOADON.MAKH, HOADON.THANHTIEN, HOADON.MANV, HOADON.NGAYLAP, NHANVIEN.TENNV FROM CT_HOADON JOIN SANPHAM ON CT_HOADON.MASP = SANPHAM.MASP JOIN HOADON ON CT_HOADON.MAHD = HOADON.MAHD JOIN NHANVIEN ON HOADON.MANV = NHANVIEN.MANV JOIN KHACHHANG ON HOADON.MAKH = KHACHHANG.MAKH WHERE HOADON.TRANGTHAI = 0 AND CT_HOADON.TRANGTHAI = 0";
            Statement st = conn.ketNoi().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                HoaDon n = new HoaDon();
                n.maSP =rs.getString("MASP");
                n.maHD = rs.getString("MAHD");
                n.tenSP = rs.getString("TENSP");
                n.maKH = rs.getString("MAKH");
                n.maNV = rs.getString("MANV");
                n.maHD = rs.getString("MAHD");
                n.GiaBan = rs.getFloat("GIABAN");
                n.SoLuong =rs.getInt("SOLUONG");
                n.NgayLap =rs.getDate("NGAYLAP");
                n.ThanhTien=rs.getFloat("THANHTIEN");
                ds.add(n);
 
            }
            //n.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DSHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    
    public ArrayList<HoaDon> timKiemHoaDon(String giatri) {
    ArrayList<HoaDon> ds = new ArrayList<>();
    try {
        KetNoi conn = new KetNoi();
        conn.ketNoi();
        CallableStatement st = conn.ketNoi().prepareCall("{call timkiemHoaDon (?)}");
        st.setString(1, giatri);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
                HoaDon n = new HoaDon();
                n.maHD =rs.getString("MAHD");
                n.maNV = rs.getString("MANV");
                n.maKH = rs.getString("MAKH");
                n.maSP = rs.getString("MASP");
                n.SoLuong =rs.getInt("SOLUONG");
                n.GiaBan = rs.getFloat("GIABAN");
                n.NgayLap =rs.getDate("NGAYLAP");
                n.ThanhTien=rs.getFloat("THANHTIEN");
                ds.add(n);
        }
        conn.ketNoi().close();
    } catch (SQLException ex) {
        Logger.getLogger(DSHoaDon.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ds;
    }
    
    public String SinhMaHD() {
        String mahd = ""; // Default value

        try (Connection connection = new KetNoi().ketNoi();
             CallableStatement st = connection.prepareCall("{call HOADONLONHAT}")) { // Call the stored procedure

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String ma = rs.getString("MAHD"); // Assuming the column name is "MAKH"
                String so = ma.substring(2,ma.length());
                
                if (Integer.parseInt(so) >= 0 && Integer.parseInt(so) <= 9)
                    mahd = "HD" + "000" + (Integer.parseInt(so) + 1);
                else
                    if (Integer.parseInt(so) >= 10 && Integer.parseInt(so) <= 99)
                    mahd = "HD" + "00" + (Integer.parseInt(so) + 1);
                else
                    if (Integer.parseInt(so) >= 100 && Integer.parseInt(so) <= 999)
                    mahd = "HD" + "0" + (Integer.parseInt(so) + 1);
                else
                    mahd = "HD" + (Integer.parseInt(so) + 1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DSHoaDon.class.getName()).log(Level.SEVERE, "Error generating customer code", ex);
            // Optional: Throw a custom exception
        }

        return mahd;
    }
    
    
    public void themHoaDon(HoaDon hDon){
        try {
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            CallableStatement st = conn.ketNoi().prepareCall("{call insert_HoaDon (?,?,?,?,?,?,?,?)}");
            st.setString(1,hDon.maHD);
            st.setString(2,hDon.maNV);
            st.setString(3,hDon.maKH);
            st.setDate(4, hDon.NgayLap);
            st.setFloat(5,hDon.ThanhTien);
            st.setString(6, hDon.maSP);
            st.setInt(7, hDon.SoLuong);     
            st.setFloat(8,hDon.GiaBan);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm thành công");
            conn.ketNoi().close();
        } catch (SQLException ex) {
            Logger.getLogger(DSHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void capNhatHoaDon(HoaDon hDon){
        try {
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            CallableStatement st = conn.ketNoi().prepareCall("{call update_HoaDon (?,?,?,?,?,?,?,?)}");
            st.setString(1,hDon.maHD);
            st.setString(2,hDon.maNV);
            st.setString(3,hDon.maKH);
            st.setDate(4, hDon.NgayLap);
            st.setFloat(5,hDon.ThanhTien);
            st.setString(6, hDon.maSP);
            st.setInt(7, hDon.SoLuong);
            st.setFloat(8,hDon.GiaBan);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            conn.ketNoi().close();
        } catch (SQLException ex) {
            Logger.getLogger(DSKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void xoaHoaDon(HoaDon hDon){
        try {
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            CallableStatement st = conn.ketNoi().prepareCall("{call delete_HoaDon (?)}");
            st.setString(1,hDon.maHD);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa thành công");
            conn.ketNoi().close();
        } catch (SQLException ex) {
            Logger.getLogger(DSKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

}
