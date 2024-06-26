/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import Class.CuaHang;
import Class.DSCuaHang;
import Class.DSDanhMuc;
import Class.DSHoaDon;
import Class.DSKhachHang;
import Class.DSKhuyenMai;
import Class.DSNhanVien;
import Class.DSPhieuNhap;
import Class.DSSanPham;
import Class.DanhMuc;
import Class.DsNhaCC;
import Class.HoaDon;
import Class.KhachHang;
import Class.KhuyenMai;
import Class.NhaCungCap;
import Class.NhanVien;
import Class.PhieuNhap;
import Class.SanPham;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Triss
 */
public class Form_TraCuu extends javax.swing.JPanel {

    /**
     * Creates new form Frm_TraCuu
     */
    
    ///Tìm kiếm KH
    DSKhachHang kh;
    ArrayList<KhachHang> dsKH;
    int vitri = 0;
    
    //Tìm kiếm NV
    DSNhanVien nv;
    ArrayList<NhanVien> dsNV;
    
    //Tìm kiếm KM
    DSKhuyenMai km;
    ArrayList<KhuyenMai> dsKhuyenMai;
    
    //Tìm kiếm HD
    DSHoaDon hd;
    ArrayList<HoaDon> dsHD;
    DSSanPham sp;
    ArrayList<SanPham> dsSP;
    
    //Tìm kiếm phiếu nhập
    DSPhieuNhap pn;
    DsNhaCC ncc;
    ArrayList<PhieuNhap> dsPN; 
    ArrayList<NhaCungCap> dsncc;
    
    //Tìm kiếm Danh mục
    DSDanhMuc dm;
    ArrayList<DanhMuc> dsDanhMuc;
    DSCuaHang ch;
    ArrayList<CuaHang> dsCuaHang;

    
    
    public Form_TraCuu() {
        initComponents();
        
        //Khach hang
        kh = new DSKhachHang();
        dsKH = kh.layDanhSachKhachHang();
        hienThiTable();
        //hienThiTextBox(vitri);
        
        //Nhanvien
        nv = new DSNhanVien();
        dsNV = nv.layDanhSachNhanVien();
        hienThiTableNhanVien();
        
        //KM
        km = new DSKhuyenMai();
        dsKhuyenMai = km.layDanhSachKhuyenMai();
        hienThiTableKM();
        
        //Hóa đơn
        hd = new DSHoaDon();
        sp = new DSSanPham();
        dsHD = hd.layDanhSachHoaDon();
        hienThiTableHoaDon();
        
        
        //Phiếu nhập
        pn = new DSPhieuNhap();
        ncc = new DsNhaCC();
        dsPN = pn.layDanhSachPhieuNhap();
        hienThiTablePhieuNhap();
        
        //Danh mục
        dm = new DSDanhMuc();
        ch = new DSCuaHang();
        dsCuaHang = ch.layDanhSachCuaHang();
        dsDanhMuc = dm.layDanhSachDanhMuc();
        hienThiTableDanhMuc();
       
        
    }
    
     private void hienThiTable()
    {
        String []Header = {"Mã Khách Hàng","Tên Khách Hàng","Địa Chỉ","SDT","Email"};
        DefaultTableModel model = new DefaultTableModel(Header,0);
        for(KhachHang n: dsKH)
        {   
            Object [] chnt = {n.maKH,n.tenKH,n.DiaChi,n.SDT,n.Email};
            model.addRow(chnt);
        }
        //tbl_SearchKH.fixTable(jScrollPane1);
        tbl_SearchKH.setModel(model);
        tbl_SearchKH.setRowSelectionInterval(vitri, vitri);
    }
     
