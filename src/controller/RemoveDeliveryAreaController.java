package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import Exceptions.EmptyFieldException;
import Exceptions.FailedToException;
import Exceptions.NothingIsSelectedException;
import Exceptions.idNotFoundException;
import Model.DeliveryArea;
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

public class RemoveDeliveryAreaController implements Serializable,Initializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private Parent parent;

	@FXML
	private AnchorPane screen;

	@FXML
	private Button removedeliveryarea;

	@FXML
	private ComboBox<DeliveryArea> oldDelievryArea;

	@FXML
	private Button back;

	@FXML
	private Button logout;

	@FXML
	private ComboBox<DeliveryArea> newDeliveryArea;

	@FXML
	void removedeliveryarea(ActionEvent event) throws idNotFoundException, NothingIsSelectedException, FailedToException {

		try {
			if(!Model.Restaurant.getInstance().getAreas().containsKey(oldDelievryArea.getValue().getId()))
				throw new idNotFoundException();

			else if(oldDelievryArea.getValue().equals(null))
				throw new NothingIsSelectedException();

			else if(Model.Restaurant.getInstance().removeDeliveryArea(oldDelievryArea.getValue(), newDeliveryArea.getValue())) 
			{
				Model.Restaurant.savaData();
				Model.Restaurant.LoadDB();
				initialize(null,null);
				others.MyMethods.infoMessage("Success!", "Successfully removed");	
			}

			else throw new FailedToException("remove");
		}
		catch(idNotFoundException e) {
			e.getMessage();
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

		HashMap<Integer,DeliveryArea> DA=Model.Restaurant.getInstance().getAreas();

		for(DeliveryArea da: DA.values()) {
			oldDelievryArea.getItems().add(da);
			newDeliveryArea.getItems().add(da);
		}

	}

}
