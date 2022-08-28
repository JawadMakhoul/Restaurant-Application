package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SplashController implements Initializable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@FXML
	private Parent parent;
	@FXML
	private AnchorPane apane;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		splash();
		
	}
	
	private void splash() {
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				}
				catch(Exception e){
					System.out.print(e);
				}
				
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							parent = FXMLLoader.load(getClass().getResource("/view/Restaurant.fxml"));
							Stage stage = new Stage();
							Scene scene = new Scene(parent);
							stage.setScene(scene);
							stage.show();
							apane.getScene().getWindow().hide();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
		}.start();
	}

}
