package server;

import java.rmi.RemoteException;


import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
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
		String sql = "INSERT INTO sanpham (MaSanPham, TenSanPham, Gia, MoTa, SoLuongTon) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
        	statement.setInt(1, sanPham.getMaSanPham());
            statement.setString(2, sanPham.getTenSanPham());
            statement.setDouble(3, sanPham.getGia());
            statement.setString(4, sanPham.getMoTa());
            statement.setInt(5, sanPham.getSoLuong());
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
	public List<SanPham> timKiemSanPham(String tenSanPham) throws RemoteException {
		List<SanPham> sanPhamList = new ArrayList<>();
	    String sql = "SELECT * FROM sanpham WHERE TenSanPham LIKE ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, "%" + tenSanPham + "%");
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
	    String sql = "INSERT INTO nhanvien (MaNhanVien,TenNhanVien, ChucVu, Luong, NgayBatDau) VALUES (?, ?, ?, ?, ?)";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	    	statement.setInt(1, nhanVien.getMaNhanVien());
	        statement.setString(2, nhanVien.getTenNhanVien());
	        statement.setString(3, nhanVien.getChucVu());
	        statement.setDouble(4, nhanVien.getLuong());
	        statement.setObject(5, nhanVien.getNgayBatDau());
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
	        statement.setObject(4, nhanVien.getNgayBatDau());
	        statement.setInt(5, nhanVien.getMaNhanVien());
	        int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public List<NhanVien> timKiemNhanVien(String tenNhanVien) throws RemoteException {
		List<NhanVien> nhanVienList = new ArrayList<>();
	    String sql = "SELECT * FROM nhanvien WHERE TenNhanVien LIKE ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, "%" + tenNhanVien + "%");
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int maNhanVien = resultSet.getInt("MaNhanVien");
	            String tenNV = resultSet.getString("TenNhanVien");
	            String chucVu = resultSet.getString("ChucVu");
	            double luong = resultSet.getDouble("Luong");
	            Date ngayBatDau = resultSet.getDate("NgayBatDau");
	            NhanVien nv = new NhanVien(maNhanVien, tenNV, chucVu, luong, ngayBatDau);
	            nhanVienList.add(nv);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return nhanVienList;
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
	            Date ngayBatDau = resultSet.getDate("NgayBatDau");
	            NhanVien nv = new NhanVien(maNhanVien, tenNV, chucVu, luong, ngayBatDau);
	            nhanVienList.add(nv);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return nhanVienList;
	}

	@Override
	public boolean themDonHang(DonHang donHang) throws RemoteException {
		String sql = "INSERT INTO donhang (MaDonHang, TenDonHang, MaKhachHang, MaNhanVien, NgayDatHang, TrangThai) VALUES (?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	    	statement.setInt(1, donHang.getMaDonHang());
	    	statement.setString(2, donHang.getTenDonHang());
	        statement.setInt(3, donHang.getMaKhachHang());
	        statement.setInt(4, donHang.getMaNhanVien());
	        statement.setDate(5, donHang.getNgayDatHang());
	        statement.setString(6, donHang.getTrangThai());
	        int rowsInserted = statement.executeUpdate();
	        return rowsInserted > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean xoaDonHang(int maDonHang) throws RemoteException {
	    String sql = "DELETE FROM donhang WHERE MaDonHang = ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setInt(1, maDonHang);
	        int rowsDeleted = statement.executeUpdate();
	        return rowsDeleted > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean capNhatDonHang(DonHang donHang) throws RemoteException {
		String sql = "UPDATE donhang SET TenDonHang = ?, MaKhachHang = ?, MaNhanVien = ?, NgayDatHang = ?, TrangThai = ? WHERE MaDonHang = ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	    	
	    	statement.setString(1, donHang.getTenDonHang());
	        statement.setInt(2, donHang.getMaKhachHang());
	        statement.setInt(3, donHang.getMaNhanVien());
	        statement.setDate(4, donHang.getNgayDatHang());
	        statement.setString(5, donHang.getTrangThai());
	        statement.setInt(6, donHang.getMaDonHang());
	        int rowsUpdated = statement.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public List<DonHang> timKiemDonHang(String tenDonHang) throws RemoteException {
		String sql = "SELECT * FROM donhang WHERE TenDonHang LIKE ?";
	    List<DonHang> donHangList = new ArrayList<>();
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, "%" + tenDonHang + "%");
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            int maDonHang = resultSet.getInt("MaDonHang");
	            String tenDonHang1 = resultSet.getString("TenDonHang");
	            int maKhachHang = resultSet.getInt("MaKhachHang");
	            int maNhanVien = resultSet.getInt("MaNhanVien");
	            Date ngayDatHang = resultSet.getDate("NgayDatHang");
	            String trangThai = resultSet.getString("TrangThai");
	            DonHang dh = new DonHang(maDonHang, tenDonHang1, maKhachHang, maNhanVien, ngayDatHang, trangThai);
	            donHangList.add(dh);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return donHangList;
	}

	@Override
	public List<DonHang> xemDonHang() throws RemoteException {
		List<DonHang> donHangList = new ArrayList<>();
	    String sql = "SELECT * FROM donhang";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            int maDonHang = resultSet.getInt("MaDonHang");
	            String tenDonHang = resultSet.getString("TenDonHang");
	            int maKhachHang = resultSet.getInt("MaKhachHang");
	            int maNhanVien = resultSet.getInt("MaNhanVien");
	            Date ngayDatHang = resultSet.getDate("NgayDatHang");
	            String trangThai = resultSet.getString("TrangThai");
	            DonHang dh = new DonHang(maDonHang, tenDonHang, maKhachHang, maNhanVien, ngayDatHang, trangThai);
	            donHangList.add(dh);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return donHangList;
	}

	@Override
	public List<ChiTietHoaDon> xemSachDonHang() throws RemoteException {
		List<ChiTietHoaDon> chiTietList = new ArrayList<>();
	    String sql = "SELECT * FROM chitietdonhang";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            int id = resultSet.getInt("ID");
	            int maDonHang = resultSet.getInt("MaDonHang");
	            int maSanPham = resultSet.getInt("MaSanPham");
	            int soLuong = resultSet.getInt("SoLuong");
	            double tongTien = resultSet.getDouble("TongTien");
	            ChiTietHoaDon chiTiet = new ChiTietHoaDon(id, maDonHang, maSanPham, soLuong, tongTien);
	            chiTietList.add(chiTiet);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return chiTietList;
	}

	@Override
	public boolean taoTaiKhoan(TaiKhoan taiKhoan) throws RemoteException {
		String sql = "INSERT INTO taikhoan (TenDangNhap, MatKhau, VaiTro) VALUES (?, ?, ?)";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, taiKhoan.getTenDangNhap());
	        statement.setString(2, taiKhoan.getMatKhau());
	        statement.setString(3, taiKhoan.getVaiTro());
	        int rowsInserted = statement.executeUpdate();
	        return rowsInserted > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public ChiTietHoaDon timKiemTheoMaDonHang(int maDonHang) throws RemoteException {
		ChiTietHoaDon ctdh = null;
        String sql = "SELECT * FROM chitietdonhang WHERE MaDonHang = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, maDonHang);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int maDonHangResult = resultSet.getInt("MaDonHang");
                int maSanPham = resultSet.getInt("MaSanPham");
                int soLuong = resultSet.getInt("SoLuong");
                double tongTien = resultSet.getDouble("TongTien");
                ctdh = new ChiTietHoaDon(id, maDonHangResult, maSanPham, soLuong, tongTien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ctdh;
    }

	@Override
	public List<ChiTietHoaDon> timKiemTheoMaSanPham(int maSanPham) throws RemoteException {
		 List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();
		    String sql = "SELECT * FROM chitietdonhang WHERE MaSanPham = ?";
		    try (PreparedStatement statement = connection.prepareStatement(sql)) {
		        statement.setInt(1, maSanPham);
		        ResultSet resultSet = statement.executeQuery();
		        while (resultSet.next()) {
		            int id = resultSet.getInt("ID");
		            int maDonHang = resultSet.getInt("MaDonHang");
		            int maSanPhamResult = resultSet.getInt("MaSanPham");
		            int soLuong = resultSet.getInt("SoLuong");
		            double tongTien = resultSet.getDouble("TongTien");
		            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(id, maDonHang, maSanPhamResult, soLuong, tongTien);
		            chiTietHoaDonList.add(chiTietHoaDon);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return chiTietHoaDonList;
	}

	@Override
	public boolean themKhachHang(KhachHang khachHang) throws RemoteException {
		String sql = "INSERT INTO khachhang (TenKhachHang, DiaChi, DienThoai, Email) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, khachHang.getTenKhachHang());
	        statement.setString(2, khachHang.getDiaChi());
	        statement.setString(3, khachHang.getSoDienThoai());
	        statement.setString(4, khachHang.getEmail());
	        int rowsInserted = statement.executeUpdate();
	        return rowsInserted > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean capNhatKhachHang(KhachHang khachHang) throws RemoteException {
		String sql = "UPDATE khachhang SET TenKhachHang = ?, DiaChi = ?, DienThoai = ?, Email = ? WHERE MaKhachHang = ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, khachHang.getTenKhachHang());
	        statement.setString(2, khachHang.getDiaChi());
	        statement.setString(3, khachHang.getSoDienThoai());
	        statement.setString(4, khachHang.getEmail());
	        statement.setInt(5, khachHang.getMaKhachHang());
	        int rowsUpdated = statement.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public List<KhachHang> timKiemKhachHang(String tenKhachHang) throws RemoteException {
		List<KhachHang> khachHangList = new ArrayList<>();
	    String sql = "SELECT * FROM khachhang WHERE TenKhachHang LIKE ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, "%" + tenKhachHang + "%");
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            int maKhachHang = resultSet.getInt("MaKhachHang");
	            String tenKhachHangResult = resultSet.getString("TenKhachHang");
	            String diaChi = resultSet.getString("DiaChi");
	            String dienThoai = resultSet.getString("DienThoai");
	            String email = resultSet.getString("Email");
	            KhachHang khachHang = new KhachHang(maKhachHang, tenKhachHangResult, diaChi, dienThoai, email);
	            khachHangList.add(khachHang);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return khachHangList;
	}

	@Override
	public List<KhachHang> xemKhachHang() throws RemoteException {
		List<KhachHang> khachHangList = new ArrayList<>();
	    String sql = "SELECT * FROM khachhang";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            int maKhachHang = resultSet.getInt("MaKhachHang");
	            String tenKhachHang = resultSet.getString("TenKhachHang");
	            String diaChi = resultSet.getString("DiaChi");
	            String dienThoai = resultSet.getString("DienThoai");
	            String email = resultSet.getString("Email");
	            KhachHang khachHang = new KhachHang(maKhachHang, tenKhachHang, diaChi, dienThoai, email);
	            khachHangList.add(khachHang);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return khachHangList;
	}

	@Override
	public boolean themChiTietDonHang(ChiTietHoaDon ctdh) throws RemoteException {
		String sql = "INSERT INTO chitietdonhang (MaDonHang, MaSanPham, SoLuong, TongTien) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setInt(1, ctdh.getMaDonHang());
	        statement.setInt(2, ctdh.getMaSanPham());
	        statement.setInt(3, ctdh.getSoLuong());
	        statement.setDouble(4, ctdh.getTongTien());
	        int rowsInserted = statement.executeUpdate();
	        return rowsInserted > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public SanPham timKiemSanPham(int maSanPham) throws RemoteException {
		SanPham sanPham = null;
	    String sql = "SELECT * FROM sanpham WHERE MaSanPham = ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setInt(1, maSanPham);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            int maSanPhamResult = resultSet.getInt("MaSanPham");
	            String tenSP = resultSet.getString("TenSanPham");
	            double gia = resultSet.getDouble("Gia");
	            String moTa = resultSet.getString("MoTa");
	            int soLuong = resultSet.getInt("SoLuongTon");
	            sanPham = new SanPham(maSanPhamResult, tenSP, gia, moTa, soLuong);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return sanPham;
	}

	@Override
	public boolean xoaChiTietDonHang(int maDonHang) {
		String sql = "DELETE FROM chitietdonhang WHERE MaDonHang = ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setInt(1, maDonHang);
	        int rowsDeleted = statement.executeUpdate();
	        return rowsDeleted > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean capNhatCTDH(ChiTietHoaDon ctdh) throws RemoteException {
		String sql = "UPDATE chitietdonhang SET SoLuong = ?, TongTien = ? WHERE MaDonHang = ? AND MaSanPham = ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setInt(1, ctdh.getSoLuong());
	        statement.setDouble(2, ctdh.getTongTien());
	        statement.setInt(3, ctdh.getMaDonHang());
	        statement.setInt(4, ctdh.getMaSanPham());
	        int rowsUpdated = statement.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}



	

}
