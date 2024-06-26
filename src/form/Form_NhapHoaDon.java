/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import Class.DSHoaDon;
import Class.DSKhachHang;
import Class.DSKhuyenMai;
import Class.DSNhanVien;
import Class.DSSanPham;
import Class.DanhMuc;
import Class.HoaDon;
import Class.KhachHang;
import Class.KhuyenMai;
import Class.NhanVien;
import Class.SanPham;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import form.Form_NhapKhachHang;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Triss
 */
public class Form_NhapHoaDon extends javax.swing.JPanel {

    /**
     * Creates new form Form_NhapHoaDon
     */
    DSNhanVien nv;
    DSKhachHang kh;
    DSHoaDon hd;
    DSSanPham sp;
    DSKhuyenMai km;
    
    
    ArrayList<HoaDon> dsHD;
    ArrayList<SanPham> dsSP;
    ArrayList<NhanVien> dsNhanVien;
    ArrayList<KhuyenMai> dsKhuyenMai;
    ArrayList<KhachHang> dsKH = new ArrayList<>();
    int vitri = 0;
    int flag = 0;
    String maKH;
    public Form_NhapHoaDon() {
        initComponents();
        hd = new DSHoaDon();
        nv = new DSNhanVien();
        kh = new DSKhachHang();
        sp = new DSSanPham();
        km = new DSKhuyenMai();
        dsHD = hd.layDanhSachHoaDon();
        dsNhanVien = nv.layDanhSachNhanVien();
        dsKH = kh.layDanhSachKhachHang();
        dsKhuyenMai = km.layDanhSachKhuyenMai();
        txt_MaHD.setEnabled(false);
        txt_MaHD.setText(hd.SinhMaHD());
        txt_TenSP.setEnabled(false);
        txt_GiaBan.setEnabled(false);
        txt_ThanhTien.setEnabled(false);
        txt_NgayLap.setEnabled(false);

        //hien thi textbox ngay lap
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(date);
        txt_NgayLap.setText(formattedDate);


        
        
        
        hienThiComboboxNhanVien(dsNhanVien,cbo_TenNV);
        //hienThiTextBox(vitri);
    
       //hienThiTableHoaDon();
        //hienThiTableCT_HoaDon();
        hienThiComboboxKhuyenMai(dsKhuyenMai, jComboBox1,txt_MaSP.getText());
    }
    
    private void hienThiComboboxNhanVien(ArrayList<NhanVien> nNhanVien, JComboBox<String> cbo){
        for (NhanVien n:nNhanVien)
        {
            cbo.addItem(n.getTenNV());
        }
    }

    //hiển thị combobox Mã khuyến mãi theo mã sản phẩm
    private void hienThiComboboxKhuyenMai(ArrayList<KhuyenMai> nKhuyenMai, JComboBox<String> cbo, String maSP){
        cbo.removeAllItems();
        for (KhuyenMai n:nKhuyenMai)
        {
            if (n.getMaSP().equals(maSP))
            {
                cbo.addItem(n.getMaKM());
            }
        }
    }
    
    private void xoaTextBox()
    {
        txt_MaSP.setText("");
        txt_TimKiem.setText("");
        txt_SoLuong.setText("");
        txt_GiaBan.setText("");
    }
    
    private void hienThiTextBox(int vitri)
    {
        if (vitri >= 0 && vitri < dsHD.size()) {
        HoaDon n = dsHD.get(vitri);
        txt_MaSP.setText(n.maSP);
        txt_TenSP.setText(n.tenSP);
        txt_MaHD.setText(n.maHD);
        txt_GiaBan.setText(String.valueOf(n.GiaBan));
        txt_SoLuong.setText(String.valueOf(n.SoLuong));
        Date ngayBatDau = n.NgayLap;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate1 = dateFormat.format(ngayBatDau);
        txt_NgayLap.setText(formattedDate1);
        txt_ThanhTien.setText(String.valueOf(n.ThanhTien));
         // Lấy tên danh mục của sản phẩm
        String maNV =n.getMaNV();
        String tenNV = ""; // Tạo một biến tạm để lưu tên danh mục

        // Tìm tên danh mục tương ứng với mã danh mục
        for (NhanVien nvien : dsNhanVien) {
            if (nvien.getMaNV().equals(maNV)) {
                tenNV = nvien.getTenNV();
                break;
            }
        }

        // Hiển thị tên danh mục trong ComboBox cbo_TenDanhMuc
        cbo_TenNV.setSelectedItem(tenNV); // Sử dụng setSelectedItem thay vì setSelectedIndex
    }
    }
    
