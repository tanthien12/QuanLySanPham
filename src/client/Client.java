package client;

import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import server.DonHang;
import server.InterfaceQLSP;

public class Client {
	private static InterfaceQLSP qlspService;
	public static void main(String[] args) {
		try {
			qlspService = (InterfaceQLSP) Naming.lookup("rmi://localhost/QLSPService");
			
			
            // Hiển thị danh sách Khách hàng sau khi thêm
//			DonHang dh = new DonHang(7, "DDD", 1, 1,null, "Da thanh toan");
//			qlspService.themDonHang(dh);
			
            System.out.println("\nDanh sách sau khi thêm:");
            List<DonHang> khachHangList = qlspService.xemDonHang();
            for (DonHang donHang : khachHangList) {
                System.out.println(donHang);
            }
            
//            // test xóa đơn hàng
//            boolean result = qlspService.xoaChiTietDonHang(101);
//            if (result) {
//                System.out.println("Xóa đơn hàng thành công!");
//            } else {
//                System.out.println("Xóa đơn hàng không thành công!");
//            }
            
            DangNhap loginFrame = new DangNhap();
            loginFrame.setVisible(true);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
