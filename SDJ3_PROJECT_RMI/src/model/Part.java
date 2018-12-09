package model;

public class Part {
	private String partName;
	private String partType;
	private double weight;
	private String vin;
	private int palletID;
	public Part(String partName, String partType, double weight, String vin, int palletID) {
		this.partName = partName;
		this.partType = partType;
		this.weight = weight;
		this.vin = vin;
		this.palletID = palletID;
	}

	public String getVin()
	{
		return vin;
	}
//
	public String getPartName()
	{
		return partName;
	}

	public double getWeight()
	{
		return weight;
	}

	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	public int getPalletID() {
		return palletID;
	}

	public void setPalletID(int palletID) {
		this.palletID = palletID;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}
}
