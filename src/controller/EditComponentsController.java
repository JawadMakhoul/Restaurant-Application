package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import Exceptions.EmptyFieldException;
import Exceptions.FailedToException;
import Exceptions.NothingIsSelectedException;
import Exceptions.SelectedAllException;
import Model.Component;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditComponentsController implements Initializable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private AnchorPane screen;

	@FXML
	private Button editcomponent;

	@FXML
	private ComboBox<Component> component;

	@FXML
	private Button back;

	@FXML
	private Button logout;

	@FXML
	private TextField firstname;

	@FXML
	private TextField price;

	@FXML
	private CheckBox lactose;

	@FXML
	private CheckBox gluten;

	@FXML
	private CheckBox notGluten;

	@FXML
	private CheckBox notLactose;

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
	void editcomponent(ActionEvent event) throws EmptyFieldException, SelectedAllException, NothingIsSelectedException, FailedToException {

		try {

			boolean flag=false;
			int p = Integer.parseInt(price.getText());
			if(firstname.getText()==null && price.getText()==null && !gluten.isSelected() && !notGluten.isSelected() && !lactose.isSelected() && !notLactose.isSelected())
				throw new EmptyFieldException();

			if(firstname.getText()!=null) {
				for(Component c: Model.Restaurant.getInstance().getComponenets().values())
				{
					if(c.equals(component.getValue()))
					{
						c.setComponentName(firstname.getText());
						flag=true;
					}
				}
			}

			if(price.getText()!=null) {
				for(Component c: Model.Restaurant.getInstance().getComponenets().values())
				{
					if(c.equals(component.getValue()))
					{
						c.setPrice(p);
						flag=true;
					}
				}
			}

			if(gluten.isSelected()&& notGluten.isSelected()) {
				throw new SelectedAllException();
			}

			if(!gluten.isSelected()&& !notGluten.isSelected()) {
				throw new NothingIsSelectedException();
			}

			if(gluten.isSelected()) {
				for(Component c: Model.Restaurant.getInstance().getComponenets().values())
				{
					if(c.equals(component.getValue()))
					{
						c.setHasGluten(true);
						flag=true;
					}
				}
			}

			if(notGluten.isSelected()) {
				for(Component c: Model.Restaurant.getInstance().getComponenets().values())
				{
					if(c.equals(component.getValue()))
					{
						c.setHasGluten(false);
						flag=true;
					}
				}
			}

			if(lactose.isSelected()&& notLactose.isSelected()) {
				throw new SelectedAllException();
			}

			if(!lactose.isSelected()&& !notLactose.isSelected()) {
				throw new NothingIsSelectedException();
			}

			if(lactose.isSelected()) {
				for(Component c: Model.Restaurant.getInstance().getComponenets().values())
				{
					if(c.equals(component.getValue()))
					{
						c.setHasLactose(true);
						flag=true;
					}
				}
			}

			if(notLactose.isSelected()) {
				for(Component c: Model.Restaurant.getInstance().getComponenets().values())
				{
					if(c.equals(component.getValue()))
					{
						c.setHasLactose(false);
						flag=true;
					}
				}
			}

			if(flag)
			{
				Model.Restaurant.savaData();
				Model.Restaurant.LoadDB();
				initialize(null,null);
				others.MyMethods.infoMessage("Success!", "Component has been updated.");
			}
			else throw new FailedToException("edit");


		}
		catch(EmptyFieldException e) {
			e.getMessage();
		}

		catch(SelectedAllException e) {
			e.getMessage();
		}

		catch(NothingIsSelectedException e) {
			e.getMessage();
		}

		catch(FailedToException e) {
			e.getMessage();
		}


	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		HashMap<Integer,Component> comps = Model.Restaurant.getInstance().getComponenets();

		for(Component c: comps.values())
		{
			component.getItems().add(c);
		}

	}

}
