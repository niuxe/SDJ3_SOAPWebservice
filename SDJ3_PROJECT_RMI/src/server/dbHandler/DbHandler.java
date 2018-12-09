package server.dbHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Car;
import model.Part;
import model.Product;

public class DbHandler {
	private Connection connection;
	private String connectionUrl;
	public DbHandler()
	{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		connectionUrl = 
				"jdbc:sqlserver://sdj3server.database.windows.net;" // complete address of server
				+ "databaseName=sdj3;" // database name
				+ "user=sdj3;" // user name 
				+ "password=projectSucks18"; //password 


	}

	private void openConnection() throws SQLException
	{
		connection = DriverManager.getConnection(connectionUrl);
	}

	private void closeConnection()
	{
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertCarIntoDatabase(Car car)
	{
		try {
			openConnection();
			String sql = "INSERT INTO Car (vin, model, brand, car_weight)" + " VALUES (?, ?, ?, ?)";
			PreparedStatement psInsertCar = connection.prepareStatement(sql);
			
			psInsertCar.setString(1, car.getVin());
			psInsertCar.setString(2, car.getModel());
			psInsertCar.setString(3, car.getBrand());
			psInsertCar.setInt(4, car.getWeight());
			psInsertCar.executeUpdate();
			System.out.println("Car added to Database.");
			return true;
		} catch (SQLException e) {
			if (e.getErrorCode() == 23505) {
				return false;
			} else {
				e.printStackTrace();
				return false;
			}
		} finally {
			closeConnection();
		}
	}
	
	public int insertPartIntoDatabase(Part part)
	{
		try {
			openConnection();
			String sql = "INSERT INTO Part (VIN, Part_name, Type, Part_weight, Pallet_id)" +
			" VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, part.getVin());
			ps.setString(2, part.getPartName());
			ps.setString(3, part.getPartType());
			ps.setDouble(4, part.getWeight());
			ps.setInt(5, part.getPalletID());
			
			ps.executeUpdate();
			System.out.println("Part added to Database.");
			return getPartID(part);
		} catch (SQLException e) {
			if (e.getErrorCode() == 23505) {
				return -1;
			} else {
				e.printStackTrace();
				return -1;
			}
		} finally {
			closeConnection();
		}
	}
	
	public int getPartID(Part part)
	{
		try {
			openConnection();
			String sql = "select part_id from Part "
					+ "where VIN = ? "
					+ "and Part_name = ? "
					+ "and Type = ? "
					+ "and Part_weight = ? "
					+ "and Pallet_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, part.getVin());
			ps.setString(2, part.getPartName());
			ps.setString(3, part.getPartType());
			ps.setDouble(4, part.getWeight());
			ps.setInt(5, part.getPalletID());
			
			ResultSet r = ps.executeQuery();
			
			while(r.next())
			{
				return r.getInt("part_id");
			}
		} catch (SQLException e) {
			if (e.getErrorCode() == 23505) {
				return -1;
			} else {
				e.printStackTrace();
				return -1;
			}
		} finally {
			closeConnection();
		}
		return -1;
	}
	
	public int insertProductIntoDatabase(Product p)
	{
		try {
			openConnection();
			String sql = "INSERT INTO PRODUCT(PRODUCT_NAME, PRODUCT_TYPE, "
					+ "DESTINATION) VALUES (?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, p.getProductName());
			ps.setString(2, p.getType());
			ps.setString(3, p.getDestination());
			ps.executeUpdate();
			
			int productID = getProductID(p.getProductName(), p.getType(),
					p.getDestination());
			
			if(productID == -1)
			{
				return -1;
			}
			

			System.out.println("Product added to Database.");
			connection.commit();
			return productID;
		} catch (SQLException e) {
			if (e.getErrorCode() == 23505) {
				return -1;
			} else {
				e.printStackTrace();
				return -1;
			}
		} finally {
			closeConnection();
		}
	}
	
	private int getProductID(String productName, String type, String destination)
	{
		try {
			String sql = "SELECT Product_id FROM PRODUCT"
					+ " WHERE PRODUCT_NAME = ? "
					+ "AND PRODUCT_TYPE = ? "
					+ "AND DESTINATION = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, productName);
			ps.setString(2, type);
			ps.setString(3, destination);
			ResultSet r = ps.executeQuery();
			
			int productID = -1;
			
			while(r.next())
			{
				productID = r.getInt("Product_id");
			}
			
			return productID;
		} catch (SQLException e) {
			if (e.getErrorCode() == 23505) {
				return -1;
			} else {
				e.printStackTrace();
				return -1;
			}
		} 
	}
	
	public boolean insertProductPart(int productID, int partID)
	{
		try {
			openConnection();
			String sql = "UPDATE PART SET PRODUCT_ID = ? WHERE PART_ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, productID);
			ps.setInt(2, partID);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			if (e.getErrorCode() == 23505) {
				return false;
			} else {
				e.printStackTrace();
				return false;
			}
		}
		finally {
			closeConnection();
		}
	}
	
	public String getCarInfo(String vin)
	{
		try {
			openConnection();
			String sql = "Select * from Car"
					+ " WHERE vin = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, vin);
			ResultSet r = ps.executeQuery();
			String carInfo = null;
			
			while(r.next())
			{
				String l ="vin: " + r.getString("vin");
				l += ", model: " + r.getString("model");
				l += ", brand: " + r.getString("brand");
				carInfo = l;
				
			}
			
			
			return carInfo;
		} catch (SQLException e) {
			if (e.getErrorCode() == 23505) {
				return null;
			} else {
				e.printStackTrace();
				return null;
			}
		} finally {
			closeConnection();
		}
	}
	
	public ArrayList<String> getAllPartsInfo(String vin)
	{
		try {
			openConnection();
			String sql = "SELECT * FROM Part" +
			" WHERE VIN = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1,vin);
			ResultSet r = ps.executeQuery();
			ArrayList<String> s = new ArrayList<String>();
			
			
			while(r.next())
			{
				String l = "Part name: " + r.getString("Part_name");
				l += ", type: " + r.getString("Type");
				l += ", pallet id: " + r.getInt("Pallet_id");
				l += ", product id: " + r.getInt("Product_id");
				s.add(l);
			}
			
			
			
			return s;
		} catch (SQLException e) {
			if (e.getErrorCode() == 23505) {
				return null;
			} else {
				e.printStackTrace();
				return null;
			}
		} finally {
			closeConnection();
		}
	}

}
