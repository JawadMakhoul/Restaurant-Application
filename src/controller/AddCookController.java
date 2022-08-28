package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddCookController implements Serializable,Initializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private Parent parent;
	@FXML
	private AnchorPane screen;

	@FXML
	private ComboBox<Gender> gender1;

	@FXML
	private ChoiceBox<Gender> gender;
	
	@FXML
	private Button addcooker;

	@FXML
	private TextField firstname;

	@FXML
	private TextField lastname;

	@FXML
	private DatePicker birthday;

	@FXML
	private ComboBox<Expertise> expertise1;
	
	@FXML
	private ChoiceBox<Expertise> expertise;

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
	void addcooker(ActionEvent event) throws WrongBirthdayDate {

		try {
			if (firstname.getText().equals("") || lastname.getText().equals("") || birthday.getValue()==null || expertise.getValue()==null || gender.getValue()==null)//checks if there's empty fields
				throw new EmptyFieldException();

			if(!chef.isSelected() && !notchef.isSelected())
				throw new NothingIsSelectedException();

			if(chef.isSelected() && notchef.isSelected())
				throw new SelectedAllException();

			if(birthday.getValue().isAfter(date))
				throw new WrongBirthdayDate();

			if(birthday.getValue().getYear()<=2003) {

				if(chef.isSelected())
				{
					if(gender.getValue().equals(Gender.Male))
					{
						boolean flag = Model.Restaurant.getInstance().addCook(new Cook(firstname.getText(), lastname.getText(), birthday.getValue(),Gender.Male,expertise.getValue(),true));//adds the object

						if(flag)
						{Model.Restaurant.LoadDB();
							Model.Restaurant.savaData();
							
							//initialize(null,null);
							others.MyMethods.infoMessage("Success!", "Successfully added");
						}
					}
					
					if(gender.getValue()==Gender.Female)
					{
						boolean flag = Model.Restaurant.getInstance().addCook(new Cook(firstname.getText(), lastname.getText(), birthday.getValue(),Gender.Female,expertise.getValue(),true));//adds the object

						if(flag)
						{
							Model.Restaurant.savaData();
							Model.Restaurant.LoadDB();
							//initialize(null,null);
							others.MyMethods.infoMessage("Success!", "Successfully added");
						}
					}
					
					if(gender.getValue()==Gender.Unknown)
					{
						boolean flag = Model.Restaurant.getInstance().addCook(new Cook(firstname.getText(), lastname.getText(), birthday.getValue(),Gender.Unknown,expertise.getValue(),true));//adds the object

						if(flag)
						{
							Model.Restaurant.savaData();
							Model.Restaurant.LoadDB();
							//initialize(null,null);
							others.MyMethods.infoMessage("Success!", "Successfully added");
						}
					}
					
				}

				if(!chef.isSelected())
				{

					if(gender.getValue()==Gender.Male)
					{
						boolean flag = Model.Restaurant.getInstance().addCook(new Cook(firstname.getText(), lastname.getText(), birthday.getValue(),Gender.Male,expertise.getValue(),true));//adds the object

						if(flag)
						{
							Model.Restaurant.savaData();
							Model.Restaurant.LoadDB();
							//initialize(null,null);
							others.MyMethods.infoMessage("Success!", "Successfully added");
						}
					}
					
					if(gender.getValue()==Gender.Female)
					{
						boolean flag = Model.Restaurant.getInstance().addCook(new Cook(firstname.getText(), lastname.getText(), birthday.getValue(),Gender.Female,expertise.getValue(),true));//adds the object

						if(flag)
						{
							Model.Restaurant.savaData();
							Model.Restaurant.LoadDB();
							//initialize(null,null);
							others.MyMethods.infoMessage("Success!", "Successfully added");
						}
					}
					
					if(gender.getValue()==Gender.Unknown)
					{
						boolean flag = Model.Restaurant.getInstance().addCook(new Cook(firstname.getText(), lastname.getText(), birthday.getValue(),Gender.Unknown,expertise.getValue(),true));//adds the object

						if(flag)
						{
							Model.Restaurant.savaData();
							Model.Restaurant.LoadDB();
							//initialize(null,null);
							others.MyMethods.infoMessage("Success!", "Successfully added");
						}
					}
				}

				else {
					throw new FailedToException("add");
				}
			}



		} catch (NumberFormatException e) {
			others.MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {//errorMessage is the class dad of most exceptions
			e.getMessage();//a pop up error message
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
		ObservableList<Gender> gendeR =  FXCollections.observableArrayList(Gender.Male, Gender.Female, Gender.Unknown);
		gender.getItems().addAll(gendeR);


		ObservableList<Expertise> Types = FXCollections.observableArrayList(Expertise.American, Expertise.Asian, Expertise.French,Expertise.Indian,Expertise.Italien,Expertise.Mediterranean);
		expertise.getItems().addAll(Types);

	}

}
