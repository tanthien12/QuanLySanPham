package client;

import java.net.MalformedURLException;


import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import server.InterfaceQLSP;
import server.KhachHang;

public class Client {
	private static InterfaceQLSP qlspService;
	public static void main(String[] args) {
		try {
			qlspService = (InterfaceQLSP) Naming.lookup("rmi://localhost/QLSPService");
	
			// Kiểm tra hàm thêm khách hàng
            KhachHang newKhachHang = new KhachHang(3, "John Doe Json", "123 Street", "123456789", "john.doe@example.com");
            boolean result = qlspService.themKhachHang(newKhachHang);
            if (result) {
                System.out.println("Thêm khách hàng thành công");
            } else {
                System.out.println("Thêm khách hàng thất bại");
            }

            // Hiển thị danh sách Khách hàng sau khi thêm
            System.out.println("\nDanh sách sau khi thêm:");
            List<KhachHang> khachHangList = qlspService.xemKhachHang();
            for (KhachHang khachHang : khachHangList) {
                System.out.println(khachHang);
            }

            DangNhap loginFrame = new DangNhap();
            loginFrame.setVisible(true);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
