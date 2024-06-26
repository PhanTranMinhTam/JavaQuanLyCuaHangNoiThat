CREATE DATABASE QL_CUAHANGNOITHAT
--GO

USE QL_CUAHANGNOITHAT
--GO

CREATE TABLE KHACHHANG(
    MAKH VARCHAR(10) NOT NULL,
    TENKH NVARCHAR(50),
    DIACHI NVARCHAR(50),
    SDT VARCHAR(15),
    EMAIL VARCHAR(50),
    CONSTRAINT PK_KHACHHANG PRIMARY KEY (MAKH)
)
CREATE TABLE NHANVIEN(
	MANV VARCHAR(10) NOT NULL,
	TENNV NVARCHAR(50),
	TENTAIKHOAN VARCHAR(50),
	CHUCVU NVARCHAR(50),
	SDT VARCHAR(10),
	EMAIL VARCHAR(50),
	MATKHAU VARCHAR(50),
	HINHANH VARCHAR(200),
	TRANGTHAI INT,
	CONSTRAINT PK_NHANVIEN PRIMARY KEY (MANV)
)


CREATE TABLE LOAICH(
    MACH VARCHAR(10) NOT NULL,
    TENCH NVARCHAR(50),
    GHICHU NVARCHAR(50),
	TRANGTHAI INT,
    CONSTRAINT PK_LOAICH PRIMARY KEY (MACH)
)
CREATE TABLE LOAISP(
    MALOAI VARCHAR(10) NOT NULL,
    TENLOAI NVARCHAR(50),
	TRANGTHAI INT,
	MACH VARCHAR(10) NOT NULL,
    CONSTRAINT PK_LOAISP PRIMARY KEY (MALOAI),
    CONSTRAINT FK_LOAISP_LOAICH FOREIGN KEY (MACH) REFERENCES LOAICH(MACH)
)
CREATE TABLE NHACUNGCAP(
	MANCC VARCHAR(10) NOT NULL,
	TENNCC NVARCHAR(50),
	DIACHI NVARCHAR(50),
	SDT VARCHAR(10),
	EMAIL VARCHAR(50),
	TRANGTHAI INT,
	MACH VARCHAR(10),
	MALOAI VARCHAR(10),
	CONSTRAINT PK_NHACUNGCAP PRIMARY KEY (MANCC),
    CONSTRAINT FK_NHACUNGCAP_LOAICH FOREIGN KEY (MACH) REFERENCES LOAICH(MACH),
    CONSTRAINT FK_NHACUNGCAP_LOAISP FOREIGN KEY (MALOAI) REFERENCES LOAISP(MALOAI)
)
CREATE TABLE SANPHAM(
    MASP VARCHAR(10) NOT NULL,
    TENSP NVARCHAR(50),
    KICHTHUOC VARCHAR(50),
    MAUSAC NVARCHAR(50),
    CHATLIEU NVARCHAR(100),
    HINHANH VARCHAR(200),
    GIABAN DECIMAL(18, 0),
    GIABANSI DECIMAL(18, 0),
    TRANGTHAI INT,
    MANCC VARCHAR(10) NOT NULL,
	MALOAI VARCHAR(10) NOT NULL,
    CONSTRAINT PK_SANPHAM PRIMARY KEY (MASP),
    CONSTRAINT FK_SANPHAM_LOAISP FOREIGN KEY (MALOAI) REFERENCES LOAISP(MALOAI),
    CONSTRAINT FK_SANPHAM_NHACUNGCAP FOREIGN KEY (MANCC) REFERENCES NHACUNGCAP(MANCC)
)
CREATE TABLE HOADON(
    MAHD VARCHAR(50) NOT NULL,
    MANV VARCHAR(10) NOT NULL,
    MAKH VARCHAR(10) NOT NULL,
    NGAYLAP DATETIME,
	THANHTIEN MONEY,
	TRANGTHAI INT,
    CONSTRAINT PK_HOADON PRIMARY KEY (MAHD),
    CONSTRAINT FK_HOADON_NHANVIEN FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV),
    CONSTRAINT FK_HOADON_KHACHHANG FOREIGN KEY (MAKH) REFERENCES KHACHHANG(MAKH)
)

SELECT * FROM HOADON
SELECT * FROM CT_HOADON

CREATE TABLE CT_HOADON(
    MAHD VARCHAR(50) NOT NULL,
    MASP VARCHAR(10) NOT NULL,
    SOLUONG INT,
    GIABAN DECIMAL(18, 0),
	TRANGTHAI INT,
    CONSTRAINT PK_CT_HOADON PRIMARY KEY (MAHD,MASP), 
    CONSTRAINT FK_CT_HOADON_HOADON FOREIGN KEY (MAHD) REFERENCES HOADON(MAHD),
    CONSTRAINT FK_CT_HOADON_SANPHAM FOREIGN KEY (MASP) REFERENCES SANPHAM(MASP)
)
CREATE TABLE PHIEUNHAP(
    MAPHIEUNHAP VARCHAR(50) NOT NULL,
    MANV VARCHAR(10) NOT NULL,
    MANCC VARCHAR(10) NOT NULL,
    NGAYNHAP DATE,
	TRANGTHAI INT,
    CONSTRAINT PK_PHIEUNHAP PRIMARY KEY (MAPHIEUNHAP), 
    CONSTRAINT FK_PHIEUNHAP_NHANVIEN FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV),
    CONSTRAINT FK_PHIEUNHAP_NHACUNGCAP FOREIGN KEY (MANCC) REFERENCES NHACUNGCAP(MANCC)
	)


