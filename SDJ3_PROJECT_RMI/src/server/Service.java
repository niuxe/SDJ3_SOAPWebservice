package server;

import model.Car;
import model.Part;
import model.Product;
import server.dbHandler.DbHandler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


import common.IService;


public class Service extends UnicastRemoteObject implements IService {
	
	private static final long serialVersionUID = 1L;
	private DbHandler db;

	
	public Service() throws RemoteException
	{
		db = new DbHandler();
	}
	
	@Override
	public boolean stationOneRegister(String vin, String model, String brand, int car_weight)  throws RemoteException
	{
		Car car = new Car(vin, model, brand, car_weight);
		
		
		return db.insertCarIntoDatabase(car);
	}
	
	@Override
	public int stationTwoRegister(String partName, String partType, double weight, String vin, int palletID)  throws RemoteException
	{
		Part part = new Part(partName, partType, weight, vin, palletID);
		
		
		return db.insertPartIntoDatabase(part);
	}
	
	@Override
	public int productRegistration(String productName, String type, String destination)  throws RemoteException
	{
		Product product = new Product(productName, type, destination);
		
		
		return db.insertProductIntoDatabase(product);		
	}

	@Override
	public boolean insertProductPart(int productID, int partID)  throws RemoteException
	{
		
		return  db.insertProductPart(productID, partID);
	}

	@Override
	public String[] searchCar(String vin)  throws RemoteException
	{
		
		
		String[] infoToReturn = null;
		String carInfo = db.getCarInfo(vin);
		if(carInfo == null)
		{
			return infoToReturn;
		}
		
		ArrayList<String> carPartsInfo = db.getAllPartsInfo(vin);
		
		if(carPartsInfo == null)
		{
			infoToReturn = new String[1];
			infoToReturn[0] = carInfo;
			return infoToReturn;
		}
		
		infoToReturn = new String[carPartsInfo.size() + 1];
		infoToReturn[0] = carInfo;
		
		for (int i = 0; i < carPartsInfo.size(); i++) {
			infoToReturn[i+1] = carPartsInfo.get(i);
		}
		
		
		return infoToReturn;
	}
	
	
}
