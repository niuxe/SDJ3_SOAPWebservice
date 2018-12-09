package client.station1;


import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import client.ViewMain;
import common.IService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class S1Controller implements Initializable {

	private IService server;
	private ViewMain view;
	
	private Alert alert;

	@FXML
    private TextField carVin;

    @FXML
    private TextField carModel;

    @FXML
    private TextField carBrand;

    @FXML
    private TextField carWeight;
	
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
    	
    	String vin = carVin.getText();
    	String model = carModel.getText();
    	String brand = carBrand.getText();
    	int weight = 0;
    	try
    	{
    		weight = Integer.parseInt(carWeight.getText());
    	}
    	catch(NumberFormatException e)
    	{
    		errorAlert("Please insert Car weight in kilograms");
    	}
    	
    	
    	
    	boolean success = registerCar(vin, model, brand, weight);
    	if(success)
    	{
    		successAlert("Car is registered successfully");
    	}
    	else
    	{
    		errorAlert("Car could not be registered");
    	}
    }
	
	public boolean registerCar(String vin, String model, String brand, int weight)
	{
		try {
			return server.stationOneRegister(vin, model, brand, weight);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
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