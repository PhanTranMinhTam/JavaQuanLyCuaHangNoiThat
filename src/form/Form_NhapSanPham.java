/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import Class.DSDanhMuc;
import Class.DSSanPham;
import Class.DanhMuc;
import Class.DsNhaCC;
import Class.NhaCungCap;
import Class.SanPham;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Triss
 */
public class Form_NhapSanPham extends javax.swing.JPanel {

    /**
     * Creates new form Form_NhapSanPham
     */
    
    DSSanPham sp;
    DSDanhMuc danhMuc;
    DsNhaCC ncc;
    ArrayList<NhaCungCap> dsNCC;
    ArrayList<SanPham> dsSP;
    ArrayList<DanhMuc> dsDanhMuc;
    String duongdananh = "";
    int vitri = 0;
    int flag = 0;
    public Form_NhapSanPham() {
    initComponents();
    sp = new DSSanPham();
    danhMuc = new DSDanhMuc();
    ncc = new DsNhaCC();
    
    dsSP = sp.layDanhSachSanPham();
    dsDanhMuc = danhMuc.layDanhSachDanhMuc();
    dsNCC = ncc.layDanhSachNCC();
    
    hienThiComboBoxes(dsDanhMuc,cbo_TenDanhMuc);
    hienThiComboboxNCC(dsNCC, cbo_NCC);
    hienThiTextBox(vitri);
    hienThiTable();
    anHienTextbox(false);


}

     public SanPham getSPMoiNhat() {
        return dsSP.get(dsSP.size() - 1);
    }
    private void hienThiTable()
    {
        String []Header = {"Mã SP","Tên SP","Kích thước ","Màu Sắc","Chất liệu","Giá Bán","Giá Sỉ","Nhà Cung Cấp"};
        DefaultTableModel model = new DefaultTableModel(Header,0);
        for(SanPham n:dsSP)
        {   
            Object [] chnt = {n.MaSP,n.TenSP,n.KichThuoc,n.MauSac,n.ChatLieu,n.GiaBan,n.GiaBanSi,n.MaNCC};
            model.addRow(chnt);
        }
        tbl_SanPham.fixTable(jScrollPane1);
        tbl_SanPham.setModel(model);
        tbl_SanPham.setRowSelectionInterval(vitri, vitri);
                
    
    }
    
    private void hienThiComboBoxes(ArrayList<DanhMuc> danhMuc, JComboBox<String> cbo){
        for (DanhMuc s:danhMuc)
        {
            cbo.addItem(s.getTenloai());
        }
    }
    
