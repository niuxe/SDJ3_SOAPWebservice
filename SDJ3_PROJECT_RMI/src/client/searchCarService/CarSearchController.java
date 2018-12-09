package client.searchCarService;


import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import client.ViewMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import common.IService;

public class CarSearchController implements Initializable {

	private IService server;
	private ViewMain view;
	private Alert alert;

	@FXML
    private TextField carVin;

    @FXML
    private TextArea resultTextArea;
    
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
    void search(MouseEvent event) {
    	
    	String vin = carVin.getText();
    	resultTextArea.setText("");
    	
    	
    	String[] result = searchCar(vin);
    	if(result == null)
    	{
    		successAlert("No car found!");
    	}
    	else
    	{
    		for (String s : result) {
				resultTextArea.setText(resultTextArea.getText() +
						s + "\n");
			}
    	}
    }
	
	public String[] searchCar(String vin)
	{
		try {
			return server.searchCar(vin);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
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