package main;
	
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Model.Restaurant;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;

public class Main extends Application implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Restaurant res;

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Splash.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Restaurant restaurant;
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		
		res=Restaurant.getInstance();
		launch(args);
	}
	
	
	
//	public static void Serializable() {
//		try {
//			FileOutputStream file = new FileOutputStream("Rest.ser");
//			ObjectOutputStream out = new ObjectOutputStream(file);
//			restaurant = Restaurant.getInstance();
//			out.writeObject(restaurant);
//			out.close();
//			file.close();
//			System.out.println("The restaurant was serialized successfully");
//		}
//		
//		catch (IOException e){
//			e.printStackTrace();
//		}
//	}
//	
//	public static void deserialize() {
//		try {
//			FileInputStream file = new FileInputStream("Rest.ser");
//			ObjectInputStream in = new ObjectInputStream(file);
//			restaurant = (Restaurant)in.readObject();
//			in.close();
//			file.close();
//			System.out.println("The restaurant was deserialized successfully");
//		}
//		
//		catch (IOException e){
//			e.printStackTrace();
//		}
//		catch (ClassNotFoundException e){
//			e.printStackTrace();
//		}
//	}
//	
//	public static void Update() {
//		try {
//			FileOutputStream file = new FileOutputStream("Rest.ser");
//			ObjectOutputStream out = new ObjectOutputStream(file);
//			out.writeObject(Main.restaurant);
//			out.close();
//			file.close();
//			
//		}
//		catch (IOException e){
//			e.printStackTrace();
//		}
//	}
	
}
