/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author Phan Tran Minh Tam
 */
public class NhanVien {
        public String MaNV;
	public String TenNV ;
	public String TenTK;
	public String ChucVu ;
	public String SDT;
	public String Email;
	public String MatKhau;
	public String HinhAnh;
        
        public NhanVien(){}

    public NhanVien(String MaNV, String TenNV, String TenTK, String ChucVu, String SDT, String Email, String MatKhau, String HinhAnh) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.TenTK = TenTK;
        this.ChucVu = ChucVu;
        this.SDT = SDT;
        this.Email = Email;
        this.MatKhau = MatKhau;
        this.HinhAnh = HinhAnh;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String TenTK) {
        this.TenTK = TenTK;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }
        
        
}