CREATE TABLE CT_PHIEUNHAP(
    MAPHIEUNHAP VARCHAR(50) NOT NULL,
    MASP VARCHAR(10) NOT NULL,
    SOLUONGNHAP INT,
    DONGIANHAP DECIMAL(18,0),
	TRANGTHAI INT,
    CONSTRAINT PK_CT_PHIEUNHAP PRIMARY KEY (MAPHIEUNHAP,MASP), 
    CONSTRAINT FK_CT_PHIEUNHAP_PHIEUNHAP FOREIGN KEY (MAPHIEUNHAP) REFERENCES PHIEUNHAP(MAPHIEUNHAP),
    CONSTRAINT FK_CT_PHIEUNHAP_SANPHAM FOREIGN KEY (MASP) REFERENCES SANPHAM(MASP) 
)

CREATE TABLE KHUYENMAI(
    MAKM VARCHAR(10) NOT NULL,
    TENKM NVARCHAR(50),
    PHANTRAMKM FLOAT,
    NGAYBATDAU DATE,
    NGAYKETHUC DATE,
	TRANGTHAI INT,
    CONSTRAINT PK_KHUYENMAI PRIMARY KEY (MAKM)
)

CREATE TABLE CT_KHUYENMAI(
    MAKM VARCHAR(10) NOT NULL,
    MASP VARCHAR(10) NOT NULL,
    GIAKM DECIMAL(18, 0),
    SOLUONGKM INT,
	TRANGTHAI INT
    CONSTRAINT PK_CT_KHUYENMAI PRIMARY KEY (MAKM,MASP), 
    CONSTRAINT FK_CT_KHUYENMAI_KHUYENMAI FOREIGN KEY (MAKM) REFERENCES KHUYENMAI(MAKM),
    CONSTRAINT FK_CT_KHUYENMAI_SANPHAM FOREIGN KEY (MASP) REFERENCES SANPHAM(MASP) 
)
-- Bảng KHACHHANG
INSERT INTO KHACHHANG (MAKH, TENKH, DIACHI, SDT, EMAIL) VALUES
('KH001', N'Nguyễn Minh Anh', N'HCM', '0326081303', 'minhanh@gmail.com'),
('KH002', N'Trần Minh Hòa', N'Tiền Giang', '0977569713', 'minhhoa@gmail.com'),
('KH003', N'Nguyễn Thị Tố Nghi', N'Vũng Tàu', '0862569722', 'tonghi@gmail.com'),
('KH004', N'Lê Thị Lan Anh', N'Bình Phước', '0345698709', 'lananh@gmail.com'),
('KH005', N'Trần Thị Mỹ Linh', N'Đồng Tháp', '0336569712', 'mylinh@gmail.com'),
('KH006', N'Phan Thị Thu Thủy', N'HCM', '0922669745', 'thuthuy@gmail.com'),
('KH007', N'Lương Huệ Mẫn', N'Cà Mau', '0994569734', 'hueman@gmail.com'),
('KH008', N'Lê Hoàng Trung', N'HCM', '0936369853', 'hoangtrung@gmail.com'),
('KH009', N'Nguyễn Chí Dũng', N'Bạc Liêu', '0987569662', 'chidung@gmail.com'),
('KH010', N'Trần Thanh Bình', N'HCM', '089429778', 'thanhbinh@gmail.com')

-- Bảng NHANVIEN
INSERT INTO NHANVIEN (MANV, TENNV, TENTAIKHOAN, CHUCVU, SDT, EMAIL, MATKHAU, HINHANH, TRANGTHAI) VALUES
('NV001', N'Nguyễn Minh Châu', 'minhchaubh', N'Bán hàng', '0994569787', 'minhchau@gmail.com', 'chaubh01', 'Hinh1.jpg', 0),
('NV002', N'Hoàng Văn Chiến', 'vanchienbh', N'Bán hàng', '0894569225', 'vanchien@gmail.com', 'chienbh02', 'Hinh2.jpg', 0),
('NV003', N'Trần Thị Ngọc Minh', 'ngocminhbh', N'Bán hàng', '0874569547', 'ngocminh@gmail.com', 'minhbh03', 'Hinh3.jpg', 0),
('NV004', N'Lê Hoàng Thắng', 'hoangthangnk', N'Nhập kho', '0856569156', 'hoangthang@gmail.com', 'thangnk01', 'Hinh4.jpg', 0),
('NV005', N'Bùi Quang Thái', 'quangthaink', N'Nhập kho', '0964569356', 'quangthai@gmail.com', 'thaink02', 'Hinh5.jpg', 0)
-- Bảng DANHMUCSP
INSERT INTO LOAICH (MACH, TENCH, GHICHU,TRANGTHAI) VALUES
('MD',  N'Minh Đạt', '',0),
('TN',  N'Tuệ Nghi', '',0),
('MT',  N'Minh Trí', '',0),
('TP',  N'Tâm Phan', '',0),
('KD',  N'Kim Đan', '',0),
('XT',  N'Xuân Tú', '',0),
('HL',  N'Hoàng Lan', '',0);
INSERT INTO LOAISP(MALOAI, TENLOAI,TRANGTHAI,MACH) VALUES
('L01',  N'Tủ',0,'XT'),
('L02',  N'Ghế',0,'KD'),
('L03',  N'Bàn',0,'MT'),
('L04',  N'Giường',0,'TP'),
('L05',  N'Đèn',0,'HL'),
('L06',  N'Nệm',0,'MD');
-- Bảng NHACUNGCAP
INSERT INTO NHACUNGCAP (MANCC, TENNCC, DIACHI, SDT, EMAIL, TRANGTHAI,MACH,MALOAI) VALUES
('HL01',  N'Tùng Dương',  N'HCM', '0833769722', 'tunduong@gmail.com', 0,'HL','L01'),
('TP01',  N'Hoàng Tâm',  N'Bình Thuận', '0889761234', 'hoangtam@gmail.com', 0,'TP','L05'),
('XT01',  N'Hòa Phát',  N'Bình Dương', '0889769654', 'hoaphat@gmail.com', 0,'XT','L02'),
('MT01',  N'Trí Nguyễn',  N'Bến Tre', '0889709786', 'tringuyen@gmail.com', 0,'MT','L03'),
('KT01',  N'Xuân Hòa',  N'HCM', '0836669356', 'xuanhoa@gmail.com', 0,'KD','L03');

