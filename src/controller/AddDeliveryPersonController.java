package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

import Exceptions.EmptyFieldException;
import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Exceptions.WrongBirthdayDate;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Utils.Gender;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddDeliveryPersonController implements Serializable,Initializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private AnchorPane screen;

	@FXML
	private Button adddeliveryperson;

	@FXML
	private TextField firstname;

	@FXML
	private TextField lastname;

	@FXML
	private DatePicker birthday;

	@FXML
	private ComboBox<Gender> gender;

	@FXML
	private ComboBox<Vehicle> vehicle;

	@FXML
	private TextField deliveryarea;

	@FXML
	private Button logout;

	@FXML
	private ImageView back1;

	@FXML
	private Button back;

	@FXML
	private Parent parent;
	@FXML
	LocalDate date = LocalDate.now();

	@FXML
	void AddDELIVERYperson(ActionEvent event) throws WrongBirthdayDate {
		try {
			if (firstname.getText().equals("") || lastname.getText().equals("") || birthday.getValue()==null || deliveryarea.getText().equals("") || gender.getValue()==null|| vehicle.getValue()==null)//checks if there's empty fields
				throw new EmptyFieldException();

			if(birthday.getValue().isAfter(date))
				throw new WrongBirthdayDate();

			HashMap<Integer,DeliveryArea> returned=Model.Restaurant.getInstance().getAreas();

			for(Integer key:returned.keySet())
				if(returned.get(key).equals(deliveryarea.getText()))
				{
					boolean flag = Model.Restaurant.getInstance().addDeliveryPerson(new DeliveryPerson(firstname.getText(), lastname.getText(), birthday.getValue(), gender.getValue(),vehicle.getValue(),returned.get(key)),returned.get(key));//adds the object

					if(flag)
					{
						Model.Restaurant.savaData();
						Model.Restaurant.LoadDB();
						initialize(null,null);
						others.MyMethods.infoMessage("Success!", "Successfully added");
					}

					else {
						throw new FailedToException("add");
					}

				}

		} catch(EmptyFieldException e) {
			e.getMessage();
		}catch(WrongBirthdayDate e) {
			e.getMessage();
		}
		catch(FailedToException e) {
			e.getMessage();
		}

		catch (NumberFormatException e) {
			others.MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} 

	}

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
					// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		ObservableList<Gender> gendeR = FXCollections.observableArrayList(Gender.Male, Gender.Female, Gender.Unknown);
		gender.getItems().addAll(gendeR);

		ObservableList<Vehicle> vehicleTypes = FXCollections.observableArrayList(Vehicle.Car, Vehicle.Motorcycle, Vehicle.Bicycle);
		vehicle.getItems().addAll(vehicleTypes);
	}

}
