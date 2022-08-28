package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditProfileController implements Serializable,Initializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
    private AnchorPane screen;


    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<Gender> gender;

    @FXML
    private ComboBox<Neighberhood> neighberhood;

    @FXML
    private PasswordField repassword;

    @FXML
    private Button editProfile;

    @FXML
    private Button back;

    @FXML
    private Button logout;
    
    @FXML
    private Parent parent;


    @FXML
    void editProfile(ActionEvent event) {
    	
    	
    	if(gender.getValue()!=null|| neighberhood.getValue()!=null || password.getText()!=null)
    	{
    		if(gender.getValue()!=null)
        		Model.Restaurant.getInstance().getRealCustomer(others.MyMethods.getCustomer().getId()).setGender(gender.getValue());
        	
        	if(neighberhood!=null)
        		Model.Restaurant.getInstance().getRealCustomer(others.MyMethods.getCustomer().getId()).setNeighberhood(neighberhood.getValue());
        	
        	if(password.getText()!=null&&repassword.getText()!=null&&password.getText().equals(repassword.getText()))
        		Model.Restaurant.getInstance().getRealCustomer(others.MyMethods.getCustomer().getId()).setPassword(password.getText());
        	
        	
        	Model.Restaurant.savaData();
			Model.Restaurant.LoadDB();
			initialize(null,null);
        	others.MyMethods.infoMessage("Success", "Profile has been updated");
    	}
    	
    	
    	if(gender.getValue()==null && neighberhood.getValue()==null && password.getText()==null && repassword.getText()==null)
    		others.MyMethods.infoMessage("info", "All the fields are empty. nothing has been updated");
    	

    }
    
    @FXML
    void back(ActionEvent event) {
    	Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					parent = FXMLLoader.load(getClass().getResource("/view/CustomerMainPage.fxml"));
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

		ObservableList<Neighberhood> neighberHood = FXCollections.observableArrayList(Neighberhood.Bat_Galim,Neighberhood.Denya,Neighberhood.DownTown,Neighberhood.French_Karmel,Neighberhood.German_Colony,Neighberhood.Hadar,Neighberhood.Halisa,Neighberhood.Kababir,Neighberhood.Karmelia,Neighberhood.Kiriat_Eliezer,Neighberhood.Kiriat_Haim,Neighberhood.Merkaz_Karmel,Neighberhood.Neot_Peres,Neighberhood.Neve_David,Neighberhood.Neve_Shanan,Neighberhood.Neve_Yosef,Neighberhood.Ramat_Almogi,Neighberhood.Ramat_Hanasi,Neighberhood.Romema,Neighberhood.Vadi_Nisnas,Neighberhood.Vadi_Saliv,Neighberhood.Vardia);
		neighberhood.getItems().addAll(neighberHood);
		
	}

}
