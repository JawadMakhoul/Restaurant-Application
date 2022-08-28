package controller;

import java.io.IOException;
import java.io.Serializable;
import Exceptions.EmptyFieldException;
import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Model.Component;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddComponentController implements Serializable{

	private static final long serialVersionUID = 1L;

	@FXML
    private AnchorPane screen;
	
	@FXML
    private Parent parent;
	
	@FXML
    private Button addcomp;
	
	@FXML
    private Button logout;

    @FXML
    private Button back;

    @FXML
    private TextField compname;

    @FXML
    private TextField compprice;
    
    @FXML
    private CheckBox lactose;

    @FXML
    private CheckBox gluten;

    @FXML
    void addcomp(ActionEvent event) {
    	
		try {
			if (compname.getText().equals("") || compprice.getText().equals(""))//checks if there's empty fields
				throw new EmptyFieldException();
			
			double componentPrice = Double.parseDouble(compprice.getText());
			
			
			/* checks if any of the checkBox is selected */
			if(lactose.isSelected()&&gluten.isSelected()) { 
				
				boolean flag = Model.Restaurant.getInstance()
						.addComponent(new Component(compname.getText(), lactose.isSelected(), gluten.isSelected(), componentPrice));//adds the object

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
			
			else if(lactose.isSelected()&&!gluten.isSelected()) {
				
				boolean flag = Model.Restaurant.getInstance()
						.addComponent(new Component(compname.getText(), lactose.isSelected(), false, componentPrice));//adds the object

				if (flag) {
					Model.Restaurant.savaData();
					Model.Restaurant.LoadDB();
					//initialize(null,null);
					others.MyMethods.infoMessage("Success!", "Successfully added"); /* pop-up internal message */
				} else {
					throw new FailedToException("add");
				}
			}
			
			else if(!lactose.isSelected()&&gluten.isSelected()) {
				boolean flag = Model.Restaurant.getInstance()
						.addComponent(new Component(compname.getText(), false, gluten.isSelected(), componentPrice));//adds the object

				if (flag) {
					Model.Restaurant.savaData();
					Model.Restaurant.LoadDB();
					//initialize(null,null);
					others.MyMethods.infoMessage("Success!", "Successfully added");
				} else {
					throw new FailedToException("add");
				}
			}
			
			else if(!lactose.isSelected()&&!gluten.isSelected()) {
				boolean flag = Model.Restaurant.getInstance()
						.addComponent(new Component(compname.getText(), false, false, componentPrice));//adds the object

				if (flag) {
					Model.Restaurant.savaData();
					Model.Restaurant.LoadDB();
					//initialize(null,null);
					others.MyMethods.infoMessage("Success!", "Successfully added");
				} else {
					throw new FailedToException("add");
				}
			}
			
			compname.setText(null);
			compprice.setText(null);
			lactose.setSelected(false);
			gluten.setSelected(false);

		} catch (NumberFormatException e) {
			others.MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {//errorMessage is the class dad of most exceptions 
			e.getMessage();//a pop up error message
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
