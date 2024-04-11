package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
	public static void main(String[] args) {
		try {
			InterfaceQLSP QLSPService = new ImplQLSP();
			LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://localhost/QLSPService", QLSPService);
			System.out.println("Server Started");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
