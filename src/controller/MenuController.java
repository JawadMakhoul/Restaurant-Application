package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import Model.Dish;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController implements Serializable, Initializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
    private AnchorPane screen;

    @FXML
    private Button logout;

    @FXML
    private Button back;
    
    @FXML
    private Parent parent;

    @FXML
    private ListView<Dish> menu;
    private HashMap<Integer,Dish> AllDishes;
    private ArrayList<Dish> Dishes;
    
    @FXML
    private Button adddish;

    @FXML
    void adddish(ActionEvent event) {

    	for(Dish d: menu.getSelectionModel().getSelectedItems())
    		Dishes.add(d);
    	Model.Restaurant.getInstance().getRealCustomer(others.MyMethods.getCustomer().getId()).setDishes(Dishes);
    	Model.Restaurant.savaData();
		Model.Restaurant.LoadDB();
		initialize(null,null);
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
		AllDishes = Model.Restaurant.getInstance().getDishes();


		for(Dish d: AllDishes.values())
		{
			menu.getItems().add(d);
		}
		

		
	}

}
