package model;

public class Car {
	private String vin;
	private String model;
	private int weight;
	private String brand;
	
	public Car(String vin, String model, String brand, int car_weight)
	{
		this.model = model;
		this.vin = vin;
		this.brand = brand;
		this.weight = car_weight;
	}
	
	public String getVin()
	{
		return vin;
	}
	
	public String getModel()
	{
		return model;
	}

	public int getWeight()
	{
		return weight;
	}
	
	public String getBrand()
	{
		return brand;
	}
}
