package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplQLSP extends UnicastRemoteObject implements InterfaceQLSP {

	private Connection connection;
	
	protected ImplQLSP() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		connection = ConnectDatabaseJDBC.getConnection();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean themSanPham(SanPham sanPham) throws RemoteException {
		String sql = "INSERT INTO sanpham (TenSanPham, Gia, MoTa, SoLuongTon) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sanPham.getTenSanPham());
            statement.setDouble(2, sanPham.getGia());
            statement.setString(3, sanPham.getMoTa());
            statement.setInt(4, sanPham.getSoLuong());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public boolean xoaSanPham(int maSanPham) throws RemoteException {
		String sql = "DELETE FROM sanpham WHERE MaSanPham = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, maSanPham);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public boolean capNhatSanPham(SanPham sanPham) throws RemoteException {
		String sql = "UPDATE sanpham SET TenSanPham = ?, Gia = ?, MoTa = ?, SoLuongTon = ? WHERE MaSanPham = ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, sanPham.getTenSanPham());
	        statement.setDouble(2, sanPham.getGia());
	        statement.setString(3, sanPham.getMoTa());
	        statement.setInt(4, sanPham.getSoLuong());
	        statement.setInt(5, sanPham.getMaSanPham());
	        int rowsUpdated = statement.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean timKiemSanPham(String tenSanPham) throws RemoteException {
		String sql = "SELECT * FROM sanpham WHERE TenSanPham LIKE ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, "%" + tenSanPham + "%");
	        ResultSet resultSet = statement.executeQuery();
	        List<SanPham> sanPhamList = new ArrayList<>();
	        while (resultSet.next()) {
	            int maSanPham = resultSet.getInt("MaSanPham");
	            String tenSP = resultSet.getString("TenSanPham");
	            double gia = resultSet.getDouble("Gia");
	            String moTa = resultSet.getString("MoTa");
	            int soLuong = resultSet.getInt("SoLuongTon");
	            SanPham sp = new SanPham(maSanPham, tenSP, gia, moTa, soLuong);
	            sanPhamList.add(sp);
	        }
	        // In kết quả tìm kiếm
	        for (SanPham sp : sanPhamList) {
	            System.out.println(sp);
	        }
	        return !sanPhamList.isEmpty();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public List<SanPham> xemSanPham() throws RemoteException {
		List<SanPham> sanPhamList = new ArrayList<>();
	    String sql = "SELECT * FROM sanpham";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            int maSanPham = resultSet.getInt("MaSanPham");
	            String tenSP = resultSet.getString("TenSanPham");
	            double gia = resultSet.getDouble("Gia");
	            String moTa = resultSet.getString("MoTa");
	            int soLuong = resultSet.getInt("SoLuongTon");
	            SanPham sp = new SanPham(maSanPham, tenSP, gia, moTa, soLuong);
	            sanPhamList.add(sp);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return sanPhamList;
	}

	@Override
	public boolean themNhanVien(NhanVien nhanVien) throws RemoteException {
	    String sql = "INSERT INTO nhanvien (TenNhanVien, ChucVu, Luong, NgayBatDau) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, nhanVien.getTenNhanVien());
	        statement.setString(2, nhanVien.getChucVu());
	        statement.setDouble(3, nhanVien.getLuong());
	        statement.setString(4, nhanVien.getNgayBatDau());
	        int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean xoaNhanVien(int maNhanVien) throws RemoteException {
	    String sql = "DELETE FROM nhanvien WHERE MaNhanVien = ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setInt(1, maNhanVien);
	        int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean capNhatNhanVien(NhanVien nhanVien) throws RemoteException {
	    String sql = "UPDATE nhanvien SET TenNhanVien = ?, ChucVu = ?, Luong = ?, NgayBatDau = ? WHERE MaNhanVien = ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, nhanVien.getTenNhanVien());
	        statement.setString(2, nhanVien.getChucVu());
	        statement.setDouble(3, nhanVien.getLuong());
	        statement.setString(4, nhanVien.getNgayBatDau());
	        statement.setInt(5, nhanVien.getMaNhanVien());
	        int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean timKiemNhanVien(String tenNhanVien) throws RemoteException {
		String sql = "SELECT * FROM nhanvien WHERE TenNhanVien LIKE ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, "%" + tenNhanVien + "%");
	        ResultSet resultSet = statement.executeQuery();
	        List<NhanVien> nhanVienList = new ArrayList<>();
	        while (resultSet.next()) {
	            int maNhanVien = resultSet.getInt("MaNhanVien");
	            String tenNV = resultSet.getString("TenNhanVien");
	            String chucVu = resultSet.getString("ChucVu");
	            double luong = resultSet.getDouble("Luong");
	            String ngayBatDau = resultSet.getString("NgayBatDau");
	            NhanVien nv = new NhanVien(maNhanVien, tenNV, chucVu, luong, ngayBatDau);
	            nhanVienList.add(nv);
	        }
	        // In kết quả tìm kiếm
	        for (NhanVien nv : nhanVienList) {
	            System.out.println(nv);
	        }
	        return !nhanVienList.isEmpty();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public List<NhanVien> xemNhanVien() throws RemoteException {
	    List<NhanVien> nhanVienList = new ArrayList<>();
	    String sql = "SELECT * FROM nhanvien";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            int maNhanVien = resultSet.getInt("MaNhanVien");
	            String tenNV = resultSet.getString("TenNhanVien");
	            String chucVu = resultSet.getString("ChucVu");
	            double luong = resultSet.getDouble("Luong");
	            String ngayBatDau = resultSet.getString("NgayBatDau");
	            NhanVien nv = new NhanVien(maNhanVien, tenNV, chucVu, luong, ngayBatDau);
	            nhanVienList.add(nv);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return nhanVienList;
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