    private void hienThiTableNhanVien()
    {
        String []Header = {"Mã Nhân Viên","Tên Nhân Viên","Chức Vụ","SDT","Email","Tài Khoản"};
        DefaultTableModel model = new DefaultTableModel(Header,0);
        for(NhanVien n: dsNV)
        {   
            Object [] chnt = {n.MaNV,n.TenNV,n.ChucVu,n.SDT,n.Email,n.TenTK};
            model.addRow(chnt);
        }
        
        tbl_SeachNV.setModel(model);
        tbl_SeachNV.setRowSelectionInterval(vitri, vitri);
                
    }
    
    
    private void hienThiTableKM()
    {
        String []Header = {"Mã KM","Tên KM","Mã SP","Số lượng KM","Giá KM","% giảm giá", "Ngày BD","Ngày KT"};
        DefaultTableModel model = new DefaultTableModel(Header,0);
        for(KhuyenMai n: dsKhuyenMai)
        {   
            Object [] chnt = {n.MaKM,n.TenKM,n.MaSP,n.SoLuongKM,n.GiaKM,n.PhanTramKM,n.NGAYBATDAU,n.NGAYKETHUC };
            model.addRow(chnt);
        }
        tbl_SearchKM.fixTable(jScrollPane2);
        tbl_SearchKM.setModel(model);
        tbl_SearchKM.setRowSelectionInterval(vitri, vitri);
           
    }
    
      private void hienThiTableHoaDon()
    {
        String []Header = {"Mã HD","Mã NV","Mã KH","Mã SP","Số Lượng","Giá Bán","Ngày Lập"};
        DefaultTableModel model = new DefaultTableModel(Header,0);
        for(HoaDon n:dsHD)
        {   
            Object [] chnt = {n.maHD,n.maNV,n.maKH,n.maSP,n.SoLuong,n.GiaBan,n.NgayLap};
            model.addRow(chnt);
        }
        tbl_SearchHD.fixTable(jScrollPane2);
        tbl_SearchHD.setModel(model);
        tbl_SearchHD.setRowSelectionInterval(vitri, vitri);
    }
      
      private void hienThiTablePhieuNhap()
    {
        String []Header = {"Mã Phiếu Nhập","Mã NV","Mã NCC","Mã SP","Số Lượng","Giá Bán","Ngày Lập"};
        DefaultTableModel model = new DefaultTableModel(Header,0);
        for(PhieuNhap n:dsPN)
        {   
            Object [] chnt = {n.maPhieuNhap,n.maNhanVien,n.maNCC,n.maSP,n.soLuong,n.DonGia,n.NgayLap};
            model.addRow(chnt);
        }
        tbl_SearchPN.fixTable(jScrollPane2);
        tbl_SearchPN.setModel(model);
        tbl_SearchPN.setRowSelectionInterval(vitri, vitri);
    }
      
