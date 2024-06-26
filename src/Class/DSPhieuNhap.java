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
public class DSPhieuNhap {
    public Connection con;
    
    
    public ArrayList<PhieuNhap> layDanhSachPhieuNhap() {
    ArrayList<PhieuNhap> ds = new ArrayList<>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    
    try {
        KetNoi conn = new KetNoi();
        connection = conn.ketNoi();
        String sql = "SELECT PHIEUNHAP.MAPHIEUNHAP, NHANVIEN.MANV, NHANVIEN.TENNV, PHIEUNHAP.NGAYNHAP, " +
                     "PHIEUNHAP.MANCC, NHACUNGCAP.TENNCC, SANPHAM.MASP, SANPHAM.TENSP, " +
                     "CT_PHIEUNHAP.SOLUONGNHAP, CT_PHIEUNHAP.DONGIANHAP " +
                     "FROM PHIEUNHAP " +
                     "JOIN NHANVIEN ON PHIEUNHAP.MANV = NHANVIEN.MANV " +
                     "JOIN NHACUNGCAP ON PHIEUNHAP.MANCC = NHACUNGCAP.MANCC " +
                     "JOIN CT_PHIEUNHAP ON PHIEUNHAP.MAPHIEUNHAP = CT_PHIEUNHAP.MAPHIEUNHAP " +
                     "JOIN SANPHAM ON CT_PHIEUNHAP.MASP = SANPHAM.MASP";
        
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        
        while(resultSet.next()) {
            PhieuNhap n = new PhieuNhap();
            n.maPhieuNhap = resultSet.getString("MAPHIEUNHAP");
            n.maNhanVien = resultSet.getString("MANV");
            n.tenSP = resultSet.getString("TENSP");
            n.maNCC = resultSet.getString("MANCC");
            n.maSP = resultSet.getString("MASP");
            n.soLuong = resultSet.getInt("SOLUONGNHAP");
            n.DonGia = resultSet.getFloat("DONGIANHAP");
            n.NgayLap = resultSet.getDate("NGAYNHAP");
            ds.add(n);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DSPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        // Đóng kết nối, statement và resultSet trong phần finally để đảm bảo rằng chúng được giải phóng
        try {
            if(resultSet != null) resultSet.close();
            if(statement != null) statement.close();
            if(connection != null) connection.close();
        } catch(SQLException ex) {
            Logger.getLogger(DSPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    return ds;
}
    public ArrayList<PhieuNhap> timKiemPhieuNhap(String giatri) {
    ArrayList<PhieuNhap> ds = new ArrayList<>();
    try {
        KetNoi conn = new KetNoi();
        conn.ketNoi();
        CallableStatement st = conn.ketNoi().prepareCall("{call timkiemPhieuNhap (?)}");
        st.setString(1, giatri);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
                PhieuNhap n = new PhieuNhap();
                n.maPhieuNhap =rs.getString("MAPHIEUNHAP");
                n.maNhanVien = rs.getString("MANV");
                n.maNCC = rs.getString("MANCC");
                n.maSP = rs.getString("MASP");
                n.soLuong =rs.getInt("SOLUONGNHAP");
                n.DonGia =rs.getFloat("DONGIANHAP");
                n.NgayLap = rs.getDate("NGAYNHAP");
                ds.add(n);
        }
        conn.ketNoi().close();
    } catch (SQLException ex) {
        Logger.getLogger(DSPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ds;
    }
    
    public String SinhMaPN() {
        String mapn = ""; // Default value

        try (Connection connection = new KetNoi().ketNoi();
             CallableStatement st = connection.prepareCall("{call PHIEUNHAPLONHAT}")) { // Call the stored procedure

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String ma = rs.getString("MAPHIEUNHAP"); // Assuming the column name is "MAPN"
                String so = ma.substring(2,ma.length());
                
                if (Integer.parseInt(so) >= 0 && Integer.parseInt(so) <= 9)
                    mapn = "PN" + "000" + (Integer.parseInt(so) + 1);
                else
                    if (Integer.parseInt(so) >= 10 && Integer.parseInt(so) <= 99)
                    mapn = "PN" + "00" + (Integer.parseInt(so) + 1);
                else
                    if (Integer.parseInt(so) >= 100 && Integer.parseInt(so) <= 999)
                    mapn = "PN" + "0" + (Integer.parseInt(so) + 1);
                else
                    mapn = "PN" + (Integer.parseInt(so) + 1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DSPhieuNhap.class.getName()).log(Level.SEVERE, "Error generating customer code", ex);
            // Optional: Throw a custom exception
        }

        return mapn;
    }
        // Phương thức thêm phiếu nhập vào cơ sở dữ liệu
    public void themPhieuNhap(PhieuNhap pNhap) {
        try {
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            CallableStatement st = conn.ketNoi().prepareCall("{call insert_phieunhap (?,?,?,?,?,?,?)}");
            st.setString(1, pNhap.maPhieuNhap);
            st.setString(2, pNhap.maNhanVien);
            st.setString(3, pNhap.maNCC);
            st.setDate(4, pNhap.NgayLap);
            st.setString(5, pNhap.maSP);
            st.setInt(6, pNhap.soLuong);
            st.setFloat(7, pNhap.DonGia);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm thành công");
            conn.ketNoi().close();
        } catch (SQLException ex) {
            Logger.getLogger(DSPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void capNhatPhieuNhap(PhieuNhap pNhap){
        try {
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            CallableStatement st = conn.ketNoi().prepareCall("{call update_PhieuNhap (?,?,?,?,?,?,?)}");
            st.setString(1,pNhap.maPhieuNhap);
            st.setString(2,pNhap.maNhanVien);
            st.setString(3,pNhap.maNCC);
            st.setDate(4, pNhap.NgayLap);
            st.setString(5, pNhap.maSP);
            st.setInt(6, pNhap.soLuong);
            st.setFloat(7,  pNhap.DonGia);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            conn.ketNoi().close();
        } catch (SQLException ex) {
            Logger.getLogger(DSPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void xoaPhieuNhap(PhieuNhap pNhap){
        try {
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            CallableStatement st = conn.ketNoi().prepareCall("{call delete_phieunhap (?)}");
            st.setString(1,pNhap.maPhieuNhap);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa thành công");
            conn.ketNoi().close();
        } catch (SQLException ex) {
            Logger.getLogger(DSPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
