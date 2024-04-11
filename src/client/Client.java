package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import server.InterfaceQLSP;
import server.SanPham;

public class Client {
	public enum OperationMode {
	    ADD,
	    DELETE,
	    EDIT
	}
	public static void main(String[] args) {
		try {
			InterfaceQLSP qlspService = (InterfaceQLSP) Naming.lookup("rmi://localhost/QLSPService");
	
			// Hiển thị danh sách Sản Phẩm 
            System.out.println("\nList");
            List<SanPham> sanPhamList = qlspService.xemSanPham();
            for (SanPham sp : sanPhamList) {
                System.out.println(sp);
            }
            DangNhap loginFrame = new DangNhap();
            loginFrame.setVisible(true);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