    private void hienThiTableHoaDon()
    {
        String []Header = {"Mã HD","Mã NV","Mã KH","Mã SP","Số Lượng","Giá Bán","Ngày Lập","Thành Tiền"};
        DefaultTableModel model = new DefaultTableModel(Header,0);
        for(HoaDon n:dsHD)
        {   
            Object [] chnt = {n.maHD,n.maNV,n.maKH,n.maSP,n.SoLuong,n.GiaBan,n.NgayLap, n.ThanhTien};
            model.addRow(chnt);
        }
        tbl_HoaDon.fixTable(jScrollPane2);
        tbl_HoaDon.setModel(model);
        tbl_HoaDon.setRowSelectionInterval(vitri, vitri);
    }
    
   
    
//     private void hienThiTableCT_HoaDon()
//     {
//         String []Header = {"Mã Hóa Đơn","Mã SP","Số Lượng","Giá Bán"};
//         DefaultTableModel model = new DefaultTableModel(Header,0);
//         for(HoaDon n:dsHD)
//         {   
//             Object [] chnt = {n.maHD,n.maSP,n.SoLuong,n.GiaBan};
//             model.addRow(chnt);
//         }
        
// //        tbl_CTHD.setModel(model);
// //        tbl_CTHD.setRowSelectionInterval(vitri, vitri);
//     }

