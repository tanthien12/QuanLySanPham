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
	
			// Thêm 
			// Hiển thị danh sách Sản Phẩm 
            System.out.println("\nList");
            List<DonHang> nvList = qlspService.xemDonHang();
            for (DonHang nv : nvList) {
                System.out.println(nv);
            }
            DangNhap loginFrame = new DangNhap();
            loginFrame.setVisible(true);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
