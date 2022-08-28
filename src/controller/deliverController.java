package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import Model.Delivery;
import javafx.application.Platform;
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

public class deliverController implements Serializable, Initializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
    private AnchorPane screen;

    @FXML
    private Button Delivered;

    @FXML
    private ComboBox<Delivery> deliveries;

    @FXML
    private Button back;

    @FXML
    private Button logout;
    
    @FXML
    private Parent parent;

    @FXML
    void Delivered(ActionEvent event) {
    	
    	if(deliveries.getValue()!=null)
    		Model.Restaurant.getInstance().deliver(deliveries.getValue());
    			

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
		HashMap<Integer,Delivery> returned = Model.Restaurant.getInstance().getDeliveries();
		
		for(Delivery d: returned.values())
		{
			deliveries.getItems().add(d);
		}
		
		
		
	}

}
