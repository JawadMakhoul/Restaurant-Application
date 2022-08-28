package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import Exceptions.EmptyFieldException;
import Exceptions.FailedToException;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Utils.Vehicle;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditDeliveryPersonController implements Initializable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private AnchorPane screen;

	@FXML
	private Button editdeliveryperson;

	@FXML
	private ComboBox<DeliveryPerson> deliveryperson;

	@FXML
	private Button back;

	@FXML
	private Button logout;

	@FXML
	private TextField firstname;

	@FXML
	private TextField lastname;

	@FXML
	private ComboBox<Vehicle> vehicle;

	@FXML
	private ComboBox<DeliveryArea> deliveryArea;

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
	void editdeliveryperson(ActionEvent event) {
		try {

			boolean flag=false;
			if(firstname.getText()==null&& lastname.getText()==null &&deliveryArea.getValue()==null && vehicle.getValue()==null)
				throw new EmptyFieldException();

			if(firstname.getText()!=null)
			{
				for(DeliveryPerson dp: Model.Restaurant.getInstance().getDeliveryPersons().values())
				{
					if(dp.equals(deliveryperson.getValue()))
					{
						dp.setFirstName(firstname.getText());
						flag=true;
					}
				}
			}

			if(lastname.getText()!=null)
			{
				for(DeliveryPerson dp: Model.Restaurant.getInstance().getDeliveryPersons().values())
				{
					if(dp.equals(deliveryperson.getValue()))
					{
						dp.setLastName(lastname.getText());
						flag=true;
					}
				}
			}

			if(deliveryArea.getValue()!=null)
			{
				for(DeliveryPerson dp: Model.Restaurant.getInstance().getDeliveryPersons().values())
				{
					if(dp.equals(deliveryperson.getValue()))
					{
						dp.setArea(deliveryArea.getValue());
						flag=true;
					}
				}
			}

			if(vehicle.getValue()!=null)
			{
				for(DeliveryPerson dp: Model.Restaurant.getInstance().getDeliveryPersons().values())
				{
					if(dp.equals(deliveryperson.getValue()))
					{
						dp.setVehicle(vehicle.getValue());
						flag=true;
					}
				}
			}

			if(flag)
			{
				Model.Restaurant.savaData();
				Model.Restaurant.LoadDB();
				initialize(null,null);
				others.MyMethods.infoMessage("Success!", "Delivery Person has been updated.");
			}
			else throw new FailedToException("edit");


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
		HashMap<Integer, DeliveryPerson> delP= Model.Restaurant.getInstance().getDeliveryPersons();

		for(DeliveryPerson dp: delP.values()) {
			deliveryperson.getItems().add(dp);
		}

		ObservableList<Vehicle> vehicleTypes = FXCollections.observableArrayList(Vehicle.Car, Vehicle.Motorcycle, Vehicle.Bicycle);
		vehicle.getItems().addAll(vehicleTypes);

		HashMap<Integer, DeliveryArea> delA = Model.Restaurant.getInstance().getAreas();

		for(DeliveryArea da: delA.values())
		{
			deliveryArea.getItems().add(da);
		}

	}

}
