package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import Exceptions.EmptyFieldException;
import Exceptions.FailedToException;
import Exceptions.illegalPriceException;
import Exceptions.illegalTimeToMakeException;
import Model.Component;
import Model.Dish;
import Utils.DishType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class AddDishController implements Initializable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ObservableList<DishType> dishType = FXCollections.observableArrayList(DishType.Dessert,DishType.Main,DishType.Starter);
		type.getItems().addAll(dishType);

		AllComponents = Model.Restaurant.getInstance().getComponenets();


		for(Component comp: AllComponents.values())
		{
			components.getItems().add(comp);
		}
		components.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}

	@FXML
	private Button adddish;

	@FXML
	private TextField dishname;

	@FXML
	private TextField price;

	@FXML
	private ComboBox<DishType> type;

	@FXML
	private TextField timetomake;

	@FXML
	private Button logout;

	@FXML
	private Button back;

	@FXML
	private AnchorPane screen;

	@FXML
	private Parent parent;

	@FXML
	private ListView<Component> components;

	private HashMap<Integer, Component> AllComponents;
	private ArrayList<Component> selected;



	@FXML
	void addDish(ActionEvent event) throws illegalPriceException, EmptyFieldException, illegalTimeToMakeException {

		try{
			int IntPrice = Integer.parseInt(price.getText());System.out.println("bbbbbb");
			int Time = Integer.parseInt(timetomake.getText());System.out.println("nnnnnnnn");
			double DoubleTime = Double.parseDouble(timetomake.getText());
			System.out.println("mmmmm");
			if(dishname.getText().equals("")||price.getText().equals("")||type.getValue()==null|| components.getSelectionModel().getSelectedItems()==null || timetomake.getText().equals(""))
				{System.out.println("qqqqqqq");throw new EmptyFieldException();}
			if(IntPrice<=0)
				{System.out.println("aaaaaaaaaa");throw new illegalPriceException();}
			if(DoubleTime<=0)
				{System.out.println("zzzzzzzz");throw new illegalTimeToMakeException();}

			ObservableList<Component> comps = components.getSelectionModel().getSelectedItems();System.out.println("ffffffffff");
			selected.addAll(comps);
			System.out.println("xxxxxxxxxxx");
			boolean flag = Model.Restaurant.getInstance()
					.addDish(new Dish(dishname.getText(), type.getValue(), selected,Time));//adds the object
			System.out.println("rrrrrrrrrrrrrrr");
			if (flag) {System.out.println("pppppppppppp");
				Model.Restaurant.savaData();System.out.println("wwwwwwwwwww");
				Model.Restaurant.LoadDB();System.out.println("eeeeeeeeeeee");
				initialize(null,null);System.out.println("...............");
				others.MyMethods.infoMessage("Success!", "Successfully added");System.out.println("yyyyyyyyyyyy");
			} else {System.out.println("ssssssssssss");
				throw new FailedToException("add");
			}
		}

		catch (EmptyFieldException e) {
			e.getMessage();
		}

		catch(illegalPriceException e) {
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

}


