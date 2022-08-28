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
import Exceptions.NothingIsSelectedException;
import Exceptions.SelectedAllException;
import Exceptions.WrongBirthdayDate;
import Model.Cook;
import Utils.Expertise;
import Utils.Gender;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditCooksController implements Initializable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private AnchorPane screen;

	@FXML
	private Button editcook;

	@FXML
	private ComboBox<Cook> cooks;

	@FXML
	private Parent parent;

	@FXML
	private ComboBox<Gender> gender;

	@FXML
	private TextField firstname;

	@FXML
	private TextField lastname;

	@FXML
	private DatePicker birthday;

	@FXML
	private ComboBox<Expertise> expertise;

	@FXML
	private CheckBox chef;

	@FXML
	private CheckBox notchef;

	@FXML
	private Button logout;

	@FXML
	private Button back;

	@FXML
	LocalDate date = LocalDate.now();

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
	void editcook(ActionEvent event) throws WrongBirthdayDate {

		try {
			if (firstname.getText().equals("") && lastname.getText().equals("") && birthday.getValue()==null && expertise.getValue()==null && gender.getValue()==null)//checks if there's empty fields
				throw new EmptyFieldException();

			if(!chef.isSelected() && !notchef.isSelected())
				throw new NothingIsSelectedException();

			if(chef.isSelected() && notchef.isSelected())
				throw new SelectedAllException();

			if(birthday.getValue().isAfter(date))
				throw new WrongBirthdayDate();

			else if(birthday.getValue().getYear()<=2003) {

				boolean flag=false;
				if(chef.isSelected())
				{
					for(Cook c: Model.Restaurant.getInstance().getCooks().values())
					{
						if(c.equals(cooks.getValue())) {
							c.setChef(true);
							flag=true;
						}
					}

				}

				if(notchef.isSelected())
				{
					for(Cook c: Model.Restaurant.getInstance().getCooks().values())
					{
						if(c.equals(cooks.getValue())) {
							c.setChef(false);
							flag=true;
						}
					}

				}

				if(firstname.getText()!=null)
				{
					for(Cook c: Model.Restaurant.getInstance().getCooks().values())
					{
						if(c.equals(cooks.getValue())) {
							c.setFirstName(firstname.getText());
							flag=true;
						}
					}

				}

				if(lastname.getText()!=null)
				{
					for(Cook c: Model.Restaurant.getInstance().getCooks().values())
					{
						if(c.equals(cooks.getValue())) {
							c.setLastName(lastname.getText());
							flag=true;
						}
					}

				}

				if(expertise.getValue()!=null) {
					for(Cook c: Model.Restaurant.getInstance().getCooks().values())
					{
						if(c.equals(cooks.getValue())) {
							c.setExpert(expertise.getValue());
							flag=true;
						}
					}
				}

				if(gender.getValue()!=null) {
					for(Cook c: Model.Restaurant.getInstance().getCooks().values())
					{
						if(c.equals(cooks.getValue())) {
							c.setGender(gender.getValue());
							flag=true;
						}
					}
				}

				if(birthday.getValue()!=null) {
					for(Cook c: Model.Restaurant.getInstance().getCooks().values())
					{
						if(c.equals(cooks.getValue())) {
							c.setBirthDay(birthday.getValue());
							flag=true;
						}
					}
				}

				if(flag)
				{
					Model.Restaurant.savaData();
					Model.Restaurant.LoadDB();
					initialize(null,null);
					others.MyMethods.infoMessage("Success!", "Cook has been updated.");
				}
				else {
					throw new FailedToException("edit");
				}

			}




		} catch (NumberFormatException e) {
			others.MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {//errorMessage is the class dad of most exceptions
			e.getMessage();//a pop up error message
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Gender> gendeR =  FXCollections.observableArrayList(Gender.Male, Gender.Female, Gender.Unknown);
		gender.getItems().addAll(gendeR);


		ObservableList<Expertise> Types = FXCollections.observableArrayList(Expertise.American, Expertise.Asian, Expertise.French,Expertise.Indian,Expertise.Italien,Expertise.Mediterranean);
		expertise.getItems().addAll(Types);

		HashMap<Integer,Cook> returned = Model.Restaurant.getInstance().getCooks();

		for(Cook c: returned.values()) {
			cooks.getItems().add(c);
		}
	}

}