    private void hienThiComboboxNCC(ArrayList<NhaCungCap> nCungCap, JComboBox<String> cbo){
        for (NhaCungCap n:nCungCap)
        {
            cbo.addItem(n.getTenNCC());
        }
    }
    private void hienThiTextBox(int vitri) {
    // Kiểm tra xem vị trí được chọn có hợp lệ không
    if (vitri >= 0 && vitri < dsSP.size()) {
        // Lấy sản phẩm được chọn từ danh sách
        SanPham sp = dsSP.get(vitri);

        // Hiển thị thông tin sản phẩm
        txt_MaSP.setText(sp.getMaSP());
        txt_TenSP.setText(sp.getTenSP());
        txt_KichThuoc.setText(sp.getKichThuoc());
        txt_MauSac.setText(sp.getMauSac());
        txt_ChatLieu.setText(sp.getChatLieu());
        txt_GiaBan.setText(String.valueOf(sp.getGiaBan()));
        txt_GiaBanSi.setText(String.valueOf(sp.getGiaBanSi()));

        // Lấy tên danh mục của sản phẩm
        String maDanhMuc = sp.getMaLoai();
        String tenDanhMuc = ""; // Tạo một biến tạm để lưu tên danh mục

        // Tìm tên danh mục tương ứng với mã danh mục
        for (DanhMuc dm : dsDanhMuc) {
            if (dm.getMaloai().equals(maDanhMuc)) {
                tenDanhMuc = dm.getTenloai();
                break;
            }
        }

        // Hiển thị tên danh mục trong ComboBox cbo_TenDanhMuc
        cbo_TenDanhMuc.setSelectedItem(tenDanhMuc); // Sử dụng setSelectedItem thay vì setSelectedIndex

        // Lấy tên nhà cung cấp của sản phẩm
        String maNCC = sp.getMaNCC();
        String tenNCC = ""; // Tạo một biến tạm để lưu tên nhà cung cấp

        // Tìm tên nhà cung cấp tương ứng với mã nhà cung cấp
        for (NhaCungCap cc : dsNCC) {
            if (cc.getMaNCC().equals(maNCC)) {
                tenNCC = cc.getTenNCC();
                break;
            }
        }
        
        // Hiển thị tên nhà cung cấp trong ComboBox cbo_NCC
        cbo_NCC.setSelectedItem(tenNCC); // Sử dụng setSelectedItem thay vì setSelectedIndex
        
        //Hien thi hinh anh
        if (sp.getHinhAnh() != null && !sp.getHinhAnh().isEmpty()) {
        try {
            // Tải hình ảnh từ đường dẫn
            ImageIcon originalIcon = new ImageIcon(sp.getHinhAnh());
            Image originalImage = originalIcon.getImage();

            // Thay đổi kích thước hình ảnh
            int labelWidth = lb_hinhanh.getWidth();
            int labelHeight = lb_hinhanh.getHeight();
            Image scaledImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

            // Tạo ImageIcon từ hình ảnh đã thay đổi kích thước
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Đặt ImageIcon vào JLabel
            lb_hinhanh.setIcon(scaledIcon);
        } catch (Exception e) {
            lb_hinhanh.setIcon(null); // Nếu có lỗi khi tải ảnh, đặt lại label
        }
    } else {
        lb_hinhanh.setIcon(null); // Nếu không có hình ảnh, đặt lại label
    }
    }
}
    
    private void xoaTextBox()
    {
        txt_MaSP.setText("");
        txt_TenSP.setText("");
        txt_KichThuoc.setText("");
        txt_ChatLieu.setText("");
        txt_GiaBan.setText("");
        txt_GiaBanSi.setText("");
        txt_MauSac.setText("");
    }
    
    private void anHienTextbox(boolean t)
    {
        txt_MaSP.setEnabled(t);
        txt_TenSP.setEnabled(t);
        txt_KichThuoc.setEnabled(t);
        txt_ChatLieu.setEnabled(t);
        txt_GiaBan.setEnabled(t);
        txt_GiaBanSi.setEnabled(t);
        txt_MauSac.setEnabled(t);
    
    }
    
