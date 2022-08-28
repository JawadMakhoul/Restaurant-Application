package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Exceptions.EmptyFieldException;
import Model.Delivery;
import Model.DeliveryPerson;
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
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class getDeliveryByPersonController implements Initializable, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
    private AnchorPane screen;

    @FXML
    private Button getdeliverybyperson;

    @FXML
    private ComboBox<DeliveryPerson> deliveryPerson;

    @FXML
    private ComboBox<Integer> month;

    @FXML
    private Button back;

    @FXML
    private Button logout;
    

    @FXML
    private ListView<Delivery> result;

    
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
    void getdeliverybyperson(ActionEvent event) {
    	
    	try {
    		if(deliveryPerson.getValue()==null || month.getValue()==null)
    			throw new EmptyFieldException();
    		
    		if(deliveryPerson!=null && month !=null)
        	{
        		TreeSet<Delivery> returned = Model.Restaurant.getInstance().getDeliveriesByPerson(deliveryPerson.getValue(), month.getValue());
        		
        		for(Delivery d: returned) {
        			result.getItems().add(d);
        		}
        	}
    	}
    	
    	catch(EmptyFieldException e) {
    		e.getMessage();
    	}
    	
    	
    	

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<Integer> months = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);
		month.getItems().addAll(months);
		
	}

}
