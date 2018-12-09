package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import client.searchCarService.CarSearchController;
import client.station1.S1Controller;
import client.station2.S2Controller;
import client.station3.S3Controller;
import common.IService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ViewMain extends Application {
	private Stage primaryStage;
	private IService service;
	private Parent station1;
	private Parent station2;
	private Parent station3;
	private Parent carSearch;
	
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		connectToServer();
		showStation1();
		
	}
	
	public void connectToServer()
	{
		if(service == null)
		{
			try {
				service = (IService) Naming.lookup("rmi://localhost:1099/Service");
			} catch (MalformedURLException | RemoteException | NotBoundException e) {
				e.printStackTrace();
			}			
		}
	}
	
	
	public void showStation1()
	{
		try {
			FXMLLoader loader = null;
				loader = new FXMLLoader(getClass().getResource("station1/Station1Frame.fxml"));
				station1= loader.load();
			Scene scene = new Scene(station1);
			
			S1Controller controller = (S1Controller)loader.getController();
			controller.setService(service);
			controller.setView(this);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setOpacity(0.95);
			primaryStage.setTitle("Station 1");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showStation2()
	{
		try {
			FXMLLoader loader = null;
				loader = new FXMLLoader(getClass().getResource("station2/Station2Frame.fxml"));
				station2 = loader.load();
			Scene scene = new Scene(station2);
			
			S2Controller controller = (S2Controller)loader.getController();
			controller.setService(service);
			controller.setView(this);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setOpacity(0.95);
			primaryStage.setTitle("Station 2");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showStation3()
	{
		try {
			FXMLLoader loader = null;
				loader = new FXMLLoader(getClass().getResource("station3/Station3Frame.fxml"));
				station3= loader.load();
			Scene scene = new Scene(station3);
			
			S3Controller controller = (S3Controller)loader.getController();
			controller.setService(service);
			controller.setView(this);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setOpacity(0.95);
			primaryStage.setTitle("Station 3");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showSearchCar()
	{
		try {
			FXMLLoader loader = null;
				loader = new FXMLLoader(getClass().getResource("searchCarService/CarSearchFrame.fxml"));
				carSearch = loader.load();
			
			Scene scene = new Scene(carSearch);
			
			CarSearchController controller = (CarSearchController)loader.getController();
			controller.setService(service);
			controller.setView(this);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setOpacity(0.95);
			primaryStage.setTitle("Car search service");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
