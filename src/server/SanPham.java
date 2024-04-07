package server;

import java.io.Serializable;

public class SanPham implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int maSanPham;
	private String tenSanPham;
	private double gia;
	private String moTa;
	private int soLuong;
	
	public SanPham(int maSanPham, String tenSanPham, double gia, String moTa, int soLuong) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.gia = gia;
		this.moTa = moTa;
		this.soLuong = soLuong;
	}

	public int getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(int maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Override
	public String toString() {
		return "SanPham [maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", gia=" + gia + ", moTa=" + moTa
				+ ", soLuong=" + soLuong + "]";
	}
	
	
	

}
