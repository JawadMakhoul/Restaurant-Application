package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import Exceptions.EmptyFieldException;
import Exceptions.FailedToException;
import Model.Component;
import Model.Customer;
import Model.Delivery;
import Model.Dish;
import Model.Order;
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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddOrderController implements Serializable,Initializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private AnchorPane screen;

	@FXML
	private Button addorder;

	@FXML
	private ListView<Dish> dishes;

	private HashMap<Integer, Dish> AllDishes;
	private ArrayList<Dish> selectedDishes;

	@FXML
	private ListView<Delivery> deliveries;
	private HashMap<Integer, Delivery> AllDeliveries;

	@FXML
	private ListView<Customer> customer;
	private HashMap<Integer, Customer> AllCustomers;

	@FXML
	private Button logout;

	@FXML
	private Button back;

	@FXML 
	private Parent parent;

	@FXML
	void Addorder(ActionEvent event) throws EmptyFieldException, FailedToException {
		try {
			if(customer.getSelectionModel().getSelectedItems()==null|| deliveries.getSelectionModel().getSelectedItems()==null|| selectedDishes.isEmpty())
				throw new EmptyFieldException();

			
			ObservableList<Dish> selected = dishes.getSelectionModel().getSelectedItems();
			selectedDishes.addAll(selected);
			
			boolean flag= Model.Restaurant.getInstance().addOrder(new Order(customer.getSelectionModel().getSelectedItem(),selectedDishes,deliveries.getSelectionModel().getSelectedItem()));

			if (flag) {
				{
					Model.Restaurant.savaData();
					Model.Restaurant.LoadDB();
					initialize(null,null);
					others.MyMethods.infoMessage("Success!", "Successfully added");
				}
			} else {
				throw new FailedToException("add");
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
			dishes.getItems().add(d);
		}
		dishes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		AllDeliveries = Model.Restaurant.getInstance().getDeliveries();
		for(Delivery d: AllDeliveries.values())
		{
			deliveries.getItems().add(d);
		}
		deliveries.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


		AllCustomers = Model.Restaurant.getInstance().getCustomers();
		for(Customer c: AllCustomers.values())
		{
			customer.getItems().add(c);
		}
		customer.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


	}

}
