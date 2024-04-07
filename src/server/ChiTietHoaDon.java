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
	private double donGia;
	
	public ChiTietHoaDon(int id, int maDonHang, int maSanPham, int soLuong, double donGia) {
		super();
		this.id = id;
		this.maDonHang = maDonHang;
		this.maSanPham = maSanPham;
		this.soLuong = soLuong;
		this.donGia = donGia;
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

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [id=" + id + ", maDonHang=" + maDonHang + ", maSanPham=" + maSanPham + ", soLuong="
				+ soLuong + ", donGia=" + donGia + "]";
	}
	
	

}
