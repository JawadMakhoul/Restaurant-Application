package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import Exceptions.EmptyFieldException;
import Exceptions.FailedToException;
import Exceptions.illegalAgeException;
import Model.Customer;
import Utils.Expertise;
import Utils.Gender;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AddCustomerController implements Serializable,Initializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private AnchorPane screen;

	@FXML
	private TextField firstname;

	@FXML
	private TextField lastname;

	@FXML
	private DatePicker birthday;

	@FXML
	private PasswordField password;

	@FXML
	private ComboBox<Gender> gender;

	@FXML
	private ComboBox<Neighberhood> neighberhood;

	@FXML
	private PasswordField repassword;

	@FXML
	private RadioButton gluten;

	@FXML
	private RadioButton lactose;

	@FXML
	private Button CreateAccount;

	@FXML
	private Button back;

	@FXML
	private Button logout;

	@FXML
	private Parent parent;

	@FXML
	private Button result;

	@FXML
	private TextField username;

	@FXML
	private Button checkpass;


	@FXML
	void CreateAccount(ActionEvent event) throws FailedToException, InterruptedException {

		try {
			if(firstname.getText().equals("")||lastname.getText().equals("")||birthday.getValue().equals(null)||gender.getValue().equals(null)||neighberhood.getValue().equals(null)||username.getText().equals(null)|| password.getText().equals(null)||repassword.getText().equals(""))
				throw new EmptyFieldException();



			else if(birthday.getValue().getYear()<=2003){


				boolean Lactose=false, Gluten=false;
				if(lactose.isSelected())
					Lactose=true;

				if(gluten.isSelected())
					Gluten=true;
				
				Customer cust = new Customer(firstname.getText(),lastname.getText(),birthday.getValue(),gender.getValue(),neighberhood.getValue(),Lactose,Gluten,username.getText(),password.getText());

				boolean flag = Model.Restaurant.getInstance().addCustomer(cust);

				if (flag) {
					Model.Restaurant.LoadDB();
					Model.Restaurant.savaData();
					
					//initialize(null,null);
					others.MyMethods.infoMessage("Success!", "Successfully added");
//					others.MyMethods.setCustomer(cust);
//					Platform.runLater(new Runnable() {
//						@Override
//						public void run() {
//							try {
//								parent = FXMLLoader.load(getClass().getResource("/view/CustomerMainPage.fxml"));
//								Stage stage = new Stage();
//								Scene scene = new Scene(parent);
//								stage.setScene(scene);
//								stage.show();
//								screen.getScene().getWindow().hide();
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//					});
					
				

				}
				else {
					throw new FailedToException("add");
				}
			}

			else if(birthday.getValue().getYear()>2003) {
				throw new illegalAgeException();
			}
			
			firstname.setText(null);
			lastname.setText(null);
			birthday.setValue(null);
			//gender.setValue(null);
			//neighberhood.setValue(null);
			lactose.setSelected(false);
			gluten.setSelected(false);
			username.setText(null);
			password.setText(null);
			repassword.setText(null);
			
			password.setStyle("-fx-background-color: white;");
			repassword.setStyle("-fx-background-color: white;");
			result.setText("");
			result.setTextFill(Color.WHITE);

		}
		catch(EmptyFieldException e) {
			e.getMessage();
		}

		catch(illegalAgeException e) {
			e.getMessage();
		}

	}

	@FXML
	void checkpass(ActionEvent event) throws InterruptedException {

		if(!others.MyMethods.CheckPassword(password.getText()))
		{
			password.setStyle("-fx-background-color: red;");
			repassword.setStyle("-fx-background-color: red;");
			result.setText("Password conditions not confirmed");
			result.setTextFill(Color.RED);

		}

		if(!others.MyMethods.CheckPassword(repassword.getText())&&others.MyMethods.CheckPassword(password.getText()))
		{
			password.setStyle("-fx-background-color: green;");
			repassword.setStyle("-fx-background-color: red;");
			result.setText("Not matched! Please try again.");
			result.setTextFill(Color.RED);
		}

		if(others.MyMethods.CheckPassword(repassword.getText())&&others.MyMethods.CheckPassword(password.getText())) {
			password.setStyle("-fx-background-color: green;");
			repassword.setStyle("-fx-background-color: green;");
			result.setText("Password conditions confirmed");
			result.setTextFill(Color.GREEN);			

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
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Gender> gendeR =  FXCollections.observableArrayList(Gender.Male, Gender.Female, Gender.Unknown);
		gender.getItems().addAll(gendeR);

		ObservableList<Neighberhood> neighberHood = FXCollections.observableArrayList(Neighberhood.Bat_Galim,Neighberhood.Denya,Neighberhood.DownTown,Neighberhood.French_Karmel,Neighberhood.German_Colony,Neighberhood.Hadar,Neighberhood.Halisa,Neighberhood.Kababir,Neighberhood.Karmelia,Neighberhood.Kiriat_Eliezer,Neighberhood.Kiriat_Haim,Neighberhood.Merkaz_Karmel,Neighberhood.Neot_Peres,Neighberhood.Neve_David,Neighberhood.Neve_Shanan,Neighberhood.Neve_Yosef,Neighberhood.Ramat_Almogi,Neighberhood.Ramat_Hanasi,Neighberhood.Romema,Neighberhood.Vadi_Nisnas,Neighberhood.Vadi_Saliv,Neighberhood.Vardia);
		neighberhood.getItems().addAll(neighberHood);

	}



}