-- Bảng SANPHAM
INSERT INTO SANPHAM (MASP, TENSP, KICHTHUOC, MAUSAC, CHATLIEU, HINHANH, GIABAN, GIABANSI, TRANGTHAI, MANCC,MALOAI) VALUES
('SP001',  N'Ghế Barcelona BCL 300', '54cmx62cmx42cm',  N'Đen', N'Ghế khung thép không gỉ, dây da cao cấp, đệm mút đàn hồi bọc da', 'GH01.jpg',3400000, 3000000, 0, 'HL01','L01'),
('SP002',  N'Ghế đôn thời trang B105', '66cmx69cmx40cm',  N'Cam',  N'Đệm mút đàn hổi cao cấp', 'GH02.jpg', 11000000, 10000000, 0,'XT01','L03'),
('SP003',  N'Ghế Thời trang B241-1', '67cmx65cmx47cm',  N'Vàng',  N'Bông định hình, khung sắt, vải', 'GH03.jpg', 17000000, 15000000, 0, 'KT01','L02'),
('SP004',  N'Ghế 4 chân', '67cmx65cmx90cm',  N'Nâu',  N'Khung gỗ', 'GH05.jpg', 17000000, 15000000, 0, 'MT01','L05');
-- Bảng HOADON
INSERT INTO HOADON (MAHD, MANV, MAKH, NGAYLAP,THANHTIEN,TRANGTHAI) VALUES
('HD0001', 'NV001', 'KH001', '2023-12-06',0,0),
('HD0002', 'NV002', 'KH001', '2023-12-07',0,0),
('HD0003', 'NV002', 'KH002', '2023-06-02',0,0),
('HD0004', 'NV002', 'KH003', '2024-10-03',0,0);

-- Bảng CT_HOADON
INSERT INTO CT_HOADON (MAHD, MASP, SOLUONG, GIABAN,TRANGTHAI) VALUES
('HD0001', 'SP001', 2, 3400000,0),
('HD0001', 'SP002', 3, 11000000,0),
('HD0002', 'SP002', 2, 25000000,0),
('HD0003', 'SP001', 10, 2000000,0),
('HD0004', 'SP003', 11, 27000000,0);

-- Bảng PHIEUNHAP
INSERT INTO PHIEUNHAP (MAPHIEUNHAP, MANV, MANCC,NGAYNHAP,TRANGTHAI) VALUES
('PN0001', 'NV001', 'HL01', '2023-05-15',0),
('PN0002', 'NV002', 'XT01','2024-04-24',0),
('PN0003', 'NV003', 'KT01','2023-01-01',0),
('PN0004', 'NV002', 'MT01','2024-01-01',0);
-- Bảng CT_PHIEUNHAP
INSERT INTO CT_PHIEUNHAP (MAPHIEUNHAP, MASP, SOLUONGNHAP,DONGIANHAP,TRANGTHAI) VALUES
('PN0001', 'SP001', 3,2000000,0),
('PN0002', 'SP003', 2,8000000,0),
('PN0003', 'SP002', 6,6000000,0),
('PN0004', 'SP004', 8,6000000,0);
-- Bảng KHUYENMAI
INSERT INTO KHUYENMAI (MAKM, TENKM, PHANTRAMKM, NGAYBATDAU, NGAYKETHUC,TRANGTHAI) VALUES
('KM001',  N'Khuyến mãi giảm giá 50%', 0.5, '2023-05-15', '2023-05-20',0),
('KM002',  N'Khuyến mãi giảm giá 20%', 0.2, '2023-04-16', '2023-04-29',0),
('KM003',  N'Khuyến mãi giảm giá 10%', 0.1, '2023-01-22', '2023-01-27',0),
('KM004',  N'Khuyến mãi giảm giá 30%', 0.3, '2023-05-23', '2023-05-25',0),
('KM005',  N'Khuyến mãi giảm giá 15%', 0.15, '2023-11-12', '2023-11-20',0);

-- Bảng CT_KHUYENMAI
INSERT INTO CT_KHUYENMAI (MAKM, MASP, GIAKM, SOLUONGKM,TRANGTHAI) VALUES
('KM001', 'SP001', 0.5, 15000,0),
('KM001', 'SP003', 0.2, 17000,0),
('KM003', 'SP003', 0.1, 18000,0),
('KM004', 'SP001', 0.3, 20000,0),
('KM005', 'SP001', 0.15, 11000,0),
('KM004', 'SP002', 0.5, 12000,0),
('KM002', 'SP003', 0.2, 9000,0),
('KM003', 'SP001', 0.1, 8000,0),
('KM001', 'SP002', 0.3, 19000,0),
('KM005', 'SP002', 0.15, 50000,0);

USE QL_CUAHANGNOITHAT
----SINH MA SAN PHAM----
CREATE PROCEDURE sinhma_masplonnhat
AS
  SELECT TOP 1 masp 
  FROM SANPHAM 
  ORDER BY masp DESC;
--- TÌM KIẾM SẢN PHẨM--------------
CREATE PROCEDURE timKiemSanPham (@giatri nvarchar(100))
AS
BEGIN
    SELECT * FROM SANPHAM WHERE 
    (MASP like N'%' + @giatri + '%' )
    or (TENSP like N'%' + @giatri + '%')
    or (MAUSAC like N'%' + @giatri + '%')
    or (CHATLIEU like N'%' + @giatri + '%')
