package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class getNumberOfDeliveriesPerTypeController implements Initializable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private Parent parent;
	@FXML
    private AnchorPane screen;


    @FXML
    private Button getNumber;

    @FXML
    private ComboBox<String> typeofdelivery;
    
    @FXML
    private Button back;

    @FXML
    private Button logout;


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
   
    
    @FXML
    void getNumber(ActionEvent event) {
    	HashMap<String,Integer> returned=Model.Restaurant.getInstance().getNumberOfDeliveriesPerType();
    	String s="";
    	
    	if(typeofdelivery.getValue()=="Regular Delivery") {
    		for(String str: returned.keySet())
        	{
    			if(str=="Regular delivery")
    				s+=returned.get(str)+ " Deliveries of " + str;
        	}
        	others.MyMethods.infoMessage("Number of deliveries per type", s);
    	}
    	
    	else if(typeofdelivery.getValue()=="Express Delivery"){
    		for(String str: returned.keySet())
        	{
    			if(str=="Express delivery")
    				s+=returned.get(str)+ " Deliveries of " + str ;
        	}
        	others.MyMethods.infoMessage("Number of deliveries per type", s);
    	}
    	
    	else others.MyMethods.infoMessage("ERROR!", "Please choose a type");
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

        ObservableList<String> types = FXCollections.observableArrayList("Regular Delivery", "Express Delivery");
        typeofdelivery.getItems().addAll(types);
        
	}

}
