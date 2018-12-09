package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IService extends Remote {
	
	
	boolean stationOneRegister(String vin, String model, String brand, int car_weight) throws RemoteException;
	
	
	int stationTwoRegister(String partName, String partType, double weight, String vin, int palletID)throws RemoteException;
	
	
	int productRegistration(String productName, String type, String destination)throws RemoteException;
	
	
	boolean insertProductPart(int productID, int partID)throws RemoteException;
	
	
	String[] searchCar(String vin)throws RemoteException;
}