    private void hienThiTableDanhMuc()
    {
        String []Header = {"Mã Danh Mục","Tên Danh Mục","Mã Cửa Hàng"};
        DefaultTableModel model = new DefaultTableModel(Header,0);
        for(DanhMuc n: dsDanhMuc)
        {   
            Object [] chnt = {n.maloai,n.Tenloai,n.mach};
            model.addRow(chnt);
        }
        tbl_SearchDM.fixTable(jScrollPane2);
        tbl_SearchDM.setModel(model);
        tbl_SearchDM.setRowSelectionInterval(vitri, vitri);
           
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPaneCustom1 = new component.TabbedPaneCustom();
        jPanel1 = new javax.swing.JPanel();
        txt_TimKiemKH = new javax.swing.JTextField();
        btn_TimKiemKH = new component.Button();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_SearchKH = new swing.table.Table();
        jPanel2 = new javax.swing.JPanel();
        txt_TimKiemNhanVien = new javax.swing.JTextField();
        btn_TimKiemNV = new component.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_SeachNV = new swing.table.Table();
        jPanel3 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        btn_TimKiem2 = new component.Button();
        jPanel4 = new javax.swing.JPanel();
        txt_SearchKM = new javax.swing.JTextField();
        btn_TimKiemKM = new component.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_SearchKM = new swing.table.Table();
        jPanel5 = new javax.swing.JPanel();
        txt_SearchHD = new javax.swing.JTextField();
        btn_TimKiemHD = new component.Button();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_SearchHD = new swing.table.Table();
        jPanel6 = new javax.swing.JPanel();
        txt_SearchPN = new javax.swing.JTextField();
        btn_TimKiemPN = new component.Button();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_SearchPN = new swing.table.Table();
        jPanel7 = new javax.swing.JPanel();
        txt_SearchDM = new javax.swing.JTextField();
        btn_TimKiemDM = new component.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_SearchDM = new swing.table.Table();

        setBackground(new java.awt.Color(255, 255, 255));

        tabbedPaneCustom1.setBackground(new java.awt.Color(153, 153, 153));
        tabbedPaneCustom1.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabbedPaneCustom1.setSelectedColor(new java.awt.Color(192, 150, 108));
        tabbedPaneCustom1.setUnselectedColor(new java.awt.Color(153, 153, 153));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btn_TimKiemKH.setBackground(new java.awt.Color(238, 230, 255));
        btn_TimKiemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_search.png"))); // NOI18N
        btn_TimKiemKH.setText("Tìm kiếm");
        btn_TimKiemKH.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_TimKiemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemKHKHActionPerformed(evt);
            }
        });

        tbl_SearchKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Tên KH", "Địa chỉ", "SĐT", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tbl_SearchKH);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1113, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_TimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_TimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_TimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addGap(250, 250, 250))
        );

        tabbedPaneCustom1.addTab("Khách hàng", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btn_TimKiemNV.setBackground(new java.awt.Color(238, 230, 255));
        btn_TimKiemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_search.png"))); // NOI18N
        btn_TimKiemNV.setText("Tìm kiếm");
        btn_TimKiemNV.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_TimKiemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemNVActionPerformed(evt);
            }
        });

        tbl_SeachNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên NV", "Tên TK", "Chức vụ", "SĐT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbl_SeachNV);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1128, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_TimKiemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_TimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TimKiemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        tabbedPaneCustom1.addTab("Nhân viên", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btn_TimKiem2.setBackground(new java.awt.Color(238, 230, 255));
        btn_TimKiem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_search.png"))); // NOI18N
        btn_TimKiem2.setText("Tìm kiếm");
        btn_TimKiem2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_TimKiem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiem2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_TimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(717, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(488, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Sản phẩm", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btn_TimKiemKM.setBackground(new java.awt.Color(238, 230, 255));
        btn_TimKiemKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_search.png"))); // NOI18N
        btn_TimKiemKM.setText("Tìm kiếm");
        btn_TimKiemKM.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_TimKiemKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemKMActionPerformed(evt);
            }
        });

        tbl_SearchKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã KM", "Tên KM", "Mã SP", "Giá KM", "% giảm giá", "Ngày BĐ", "Ngày KT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbl_SearchKM);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1114, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txt_SearchKM, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_TimKiemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SearchKM, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Khuyến mãi", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btn_TimKiemHD.setBackground(new java.awt.Color(238, 230, 255));
        btn_TimKiemHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_search.png"))); // NOI18N
        btn_TimKiemHD.setText("Tìm kiếm");
        btn_TimKiemHD.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_TimKiemHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemHDActionPerformed(evt);
            }
        });

        tbl_SearchHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã HD", "Mã NV", "Mã KH", "Mã SP", "Số lượng", "Giá bán", "Ngày lập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tbl_SearchHD);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1118, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txt_SearchHD, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_TimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SearchHD, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(230, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Hóa đơn", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        btn_TimKiemPN.setBackground(new java.awt.Color(238, 230, 255));
        btn_TimKiemPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_search.png"))); // NOI18N
        btn_TimKiemPN.setText("Tìm kiếm");
        btn_TimKiemPN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_TimKiemPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemPNActionPerformed(evt);
            }
        });

        tbl_SearchPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã PN", "Mã NV", "Ngày nhập", "Mã NCC", "Mã SP", "Số lượng nhập", "Đơn giá nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tbl_SearchPN);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txt_SearchPN, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_TimKiemPN, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SearchPN, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiemPN, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addGap(183, 183, 183))
        );

        tabbedPaneCustom1.addTab("Phiếu nhập", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        btn_TimKiemDM.setBackground(new java.awt.Color(238, 230, 255));
        btn_TimKiemDM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_search.png"))); // NOI18N
        btn_TimKiemDM.setText("Tìm kiếm");
        btn_TimKiemDM.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_TimKiemDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemDMActionPerformed(evt);
            }
        });

        tbl_SearchDM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã danh mục", "Tên danh mục", "Mã cửa hàng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl_SearchDM);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1118, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txt_SearchDM, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_TimKiemDM, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SearchDM, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiemDM, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Danh mục", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_TimKiemDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemDMActionPerformed
        // TODO add your handling code here:
        if (txt_SearchDM.getText()!=null)
        {
            dsDanhMuc = dm.timKiemDanhMuc(txt_SearchDM.getText());
            vitri = 0;
            hienThiTableDanhMuc();
            //hienThiTextBox(vitri);
        }
    }//GEN-LAST:event_btn_TimKiemDMActionPerformed

    private void btn_TimKiemPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemPNActionPerformed
        // TODO add your handling code here:
        if (txt_SearchPN.getText()!=null)
        {
            dsPN = pn.timKiemPhieuNhap(txt_SearchPN.getText());
            vitri = 0;
            hienThiTablePhieuNhap();
            //hienThiTextBox(vitri);
        }
    }//GEN-LAST:event_btn_TimKiemPNActionPerformed

    private void btn_TimKiemHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemHDActionPerformed
        // TODO add your handling code here:
        if (txt_SearchHD.getText()!=null)
        {
            dsHD = hd.timKiemHoaDon(txt_SearchHD.getText());
            vitri = 0;
            hienThiTableHoaDon();
            //hienThiTextBox(vitri);
        }
    }//GEN-LAST:event_btn_TimKiemHDActionPerformed

    private void btn_TimKiemKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemKMActionPerformed
        // TODO add your handling code here:
        if (txt_SearchKM.getText()!=null)
        {
            dsKhuyenMai = km.timKiemKhuyenMai(txt_SearchKM.getText());
            vitri = 0;
            hienThiTableKM();
        }
    }//GEN-LAST:event_btn_TimKiemKMActionPerformed

    private void btn_TimKiem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_TimKiem2ActionPerformed

    private void btn_TimKiemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemNVActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btn_TimKiemNVActionPerformed

    private void btn_TimKiemKHKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemKHKHActionPerformed
        // TODO add your handling code here:
        if (txt_TimKiemKH.getText()!=null)
        {
            dsKH = kh.timKiemKhachHang(txt_TimKiemKH.getText());
            vitri = 0;
            hienThiTable();
            //hienThiTextBox(vitri);
        }
    }//GEN-LAST:event_btn_TimKiemKHKHActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.Button btn_TimKiem2;
    private component.Button btn_TimKiemDM;
    private component.Button btn_TimKiemHD;
    private component.Button btn_TimKiemKH;
    private component.Button btn_TimKiemKM;
    private component.Button btn_TimKiemNV;
    private component.Button btn_TimKiemPN;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jTextField3;
    private component.TabbedPaneCustom tabbedPaneCustom1;
    private swing.table.Table tbl_SeachNV;
    private swing.table.Table tbl_SearchDM;
    private swing.table.Table tbl_SearchHD;
    private swing.table.Table tbl_SearchKH;
    private swing.table.Table tbl_SearchKM;
    private swing.table.Table tbl_SearchPN;
    private javax.swing.JTextField txt_SearchDM;
    private javax.swing.JTextField txt_SearchHD;
    private javax.swing.JTextField txt_SearchKM;
    private javax.swing.JTextField txt_SearchPN;
    private javax.swing.JTextField txt_TimKiemKH;
    private javax.swing.JTextField txt_TimKiemNhanVien;
    // End of variables declaration//GEN-END:variables
}