END;

exec timKiemSanPham N'SP001'


--Lấy GIABANSI của sản phẩm
CREATE PROCEDURE layGiaBanSi (@masp nvarchar(10))
AS
  SELECT GIABANSI FROM SANPHAM WHERE MASP = @masp;

exec LayGiaBanSi 'SP001'

--Lấy GIABAN của sản phẩm
CREATE PROCEDURE layGiaBan (@masp nvarchar(10))
AS
  SELECT GIABAN FROM SANPHAM WHERE MASP = @masp;

exec LayGiaBan 'SP001'

---INSERT SAN PHAM----------------------

CREATE PROC insert_SanPham
    @maSP VARCHAR(10),
    @tenSP NVARCHAR(50),
    @kichThuoc VARCHAR(50),
    @mauSac NVARCHAR(50),
    @chatLieu NVARCHAR(100),
    @hinhAnh VARCHAR(200),
    @giaBan DECIMAL(18, 0),
    @giaBanSi DECIMAL(18, 0),
    @mancc VARCHAR(10),
    @maloai VARCHAR(10)
AS
BEGIN
    INSERT INTO SANPHAM (MASP, TENSP, KICHTHUOC, MAUSAC, CHATLIEU, HINHANH, GIABAN, GIABANSI, TRANGTHAI, MANCC,MALOAI)
    VALUES (@maSP, @tenSP, @kichThuoc, @mauSac, @chatLieu, @hinhAnh, @giaBan, @giaBanSi, 0, @mancc, @maloai);
END;

exec insert_SanPham 'SP004',N'Ghế Barcelona BCL 300','54cmx62cmx42cm',N'Đen',N'Ghế khung thép không gỉ, dây da cao cấp, đệm mút đàn hồi bọc da','GH01.jpg',3400000,3000000,'HL01','L01'

--UPDATE SAN PHAM
CREATE PROCEDURE update_SanPham
    @maSP VARCHAR(10),
    @tenSP NVARCHAR(50),
    @kichThuoc VARCHAR(50),
    @mauSac NVARCHAR(50),
    @chatLieu NVARCHAR(100),
    @hinhAnh VARCHAR(200),
    @giaBan DECIMAL(18, 0),
    @giaBanSi DECIMAL(18, 0),
    @mancc VARCHAR(10),
    @maloai VARCHAR(10)
AS
BEGIN
    UPDATE SANPHAM
    SET 
        TENSP = @tenSP,
        KICHTHUOC = @kichThuoc,
        MAUSAC = @mauSac,
        CHATLIEU = @chatLieu,
        HINHANH = @hinhAnh,
        GIABAN = @giaBan,
        GIABANSI = @giaBanSi,
        MANCC = @mancc,
        MALOAI = @maloai
    WHERE 
        MASP = @maSP;
END;

exec update_SanPham 'SP004',N'G','54cmx62cmx42cm',N'Đen',N'Ghế khung thép không gỉ, dây da cao cấp, đệm mút đàn hồi bọc da','GH01.jpg',3400000,3000000,'HL01','L01'

--DELETE SAN PHAM
CREATE PROCEDURE delete_SanPham (@maSP VARCHAR(10))
AS
BEGIN
    UPDATE SANPHAM
    SET TRANGTHAI = 1
    WHERE MASP = @maSP;
END;

exec delete_SanPham 'SP004'


--UPDATE DANH MUC SP
SELECT * FROM DANHMUCSP

--CREATE PROC update_DanhMuc(@maDanhMuc varchar(10),@tenDanhMuc nvarchar(50), @ghiChu nvarchar(50))
--as 
--	UPDATE DANHMUCSP
--	SET TENDANHMUC = @tenDanhMuc, GHICHU = @ghiChu
--	WHERE MADANHMUC = @maDanhMuc

--exec update_DanhMuc 'GHE11',N'Ghế xịn',N'Ghế siêu cấp vip pro'
-------------Delete DanhMuc ---------------------
--CREATE PROC delete_DanhMuc(@maDanhMuc varchar(10))
--as 
--	UPDATE DANHMUCSP
--	SET TRANGTHAI = 1  
--	WHERE MADANHMUC = @maDanhMuc


--exec delete_DanhMuc 'GHE11'
----danh sach loaisp theo cua hang ---
CREATE PROCEDURE DanhsachLoaiSP_TheoCuaHang(@mach nvarchar(15))
AS
  SELECT * FROM LOAISP where mach =@mach and TRANGTHAI = 0 
----SINH MA DANH MUC------
CREATE PROCEDURE sinhma_madmlonnhat
AS
  SELECT TOP 1 MALOAI 
  FROM LOAISP 
  ORDER BY MALOAI DESC;
--- TÌM KIẾM DANH MUC--------------
CREATE PROC timKiemDanhMuc (@giatri nvarchar(100))
as
	select * from LOAISP where 
	(MALOAI like N'%' + @giatri + '%' )
	or (TENLOAI like N'%' + @giatri + '%')
	or (MACH like N'%' + @giatri + '%')

exec timKiemDanhMuc N'L01'

---INSERT DANH MỤC SP----------------------
USE QL_CUAHANGNOITHAT
CREATE PROC insert_loaisp(@maloai varchar(10),@tenloai nvarchar(50),@mach varchar(10))
as 
	INSERT INTO LOAISP VALUES (@maloai, @tenloai,0,@mach)

exec insert_loaisp 'L011',N'Ghế dựa','MT'
--UPDATE DANH MUC SP
SELECT * FROM DANHMUCSP

