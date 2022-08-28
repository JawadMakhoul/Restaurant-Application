package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Exceptions.EmptyFieldException;
import Model.Customer;
import Model.Order;
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

public class getOrderByCustomerController implements Initializable, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
    private AnchorPane screen;

    @FXML
    private Button getOrderByCustomer;

    @FXML
    private ComboBox<Customer> customers;

    @FXML
    private ComboBox<Order> orders;

    @FXML
    private Button back;

    @FXML
    private Button logout;
    
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
    void getOrderByCustomer(ActionEvent event) throws EmptyFieldException {
    	
    	HashMap<Customer, TreeSet<Order>> returned = Model.Restaurant.getInstance().getOrderByCustomer();
    	String str="";
    	
    	if(customers.getValue()!=null&&orders.getValue()!=null) {
    		
    		for(Customer c: returned.keySet())
        	{
        		if(c.equals(customers.getValue()))
        		{
        			for(Order o: returned.get(c))
        			{
        				if(o.equals(orders.getValue()))
        					str+="\n" + o.toString();
        			}
        		}
        	}
        	
        	others.MyMethods.infoMessage(str, ".");
    	}
    	
    	else throw new EmptyFieldException();
    	

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<Customer> functions = FXCollections.observableArrayList(Model.Restaurant.getInstance().getCustomers().values());
		customers.getItems().addAll(functions);
		
	}

   

}
