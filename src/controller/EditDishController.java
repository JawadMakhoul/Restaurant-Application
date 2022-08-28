package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import Exceptions.EmptyFieldException;
import Exceptions.FailedToException;
import Model.Dish;
import Utils.DishType;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditDishController implements Initializable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private AnchorPane screen;

	@FXML
	private Button editdishes;

	@FXML
	private ComboBox<Dish> dishes;

	@FXML
	private Button back;

	@FXML
	private Button logout;

	@FXML
	private TextField price;

	@FXML
	private ComboBox<DishType> type;

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
	void editdishes(ActionEvent event) throws FailedToException {
		try {

			int p=Integer.parseInt(price.getText());
			boolean flag=false;


			if(dishes.getValue()==null && price.getText()==null && type.getValue()==null)
				throw new EmptyFieldException();

			if(price.getText()!=null)
			{
				for(Dish d: Model.Restaurant.getInstance().getDishes().values())
				{
					if(d.equals(dishes.getValue()))
					{
						d.setPrice(p);
						flag=true;
					}
				}
			}

			if(type.getValue()!=null)
			{
				for(Dish d: Model.Restaurant.getInstance().getDishes().values())
				{
					if(d.equals(dishes.getValue()))
					{
						d.setType(type.getValue());
						flag=true;
					}
				}
			}

			if(flag)
			{
				Model.Restaurant.savaData();
				Model.Restaurant.LoadDB();
				initialize(null,null);
				others.MyMethods.infoMessage("Success!", "Dish has been updated.");
			}
			else throw new FailedToException("edit");

		}

		catch(EmptyFieldException e) {
			e.getMessage();
		}

		catch(FailedToException e) {
			e.getMessage();
		}


	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ObservableList<DishType> dishType = FXCollections.observableArrayList(DishType.Dessert,DishType.Main,DishType.Starter);
		type.getItems().addAll(dishType);

		HashMap<Integer, Dish> alldishes = Model.Restaurant.getInstance().getDishes();

		for(Dish d: alldishes.values()) {
			dishes.getItems().add(d);
		}


	}


}