CREATE PROC update_DanhMuc(@maloai varchar(10),@tenloai nvarchar(50),@mach varchar(10))
as 
	UPDATE LOAISP
	SET TENLOAI = @tenloai
	WHERE maloai = @maloai

exec update_DanhMuc 'L011',N'Ghế dựa','MT'
-------------Delete DanhMuc ---------------------
CREATE PROC delete_DanhMuc(@maloai varchar(10))
as 
	UPDATE LOAISP
	SET TRANGTHAI = 1  
	WHERE maloai = @maloai


exec delete_DanhMuc 'GHE11'
-------theo cua hang -------
CREATE PROC DanhSachLoaiSPTheo_cuahang (@mach nvarchar(15))
AS 
	SELECT * FROM LOAISP WHERE MACH=@mach and TRANGTHAI = 0 

EXEC DanhSachNCCTheo_cuahang 'HL'
----------danhsachncctheo_sdt-----------
CREATE PROC DanhSachNCCTheo_SDT (@sdt nvarchar(max))
AS 
	SELECT * FROM NHACUNGCAP WHERE SDT=@sdt
---SINH MA NHA CUNG CAP------
Create PROC sinhma_mancclonnhat(@mach nvarchar(15))
AS
  SELECT TOP 1 mancc 
  FROM NHACUNGCAP where MACH = @mach
  ORDER BY mancc DESC

exec sinhma_mancclonnhat 'HL'
--- TÌM KIẾM NHÀ CUNG CẤP --------------
CREATE PROC timKiemNCC (@giatri nvarchar(100))
as
	select * from NHACUNGCAP where 
	(MANCC like N'%' + @giatri + '%' )
	or (TENNCC like N'%' + @giatri + '%')
	or (DIACHI like N'%' + @giatri + '%')
	or (SDT like N'%' + @giatri + '%')
	or (EMAIL like N'%' + @giatri + '%')
	or (MACH like N'%' + @giatri + '%')
	or (MALOAI like N'%' + @giatri + '%')

exec timKiemNCC N'NCC001'

---INSERT NHÀ CUNG CẤP----------------------
USE QL_CUAHANGNOITHAT
CREATE PROC insert_NHACUNGCAP(@maNCC varchar(10),@tenNCC nvarchar(50),@diachi nvarchar(50), @sdt nvarchar(50),@email nvarchar(50),@mach varchar(10), @maloai varchar(10))
as 
	INSERT INTO NHACUNGCAP VALUES (@maNCC, @tenNCC, @diachi,@sdt,@email,0,@mach,@maloai)

exec insert_NHACUNGCAP N'MT04',N'Hoàng Kiều',N'Cà Mau',N'0981234567',N'hoangkieu@gmail.com','MT','L011'
--UPDATE NHACUNGCAP ----------
SELECT * FROM DANHMUCSP

CREATE PROC update_NHACUNGCAP(@maNCC varchar(10),@tenNCC nvarchar(50),@diachi nvarchar(50), @sdt nvarchar(50),@email nvarchar(50),@mach varchar(10),@maloai varchar(10))
as 
	UPDATE NHACUNGCAP
	SET TENNCC = @tenNCC, DIACHI = @diachi,SDT = @sdt
	WHERE MANCC = @maNCC

exec update_NHACUNGCAP 'NCC001',N'Mạnh Cường',N'Đà Lạt',N'0987213123',N'manhcuon@gmail.com'
-------------Delete NHACUNGCAP ---------------------
CREATE PROC delete_NHACUNGCAP(@maNCC varchar(10))
as 
	UPDATE NHACUNGCAP
	SET TRANGTHAI = 1  
	WHERE MANCC = @maNCC


exec delete_NHACUNGCAP 'HL01'
-----------Tìm kiếm khách hàng -------------------


---Tìm khách hàng theo số điện thoại


CREATE PROC DanhSachKHTheo_SDT (@sdt nvarchar(max))
AS 
	SELECT * FROM KHACHHANG WHERE SDT=@sdt

EXEC DanhSachKHTheo_SDT '0345698709'


CREATE PROC timKiemKhachHang (@giatri nvarchar(100))
as
	select * from KHACHHANG where 
	(MAKH like N'%' + @giatri + '%' )
	or (TENKH like N'%' + @giatri + '%')
	or (DIACHI like N'%' + @giatri + '%')
	or (SDT like N'%' + @giatri + '%')
	or (EMAIL like N'%' + @giatri + '%')

exec timKiemKhachHang N'KH001'
----SINH MA KHÁCH HANG ---
CREATE PROCEDURE sinhma_makhlonnhat
AS
  SELECT TOP 1 MAKH 
  FROM KHACHHANG 
  ORDER BY MAKH DESC;
---INSERT khách hàng ----------------------
USE QL_CUAHANGNOITHAT
CREATE PROC insert_KhachHang(@maKH varchar(10),@tenKH nvarchar(50),@diachi nvarchar(50), @sdt nvarchar(50),@email nvarchar(50))
as 
	INSERT INTO KHACHHANG VALUES (@maKH, @tenKH, @diachi,@sdt,@email)

exec insert_KhachHang N'KH099',N'Phạm Minh Tiến',N'Đồng Tháp',N'0981234117',N'minhtien@gmail.com'
--UPDATE Khách hàng ----------
SELECT * FROM DANHMUCSP

CREATE PROC update_KhachHang(@maKH varchar(10),@tenKH nvarchar(50),@diachi nvarchar(50), @sdt nvarchar(50),@email nvarchar(50))
as 
	UPDATE KHACHHANG
	SET TENKH = @tenKH, DIACHI = @diachi,SDT = @sdt
	WHERE MAKH = @maKH

