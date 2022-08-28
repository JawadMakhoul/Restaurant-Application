package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

import Exceptions.EmptyFieldException;
import Exceptions.FailedToException;
import Exceptions.WrongBirthdayDate;
import Exceptions.illegalAgeException;
import Model.Cook;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditCustomerController implements Initializable, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
    private AnchorPane screen;

    @FXML
    private Button editcustomer;

    @FXML
    private ComboBox<Customer> customers;

    @FXML
    private Button back;

    @FXML
    private Button logout;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private DatePicker birthday;

    @FXML
    private ComboBox<Gender> gender;

    @FXML
    private ComboBox<Neighberhood> neighberhood;

    @FXML
    private RadioButton gluten;

    @FXML
    private RadioButton lactose;
    
    @FXML
    private Parent parent;
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
    void editcustomer(ActionEvent event) throws WrongBirthdayDate, FailedToException {
    	
    	try {
			if(firstname.getText().equals("")&&lastname.getText().equals("")&&birthday.getValue().equals(null)&&gender.getValue().equals(null)&&neighberhood.getValue().equals(null))
				throw new EmptyFieldException();

			if(birthday.getValue().isAfter(date))
				throw new WrongBirthdayDate();

			else if(birthday.getValue().getYear()<=2003){


				boolean Lactose=false, Gluten=false,flag=false;
				if(lactose.isSelected())
					Lactose=true;

				if(gluten.isSelected())
					Gluten=true;

				if(firstname.getText()!=null) {
					for(Customer c: Model.Restaurant.getInstance().getCustomers().values()) {
						if(c.equals(customers.getValue())) {
							
								c.setFirstName(firstname.getText());
								flag=true;
							
						}
					}
				}
				if(lastname.getText()!=null) {
					for(Customer c: Model.Restaurant.getInstance().getCustomers().values()) {
						if(c.equals(customers.getValue())) {
							
								c.setLastName(lastname.getText());
								flag=true;
							
						}
					}
				}
				
				if(birthday.getValue()!=null) {
					for(Customer c: Model.Restaurant.getInstance().getCustomers().values()) {
						if(c.equals(customers.getValue())) {
							
								c.setBirthDay(birthday.getValue());
								flag=true;
							
						}
					}
				}
				
				if(gender.getValue()!=null) {
					for(Customer c: Model.Restaurant.getInstance().getCustomers().values()) {
						if(c.equals(customers.getValue())) {
							
								c.setGender(gender.getValue());
								flag=true;
							
						}
					}
				}
				
				if(neighberhood.getValue()!=null) {
					for(Customer c: Model.Restaurant.getInstance().getCustomers().values()) {
						if(c.equals(customers.getValue())) {
							
								c.setNeighberhood(neighberhood.getValue());
								flag=true;
							
						}
					}
				}
				
				if(Lactose==true) {
					for(Customer c: Model.Restaurant.getInstance().getCustomers().values()) {
						if(c.equals(customers.getValue())) {
							
								c.setSensitiveToLactose(true);
								flag=true;
							
						}
					}
				}
				
				if(Lactose!=true) {
					for(Customer c: Model.Restaurant.getInstance().getCustomers().values()) {
						if(c.equals(customers.getValue())) {
							c.setSensitiveToLactose(false);
							flag=true;
						}
					}
				}
				
				if(Gluten==true) {
					for(Customer c: Model.Restaurant.getInstance().getCustomers().values()) {
						if(c.equals(customers.getValue())) {
							c.setSensitiveToGluten(true);
							flag=true;
						}
					}
				}
				
				if(Gluten!=true) {
					for(Customer c: Model.Restaurant.getInstance().getCustomers().values()) {
						if(c.equals(customers.getValue())) {
							c.setSensitiveToGluten(false);
							flag=true;
						}
					}
				}
				
				if(flag) {
					Model.Restaurant.savaData();
					Model.Restaurant.LoadDB();
					initialize(null,null);
					others.MyMethods.infoMessage("Success!", "Customer has been updated.");
				}
				
				else {
					throw new FailedToException("edit");
				} 
				
			}

			else if(birthday.getValue().getYear()>2003) {
				throw new illegalAgeException();
			}
			
		

		}
		catch(EmptyFieldException e) {
			e.getMessage();
		}

		catch(illegalAgeException e) {
			e.getMessage();
		}
    	
    	catch(FailedToException e) {
    		e.getMessage();
    	}
    	
    	catch(WrongBirthdayDate e) {
    		e.getMessage();
    	}

    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Gender> gendeR =  FXCollections.observableArrayList(Gender.Male, Gender.Female, Gender.Unknown);
		gender.getItems().addAll(gendeR);


		ObservableList<Neighberhood> neighberHood = FXCollections.observableArrayList(Neighberhood.Bat_Galim,Neighberhood.Denya,Neighberhood.DownTown,Neighberhood.French_Karmel,Neighberhood.German_Colony,Neighberhood.Hadar,Neighberhood.Halisa,Neighberhood.Kababir,Neighberhood.Karmelia,Neighberhood.Kiriat_Eliezer,Neighberhood.Kiriat_Haim,Neighberhood.Merkaz_Karmel,Neighberhood.Neot_Peres,Neighberhood.Neve_David,Neighberhood.Neve_Shanan,Neighberhood.Neve_Yosef,Neighberhood.Ramat_Almogi,Neighberhood.Ramat_Hanasi,Neighberhood.Romema,Neighberhood.Vadi_Nisnas,Neighberhood.Vadi_Saliv,Neighberhood.Vardia);
		neighberhood.getItems().addAll(neighberHood);

		HashMap<Integer,Customer> returned = Model.Restaurant.getInstance().getCustomers();
		
		for(Customer c: returned.values()) {
			customers.getItems().add(c);
		}
		
	}

}
