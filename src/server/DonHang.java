package server;

import java.io.Serializable;
import java.sql.Date;

public class DonHang implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int maDonHang;
	private String tenDonHang;
	private int maKhachHang;
	private int maNhanVien;
	private Date ngayDatHang;
	private String trangThai;
	
	public DonHang(int maDonHang, String tenDonHang, int maKhachHang, int maNhanVien, Date ngayDatHang,
			String trangThai) {
		super();
		this.maDonHang = maDonHang;
		this.tenDonHang = tenDonHang;
		this.maKhachHang = maKhachHang;
		this.maNhanVien = maNhanVien;
		this.ngayDatHang = ngayDatHang;
		this.trangThai = trangThai;
	}
	

	public int getMaDonHang() {
		return maDonHang;
	}


	public void setMaDonHang(int maDonHang) {
		this.maDonHang = maDonHang;
	}


	public String getTenDonHang() {
		return tenDonHang;
	}


	public void setTenDonHang(String tenDonHang) {
		this.tenDonHang = tenDonHang;
	}


	public int getMaKhachHang() {
		return maKhachHang;
	}


	public void setMaKhachHang(int maKhachHang) {
		this.maKhachHang = maKhachHang;
	}


	public int getMaNhanVien() {
		return maNhanVien;
	}


	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}


	public Date getNgayDatHang() {
		return ngayDatHang;
	}


	public void setNgayDatHang(Date ngayDatHang) {
		this.ngayDatHang = ngayDatHang;
	}


	public String getTrangThai() {
		return trangThai;
	}


	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}


	@Override
	public String toString() {
		return "DonHang [maDonHang=" + maDonHang + ", tenDonHang=" + tenDonHang + ", maKhachHang=" + maKhachHang
				+ ", maNhanVien=" + maNhanVien + ", ngayDatHang=" + ngayDatHang + ", trangThai=" + trangThai + "]";
	}


	
	
	
	
	
	
	

}
