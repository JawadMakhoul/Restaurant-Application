package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import Exceptions.FailedToException;
import Exceptions.NothingIsSelectedException;
import Model.Dish;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class RemoveDishController implements Serializable,Initializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private Parent parent;

	@FXML
	private AnchorPane screen;

	@FXML
	private Button removedish;

	@FXML
	private ComboBox<Dish> dishes;

	@FXML
	private Button back;

	@FXML
	private Button logout;



	@FXML
	void removedish(ActionEvent event) {

		try {
			if(dishes.getValue()==null)
				throw new NothingIsSelectedException();

			else if(Model.Restaurant.getInstance().removeDish(dishes.getValue()))
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

		HashMap<Integer,Dish> returned = Model.Restaurant.getInstance().getDishes();

		for(Dish d: returned.values()) {
			dishes.getItems().add(d);
		}

	}

}
