package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import Exceptions.EmptyFieldException;
import Exceptions.FailedToException;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Utils.Neighberhood;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditDeliveryAreaController implements Initializable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private AnchorPane screen;

	@FXML
	private Button editdeliveryArea;

	@FXML
	private ComboBox<DeliveryArea> deliveryAreas;

	@FXML
	private Button back;

	@FXML
	private Button logout;

	@FXML
	private TextField deliveryAreaname;

	@FXML
	private ComboBox<DeliveryPerson> deliveryPerson;

	@FXML
	private ComboBox<Delivery> delivery;

	@FXML
	private ComboBox<Neighberhood> neighberhood;

	@FXML
	private Parent parent;

	@FXML
	void back(ActionEvent event) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					parent = FXMLLoader.load(getClass().getResource("/view/ManagerMainPage.fxml"));
					Stage stage = new Stage();
					Scene scene = new Scene(parent);
					stage.setScene(scene);
					stage.show();
					screen.getScene().getWindow().hide();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@FXML
	void logout(ActionEvent event) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					parent = FXMLLoader.load(getClass().getResource("/view/Restaurant.fxml"));
					Stage stage = new Stage();
					Scene scene = new Scene(parent);
					stage.setScene(scene);
					stage.show();
					screen.getScene().getWindow().hide();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@FXML
	void editdeliveryArea(ActionEvent event) throws FailedToException {

		try {
	
			boolean flag=false;
			
			if(deliveryAreaname.getText()==null ||  deliveryPerson.getValue()==null || delivery.getValue()==null||neighberhood.getValue()==null)
				throw new EmptyFieldException();
			
			if(deliveryAreaname.getText()!=null)
			{
				for(DeliveryArea da: Model.Restaurant.getInstance().getAreas().values())
				{
					if(deliveryAreas.getValue().equals(da))
					{
						da.setAreaName(deliveryAreaname.getText());
						flag=true;
					}
				}
			}
			
			if(deliveryPerson.getValue()!=null)
			{
				for(DeliveryArea da: Model.Restaurant.getInstance().getAreas().values())
				{
					if(deliveryAreas.getValue().equals(da))
					{
						da.getDelPersons().add(deliveryPerson.getValue());
						flag=true;
					}
				}
			}
			
			if(delivery.getValue()!=null)
			{
				for(DeliveryArea da: Model.Restaurant.getInstance().getAreas().values())
				{
					if(deliveryAreas.getValue().equals(da))
					{
						da.getDelivers().add(delivery.getValue());
						flag=true;
					}
				}
			}
			
			if(neighberhood.getValue()!=null)
			{
				for(DeliveryArea da: Model.Restaurant.getInstance().getAreas().values())
				{
					if(deliveryAreas.getValue().equals(da))
					{
						da.getNeighberhoods().add(neighberhood.getValue());
						flag=true;
					}
				}
			}
			
			if (flag) {
				{
					Model.Restaurant.savaData();
					Model.Restaurant.LoadDB();
					initialize(null,null);
					others.MyMethods.infoMessage("Success!", "Successfully updated");
				}
			} else {
				throw new FailedToException("edit");
			}
			
		}
		
		catch(EmptyFieldException e) {
			e.getMessage();
		}
		catch(FailedToException e) {
			e.getMessage();
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		HashMap<Integer,Delivery> returned = Model.Restaurant.getInstance().getDeliveries();

		for(Delivery d: returned.values()) {
			delivery.getItems().add(d);
		}

		HashMap<Integer, DeliveryPerson> delP= Model.Restaurant.getInstance().getDeliveryPersons();

		for(DeliveryPerson dp: delP.values()) {
			deliveryPerson.getItems().add(dp);
		}

		HashMap<Integer, DeliveryArea> delA = Model.Restaurant.getInstance().getAreas();

		for(DeliveryArea da: delA.values())
		{
			deliveryAreas.getItems().add(da);
		}
		

		ObservableList<Neighberhood> neighberHood = FXCollections.observableArrayList(Neighberhood.Bat_Galim,Neighberhood.Denya,Neighberhood.DownTown,Neighberhood.French_Karmel,Neighberhood.German_Colony,Neighberhood.Hadar,Neighberhood.Halisa,Neighberhood.Kababir,Neighberhood.Karmelia,Neighberhood.Kiriat_Eliezer,Neighberhood.Kiriat_Haim,Neighberhood.Merkaz_Karmel,Neighberhood.Neot_Peres,Neighberhood.Neve_David,Neighberhood.Neve_Shanan,Neighberhood.Neve_Yosef,Neighberhood.Ramat_Almogi,Neighberhood.Ramat_Hanasi,Neighberhood.Romema,Neighberhood.Vadi_Nisnas,Neighberhood.Vadi_Saliv,Neighberhood.Vardia);
		neighberhood.getItems().addAll(neighberHood);


	}

}
