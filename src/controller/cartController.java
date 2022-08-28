package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeSet;
import Exceptions.FailedToException;
import Exceptions.NothingIsSelectedException;
import Model.Customer;
import Model.Order;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class cartController implements Serializable, Initializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		HashMap<Customer, TreeSet<Order>> ordersByCustomer=Model.Restaurant.getInstance().getOrderByCustomer();
		
		for(Customer c: ordersByCustomer.keySet()) {
			if(c.equals(others.MyMethods.getCustomer())) {
				dishesINcart.getItems().addAll(ordersByCustomer.get(c));
			}
		}
		
	}

    @FXML
    private AnchorPane screen;

    @FXML
    private Button logout;

    @FXML
    private Button back;

    @FXML
    private ListView<Order> dishesINcart;

    @FXML
    private Button checkout;
    
    @FXML
    private Button removeorder;

    
    @FXML
    private Parent parent;

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

    @FXML
    void checkout(ActionEvent event) {
    	
    	others.MyMethods.infoMessage("Done!", "your order has been placed successfully");

    }
    
    @FXML
	void removeorder(ActionEvent event) {

		try {
			if(dishesINcart.getSelectionModel().getSelectedItem()==null)
				throw new NothingIsSelectedException();

			else if(Model.Restaurant.getInstance().removeOrder(dishesINcart.getSelectionModel().getSelectedItem()))
			{
				Model.Restaurant.savaData();
				Model.Restaurant.LoadDB();
				initialize(null,null);
				others.MyMethods.infoMessage("Success!", "Successfully removed");	
			}

			else throw new FailedToException("remove");
		}
		catch(NothingIsSelectedException e) {
			e.getMessage();
		}
		
		catch(FailedToException e) {
			e.getMessage();
		}

	}


}
