package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

import Exceptions.EmptyFieldException;
import Exceptions.FailedToException;
import Exceptions.SelectedAllException;
import Exceptions.WrongDeliveryDate;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
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

public class EditDeliveriesController implements Initializable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private AnchorPane screen;

	@FXML
	private Button editdeliveries;

	@FXML
	private ComboBox<Delivery> deliveries;

	@FXML
	private Button back;

	@FXML
	private Button logout;

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
	private Parent parent;

	@FXML
	LocalDate date = LocalDate.now();

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
	void editdeliveries(ActionEvent event) throws EmptyFieldException, FailedToException, WrongDeliveryDate, SelectedAllException {

		try {

			boolean flag=false;
			if(deliveries.getValue()==null&& deliveryperson.getValue()==null&& deliveryarea.getValue()==null&&deliveredDATE.getValue()==null)
				throw new EmptyFieldException();

			if(isdelivered.isSelected()&&notdelivered.isSelected())
				throw new SelectedAllException();

			if(deliveredDATE.getValue().isAfter(date))
				throw new WrongDeliveryDate();

			if(deliveryperson.getValue()!=null) {

				for(Delivery d: Model.Restaurant.getInstance().getDeliveries().values())
				{
					if(d.equals(deliveries.getValue())) {
						d.setDeliveryPerson(deliveryperson.getValue());
						flag=true;
					}
				}
			}

			if(deliveryarea.getValue()!=null) {

				for(Delivery d: Model.Restaurant.getInstance().getDeliveries().values())
				{
					if(d.equals(deliveries.getValue())) {
						d.setArea(deliveryarea.getValue());
						flag=true;
					}
				}
			}
			
			if(deliveredDATE.getValue()!=null) {

				for(Delivery d: Model.Restaurant.getInstance().getDeliveries().values())
				{
					if(d.equals(deliveries.getValue())) {
						d.setDeliveredDate(deliveredDATE.getValue());
						flag=true;
					}
				}
			}
			
			if(isdelivered.isSelected())
			{
				for(Delivery d: Model.Restaurant.getInstance().getDeliveries().values())
				{
					if(d.equals(deliveries.getValue())) {
						d.setDelivered(true);
						flag=true;
					}
				}
			}
			
			if(notdelivered.isSelected())
			{
				for(Delivery d: Model.Restaurant.getInstance().getDeliveries().values())
				{
					if(d.equals(deliveries.getValue())) {
						d.setDelivered(false);
						flag=true;
					}
				}
			}
			
			if(flag) {
				Model.Restaurant.savaData();
				Model.Restaurant.LoadDB();
				initialize(null,null);
				others.MyMethods.infoMessage("Success!", "Delivery has been updated.");
			}
			
			else {
				throw new FailedToException("edit");
			} 
				
		}
		catch(EmptyFieldException e) {
			e.getMessage();
		}

		catch(SelectedAllException e) {
			e.getMessage();
		}
		
		catch(WrongDeliveryDate e) {
			e.getMessage();
		}
		
		catch(FailedToException e) {
			e.getMessage();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		HashMap<Integer,Delivery> returned = Model.Restaurant.getInstance().getDeliveries();

		for(Delivery d: returned.values()) {
			deliveries.getItems().add(d);
		}

		HashMap<Integer, DeliveryPerson> delP= Model.Restaurant.getInstance().getDeliveryPersons();

		for(DeliveryPerson dp: delP.values()) {
			deliveryperson.getItems().add(dp);
		}

		HashMap<Integer, DeliveryArea> delA = Model.Restaurant.getInstance().getAreas();

		for(DeliveryArea da: delA.values())
		{
			deliveryarea.getItems().add(da);
		}

	}

}
