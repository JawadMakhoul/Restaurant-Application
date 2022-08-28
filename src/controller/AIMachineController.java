package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Exceptions.EmptyFieldException;
import Exceptions.FailedToException;
import Model.Component;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.Dish;
import Model.Order;
import javafx.application.Platform;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AIMachineController implements Initializable, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
    private AnchorPane screen;

    @FXML
    private Button createAI;

    @FXML
    private ComboBox<DeliveryPerson> deliveryperson;

    @FXML
    private ComboBox<DeliveryArea> deliveryarea;

    @FXML
    private Button back;

    @FXML
    private Button logout;

    @FXML
    private ListView<Order> orders;
    
    private HashMap<Integer, Order> AllOrders;
	private TreeSet<Order> selected;
    
    @FXML
    private Parent parent;

    @FXML
    void CreateAIMachine(ActionEvent event) throws EmptyFieldException, FailedToException {
    	
    	try {
    		if(deliveryperson.getValue()==null||deliveryarea.getValue()==null||orders.getSelectionModel().getSelectedItems()==null)
        		throw new EmptyFieldException();
        	
        	ObservableList<Order> Orders = orders.getSelectionModel().getSelectedItems();
    		selected.addAll(Orders);
    		
    		TreeSet<Delivery> returned = Model.Restaurant.getInstance().createAIMacine(deliveryperson.getValue(), deliveryarea.getValue(), selected);
        	
    		if(!returned.isEmpty()) {
    			Model.Restaurant.savaData();
				Model.Restaurant.LoadDB();
				initialize(null,null);
    			others.MyMethods.infoMessage("Success!", "Successfully created");
    		}
    		else {
    			throw new FailedToException("create");
    		}

    	}
    	catch(EmptyFieldException e) {
    		e.getMessage();
    	}
    	catch(FailedToException e) {
    		e.getMessage();
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
		
		AllOrders = Model.Restaurant.getInstance().getOrders();


		for(Order o: AllOrders.values())
		{
			orders.getItems().add(o);
		}
		//orders.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
	}

}