exec update_KhachHang 'KH001',N'Nguyễn Phương Uyên',N'Tây Ninh',N'0987219999',N'phuonguyen@gmail.com'
---SINH MA NHAN VIEN------
CREATE PROCEDURE sinhma_manvlonnhat
AS
  SELECT TOP 1 manv 
  FROM NHANVIEN 
  ORDER BY manv DESC;
--- TÌM KIẾM NHÂN VIÊN --------------
CREATE PROC timKiemNhanVien (@giatri nvarchar(100))
as
	select * from NHANVIEN where 
	(MANV like N'%' + @giatri + '%' )
	or (TENNV like N'%' + @giatri + '%')
	or (TENTAIKHOAN like N'%' + @giatri + '%')
	or (SDT like N'%' + @giatri + '%')
	or (EMAIL like N'%' + @giatri + '%')

exec timKiemNhanVien N'NV001'

---INSERT Nhân Viên----------------------
USE QL_CUAHANGNOITHAT

CREATE PROCEDURE insert_NhanVien (
    @MaNV NVARCHAR(MAX),
    @TenNV NVARCHAR(MAX),
    @TenTK NVARCHAR(MAX),
    @ChucVu NVARCHAR(MAX),
    @SDT NVARCHAR(MAX),
    @Email NVARCHAR(MAX),
    @MatKhau NVARCHAR(MAX),
    @HinhAnh NVARCHAR(MAX) -- Tham số mới cho đường dẫn ảnh
) AS
BEGIN
    -- Thân stored procedure
    INSERT INTO NHANVIEN(MaNV, TenNV, TENTAIKHOAN, ChucVu, SDT, Email, MatKhau, HinhAnh)
    VALUES (@MaNV, @TenNV, @TenTK, @ChucVu, @SDT, @Email, @MatKhau, @HinhAnh);
END;
---update nhan vien 
CREATE PROCEDURE update_NhanVien (
    @MaNV NVARCHAR(MAX),
    @TenNV NVARCHAR(MAX),
    @TenTK NVARCHAR(MAX),
    @ChucVu NVARCHAR(MAX),
    @SDT NVARCHAR(MAX),
    @Email NVARCHAR(MAX),
    @MatKhau NVARCHAR(MAX),
    @HinhAnh NVARCHAR(MAX)
) AS
BEGIN
    UPDATE NHANVIEN
    SET 
        TenNV = @TenNV,
        TENTAIKHOAN = @TenTK,
        ChucVu = @ChucVu,
        SDT = @SDT,
        Email = @Email,
        MatKhau = @MatKhau,
        HinhAnh = @HinhAnh
    WHERE 
        MaNV = @MaNV;
END;
--Xoa nhan vien
CREATE PROC delete_nhanvien(@maNV varchar(10))
as 
	UPDATE NHANVIEN
	SET TRANGTHAI = 1  
	WHERE MANV = @maNV
--UPDATE NHACUNGCAP ----------
SELECT * FROM DANHMUCSP

--CREATE PROC update_NHACUNGCAP(@maNCC varchar(10),@tenNCC nvarchar(50),@diachi nvarchar(50), @sdt nvarchar(50),@email nvarchar(50))
--as 
--	UPDATE NHACUNGCAP
--	SET TENNCC = @tenNCC, DIACHI = @diachi,SDT = @sdt
--	WHERE MANCC = @maNCC

--exec update_NHACUNGCAP 'NCC001',N'Mạnh Cường',N'Đà Lạt',N'0987213123',N'manhcuon@gmail.com'
-------------Delete DanhMuc ---------------------
--CREATE PROC delete_NHACUNGCAP(@maNCC varchar(10))
--as 
--	UPDATE NHACUNGCAP
--	SET TRANGTHAI = 1  
--	WHERE MANCC = @maNCC


exec delete_NHACUNGCAP 'NCC008'

---KHUYEN MAI

---SINH MA KHUYEN MA------

DROP PROC sinhma_makmlonnhat

CREATE PROC sinhma_makmlonnhat AS
BEGIN
	IF ((SELECT COUNT(*) FROM KHUYENMAI) = 0)
		SELECT  'KM001' MAHD
	ELSE
		SELECT TOP 1 MAKM FROM KHUYENMAI ORDER BY MAKM DESC
END
--- TÌM KIẾM KHUYEN MAI--------------


CREATE PROC timKiemKhuyenMai (@giatri nvarchar(100))
as
    select KM. *,CTKM.*
    FROM KHUYENMAI KM, CT_KHUYENMAI CTKM
    WHERE KM.MAKM = CTKM.MAKM
    AND (KM.MAKM = @giatri)
    OR (KM.TENKM like N'%' + @giatri + '%')
    OR (CTKM.MASP = @giatri)
    AND KM.TRANGTHAI = 0 AND CTKM.TRANGTHAI = 0

--Lấy GIAKHUYENMAI của khuyến mãi và sản phẩm
CREATE PROCEDURE layGiaKM (@maKM nvarchar(10), @maSP nvarchar(10))
AS
  SELECT GIAKM FROM CT_KHUYENMAI WHERE MAKM = @maKM AND MASP = @maSP;

exec layGiaKM 'KM06','SP003'



CREATE PROCEDURE insert_KhuyenMai
    @maKM VARCHAR(10),
    @tenKM NVARCHAR(50),
    @ptkhuyenmai FLOAT,
    @ngayBD DATE,
    @ngayKT DATE,
    @maSP VARCHAR(10),
    @giaKM DECIMAL(18, 0),
    @soLuongKM INT
AS
BEGIN
    INSERT INTO KHUYENMAI (MAKM, TENKM, PHANTRAMKM, NGAYBATDAU, NGAYKETHUC,TRANGTHAI)
    VALUES (@maKM, @tenKM, @ptkhuyenmai, @ngayBD, @ngayKT,0);

    INSERT INTO CT_KHUYENMAI (MAKM, MASP, GIAKM, SOLUONGKM,TRANGTHAI)
    VALUES (@maKM, @maSP, @giaKM, @soLuongKM, 0);
