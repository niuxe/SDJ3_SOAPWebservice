package webInterfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebInterface {
	
	@WebMethod
	boolean stationOneRegister(String vin, String model, String brand, int car_weight);
	
	@WebMethod
	int stationTwoRegister(String partName, String partType, double weight, String vin, int palletID);
	
	@WebMethod
	int productRegistration(String productName, String type, String destination);
	
	@WebMethod
	boolean insertProductPart(int productID, int partID);
	
	@WebMethod
	String searchCar(String vin);
}
