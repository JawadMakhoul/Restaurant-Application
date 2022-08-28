package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import Exceptions.EmptyFieldException;
import Exceptions.FailedToException;
import Exceptions.SelectedAllException;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.RegularDelivery;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddDeliveryController implements Initializable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private AnchorPane screen;

	@FXML
	private Button adddelivery;

	@FXML
	private ComboBox<DeliveryPerson> deliveryperson;

	@FXML
	private ComboBox<DeliveryArea> deliveryarea;

	@FXML
	private CheckBox isdelivered;

	@FXML
	private CheckBox notdelivered;

	@FXML
	private DatePicker deliveredDATE;

	@FXML
	private Button back;

	@FXML
	private Button logout;

	@FXML
	private Parent parent;

	@FXML
	void addDelivery(ActionEvent event) throws SelectedAllException, FailedToException {

		try {
			if(deliveryperson.getValue()==null && deliveryarea.getValue()==null&&deliveredDATE.getValue()==null)
				throw new EmptyFieldException();

			if(isdelivered.isSelected()&&notdelivered.isSelected())
				throw new SelectedAllException();

			if(isdelivered.isSelected())
			{
				boolean flag = Model.Restaurant.getInstance().addDelivery(new RegularDelivery(deliveryperson.getValue(),deliveryarea.getValue(),true,deliveredDATE.getValue()));

				if (flag) {
					Model.Restaurant.savaData();
					Model.Restaurant.LoadDB();
					initialize(null,null);
					others.MyMethods.infoMessage("Success!", "Successfully added");

				} else {
					throw new FailedToException("add");
				}
			}

			if(notdelivered.isSelected())
			{
				boolean flag = Model.Restaurant.getInstance().addDelivery(new RegularDelivery(deliveryperson.getValue(),deliveryarea.getValue(),false,deliveredDATE.getValue()));

				if (flag) {
					Model.Restaurant.savaData();
					Model.Restaurant.LoadDB();
					initialize(null,null);
					others.MyMethods.infoMessage("Success!", "Successfully added");

				} else {
					throw new FailedToException("add");
				}
			}
		}

		catch(EmptyFieldException e) {
			e.getMessage();
		}

		catch(SelectedAllException e) {
			e.getMessage();
		}

		catch (FailedToException e) {
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
		// TODO Auto-generated method stub
		HashMap<Integer, DeliveryPerson> dp=Model.Restaurant.getInstance().getDeliveryPersons();

		for(DeliveryPerson dep: dp.values())
			deliveryperson.getItems().add(dep);

		HashMap<Integer, DeliveryArea> da = Model.Restaurant.getInstance().getAreas();

		for(DeliveryArea dea: da.values())
			deliveryarea.getItems().add(dea);
	}

}
