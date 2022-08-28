package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Exceptions.NothingIsSelectedException;
import Model.Cook;
import Utils.Expertise;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class getCooksByExpertiseController implements Serializable, Initializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
    private AnchorPane screen;

    @FXML
    private Button getcooksbyexpertise;

    @FXML
    private ComboBox<String> expertises;

    @FXML
    private Button back;

    @FXML
    private Button logout;
    
    @FXML
    private Parent parent;

    @FXML
    void getcookbyexpertise(ActionEvent event) {
    	
    	try {
    		if(expertises.getValue()==null)
    			throw new NothingIsSelectedException();
    		
    		if(expertises.getValue().equals("American")) {
        		ArrayList<Cook> CookBYexpertise=Model.Restaurant.getInstance().GetCooksByExpertise(Expertise.American);
            	String str="";
            	
            	for(int i=0;i<CookBYexpertise.size();i++)
            		str=CookBYexpertise.get(i).toString()+"\n";   
            		 
            	
            	others.MyMethods.infoMessage("Cooks by Expertise: ", str);
        	}
        	
        	if(expertises.getValue().equals("Mediterranean")) {
        		ArrayList<Cook> CookBYexpertise=Model.Restaurant.getInstance().GetCooksByExpertise(Expertise.Mediterranean);
            	String str="";
            	
            	for(Cook c: CookBYexpertise)
            		str=c.toString()+"\n";
         
            	
            	others.MyMethods.infoMessage("Cooks by Expertise: ", str);
        	}
        	
        	if(expertises.getValue().equals("Italien")) {
        		ArrayList<Cook> CookBYexpertise=Model.Restaurant.getInstance().GetCooksByExpertise(Expertise.Italien);
            	String str="";
            	
            	for(Cook c: CookBYexpertise)
            		str=c.toString()+"\n";
         
            	
            	others.MyMethods.infoMessage("Cooks by Expertise: ", str);
        	}
        	
        	if(expertises.getValue().equals("Indian")) {
        		ArrayList<Cook> CookBYexpertise=Model.Restaurant.getInstance().GetCooksByExpertise(Expertise.Indian);
            	String str="";
            	
            	for(Cook c: CookBYexpertise)
            		str=c.toString()+"\n";
         
            	
            	others.MyMethods.infoMessage("Cooks by Expertise: ", str);
        	}
        	
        	if(expertises.getValue().equals("Asian")) {
        		ArrayList<Cook> CookBYexpertise=Model.Restaurant.getInstance().GetCooksByExpertise(Expertise.Asian);
            	String str="";
            	
            	for(Cook c: CookBYexpertise)
            		str=c.toString()+"\n";
         
            	
            	others.MyMethods.infoMessage("Cooks by Expertise: ", str);
        	}
        	
        	if(expertises.getValue().equals("French")) {
        		ArrayList<Cook> CookBYexpertise=Model.Restaurant.getInstance().GetCooksByExpertise(Expertise.French);
            	String str="";
            	
            	for(Cook c: CookBYexpertise)
            		str=c.toString()+"\n";
         
            	
            	others.MyMethods.infoMessage("Cooks by Expertise: ", str);
        	}
    	}
    	
    	catch(NothingIsSelectedException e) {
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 ObservableList<String> Types = FXCollections.observableArrayList("American", "Asian", "French","Indian","Italien","Mediterranean");
	        expertises.getItems().addAll(Types);
	}

}
