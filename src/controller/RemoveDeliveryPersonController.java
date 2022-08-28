package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import Exceptions.FailedToException;
import Exceptions.NothingIsSelectedException;
import Model.DeliveryPerson;
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

public class RemoveDeliveryPersonController implements Serializable,Initializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@FXML
	private Parent parent;

	@FXML
	private AnchorPane screen;


	@FXML
	private Button removedeliveryperson;

	@FXML
	private ComboBox<DeliveryPerson> deliveryperson;

	@FXML
	private Button back;

	@FXML
	private Button logout;

	@FXML
	void removedeliveryperson(ActionEvent event) {

		try {
			if(deliveryperson.getValue()==null)
				throw new NothingIsSelectedException();
			if(Model.Restaurant.getInstance().removeDeliveryPerson(deliveryperson.getValue()))
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

		HashMap<Integer,DeliveryPerson> dP = Model.Restaurant.getInstance().getDeliveryPersons();

		for(DeliveryPerson dp: dP.values())
		{
			deliveryperson.getItems().add(dp);
		}


	}

}
