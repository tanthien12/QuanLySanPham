package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ImplQLSP extends UnicastRemoteObject implements InterfaceQLSP {

	protected ImplQLSP() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean themSanPham(SanPham sanPham) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean xoaSanPham(int maSanPham) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean capNhatSanPham(SanPham sanPham) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean timKiemSanPham(String tenSanPham) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SanPham> xemSanPham() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean themNhanVien(NhanVien nhanVien) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean xoaNhanVien(int maNhanVien) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean capNhatNhanVien(NhanVien nhanVien) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean timKiemNhanVien(String tenNhanVien) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<NhanVien> xemNhanVien() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean themKhachHang(KhachHang khachHang) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean capNhatKhachHang(KhachHang khachHang) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean timKiemKhachHang(String tenKhachHang) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<KhachHang> xemKhachHang() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean themDonHang(DonHang donHang) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean xoaDonHang(int maDonHang) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean capNhatDonHang(DonHang donHang) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean timKiemDonHang(String tenDonHang) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DonHang> xemDonHang() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChiTietHoaDon> xemSachDonHang() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean taoTaiKhoan(TaiKhoan taiKhoan) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	

}
