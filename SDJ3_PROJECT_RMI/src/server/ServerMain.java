package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import common.IService;

public class ServerMain {
	public static void main(String[] args)
	{
		try {
			
			LocateRegistry.createRegistry(1099);

			IService service = new Service();

			Naming.rebind("Service", service);

			System.out.println("Starting server....");
			
			
			
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
