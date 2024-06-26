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
public class DSKhuyenMai {
    
    public Connection con;
    
    
    public ArrayList<KhuyenMai> layDanhSachKhuyenMai()
    {
        ArrayList<KhuyenMai> ds = new ArrayList<>();
        try {
            KetNoi conn = new KetNoi();
            conn.ketNoi();

            //String sql = "SELECT KHUYENMAI.*, CT_KHUYENMAI.*, SANPHAM.TENSP FROM KHUYENMAI INNER JOIN CT_KHUYENMAI ON KHUYENMAI.MAKM = CT_KHUYENMAI.MAKM INNER JOIN SANPHAM ON CT_KHUYENMAI.MASP = SANPHAM.MASP";
            String sql= "SELECT KM.*, CTKM.* FROM KHUYENMAI KM, CT_KHUYENMAI CTKM WHERE KM.MAKM = CTKM.MAKM AND KM.TRANGTHAI=0 AND CTKM.TRANGTHAI=0";
            Statement st = conn.ketNoi().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                KhuyenMai n = new KhuyenMai();
                n.MaKM = rs.getString("MAKM");
                n.TenKM = rs.getString("TENKM");
                n.MaSP = rs.getString("MASP");
                n.GiaKM=rs.getFloat("GIAKM");
                n.SoLuongKM = rs.getInt("SOLUONGKM");
                n.PhanTramKM = rs.getFloat("PHANTRAMKM");
                n.NGAYBATDAU = rs.getDate("NGAYBATDAU");
                n.NGAYKETHUC = rs.getDate("NGAYKETHUC");
                ds.add(n);
            }

            conn.ketNoi().close();
        } catch (SQLException ex) {
            Logger.getLogger(DSKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    
    
    public ArrayList<KhuyenMai> timKiemKhuyenMai(String giatri) {
        ArrayList<KhuyenMai> ds = new ArrayList<>();
        try {
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            CallableStatement st = conn.ketNoi().prepareCall("{call timKiemKhuyenMai (?)}");
            st.setString(1, giatri);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                KhuyenMai n = new KhuyenMai();
                n.MaKM = rs.getString("MAKM"); // Đảm bảo tên cột trong cơ sở dữ liệu chính xác
                n.TenKM = rs.getString("TENKM");
                n.GiaKM = rs.getFloat("GIAKM");
                n.PhanTramKM = rs.getFloat("PHANTRAMKM");
                n.SoLuongKM = rs.getInt("SOLUONGKM");
                n.NGAYBATDAU = rs.getDate("NGAYBATDAU");
                n.NGAYKETHUC = rs.getDate("NGAYKETHUC");
                n.MaSP = rs.getString("MASP");
                ds.add(n);
            }
            conn.ketNoi().close();
        } catch (SQLException ex) {
            Logger.getLogger(DSKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }

    ////Lấy GIAKM từ MASP và MAKM
    public float layGiaKM(String makm, String masp) {
        float giakm = 0;
        try {
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            CallableStatement st = conn.ketNoi().prepareCall("{call layGiaKM (?,?)}");
            st.setString(1, makm);
            st.setString(2, masp);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                giakm = rs.getFloat("GIAKM");
            }
            conn.ketNoi().close();
        } catch (SQLException ex) {
            Logger.getLogger(DSKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);

        }
        return giakm;   
    }
    
   

    
    public void themKhuyenMai(KhuyenMai kMai){
        try {
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            CallableStatement st = conn.ketNoi().prepareCall("{call insert_KhuyenMai (?,?,?,?,?,?,?,?)}");
            st.setString(1,kMai.MaKM);
            st.setString(2,kMai.TenKM);
            st.setFloat(3,kMai.PhanTramKM);
            st.setDate(4, kMai.NGAYBATDAU);
            st.setDate(5, kMai.NGAYKETHUC);
            st.setString(6, kMai.MaSP);
            st.setFloat(7,  kMai.GiaKM);
            st.setInt(8,kMai.SoLuongKM);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm thành công");
            conn.ketNoi().close();
        } catch (SQLException ex) {
            Logger.getLogger(DSKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String SinhMaKhuyenMai() {
        String makm = ""; 

        try (Connection connection = new KetNoi().ketNoi();
             CallableStatement st = connection.prepareCall("{call sinhma_makmlonnhat}")) { // Call the stored procedure

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String ma = rs.getString("MAKM"); // Assuming the column name is "MAKH"
                String so = ma.substring(2,ma.length());
                
                if (Integer.parseInt(so) >= 0 && Integer.parseInt(so) <= 9)
                    makm = "KM" + "00" + (Integer.parseInt(so) + 1);
                else
                    if (Integer.parseInt(so) >= 10 && Integer.parseInt(so) <= 99)
                    makm = "KM" + "0" + (Integer.parseInt(so) + 1);
                else
                    makm = "KM" + (Integer.parseInt(so) + 1);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DSHoaDon.class.getName()).log(Level.SEVERE, "Error generating customer code", ex);
            // Optional: Throw a custom exception
        }

        return makm;
    }
    
    
    
    
    
    public void capNhatKhuyenMai(KhuyenMai kMai){
        try {
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            CallableStatement st = conn.ketNoi().prepareCall("{call update_KhuyenMai (?,?,?,?,?,?,?,?)}");
            st.setString(1,kMai.MaKM);
            st.setString(2,kMai.TenKM);
            st.setFloat(3,kMai.PhanTramKM);
            st.setDate(4, kMai.NGAYBATDAU);
            st.setDate(5, kMai.NGAYKETHUC);
            st.setString(6, kMai.MaSP);
            st.setFloat(7,  kMai.GiaKM);
            st.setInt(8,kMai.SoLuongKM);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            conn.ketNoi().close();
        } catch (SQLException ex) {
            Logger.getLogger(DSKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void xoaKhuyenMai(KhuyenMai kMai)
    {
        try {
            KetNoi conn = new KetNoi();
            conn.ketNoi();
            CallableStatement st = conn.ketNoi().prepareCall("{call delete_KhuyenMai(?)}");
            st.setString(1,kMai.MaKM);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa thành công");
            conn.ketNoi().close();
        } catch (SQLException ex) {
            Logger.getLogger(DSKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}