END;

SELECT * FROM CT_KHUYENMAI

exec insert_KhuyenMai 'KM999','KM 99%',0.99,'2024-02-10','2024-02-15','SP002',99000,2000

--Procedure sửa khuyến mãi
CREATE PROCEDURE update_KhuyenMai
    @maKM VARCHAR(10),
    @tenKM NVARCHAR(50),
    @ptkhuyenmai FLOAT,
    @ngayBD DATE,
    @ngayKT DATE,
    @maSP VARCHAR(10),
    @giaKM DECIMAL(18, 0),
    @soLuongKM INT
AS
BEGIN
    UPDATE KHUYENMAI
    SET TENKM = @tenKM, PHANTRAMKM = @ptkhuyenmai, NGAYBATDAU = @ngayBD, NGAYKETHUC = @ngayKT
    WHERE MAKM = @maKM;

    UPDATE CT_KHUYENMAI
    SET GIAKM = @giaKM, SOLUONGKM = @soLuongKM
    WHERE MAKM = @maKM AND MASP = @maSP;
END;

SELECT * FROM CT_KHUYENMAI

exec update_KhuyenMai 'KM999','KM 99%',0.99,'2024-02-10','2024-02-15','SP002',99000,2000

--Procedure xóa khuyến mãi
CREATE PROCEDURE delete_KhuyenMai
    @maKM VARCHAR(10)
AS
BEGIN

	UPDATE CT_KHUYENMAI
	SET TRANGTHAI = 1
	WHERE MAKM = @maKM;

    UPDATE KHUYENMAI
    SET TRANGTHAI = 1
    WHERE MAKM = @maKM;

   
END;

SELECT * FROM KHUYENMAI

exec delete_KhuyenMai 'KM100'
-----------SINH MA DANH MUC ----- 
--ALTER PROC madmlonnhat_theosp(@sanpham nvarchar(15))
--as
--	select top 1 madanhmuc from DANHMUCSP where SANPHAM = @sanpham order by MADANHMUC desc

--exec madmlonnhat_theosp 'BN'
CREATE PROC timkiemHoaDon (@giatri nvarchar(100))
AS
    SELECT DISTINCT HOADON.MAHD, HOADON.MANV, HOADON.MAKH, HOADON.NGAYLAP, CT_HOADON.MASP, CT_HOADON.SOLUONG, CT_HOADON.GIABAN 
    FROM HOADON
    INNER JOIN CT_HOADON ON HOADON.MAHD = CT_HOADON.MAHD
    WHERE (CT_HOADON.MASP LIKE N'%' + @giatri + '%')
        OR (HOADON.MAHD LIKE N'%' + @giatri + '%')
        OR (HOADON.MANV LIKE N'%' + @giatri + '%')
        OR (HOADON.MAKH LIKE N'%' + @giatri + '%')
        OR (CT_HOADON.GIABAN LIKE N'%' + @giatri + '%')
        OR (CT_HOADON.SOLUONG LIKE N'%' + @giatri + '%')
        OR (HOADON.NGAYLAP LIKE N'%' + @giatri + '%')

exec timkiemHoaDon N'SP001'
---HOA DON


CREATE PROC HOADONLONHAT AS
BEGIN
	IF ((SELECT COUNT(*) FROM HOADON) = 0)
		SELECT  'HD0001' MAHD
	ELSE
		SELECT TOP 1 MAHD FROM HOADON ORDER BY MAHD DESC
END

EXEC HOADONLONHAT

--Insert hoadon va ct_hoadon
drop PROC insert_HoaDon
CREATE PROCEDURE insert_HoaDon
    @maHD VARCHAR(50),
    @maNV VARCHAR(10),
    @maKH VARCHAR(10),
    @ngayLap DATETIME,
	@thanhtien MONEY,
    @maSP VARCHAR(10),
    @soLuong INT,
    @giaBan DECIMAL(18, 0)
	
AS
BEGIN
    INSERT INTO HOADON (MAHD, MANV, MAKH, NGAYLAP, THANHTIEN ,TRANGTHAI)
    VALUES (@maHD, @maNV, @maKH, @ngayLap,@thanhtien,0);

    INSERT INTO CT_HOADON (MAHD, MASP, SOLUONG, GIABAN,TRANGTHAI)
	VALUES (@maHD, @maSP, @soLuong, @giaBan,0);
END;

EXEC insert_HoaDon 'HD0001','NV002','KH001','2024-05-06',24000,'SP003',2,3400000

--Procedure sửa hóa đơn
CREATE PROCEDURE update_HoaDon
    @maHD VARCHAR(50),
    @maNV VARCHAR(10),
    @maKH VARCHAR(10),
    @ngayLap DATETIME,
	@thanhtien MONEY,
    @maSP VARCHAR(10),
    @soLuong INT,
    @giaBan DECIMAL(18, 0)
AS
BEGIN
    UPDATE CT_HOADON
    SET SOLUONG = @soLuong, GIABAN = @giaBan
    WHERE MAHD = @maHD AND MASP = @maSP;

    UPDATE HOADON
    SET MANV = @maNV, MAKH = @maKH, NGAYLAP = @ngayLap, THANHTIEN = @thanhtien
    WHERE MAHD = @maHD;
    
END;

exec update_HoaDon 'HD0001','NV002','KH001','2024-05-06',50000,'SP003',2,3400000

--Procedure xóa hóa đơn
CREATE PROCEDURE delete_HoaDon
    @maHD VARCHAR(50)
