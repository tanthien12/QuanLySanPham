package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface InterfaceQLSP extends Remote {
	//San pham
	boolean themSanPham(SanPham sanPham) throws RemoteException;
	boolean xoaSanPham(int maSanPham) throws RemoteException;
	boolean capNhatSanPham(SanPham sanPham) throws RemoteException;
	boolean timKiemSanPham(String tenSanPham) throws RemoteException;
	List<SanPham> xemSanPham() throws RemoteException;
	
	//Nhan vien
	boolean themNhanVien(NhanVien nhanVien) throws RemoteException;
	boolean xoaNhanVien(int maNhanVien) throws RemoteException;
	boolean capNhatNhanVien(NhanVien nhanVien) throws RemoteException;
	boolean timKiemNhanVien(String tenNhanVien) throws RemoteException;
	List<NhanVien> xemNhanVien() throws RemoteException;
	
	//Khach hang
	boolean themKhachHang(KhachHang khachHang) throws RemoteException;
	boolean capNhatKhachHang(KhachHang khachHang) throws RemoteException;
	boolean timKiemKhachHang(String tenKhachHang) throws RemoteException;
	List<KhachHang> xemKhachHang() throws RemoteException;
	
	//Don hang
	boolean themDonHang(DonHang donHang) throws RemoteException;
	boolean xoaDonHang(int maDonHang) throws RemoteException;
	boolean capNhatDonHang(DonHang donHang) throws RemoteException;
	boolean timKiemDonHang(String tenDonHang) throws RemoteException;
	List<DonHang> xemDonHang() throws RemoteException;
	
	//Chi tiet don hang
	List<ChiTietHoaDon> xemSachDonHang() throws RemoteException;
	
	// Tai khoan
	boolean taoTaiKhoan(TaiKhoan taiKhoan) throws RemoteException;
	

}
