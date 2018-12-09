package client.station2;


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

public class S2Controller implements Initializable {

	private IService server;
	private ViewMain view;
	private Alert alert;

    @FXML
    private TextField carVinField;

    @FXML
    private TextField partNameField;

    @FXML
    private TextField partTypeField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField palletField;
    
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
    	
    	String vin = carVinField.getText();
    	String partName = partNameField.getText();
    	String partType = partTypeField.getText();
    	double weight = 0;
    	try
    	{
    		weight = Double.parseDouble(weightField.getText());
    	}
    	catch(NumberFormatException e)
    	{
    		errorAlert("Please insert part weight in kilograms and grams");
    	}
    	
    	int palletID = 0;
    	try
    	{
    		palletID = Integer.parseInt(palletField.getText());
    	}
    	catch(NumberFormatException e)
    	{
    		errorAlert("Please insert pallet id as a Number (Integer)");
    	}
    	
    	
    	
    	int success = registerPart(partName, partType, weight, vin, palletID);
    	if(success != -1)
    	{
    		successAlert("Part is registered successfully with id " + success);
    	}
    	else
    	{
    		errorAlert("Part could not be registered");
    	}
    }
	
	public int registerPart(String partName, String partType, double weight, String vin, int palletID)
	{
		try {
			return server.stationTwoRegister(partName, partType, weight, vin, palletID);
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