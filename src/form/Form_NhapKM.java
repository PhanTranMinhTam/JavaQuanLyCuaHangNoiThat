/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import Class.DSDanhMuc;
import Class.DSKhuyenMai;
import Class.DanhMuc;
import Class.KhuyenMai;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Triss
 */
public class Form_NhapKM extends javax.swing.JPanel {

    /**
     * Creates new form Form_NhapKM
     */
    DSKhuyenMai km;
    ArrayList<KhuyenMai> dsKhuyenMai;
    int vitri = 0;
    int flag = 0;
    public Form_NhapKM() {
        initComponents();
        km = new DSKhuyenMai();
        dsKhuyenMai = km.layDanhSachKhuyenMai();
        hienThiTextBox(vitri);
        hienThiCombobox();
        hienThiTable();
        anHienButton(true);
        anHienTextbox(false);
    }
    
    private void hienThiTextBox(int vitri)
    {
        KhuyenMai n = dsKhuyenMai.get(vitri);
        txt_MaKM.setText(n.MaKM);
        txt_TenKM.setText(n.TenKM);
        txt_SoLuongKM.setText(n.SoLuongKM+"");
        txt_PTGiamGia.setText(Integer.toString((int) n.PhanTramKM));
        txt_GiaKM.setText(Integer.toString((int) n.GiaKM));
        txt_NgayBD.setText(n.NGAYBATDAU + "");
        txt_NgayKT.setText(n.NGAYKETHUC + "");
    }
    
    private void hienThiCombobox(){
       DefaultComboBoxModel<String> maSPModel = new DefaultComboBoxModel<>();
    
        HashSet<String> uniqueMaSP = new HashSet<>();

       // Lặp qua danh sách các khuyến mãi và thêm các mã sản phẩm duy nhất vào HashSet
        for (KhuyenMai km : dsKhuyenMai) {
            if (!uniqueMaSP.contains(km.getMaSP())) {
                uniqueMaSP.add(km.getMaSP());
                maSPModel.addElement(km.getMaSP());
            }
        }
        
        cbo_MaSP.setModel(maSPModel);
    }
    
    private void hienThiTable()
    {
        String []Header = {"Mã KM","Tên KM","Mã SP","Số lượng KM","Giá KM","% giảm giá", "Ngày BD","Ngày KT"};
        DefaultTableModel model = new DefaultTableModel(Header,0);
        for(KhuyenMai n: dsKhuyenMai)
        {   
            Object [] chnt = {n.MaKM,n.TenKM,n.MaSP,n.SoLuongKM,n.GiaKM,n.PhanTramKM,n.NGAYBATDAU,n.NGAYKETHUC };
            model.addRow(chnt);
        }
        tbl_KhuyenMai.fixTable(jScrollPane1);
        tbl_KhuyenMai.setModel(model);
        tbl_KhuyenMai.setRowSelectionInterval(vitri, vitri);
           
    }
    
    private void xoaTextBox()
    {
        txt_MaKM.setText("");
        txt_TenKM.setText("");
        txt_TimKiem.setText("");
        txt_GiaKM.setText("");
        txt_PTGiamGia.setText("");
        txt_NgayKT.setText("");
        txt_NgayBD.setText("");
        txt_SoLuongKM.setText("");
    }
    
     private void anHienTextbox(boolean t)
    {
        txt_MaKM.setEnabled(t);
        txt_TenKM.setEnabled(t);
        txt_NgayKT.setEnabled(t);
        txt_NgayBD.setEnabled(t);
        txt_GiaKM.setEnabled(t);
        txt_PTGiamGia.setEnabled(t);
        txt_SoLuongKM.setEnabled(t);
    }
    
    private void anHienButton(boolean t)
    {
        btn_Them.setEnabled(t);
        btn_Sua.setEnabled(t);
        btn_Luu.setEnabled(!t);
        btn_Xoa.setEnabled(t);
    
    }

    private KhuyenMai convertToObj()
    {
        KhuyenMai km = new KhuyenMai();
        km.MaKM = txt_MaKM.getText();
        km.TenKM = txt_TenKM.getText();
        km.PhanTramKM = Float.parseFloat(txt_PTGiamGia.getText());
        km.NGAYBATDAU = Date.valueOf(txt_NgayBD.getText());
        km.NGAYKETHUC = Date.valueOf(txt_NgayKT.getText());
        km.MaSP = cbo_MaSP.getSelectedItem().toString();
        km.GiaKM = Float.parseFloat(txt_GiaKM.getText());
        km.SoLuongKM = Integer.parseInt(txt_SoLuongKM.getText());
        
        
      
        
        return km;
        
    }
    
