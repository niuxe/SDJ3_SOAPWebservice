package model;

public class Product {
	private String productName;
	private String type;
	private String destination;
	
	public Product(String productName, String type, String destination) {
		this.productName = productName;
		this.type = type;
		this.destination = destination;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
}
