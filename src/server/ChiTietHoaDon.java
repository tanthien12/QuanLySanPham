package server;

import java.io.Serializable;

public class ChiTietHoaDon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int maDonHang;
	private int maSanPham;
	private int soLuong;
	private double tongTien;
	
	
	public ChiTietHoaDon(int id, int maDonHang, int maSanPham, int soLuong, double tongTien) {
		super();
		this.id = id;
		this.maDonHang = maDonHang;
		this.maSanPham = maSanPham;
		this.soLuong = soLuong;
		this.tongTien = tongTien;
	}
	public ChiTietHoaDon(int maDonHang, int maSanPham, int soLuong, double tongTien) {
		super();
		this.maDonHang = maDonHang;
		this.maSanPham = maSanPham;
		this.soLuong = soLuong;
		this.tongTien = tongTien;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMaDonHang() {
		return maDonHang;
	}
	public void setMaDonHang(int maDonHang) {
		this.maDonHang = maDonHang;
	}
	public int getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(int maSanPham) {
		this.maSanPham = maSanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	@Override
	public String toString() {
		return "ChiTietHoaDon [id=" + id + ", maDonHang=" + maDonHang + ", maSanPham=" + maSanPham + ", soLuong="
				+ soLuong + ", tongTien=" + tongTien + "]";
	}
	
	
	
	

}