    private void anHienButton(boolean t)
    {
        btn_Them.setEnabled(t);
        btn_Sua.setEnabled(t);
        btn_Xoa.setEnabled(t);
        btn_Luu.setEnabled(!t);
    
    }
    
    

//    private void hienThiComboBoxes() {
//    // Assuming danhMucCombo is the name of your JComboBox for danhMuc
//    DefaultComboBoxModel<String> maNhaCungCapModel = new DefaultComboBoxModel<>();
//    DefaultComboBoxModel<String> maDanhMucModel = new DefaultComboBoxModel<>();
//    for (SanPham n:dsSP) {
//        maNhaCungCapModel.addElement(n.getMaNCC());
//        maDanhMucModel.addElement(n.getMaDanhMuc());
//        
//        }
//        cbo_NCC.setModel(maNhaCungCapModel); // Set the model to the danhMucCombo
//        cbo_TenDanhMuc.setModel(maDanhMucModel);
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        lb_MaDanhMuc = new javax.swing.JLabel();
        lb_TenDanhMuc = new javax.swing.JLabel();
        lb_GhiChu = new javax.swing.JLabel();
        lb_TenDanhMuc1 = new javax.swing.JLabel();
        lb_GhiChu1 = new javax.swing.JLabel();
        lb_MaDanhMuc1 = new javax.swing.JLabel();
        lb_TenDanhMuc2 = new javax.swing.JLabel();
        lb_GhiChu2 = new javax.swing.JLabel();
        lb_TenDanhMuc3 = new javax.swing.JLabel();
        btn_ChonAnh = new component.Button();
        btn_Them = new component.Button();
        jScrollPane21 = new javax.swing.JScrollPane();
        txt_TimKiem = new javax.swing.JTextPane();
        btn_TimKiem = new component.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_SanPham = new swing.table.Table();
        btn_Xoa = new component.Button();
        btn_Luu = new component.Button();
        btn_Sua = new component.Button();
        cbo_TenDanhMuc = new javax.swing.JComboBox<>();
        cbo_NCC = new javax.swing.JComboBox<>();
        txt_KichThuoc = new javax.swing.JTextField();
        txt_MauSac = new javax.swing.JTextField();
        txt_TenSP = new javax.swing.JTextField();
        txt_ChatLieu = new javax.swing.JTextField();
        txt_GiaBan = new javax.swing.JTextField();
        txt_GiaBanSi = new javax.swing.JTextField();
        txt_MaSP = new javax.swing.JTextField();
        btn_Reset = new component.Button();
        lb_hinhanh = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(0, 0, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("NHẬP SẢN PHẨM");

        lb_MaDanhMuc.setBackground(new java.awt.Color(0, 0, 255));
        lb_MaDanhMuc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_MaDanhMuc.setForeground(new java.awt.Color(255, 0, 51));
        lb_MaDanhMuc.setText("Mã sản phẩm :  ");

        lb_TenDanhMuc.setBackground(new java.awt.Color(0, 0, 255));
        lb_TenDanhMuc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_TenDanhMuc.setForeground(new java.awt.Color(255, 0, 51));
        lb_TenDanhMuc.setText("Tên sản phẩm:");

        lb_GhiChu.setBackground(new java.awt.Color(0, 0, 255));
        lb_GhiChu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_GhiChu.setForeground(new java.awt.Color(255, 0, 51));
        lb_GhiChu.setText("Tên danh mục:");

        lb_TenDanhMuc1.setBackground(new java.awt.Color(0, 0, 255));
        lb_TenDanhMuc1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_TenDanhMuc1.setForeground(new java.awt.Color(255, 0, 51));
        lb_TenDanhMuc1.setText("Tên nhà cung cấp:");

        lb_GhiChu1.setBackground(new java.awt.Color(0, 0, 255));
        lb_GhiChu1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_GhiChu1.setForeground(new java.awt.Color(255, 0, 51));
        lb_GhiChu1.setText("Kích thước:");

        lb_MaDanhMuc1.setBackground(new java.awt.Color(0, 0, 255));
        lb_MaDanhMuc1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_MaDanhMuc1.setForeground(new java.awt.Color(255, 0, 51));
        lb_MaDanhMuc1.setText("Chất liệu:");

        lb_TenDanhMuc2.setBackground(new java.awt.Color(0, 0, 255));
        lb_TenDanhMuc2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_TenDanhMuc2.setForeground(new java.awt.Color(255, 0, 51));
        lb_TenDanhMuc2.setText("Giá bán:");

        lb_GhiChu2.setBackground(new java.awt.Color(0, 0, 255));
        lb_GhiChu2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_GhiChu2.setForeground(new java.awt.Color(255, 0, 51));
        lb_GhiChu2.setText("Giá bám sỉ:");

        lb_TenDanhMuc3.setBackground(new java.awt.Color(0, 0, 255));
        lb_TenDanhMuc3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_TenDanhMuc3.setForeground(new java.awt.Color(255, 0, 51));
        lb_TenDanhMuc3.setText("Màu sắc:");

        btn_ChonAnh.setBackground(new java.awt.Color(238, 230, 255));
        btn_ChonAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_select.png"))); // NOI18N
        btn_ChonAnh.setText("Chọn");
        btn_ChonAnh.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_ChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChonAnhActionPerformed(evt);
            }
        });

        btn_Them.setBackground(new java.awt.Color(238, 230, 255));
        btn_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_add.png"))); // NOI18N
        btn_Them.setText("Thêm");
        btn_Them.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        txt_TimKiem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane21.setViewportView(txt_TimKiem);

        btn_TimKiem.setBackground(new java.awt.Color(238, 230, 255));
        btn_TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_search.png"))); // NOI18N
        btn_TimKiem.setText("Tìm kiếm");
        btn_TimKiem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

        tbl_SanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Mã danh mục", "Kích thước", "Chất liệu", "Màu sắc", "Giá bán", "Giá bán sỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_SanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbl_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_SanPham);

        btn_Xoa.setBackground(new java.awt.Color(238, 230, 255));
        btn_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_delete.png"))); // NOI18N
        btn_Xoa.setText("Xóa");
        btn_Xoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        btn_Luu.setBackground(new java.awt.Color(238, 230, 255));
        btn_Luu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_save.png"))); // NOI18N
        btn_Luu.setText("Lưu");
        btn_Luu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuActionPerformed(evt);
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

        txt_KichThuoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_KichThuoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_KichThuocKeyPressed(evt);
            }
        });

        txt_MauSac.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_MauSac.setToolTipText("");
        txt_MauSac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_MauSacKeyPressed(evt);
            }
        });

        txt_TenSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_TenSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_TenSPKeyPressed(evt);
            }
        });

        txt_ChatLieu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_ChatLieu.setToolTipText("");
        txt_ChatLieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_ChatLieuKeyPressed(evt);
            }
        });

        txt_GiaBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_GiaBan.setToolTipText("");
        txt_GiaBan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_GiaBanKeyPressed(evt);
            }
        });

        txt_GiaBanSi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_GiaBanSi.setToolTipText("");
        txt_GiaBanSi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_GiaBanSiKeyPressed(evt);
            }
        });

        txt_MaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_Reset.setBackground(new java.awt.Color(238, 230, 255));
        btn_Reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_refesh.png"))); // NOI18N
        btn_Reset.setText("Làm mới");
        btn_Reset.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ResetActionPerformed(evt);
            }
        });

        lb_hinhanh.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(469, 469, 469)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addGap(375, 375, 375))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(btn_Them, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50)
                .addComponent(btn_Sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50)
                .addComponent(btn_Xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50)
                .addComponent(btn_Reset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50)
                .addComponent(btn_Luu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(140, 140, 140))
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(btn_ChonAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_hinhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lb_TenDanhMuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_MaDanhMuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_GhiChu)
                    .addComponent(lb_TenDanhMuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_GhiChu1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbo_TenDanhMuc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_TenSP)
                    .addComponent(cbo_NCC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_MaSP)
                    .addComponent(txt_KichThuoc))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_TenDanhMuc2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_MaDanhMuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_TenDanhMuc3)
                    .addComponent(lb_GhiChu2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_GiaBan)
                    .addComponent(txt_ChatLieu)
                    .addComponent(txt_GiaBanSi)
                    .addComponent(txt_MauSac))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lb_hinhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_ChonAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_ChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(txt_GiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(txt_GiaBanSi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lb_MaDanhMuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(lb_TenDanhMuc2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(lb_GhiChu2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb_TenDanhMuc3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_MaDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(txt_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb_TenDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb_GhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbo_TenDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb_TenDanhMuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbo_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_GhiChu1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_KichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane21)
                    .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private SanPham convertTextbox_to_Object()
    {
        SanPham sp = new SanPham();
        sp.MaSP = txt_MaSP.getText();
        sp.TenSP = txt_TenSP.getText();
        sp.KichThuoc = txt_KichThuoc.getText();
        sp.MauSac = txt_MauSac.getText();
        sp.ChatLieu = txt_ChatLieu.getText();
        sp.HinhAnh = duongdananh;
        sp.GiaBan = Integer.parseInt(txt_GiaBan.getText());
        sp.GiaBanSi = Integer.parseInt(txt_GiaBanSi.getText());
        sp.MaNCC = dsNCC.get(cbo_NCC.getSelectedIndex()).getMaNCC();
        sp.MaLoai = dsDanhMuc.get(cbo_TenDanhMuc.getSelectedIndex()).getMaloai();
        //sp.MaCH = "HL";
       
        return sp;
    }
    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        // TODO add your handling code here:
        if (txt_TimKiem.getText()!=null)
        {
            dsSP = sp.timKiemSanPham(txt_TimKiem.getText());
            vitri = 0;
            hienThiTable();
            hienThiTextBox(vitri);
        }
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here:
        flag = 1;
        anHienTextbox(true);
        txt_MaSP.setEnabled(false);
        txt_MaSP.setText(sp.SinhMaSanPham());
        btn_Them.setEnabled(false);
        btn_Sua.setEnabled(false);
        btn_Xoa.setEnabled(false);
    }//GEN-LAST:event_btn_ThemActionPerformed
    
    public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg= img.getScaledInstance(200, 200,Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    private void btn_ChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ChonAnhActionPerformed
        // TODO add your handling code here:
         try {
        // Lấy đường dẫn thư mục gốc của dự án
        String projectDir = System.getProperty("user.dir");

        // Kết hợp với phần tương đối của đường dẫn của thư mục images.nhanvien
        String relativePath = "src\\images.sanpham";

        // Tạo đối tượng File từ đường dẫn tương đối
        File directory = new File(projectDir, relativePath);

        JFileChooser f = new JFileChooser(directory.getPath());
        f.setDialogTitle("Mở file");
        f.showOpenDialog(null);
        File ftenanh = f.getSelectedFile();
        duongdananh = "src\\images.sanpham\\" + ftenanh.getName(); // Lấy đường dẫn tương đối của file

        lb_hinhanh.setIcon(ResizeImage(duongdananh));
    } catch (Exception ex) {
        System.out.println("Chưa chọn ảnh");
        System.out.println(duongdananh);
        ex.printStackTrace();
    }
    }//GEN-LAST:event_btn_ChonAnhActionPerformed

    private void tbl_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SanPhamMouseClicked
        int vitri = tbl_SanPham.getSelectedRow();

    // Kiểm tra xem một dòng có được chọn không
    if (vitri >= 0 && vitri < dsSP.size()) {
        // Hiển thị thông tin sản phẩm tương ứng
        hienThiTextBox(vitri);

        // Lấy sản phẩm được chọn từ danh sách
        SanPham selectedSanPham = dsSP.get(vitri);

        // Lấy mã Nhà Cung Cấp và Danh Mục của sản phẩm được chọn
        String maNCC = selectedSanPham.getMaNCC();
        String maDanhMuc = selectedSanPham.getMaLoai();

        // Chọn mã Nhà Cung Cấp trong ComboBox cbo_NCC
        for (int i = 0; i < cbo_NCC.getItemCount(); i++) {
            if (cbo_NCC.getItemAt(i).equals(maNCC)) {
                cbo_NCC.setSelectedIndex(i);
                break;
            }
        }

        // Chọn tên danh mục tương ứng với mã danh mục trong ComboBox cbo_TenDanhMuc
        for (int i = 0; i < cbo_TenDanhMuc.getItemCount(); i++) {
            if (dsDanhMuc.get(i).getMaloai().equals(maDanhMuc)) {
                cbo_TenDanhMuc.setSelectedIndex(i);
                break;
            }
        }
    }
    }//GEN-LAST:event_tbl_SanPhamMouseClicked

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        // TODO add your handling code here:
        flag = 3;
        txt_GiaBan.setEnabled(true);
        txt_GiaBanSi.setEnabled(true);
        btn_Luu.setEnabled(true);
        btn_Them.setEnabled(false);
        btn_Sua.setEnabled(false);
        btn_Xoa.setEnabled(false);

    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuActionPerformed
        // TODO add your handling code here:
        SanPham spham = convertTextbox_to_Object();
        if (flag == 1){
            sp.themSanPham(spham);
            System.out.println("Mã loại: "+ dsDanhMuc.get(cbo_TenDanhMuc.getSelectedIndex()).getMaloai());
            System.out.println("Mã NCC: "+ dsNCC.get(cbo_NCC.getSelectedIndex()).getMaNCC());
        }
        else if (flag == 2)
        {
            sp.capNhatSanPham(spham);  
        }
        else if (flag == 3)
        {
            int kq= JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa không?","Thông báo",JOptionPane.YES_NO_OPTION,JOptionPane.YES_NO_CANCEL_OPTION);
            if(kq==JOptionPane.YES_OPTION) {
               sp.xoaSanPham(spham);
            }
        }
        
        flag = 0;
        sp = new DSSanPham();
        dsSP = sp.layDanhSachSanPham();
        hienThiTable();
        
        btn_Luu.setEnabled(false);
        btn_Them.setEnabled(true);
        btn_Sua.setEnabled(true);
        btn_Xoa.setEnabled(true);
            
        
        
    }//GEN-LAST:event_btn_LuuActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // TODO add your handling code here:
        flag = 2;
        anHienTextbox(true);
        txt_MaSP.setEnabled(false);
        btn_Luu.setEnabled(true);
        btn_Them.setEnabled(false);
        //btn_XuatHD.setEnabled(false);
        btn_Sua.setEnabled(false);
        btn_Xoa.setEnabled(false);

    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ResetActionPerformed
        // TODO add your handling code here:
        xoaTextBox();
    }//GEN-LAST:event_btn_ResetActionPerformed

    private void txt_TenSPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TenSPKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            txt_KichThuoc.requestFocus();
        }
    }//GEN-LAST:event_txt_TenSPKeyPressed

    private void txt_KichThuocKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_KichThuocKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            txt_ChatLieu.requestFocus();
        }
    }//GEN-LAST:event_txt_KichThuocKeyPressed

    private void txt_ChatLieuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ChatLieuKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            txt_GiaBan.requestFocus();
        }
    }//GEN-LAST:event_txt_ChatLieuKeyPressed

    private void txt_GiaBanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_GiaBanKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            txt_GiaBanSi.requestFocus();
        }
    }//GEN-LAST:event_txt_GiaBanKeyPressed

    private void txt_GiaBanSiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_GiaBanSiKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            txt_MauSac.requestFocus();
        }
    }//GEN-LAST:event_txt_GiaBanSiKeyPressed

    private void txt_MauSacKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_MauSacKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MauSacKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.Button btn_ChonAnh;
    private component.Button btn_Luu;
    private component.Button btn_Reset;
    private component.Button btn_Sua;
    private component.Button btn_Them;
    private component.Button btn_TimKiem;
    private component.Button btn_Xoa;
    private javax.swing.JComboBox<String> cbo_NCC;
    private javax.swing.JComboBox<String> cbo_TenDanhMuc;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JLabel lb_GhiChu;
    private javax.swing.JLabel lb_GhiChu1;
    private javax.swing.JLabel lb_GhiChu2;
    private javax.swing.JLabel lb_MaDanhMuc;
    private javax.swing.JLabel lb_MaDanhMuc1;
    private javax.swing.JLabel lb_TenDanhMuc;
    private javax.swing.JLabel lb_TenDanhMuc1;
    private javax.swing.JLabel lb_TenDanhMuc2;
    private javax.swing.JLabel lb_TenDanhMuc3;
    private javax.swing.JLabel lb_hinhanh;
    private swing.table.Table tbl_SanPham;
    private javax.swing.JTextField txt_ChatLieu;
    private javax.swing.JTextField txt_GiaBan;
    private javax.swing.JTextField txt_GiaBanSi;
    private javax.swing.JTextField txt_KichThuoc;
    private javax.swing.JTextField txt_MaSP;
    private javax.swing.JTextField txt_MauSac;
    private javax.swing.JTextField txt_TenSP;
    private javax.swing.JTextPane txt_TimKiem;
    // End of variables declaration//GEN-END:variables

  
}