    //kiểm tra ngày BD < ngày KT và ngày KT phải lớn hơn ngày hiện tại
    private boolean kiemTraNgay()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateBD = null;
        java.util.Date dateKT = null;
        java.util.Date dateNow = new java.util.Date();
        try {
            dateBD = sdf.parse(txt_NgayBD.getText());
            dateKT = sdf.parse(txt_NgayKT.getText());
        } catch (ParseException ex) {
            Logger.getLogger(Form_NhapKM.class.getName()).
            log(Level.SEVERE, null, ex);
        }
        if(dateBD.after(dateKT) || dateKT.before(dateNow))
        {
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateTimeBD = new component.DateChooser();
        dateTimeKT = new component.DateChooser();
        roundPanel1 = new swing.round.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        lb_MaDanhMuc = new javax.swing.JLabel();
        lb_TenDanhMuc = new javax.swing.JLabel();
        lb_GhiChu = new javax.swing.JLabel();
        lb_MaDanhMuc1 = new javax.swing.JLabel();
        lb_TenDanhMuc2 = new javax.swing.JLabel();
        lb_GhiChu2 = new javax.swing.JLabel();
        lb_TenDanhMuc3 = new javax.swing.JLabel();
        btn_Them = new component.Button();
        btn_Sua = new component.Button();
        btn_Reset = new component.Button();
        btn_TimKiem = new component.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_KhuyenMai = new swing.table.Table();
        btn_Luu = new component.Button();
        btn_Xoa = new component.Button();
        lb_GhiChu1 = new javax.swing.JLabel();
        cbo_MaSP = new javax.swing.JComboBox<>();
        btn_NgayBD_Popup = new javax.swing.JButton();
        btn_NgayKT_Popup = new javax.swing.JButton();
        txt_NgayBD = new javax.swing.JTextField();
        txt_NgayKT = new javax.swing.JTextField();
        txt_TenKM = new javax.swing.JTextField();
        txt_MaKM = new javax.swing.JTextField();
        txt_SoLuongKM = new javax.swing.JTextField();
        txt_PTGiamGia = new javax.swing.JTextField();
        txt_GiaKM = new javax.swing.JTextField();
        txt_TimKiem = new javax.swing.JTextField();

        dateTimeBD.setForeground(new java.awt.Color(192, 150, 108));
        dateTimeBD.setDateFormat("yyyy-MM-dd");
        dateTimeBD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dateTimeBD.setTextRefernce(txt_NgayBD);

        dateTimeKT.setForeground(new java.awt.Color(192, 150, 108));
        dateTimeKT.setDateFormat("yyyy-MM-dd");
        dateTimeKT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dateTimeKT.setTextRefernce(txt_NgayKT);

        setBackground(new java.awt.Color(255, 255, 255));

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(0, 0, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("THÔNG TIN KHUYẾN MÃI");

        lb_MaDanhMuc.setBackground(new java.awt.Color(0, 0, 255));
        lb_MaDanhMuc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_MaDanhMuc.setForeground(new java.awt.Color(255, 0, 51));
        lb_MaDanhMuc.setText("Mã khuyến mãi:");

        lb_TenDanhMuc.setBackground(new java.awt.Color(0, 0, 255));
        lb_TenDanhMuc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_TenDanhMuc.setForeground(new java.awt.Color(255, 0, 51));
        lb_TenDanhMuc.setText("Tên khuyến mãi:");

        lb_GhiChu.setBackground(new java.awt.Color(0, 0, 255));
        lb_GhiChu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_GhiChu.setForeground(new java.awt.Color(255, 0, 51));
        lb_GhiChu.setText("Mã sản phẩm:");

        lb_MaDanhMuc1.setBackground(new java.awt.Color(0, 0, 255));
        lb_MaDanhMuc1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_MaDanhMuc1.setForeground(new java.awt.Color(255, 0, 51));
        lb_MaDanhMuc1.setText("Giá khuyến mãi:");

        lb_TenDanhMuc2.setBackground(new java.awt.Color(0, 0, 255));
        lb_TenDanhMuc2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_TenDanhMuc2.setForeground(new java.awt.Color(255, 0, 51));
        lb_TenDanhMuc2.setText("% giảm giá:");

        lb_GhiChu2.setBackground(new java.awt.Color(0, 0, 255));
        lb_GhiChu2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_GhiChu2.setForeground(new java.awt.Color(255, 0, 51));
        lb_GhiChu2.setText("Ngày bắt đầu:");

        lb_TenDanhMuc3.setBackground(new java.awt.Color(0, 0, 255));
        lb_TenDanhMuc3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_TenDanhMuc3.setForeground(new java.awt.Color(255, 0, 51));
        lb_TenDanhMuc3.setText("Ngày kết thúc:");

        btn_Them.setBackground(new java.awt.Color(238, 230, 255));
        btn_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_add.png"))); // NOI18N
        btn_Them.setText("Thêm");
        btn_Them.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
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

        btn_Reset.setBackground(new java.awt.Color(238, 230, 255));
        btn_Reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_refesh.png"))); // NOI18N
        btn_Reset.setText("Làm mới");
        btn_Reset.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ResetActionPerformed(evt);
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

        tbl_KhuyenMai.setBackground(new java.awt.Color(240, 240, 240));
        tbl_KhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã KM", "Tên KM", "Mã SP", "Giá KM", "5 giảm giá", "Ngày bắt đầu", "Ngày kết thúc"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_KhuyenMai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbl_KhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_KhuyenMai);

        btn_Luu.setBackground(new java.awt.Color(238, 230, 255));
        btn_Luu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_save.png"))); // NOI18N
        btn_Luu.setText("Lưu");
        btn_Luu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuActionPerformed(evt);
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

        lb_GhiChu1.setBackground(new java.awt.Color(0, 0, 255));
        lb_GhiChu1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_GhiChu1.setForeground(new java.awt.Color(255, 0, 51));
        lb_GhiChu1.setText("Số lượng khuyến mãi:");

        cbo_MaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbo_MaSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_NgayBD_Popup.setText("..");
        btn_NgayBD_Popup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NgayBD_PopupActionPerformed(evt);
            }
        });

        btn_NgayKT_Popup.setText("..");
        btn_NgayKT_Popup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NgayKT_PopupActionPerformed(evt);
            }
        });

        txt_NgayBD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_NgayKT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_TenKM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_MaKM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_SoLuongKM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_PTGiamGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_GiaKM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(469, 469, 469)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(350, 350, 350))
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 823, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(btn_Them, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addGap(68, 68, 68)
                .addComponent(btn_Sua, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addGap(68, 68, 68)
                .addComponent(btn_Xoa, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addGap(77, 77, 77)
                .addComponent(btn_Reset, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addGap(72, 72, 72)
                .addComponent(btn_Luu, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addGap(90, 90, 90))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_GhiChu)
                    .addComponent(lb_GhiChu1)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lb_TenDanhMuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_MaDanhMuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_MaKM, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(cbo_MaSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_TenKM)
                    .addComponent(txt_SoLuongKM))
                .addGap(102, 102, 102)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_TenDanhMuc2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_MaDanhMuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_GhiChu2)
                    .addComponent(lb_TenDanhMuc3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(txt_NgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_NgayKT_Popup))
                    .addComponent(txt_PTGiamGia)
                    .addComponent(txt_GiaKM, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(txt_NgayBD)
                        .addGap(18, 18, 18)
                        .addComponent(btn_NgayBD_Popup)))
                .addGap(109, 109, 109))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_MaDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_MaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_TenDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_GhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lb_GhiChu1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                            .addComponent(txt_SoLuongKM)))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_GiaKM)
                            .addComponent(lb_MaDanhMuc1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb_TenDanhMuc2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_PTGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addComponent(lb_GhiChu2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_NgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_NgayBD_Popup))))
                        .addGap(20, 20, 20)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_NgayKT_Popup)
                            .addComponent(txt_NgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_TenDanhMuc3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(65, 65, 65))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here:
        flag = 1;
        xoaTextBox();
        anHienTextbox(true);
        anHienButton(false);
        txt_MaKM.setEditable(false);
        txt_MaKM.setText(km.SinhMaKhuyenMai());
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // TODO add your handling code here:
        flag = 2;
        anHienButton(false);
        anHienTextbox(true);
        txt_MaKM.setEnabled(false);
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ResetActionPerformed
        // TODO add your handling code here:
        xoaTextBox();
    }//GEN-LAST:event_btn_ResetActionPerformed

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        // TODO add your handling code here:
        if (txt_TimKiem.getText()!=null)
        {
            dsKhuyenMai = km.timKiemKhuyenMai(txt_TimKiem.getText());
            vitri = 0;
            hienThiTable();
            hienThiTextBox(vitri);
        }
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void btn_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuActionPerformed
        // TODO add your handling code here:
        KhuyenMai kMai = convertToObj();
        
        if (flag == 1){
            if (kiemTraNgay() == false)
            {
                JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc và ngày kết thúc phải lớn hơn ngày hiện tại");
                return;
            }
            km.themKhuyenMai(kMai);
        }
        else if (flag == 2){
            if (kiemTraNgay() == false)
            {
                JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc và ngày kết thúc phải lớn hơn ngày hiện tại");
                return;
            }
            kMai.setMaKM(txt_MaKM.getText());
            km.capNhatKhuyenMai(kMai); 
        }         
        else if (flag == 3){
            int kq= JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa không?","Thông báo",JOptionPane.YES_NO_OPTION,JOptionPane.YES_NO_CANCEL_OPTION);
            if(kq==JOptionPane.YES_OPTION) {
                km.xoaKhuyenMai(kMai);
                //vitri = vitri - 1;
            }
        } 

        
        flag = 0;
        km = new DSKhuyenMai();
        dsKhuyenMai = km.layDanhSachKhuyenMai();
        hienThiTextBox(vitri);
        hienThiTable();

        btn_Luu.setEnabled(false);
        btn_Them.setEnabled(true);
        btn_Sua.setEnabled(true);
        btn_Xoa.setEnabled(true);
    }//GEN-LAST:event_btn_LuuActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        // TODO add your handling code here:
        flag = 3;
        anHienButton(false);
        anHienTextbox(true);
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void tbl_KhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KhuyenMaiMouseClicked
        // TODO add your handling code here:
        int vitri = tbl_KhuyenMai.getSelectedRow();
    
    // Check if a row is selected
        if (vitri >= 0 && vitri < dsKhuyenMai.size()) {
            // Retrieve the selected NhanVien object from the list
            KhuyenMai selectedKhuyenMai= dsKhuyenMai.get(vitri);


            String maSanPham = selectedKhuyenMai.getMaSP();

 
            cbo_MaSP.setSelectedItem(maSanPham);
        }
        hienThiTextBox(vitri);
    }//GEN-LAST:event_tbl_KhuyenMaiMouseClicked

    private void btn_NgayKT_PopupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NgayKT_PopupActionPerformed
        // TODO add your handling code here:
        dateTimeKT.showPopup();
    }//GEN-LAST:event_btn_NgayKT_PopupActionPerformed

    private void btn_NgayBD_PopupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NgayBD_PopupActionPerformed
        // TODO add your handling code here:
        dateTimeBD.showPopup();
    }//GEN-LAST:event_btn_NgayBD_PopupActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.Button btn_Luu;
    private javax.swing.JButton btn_NgayBD_Popup;
    private javax.swing.JButton btn_NgayKT_Popup;
    private component.Button btn_Reset;
    private component.Button btn_Sua;
    private component.Button btn_Them;
    private component.Button btn_TimKiem;
    private component.Button btn_Xoa;
    private javax.swing.JComboBox<String> cbo_MaSP;
    private component.DateChooser dateTimeBD;
    private component.DateChooser dateTimeKT;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_GhiChu;
    private javax.swing.JLabel lb_GhiChu1;
    private javax.swing.JLabel lb_GhiChu2;
    private javax.swing.JLabel lb_MaDanhMuc;
    private javax.swing.JLabel lb_MaDanhMuc1;
    private javax.swing.JLabel lb_TenDanhMuc;
    private javax.swing.JLabel lb_TenDanhMuc2;
    private javax.swing.JLabel lb_TenDanhMuc3;
    private swing.round.RoundPanel roundPanel1;
    private swing.table.Table tbl_KhuyenMai;
    private javax.swing.JTextField txt_GiaKM;
    private javax.swing.JTextField txt_MaKM;
    private javax.swing.JTextField txt_NgayBD;
    private javax.swing.JTextField txt_NgayKT;
    private javax.swing.JTextField txt_PTGiamGia;
    private javax.swing.JTextField txt_SoLuongKM;
    private javax.swing.JTextField txt_TenKM;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
