package service;

import dbHandler.DbHandler;
import model.Car;
import model.Part;
import model.Product;
import webInterfaces.WebInterface;

import java.util.ArrayList;

import javax.jws.WebService;

@WebService(endpointInterface = "webInterfaces.WebInterface")
public class Service implements WebInterface {
	
	@Override
	public boolean stationOneRegister(String vin, String model, String brand, int car_weight) {
		Car car = new Car(vin, model, brand, car_weight);
		DbHandler db = new DbHandler();
		
		return db.insertCarIntoDatabase(car);
	}
	
	@Override
	public int stationTwoRegister(String partName, String partType, double weight, String vin, int palletID) {
		Part part = new Part(partName, partType, weight, vin, palletID);
		DbHandler db = new DbHandler();
		
		return db.insertPartIntoDatabase(part);
	}
	
	@Override
	public int productRegistration(String productName, String type, String destination) {
		Product product = new Product(productName, type, destination);
		DbHandler db = new DbHandler();
		
		return db.insertProductIntoDatabase(product);		
	}

	@Override
	public boolean insertProductPart(int productID, int partID) {
		DbHandler db = new DbHandler();
		return  db.insertProductPart(productID, partID);
	}

	@Override
	public String[] searchCar(String vin)
	{
		DbHandler db = new DbHandler();
		
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