    private HoaDon convertToObject()
    {
        HoaDon n = new HoaDon();
        n.maHD = txt_MaHD.getText();
        n.maNV = dsNhanVien.get(cbo_TenNV.getSelectedIndex()).getMaNV();
        n.maKH = maKH;
        n.NgayLap = Date.valueOf(txt_NgayLap.getText());
        n.ThanhTien = Float.parseFloat(txt_ThanhTien.getText());
        n.maSP = txt_MaSP.getText();
        n.SoLuong = Integer.parseInt(txt_SoLuong.getText());
        n.GiaBan = Float.parseFloat(txt_GiaBan.getText());
        return n;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_HoaDon = new swing.table.Table();
        btn_Cash = new component.Button();
        btn_Sua = new component.Button();
        btn_Xoa = new component.Button();
        jScrollPane21 = new javax.swing.JScrollPane();
        txt_TimKiem = new javax.swing.JTextPane();
        btn_Luu = new component.Button();
        btn_TimKiem = new component.Button();
        PanelSP = new javax.swing.JPanel();
        txt_MaSP = new javax.swing.JTextField();
        lb_MaDanhMuc8 = new javax.swing.JLabel();
        lb_MaDanhMuc9 = new javax.swing.JLabel();
        txt_TenSP = new javax.swing.JTextField();
        txt_SoLuong = new javax.swing.JTextField();
        lb_MaDanhMuc10 = new javax.swing.JLabel();
        lb_MaDanhMuc11 = new javax.swing.JLabel();
        txt_GiaBan = new javax.swing.JTextField();
        lb_MaDanhMuc12 = new javax.swing.JLabel();
        lb_MaDanhMuc13 = new javax.swing.JLabel();
        txt_ThanhTien = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        btn_Reset = new component.Button();
        jPanel3 = new javax.swing.JPanel();
        lb_MaDanhMuc = new javax.swing.JLabel();
        txt_MaHD = new javax.swing.JTextField();
        lb_MaDanhMuc1 = new javax.swing.JLabel();
        cbo_TenNV = new javax.swing.JComboBox<>();
        lb_MaDanhMuc2 = new javax.swing.JLabel();
        txt_DienThoai = new javax.swing.JTextField();
        lb_MaDanhMuc3 = new javax.swing.JLabel();
        txt_TenKH = new javax.swing.JTextField();
        txt_NgayLap = new javax.swing.JTextField();
        lb_MaDanhMuc4 = new javax.swing.JLabel();
        btn_ThemKH = new javax.swing.JButton();
        btn_ThemHD = new component.Button();
        jLabel2 = new javax.swing.JLabel();
        btn_XuatHD = new component.Button();

        setBackground(new java.awt.Color(250, 250, 250));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tbl_HoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã KH", "Mã NV", "Ngày lập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_HoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_HoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_HoaDon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btn_Cash.setBackground(new java.awt.Color(238, 230, 255));
        btn_Cash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_add.png"))); // NOI18N
        btn_Cash.setText("Tính tiền");
        btn_Cash.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_Cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CashActionPerformed(evt);
            }
        });

        btn_Sua.setBackground(new java.awt.Color(238, 230, 255));
        btn_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_edit.png"))); // NOI18N
        btn_Sua.setText("Sửa");
        btn_Sua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        btn_Xoa.setBackground(new java.awt.Color(238, 230, 255));
        btn_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_delete.png"))); // NOI18N
        btn_Xoa.setText("Xóa");
        btn_Xoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        txt_TimKiem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane21.setViewportView(txt_TimKiem);

        btn_Luu.setBackground(new java.awt.Color(238, 230, 255));
        btn_Luu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_save.png"))); // NOI18N
        btn_Luu.setText("Lưu");
        btn_Luu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuActionPerformed(evt);
            }
        });

        btn_TimKiem.setBackground(new java.awt.Color(238, 230, 255));
        btn_TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_search.png"))); // NOI18N
        btn_TimKiem.setText("Tìm kiếm");
        btn_TimKiem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

        PanelSP.setBackground(new java.awt.Color(255, 255, 255));
        PanelSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        txt_MaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MaSPActionPerformed(evt);
            }
        });

        lb_MaDanhMuc8.setBackground(new java.awt.Color(0, 0, 255));
        lb_MaDanhMuc8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_MaDanhMuc8.setForeground(new java.awt.Color(255, 0, 51));
        lb_MaDanhMuc8.setText("Mã sản phẩm:");

        lb_MaDanhMuc9.setBackground(new java.awt.Color(0, 0, 255));
        lb_MaDanhMuc9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_MaDanhMuc9.setForeground(new java.awt.Color(255, 0, 51));
        lb_MaDanhMuc9.setText("Tên sản phẩm:");

        txt_SoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SoLuongActionPerformed(evt);
            }
        });

        lb_MaDanhMuc10.setBackground(new java.awt.Color(0, 0, 255));
        lb_MaDanhMuc10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_MaDanhMuc10.setForeground(new java.awt.Color(255, 0, 51));
        lb_MaDanhMuc10.setText("Số lượng:");

        lb_MaDanhMuc11.setBackground(new java.awt.Color(0, 0, 255));
        lb_MaDanhMuc11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_MaDanhMuc11.setForeground(new java.awt.Color(255, 0, 51));
        lb_MaDanhMuc11.setText("Đơn giá:");

        lb_MaDanhMuc12.setBackground(new java.awt.Color(0, 0, 255));
        lb_MaDanhMuc12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_MaDanhMuc12.setForeground(new java.awt.Color(255, 0, 51));
        lb_MaDanhMuc12.setText("Khuyến mãi:");

        lb_MaDanhMuc13.setBackground(new java.awt.Color(0, 0, 255));
        lb_MaDanhMuc13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_MaDanhMuc13.setForeground(new java.awt.Color(255, 0, 51));
        lb_MaDanhMuc13.setText("Thành tiền:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout PanelSPLayout = new javax.swing.GroupLayout(PanelSP);
        PanelSP.setLayout(PanelSPLayout);
        PanelSPLayout.setHorizontalGroup(
            PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSPLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSPLayout.createSequentialGroup()
                        .addComponent(lb_MaDanhMuc8)
                        .addGap(40, 40, 40)
                        .addComponent(txt_MaSP))
                    .addGroup(PanelSPLayout.createSequentialGroup()
                        .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_MaDanhMuc9)
                            .addComponent(lb_MaDanhMuc10))
                        .addGap(33, 33, 33)
                        .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_SoLuong)
                            .addComponent(txt_TenSP)
                            .addComponent(txt_GiaBan)))
                    .addComponent(lb_MaDanhMuc11)
                    .addComponent(lb_MaDanhMuc13)
                    .addGroup(PanelSPLayout.createSequentialGroup()
                        .addComponent(lb_MaDanhMuc12)
                        .addGap(52, 52, 52)
                        .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_ThanhTien)
                            .addComponent(jComboBox1, 0, 285, Short.MAX_VALUE))))
                .addGap(41, 41, 41))
        );
        PanelSPLayout.setVerticalGroup(
            PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_MaDanhMuc8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_MaDanhMuc9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_MaDanhMuc10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_GiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_MaDanhMuc11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelSPLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lb_MaDanhMuc12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelSPLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_MaDanhMuc13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        btn_Reset.setBackground(new java.awt.Color(238, 230, 255));
        btn_Reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_refesh.png"))); // NOI18N
        btn_Reset.setText("Làm mới");
        btn_Reset.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ResetActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        lb_MaDanhMuc.setBackground(new java.awt.Color(0, 0, 255));
        lb_MaDanhMuc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_MaDanhMuc.setForeground(new java.awt.Color(255, 0, 51));
        lb_MaDanhMuc.setText("Mã hóa đơn:");

        lb_MaDanhMuc1.setBackground(new java.awt.Color(0, 0, 255));
        lb_MaDanhMuc1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_MaDanhMuc1.setForeground(new java.awt.Color(255, 0, 51));
        lb_MaDanhMuc1.setText("Tên nhân viên:");

        lb_MaDanhMuc2.setBackground(new java.awt.Color(0, 0, 255));
        lb_MaDanhMuc2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_MaDanhMuc2.setForeground(new java.awt.Color(255, 0, 51));
        lb_MaDanhMuc2.setText("Điện thoại KH:");

        txt_DienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_DienThoaiActionPerformed(evt);
            }
        });

        lb_MaDanhMuc3.setBackground(new java.awt.Color(0, 0, 255));
        lb_MaDanhMuc3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_MaDanhMuc3.setForeground(new java.awt.Color(255, 0, 51));
        lb_MaDanhMuc3.setText("Tên KH:");

        lb_MaDanhMuc4.setBackground(new java.awt.Color(0, 0, 255));
        lb_MaDanhMuc4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_MaDanhMuc4.setForeground(new java.awt.Color(255, 0, 51));
        lb_MaDanhMuc4.setText("Ngày lập:");

        btn_ThemKH.setText("...");
        btn_ThemKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ThemKHMouseClicked(evt);
            }
        });
        btn_ThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lb_MaDanhMuc2)
                        .addGap(58, 58, 58)
                        .addComponent(txt_DienThoai))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_MaDanhMuc)
                            .addComponent(lb_MaDanhMuc1))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbo_TenNV, 0, 282, Short.MAX_VALUE)
                            .addComponent(txt_MaHD)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lb_MaDanhMuc3)
                        .addGap(117, 117, 117)
                        .addComponent(txt_TenKH))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lb_MaDanhMuc4)
                        .addGap(105, 105, 105)
                        .addComponent(txt_NgayLap)))
                .addGap(18, 18, 18)
                .addComponent(btn_ThemKH)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_MaDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_MaDanhMuc1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbo_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_MaDanhMuc2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_DienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_MaDanhMuc3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThemKH))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_MaDanhMuc4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        btn_ThemHD.setBackground(new java.awt.Color(238, 230, 255));
        btn_ThemHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_add.png"))); // NOI18N
        btn_ThemHD.setText("Thêm");
        btn_ThemHD.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_ThemHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemHDActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 0, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("HÓA ĐƠN BÁN HÀNG");

        btn_XuatHD.setBackground(new java.awt.Color(238, 230, 255));
        btn_XuatHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_export.png"))); // NOI18N
        btn_XuatHD.setText("Xuất HD");
        btn_XuatHD.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_XuatHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XuatHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(395, 395, 395)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addGap(364, 364, 364))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(40, 40, 40)
                        .addComponent(PanelSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 778, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_Cash, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(btn_Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30))))
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(btn_ThemHD, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addComponent(btn_Sua, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(50, 50, 50)
                .addComponent(btn_Xoa, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(51, 51, 51)
                .addComponent(btn_XuatHD, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addComponent(btn_Luu, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addGap(102, 102, 102))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Cash, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XuatHD, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane21)
                    .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemKHActionPerformed
        // TODO add your handling code here:
        //show form khach hang
        Form_NhapKhachHang frmKH = new Form_NhapKhachHang();
        
        JFrame panel = new JFrame();
        panel.setSize(1280, 720); 
        panel.setLocationRelativeTo(null); 
        panel.getContentPane().add(frmKH);
        panel.setVisible(true);
    
  
        panel.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                KhachHang khachHangMoi = frmKH.getKhachHangMoiNhat();
                if (khachHangMoi != null) {
                    txt_DienThoai.setText(khachHangMoi.getSDT());
                    txt_TenKH.setText(khachHangMoi.getTenKH());
                }
                System.out.println("Khach hang moi: " + khachHangMoi);
                System.out.println("Mã khách hàng: "+ khachHangMoi.getMaKH());
            }
        });
            
        
    }//GEN-LAST:event_btn_ThemKHActionPerformed

    private void btn_CashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CashActionPerformed
        // TODO add your handling code here:
        //Lấy GIAKHUYENMAI từ thông qua combobox KM
        String maKM = jComboBox1.getSelectedItem().toString();
        String maSP = txt_MaSP.getText();
        
        float giaKhuyenMai = km.layGiaKM(maKM, maSP);
        System.out.println("Mã sản phẩm: " + maSP);
        System.out.println("Mã khuyến mãi: " + maKM);
        System.out.println("Giá khuyến mãi: " + giaKhuyenMai);

        //Tính thành tiền
        int soLuong = Integer.parseInt(txt_SoLuong.getText());
        float giaBan = Float.parseFloat(txt_GiaBan.getText());
        float thanhTien = (soLuong * giaBan) - giaKhuyenMai;
        DecimalFormat df =  new DecimalFormat("#");
        df.setMaximumFractionDigits(0);
        txt_ThanhTien.setText(df.format(thanhTien));
        //print txt_ThanhTien
        //System.out.println("Thành tiền: " + txt_ThanhTien.getText());    

    }//GEN-LAST:event_btn_CashActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // TODO add your handling code here:
        flag = 2;
        btn_Luu.setEnabled(true);
        btn_ThemHD.setEnabled(false);
        btn_XuatHD.setEnabled(false);
        btn_Sua.setEnabled(false);
        btn_Xoa.setEnabled(false);

    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        // TODO add your handling code here:
        flag = 3;
        btn_Luu.setEnabled(true);
        btn_ThemHD.setEnabled(false);
        btn_XuatHD.setEnabled(false);
        btn_Sua.setEnabled(false);
        btn_Xoa.setEnabled(false);
     

    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuActionPerformed
        // TODO add your handling code here:
        System.out.println("Giá trị của txt_ThanhTien trước khi convertToObject() được gọi: " + txt_ThanhTien.getText());
        HoaDon hdDon = convertToObject();
        if (flag ==1){
            DefaultTableModel model = (DefaultTableModel) tbl_HoaDon.getModel();
            //Duyệt qua các dòng trong bảng
            for (int i = 0; i < model.getRowCount(); i++) {
                //thêm vào cơ sở dữ liệu
                hd.themHoaDon(hdDon);
            }
       
        }
        else if (flag == 2)
        {
            hd.capNhatHoaDon(hdDon);
        
        
        }
        else if (flag == 3)
        {
            int kq= JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa không?","Thông báo",JOptionPane.YES_NO_OPTION,JOptionPane.YES_NO_CANCEL_OPTION);
            if(kq==JOptionPane.YES_OPTION) {
               hd.xoaHoaDon(hdDon);
            }

        }


        flag = 0;
        hd = new DSHoaDon();
        dsHD = hd.layDanhSachHoaDon();
        hienThiTableHoaDon();
        //cập nhật lại mã hóa đơn tăng lên 1
        
        btn_Luu.setEnabled(false);
        btn_ThemHD.setEnabled(true);
        btn_Sua.setEnabled(true);
        btn_Xoa.setEnabled(true);
        
    }//GEN-LAST:event_btn_LuuActionPerformed

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
         if (txt_TimKiem.getText()!=null)
        {
            dsHD = hd.timKiemHoaDon(txt_TimKiem.getText());
            vitri = 0;
            hienThiTableHoaDon();
            hienThiTextBox(vitri);
        }
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void btn_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ResetActionPerformed
        // TODO add your handling code here:
        xoaTextBox();
    }//GEN-LAST:event_btn_ResetActionPerformed

    private void btn_ThemHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemHDActionPerformed
        // TODO add your handling code here:
        //flag = 1;
        txt_MaHD.setEnabled(false);
        //txt_MaHD.setText(hd.SinhMaHD());
        //xoaTextBox();
        //Ngầy lập là ngày tháng hiện tại Datetime Now
        // java.util.Date date = new java.util.Date();
        // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // txt_NgayLap.setText(formatter.format(date));

        HoaDon newHoaDon = convertToObject();
        dsHD.add(newHoaDon);

        //in danh sách hóa đơn
        System.out.println("Danh sách hóa đơn: "+ dsHD);

        hienThiTableHoaDon();
