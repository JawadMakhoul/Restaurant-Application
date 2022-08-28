package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Exceptions.EmptyFieldException;
import Model.Customer;
import Model.DeliveryArea;
import Model.Order;
import javafx.application.Platform;
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

public class GetOrderByDeliveryAreaController implements Serializable, Initializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
    private AnchorPane screen;

    @FXML
    private Button getOrderByDeliveryArea;

    @FXML
    private ComboBox<DeliveryArea> deliveryarea;

    @FXML
    private ListView<Order> orders;

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
    void getOrderByDeliveryArea(ActionEvent event) {
    	
    	try {
    		if(deliveryarea.getValue()==null)
    			throw new EmptyFieldException();
    		
    		if(deliveryarea.getValue()!=null)
    		{
    			HashMap<DeliveryArea, HashSet<Order>> returned=Model.Restaurant.getInstance().getOrderByDeliveryArea();
    			
    			for(DeliveryArea Da: returned.keySet())
    			{
    				if(Da.equals(deliveryarea.getValue()))
    				{
    					for(Order o: returned.get(Da))
    					{
    						orders.getItems().add(o);
    					}
    				}
    			}
    		}
    		
    		
    	}
    	
    	catch(EmptyFieldException e) {
    		e.getMessage();
    	}

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		HashMap<Integer,DeliveryArea> da = Model.Restaurant.getInstance().getAreas();
		
		for(DeliveryArea dea: da.values())
		{
			deliveryarea.getItems().add(dea);
		}
		
	}

}