AS
BEGIN
    UPDATE CT_HOADON
    SET TRANGTHAI = 1
    WHERE MAHD = @maHD;

    UPDATE HOADON
    SET TRANGTHAI = 1
    WHERE MAHD = @maHD;
END;

--SELECT CT_HOADON.MAHD, CT_HOADON.MASP, SANPHAM.TENSP, CT_HOADON.GIABAN, CT_HOADON.SOLUONG, HOADON.MAKH, HOADON.MANV, HOADON.NGAYLAP, NHANVIEN.TENNV FROM CT_HOADON JOIN SANPHAM ON CT_HOADON.MASP = SANPHAM.MASP JOIN HOADON ON CT_HOADON.MAHD = HOADON.MAHD JOIN NHANVIEN ON HOADON.MANV = NHANVIEN.MANV JOIN KHACHHANG ON HOADON.MAKH = KHACHHANG.MAKH WHERE HOADON.TRANGTHAI = 0 AND CT_HOADON.TRANGTHAI = 0

--SELECT CT_HOADON.MAHD, CT_HOADON.MASP, SANPHAM.TENSP, CT_HOADON.GIABAN, CT_HOADON.SOLUONG, HOADON.MAKH, HOADON.MANV, HOADON.NGAYLAP, NHANVIEN.TENNV FROM CT_HOADON JOIN SANPHAM ON CT_HOADON.MASP = SANPHAM.MASP JOIN HOADON ON CT_HOADON.MAHD = HOADON.MAHD JOIN NHANVIEN ON HOADON.MANV = NHANVIEN.MANV JOIN KHACHHANG ON HOADON.MAKH = KHACHHANG.MAKH

-----Phieu nhap -------------
CREATE PROC timkiemPhieuNhap (@giatri nvarchar(100))
AS
    SELECT DISTINCT PHIEUNHAP.MAPHIEUNHAP, PHIEUNHAP.MANV, PHIEUNHAP.MANCC, PHIEUNHAP.NGAYNHAP, CT_PHIEUNHAP.SOLUONGNHAP, CT_PHIEUNHAP.DONGIANHAP,CT_PHIEUNHAP.MASP 
    FROM PHIEUNHAP
    INNER JOIN CT_PHIEUNHAP ON PHIEUNHAP.MAPHIEUNHAP = CT_PHIEUNHAP.MAPHIEUNHAP
    WHERE (CT_PHIEUNHAP.MASP LIKE N'%' + @giatri + '%')
        OR (PHIEUNHAP.MAPHIEUNHAP LIKE N'%' + @giatri + '%')
        OR (PHIEUNHAP.MANV LIKE N'%' + @giatri + '%')
        OR (PHIEUNHAP.MANCC LIKE N'%' + @giatri + '%')
        OR (CT_PHIEUNHAP.DONGIANHAP LIKE N'%' + @giatri + '%')
        OR (CT_PHIEUNHAP.SOLUONGNHAP LIKE N'%' + @giatri + '%')
        OR (PHIEUNHAP.NGAYNHAP LIKE N'%' + @giatri + '%')

exec timkiemPhieuNhap N'SP001'
---------phieunhap_sinhma----------
CREATE PROC PHIEUNHAPLONHAT AS
BEGIN
	IF ((SELECT COUNT(*) FROM PHIEUNHAP) = 0)
		SELECT  'PN0001' MAPHIEUNHAP
	ELSE
		SELECT TOP 1 MAPHIEUNHAP FROM PHIEUNHAP ORDER BY MAPHIEUNHAP DESC
END

EXEC HOADONLONHAT

--Insert phieunhap va ct_phieunhap
CREATE PROCEDURE insert_phieunhap
    @maphieunhap VARCHAR(50),
    @maNV VARCHAR(10),
    @maNCC VARCHAR(10),
    @ngayLap DATETIME,
    @maSP VARCHAR(10),
    @soLuong INT,
    @giaBan DECIMAL(18, 0)
AS
BEGIN
    INSERT INTO PHIEUNHAP(MAPHIEUNHAP, MANV, MANCC,NGAYNHAP,TRANGTHAI)
    VALUES (@maphieunhap, @maNV, @maNCC,@ngayLap,0);

    INSERT INTO CT_PHIEUNHAP(MAPHIEUNHAP, MASP, SOLUONGNHAP, DONGIANHAP,TRANGTHAI)
    VALUES (@maphieunhap, @maSP, @soLuong, @giaBan,0);
END;

EXEC insert_phieunhap'PN0005','NV001','HL01','2023-12-06','SP001',2,3400000

--Procedure sửa phieu nhap
CREATE PROCEDURE update_PhieuNhap
    @maphieuhap VARCHAR(50),
    @maNV VARCHAR(10),
    @maKH VARCHAR(10),
    @ngayLap DATETIME,
    @maSP VARCHAR(10),
    @soLuong INT,
    @giaBan DECIMAL(18, 0)
AS
BEGIN
    UPDATE CT_PHIEUNHAP
    SET SOLUONGNHAP = @soLuong, DONGIANHAP = @giaBan
    WHERE MAPHIEUNHAP = @maphieuhap AND MASP = @maSP;

    UPDATE PHIEUNHAP
    SET MANV = @maNV, MANCC = @maKH, NGAYNHAP = @ngayLap
    WHERE MAPHIEUNHAP = @maphieuhap;
    
END;

--Procedure xóa phieu nhap
CREATE PROCEDURE delete_phieunhap
    @maphieunhap VARCHAR(50)
AS
BEGIN
    UPDATE CT_PHIEUNHAP
    SET TRANGTHAI = 1
    WHERE MAPHIEUNHAP = @maphieunhap;

    UPDATE PHIEUNHAP
    SET TRANGTHAI = 1
    WHERE MAPHIEUNHAP = @maphieunhap;
END;

