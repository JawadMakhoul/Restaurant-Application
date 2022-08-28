package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;

import Exceptions.EmptyFieldException;
import Exceptions.FailedToException;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Utils.Neighberhood;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddDeliveryAreaController implements Serializable, Initializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@FXML
    private AnchorPane screen;

    @FXML
    private Button adddeliveryArea;

    @FXML
    private TextField deliveryAreaname;

    @FXML
    private Button logout;

    @FXML
    private Button back;

    @FXML
    private ComboBox<DeliveryPerson> deliveryPerson;

    @FXML
    private ComboBox<Delivery> delivery;

    @FXML
    private TextField delivertime;

    @FXML
    private ListView<Neighberhood> neighberhoods;

	@FXML
	private Parent parent;



	@FXML
	void adddeliveryArea(ActionEvent event) throws FailedToException {

		try {
			if(deliveryAreaname.getText()==null && deliveryPerson.getValue()==null && delivery.getValue()==null && neighberhoods.getSelectionModel().getSelectedItem()==null)
				throw new EmptyFieldException();
			
			ObservableList<Neighberhood> neigh= neighberhoods.getSelectionModel().getSelectedItems();
			HashSet<Neighberhood> hashNeighberhoods=new HashSet<Neighberhood>();
			for(Neighberhood n : neigh) {
				hashNeighberhoods.add(n);
			}
			
			int T=Integer.parseInt(delivertime.getText());
			boolean flag=Model.Restaurant.getInstance().addDeliveryArea(new DeliveryArea(deliveryAreaname.getText(),hashNeighberhoods,T));
			
			if (flag) {
				{
					Model.Restaurant.savaData();
					Model.Restaurant.LoadDB();
					//initialize(null,null);
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
		// TODO Auto-generated method stub
		HashMap<Integer, DeliveryPerson> dp=Model.Restaurant.getInstance().getDeliveryPersons();

		for(DeliveryPerson dep: dp.values())
			deliveryPerson.getItems().add(dep);


		HashMap<Integer, Delivery> d= Model.Restaurant.getInstance().getDeliveries();
		for(Delivery de: d.values())
			delivery.getItems().add(de);


		for(Neighberhood n: Neighberhood.values())
			neighberhoods.getItems().addAll(n);
	}

}