//        hienThiTextBox(dsHD.size()-1);
//
//        //cập nhật lại mã hóa đơn tăng lên 1
//        String lastMaHD = dsHD.get(dsHD.size()-1).getMaHD();
//        String prefix = lastMaHD.substring(0, 2); // Lấy phần chữ của mã hóa đơn (HD)
//        int number = Integer.parseInt(lastMaHD.substring(2)); // Lấy phần số của mã hóa đơn (001)
//        number++; // Tăng số lên 1
//        String newMaHD = prefix + String.format("%04d", number); // Ghép lại với phần chữ và đảm bảo có đủ 3 chữ số
//        txt_MaHD.setText(newMaHD);
     
        btn_ThemHD.setEnabled(true);
        btn_Sua.setEnabled(false);
        btn_Xoa.setEnabled(false);
        flag = 1;
     
    }//GEN-LAST:event_btn_ThemHDActionPerformed

    private void btn_ThemKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemKHMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThemKHMouseClicked

    private void txt_DienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_DienThoaiActionPerformed
        // TODO add your handling code here:
        //Trường hợp tìm thấy số điện thoại khách hàng
        if (txt_DienThoai.getText() != "" && kh.DanhSachKhachHang_TheoSDT(txt_DienThoai.getText()).size() > 0 ){
            dsKH = kh.DanhSachKhachHang_TheoSDT(txt_DienThoai.getText());
            KhachHang k = dsKH.get(0);
            maKH = k.getMaKH();
            txt_TenKH.setText(k.getTenKH());
        }
        //Trường hợp không tìm thấy số điện thoại khách hàng
        else{
            JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng với số điện thoại trên");
            Form_NhapKhachHang frmKH = new Form_NhapKhachHang();
            
            JFrame panel = new JFrame();
            panel.setSize(1280, 720); 
            panel.setLocationRelativeTo(null); 
            panel.getContentPane().add(frmKH);
            panel.setVisible(true);
        
            //Kiểm tra xem form KhachHang đã đóng chưa
            panel.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    KhachHang khachHangMoi = frmKH.getKhachHangMoiNhat();
                    if (khachHangMoi != null) {
                        txt_DienThoai.setText(khachHangMoi.getSDT());
                        txt_TenKH.setText(khachHangMoi.getTenKH());
                    }
                    System.out.println("Khach hang moi: " + khachHangMoi);
                    System.out.println("Mã khách hàng: "+ khachHangMoi.getMaKH());
                }
            });
        }
    }//GEN-LAST:event_txt_DienThoaiActionPerformed

    private void txt_MaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MaSPActionPerformed
        // TODO add your handling code here:
        if (txt_MaSP.getText() != "" && sp.timKiemSanPham(txt_MaSP.getText()).size() > 0){
            dsSP = sp.timKiemSanPham(txt_MaSP.getText());
            SanPham s = dsSP.get(0);
            txt_TenSP.setText(s.getTenSP());
            hienThiComboboxKhuyenMai(dsKhuyenMai, jComboBox1,txt_MaSP.getText());
            // DecimalFormat df =  new DecimalFormat("#");
            // df.setMaximumFractionDigits(0);
            // txt_GiaBan.setText(df.format(s.getGiaBan()));;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm với mã sản phẩm trên");
        }
    }//GEN-LAST:event_txt_MaSPActionPerformed

    private void txt_SoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SoLuongActionPerformed
        // TODO add your handling code here:
        int soLuong = Integer.parseInt(txt_SoLuong.getText());
        
        //Nếu soLuong > 10 thì lấy cột GIABANSI
        if (soLuong > 10){
            //Hiển thị thông báo là bán theo giá bán sỉ
            JOptionPane.showMessageDialog(null, "Số lượng mua lớn hơn 10, giá bán sỉ được áp dụng");
            DecimalFormat df =  new DecimalFormat("#");
            df.setMaximumFractionDigits(0);
            txt_GiaBan.setText(df.format(sp.timKiemSanPham(txt_MaSP.getText()).get(0).getGiaBanSi()));
        }
        else{
            DecimalFormat df =  new DecimalFormat("#");
            df.setMaximumFractionDigits(0);
            txt_GiaBan.setText(df.format(sp.timKiemSanPham(txt_MaSP.getText()).get(0).getGiaBan()));
        }
    }//GEN-LAST:event_txt_SoLuongActionPerformed

    private void tbl_HoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_HoaDonMouseClicked
        // TODO add your handling code here:
        int vitri = tbl_HoaDon.getSelectedRow();
        if (vitri >= 0 && dsHD.size() > 0){
            hienThiTextBox(vitri);

            //Lấy HD được chọn
            HoaDon hoaDonChon = dsHD.get(vitri);

            //Lấy tenKH, SDT
            txt_TenKH.setText(kh.timKiemKhachHang(hoaDonChon.getMaKH()).get(0).getTenKH());
            txt_DienThoai.setText(kh.timKiemKhachHang(hoaDonChon.getMaKH()).get(0).getSDT());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            txt_NgayLap.setText(formatter.format(hoaDonChon.getNgayLap()));
            txt_SoLuong.setText(String.valueOf(hoaDonChon.getSoLuong()));
            String maSP = hoaDonChon.getMaSP();

            //Hiển thị mã KM trên combobox tương ứng
            hienThiComboboxKhuyenMai(dsKhuyenMai, jComboBox1, maSP);
            String maKM = jComboBox1.getSelectedItem().toString();
            System.out.println("Mã KM: " + maKM);
            //Select maKM đúng dòng
            for (int i = 0; i < jComboBox1.getItemCount(); i++){
                if (dsKhuyenMai.get(i).getMaKM().equals(maKM)){
                    jComboBox1.setSelectedIndex(i);
                }
            }
           
            float giaKhuyenMai = km.layGiaKM(maKM, maSP);
            System.out.println("Mã sản phẩm: " + maSP);
            System.out.println("Mã khuyến mãi: " + maKM);
            System.out.println("Giá khuyến mãi: " + giaKhuyenMai);

            //Tính thành tiền
            int soLuong = Integer.parseInt(txt_SoLuong.getText());
            float giaBan = Float.parseFloat(txt_GiaBan.getText());
            float thanhTien = (soLuong * giaBan) - giaKhuyenMai;
            DecimalFormat df =  new DecimalFormat("#");
            df.setMaximumFractionDigits(0);
            txt_ThanhTien.setText(df.format(thanhTien));
 
        }

    }//GEN-LAST:event_tbl_HoaDonMouseClicked

    private void btn_XuatHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XuatHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_XuatHDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelSP;
    private component.Button btn_Cash;
    private component.Button btn_Luu;
    private component.Button btn_Reset;
    private component.Button btn_Sua;
    private component.Button btn_ThemHD;
    private javax.swing.JButton btn_ThemKH;
    private component.Button btn_TimKiem;
    private component.Button btn_Xoa;
    private component.Button btn_XuatHD;
    private javax.swing.JComboBox<String> cbo_TenNV;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JLabel lb_MaDanhMuc;
    private javax.swing.JLabel lb_MaDanhMuc1;
    private javax.swing.JLabel lb_MaDanhMuc10;
    private javax.swing.JLabel lb_MaDanhMuc11;
    private javax.swing.JLabel lb_MaDanhMuc12;
    private javax.swing.JLabel lb_MaDanhMuc13;
    private javax.swing.JLabel lb_MaDanhMuc2;
    private javax.swing.JLabel lb_MaDanhMuc3;
    private javax.swing.JLabel lb_MaDanhMuc4;
    private javax.swing.JLabel lb_MaDanhMuc8;
    private javax.swing.JLabel lb_MaDanhMuc9;
    private swing.table.Table tbl_HoaDon;
    private javax.swing.JTextField txt_DienThoai;
    private javax.swing.JTextField txt_GiaBan;
    private javax.swing.JTextField txt_MaHD;
    private javax.swing.JTextField txt_MaSP;
    private javax.swing.JTextField txt_NgayLap;
    private javax.swing.JTextField txt_SoLuong;
    private javax.swing.JTextField txt_TenKH;
    private javax.swing.JTextField txt_TenSP;
    private javax.swing.JTextField txt_ThanhTien;
    private javax.swing.JTextPane txt_TimKiem;
    // End of variables declaration//GEN-END:variables


}
