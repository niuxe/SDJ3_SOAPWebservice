package client.station3;


import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import client.ViewMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import common.IService;

public class S3Controller implements Initializable {

	private IService server;
	private ViewMain view;
	private Alert alert;

	   @FXML
	    private TextField productNameField;

	    @FXML
	    private TextField typeField;

	    @FXML
	    private TextField destinationField;

	    @FXML
	    private TextField partIDField;

	    @FXML
	    private TextField productIDField;

	    @FXML
	    void addPartToProduct(MouseEvent event) {
	    	int partID = -1;
	    	int productID = -1;
	    	
	    	try
	    	{
	    		partID = Integer.parseInt(partIDField.getText());
	    	}
	    	catch(NumberFormatException e)
	    	{
	    		errorAlert("Please insert Part id number");
	    		return;
	    	}
	    	
	    	try
	    	{
	    		productID = Integer.parseInt(productIDField.getText());
	    	}
	    	catch(NumberFormatException e)
	    	{
	    		errorAlert("Please insert Product id number");
	    		return;
	    	}
	    	
	    	boolean result = false;
			try {
				result = server.insertProductPart(productID, partID);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	if(result == false)
	    	{
	    		errorAlert("Error adding part to product");
	    	}
	    	else
	    	{
	    		successAlert("Part seccessfully added to product");
	    	}
	    	
	    	
	    }
	    @FXML
	    void goToSearchCarService(ActionEvent event) {
	    	view.showSearchCar();
	    }

	    @FXML
	    void goToStation1(ActionEvent event) {
	    	view.showStation1();
	    }

	    @FXML
	    void goToStation2(ActionEvent event) {
	    	view.showStation2();

	    }

	    @FXML
	    void goToStation3(ActionEvent event) {
	    	view.showStation3();

	    }
	    
    @FXML
    void register(MouseEvent event) {
    	
    	String productName = productNameField.getText();
    	String type = typeField.getText();
    	String destination = destinationField.getText();
    	
    	
    	
    	int result = registerProduct(productName, type, destination);
    	if(result == -1)
    	{
    		errorAlert("Product could not be registered");
    	}
    	else
    	{
    		successAlert("Product is registered successfully with product ID = " + result);
    	}
    }
	
	private int registerProduct(String productName, String type, String destination)
	{
		try {
			return server.productRegistration(productName, type, destination);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return -1;
	}

	
	public void errorAlert(String string)
	{
		if(alert == null)
		{
			alert = new Alert(Alert.AlertType.ERROR);
		}
		alert.setAlertType(Alert.AlertType.ERROR);
		alert.setContentText(string);
		alert.showAndWait();
		
	}
	
	public void successAlert(String string)
	{
		if(alert == null)
		{
			alert = new Alert(Alert.AlertType.INFORMATION);
		}
		alert.setAlertType(Alert.AlertType.INFORMATION);
		alert.setContentText(string);
		alert.showAndWait();
	}
	
	public void setService(IService service)
	{
		this.server = service;
	}
	
	public void setView(ViewMain view)
	{
		this.view = view;
	}
	


	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		
	}

}