package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.TreeSet;
import Model.Component;
import Model.Cook;
import Model.Customer;
import Model.Dish;
import Model.Order;
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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomerMainPageController implements Serializable, Initializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private AnchorPane screen;

	@FXML
	private Button addorder;

	@FXML
	private Button cart;

	@FXML
	private Button removeorder;

	@FXML
	private Button relevantdishlist;

	@FXML
	private Button cookbyexpert;

	@FXML
	private Button popularcomponent;

	@FXML
	private Button menu;

	@FXML
	private Button logout;

	@FXML
	private ComboBox<String> expertise;

	@FXML
	private Button editProfile;

	@FXML
	private Parent parent;

	@FXML
	void Add(MouseEvent event) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					parent = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
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
	void Remove(MouseEvent event) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					parent = FXMLLoader.load(getClass().getResource("/view/Cart.fxml"));
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
	void cart(ActionEvent event) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					parent = FXMLLoader.load(getClass().getResource("/view/Cart.fxml"));
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
	void cookbyexpert(ActionEvent event) {

		if(expertise.getValue().equals("American")) {
			ArrayList<Cook> CookBYexpertise=Model.Restaurant.getInstance().GetCooksByExpertise(Expertise.American);
			String str="";

			for(int i=0;i<CookBYexpertise.size();i++)
				str=CookBYexpertise.get(i).toString()+"\n";   


			others.MyMethods.infoMessage("Cooks by Expertise: ", str);
		}

		if(expertise.getValue().equals("Mediterranean")) {
			ArrayList<Cook> CookBYexpertise=Model.Restaurant.getInstance().GetCooksByExpertise(Expertise.Mediterranean);
			String str="";

			for(Cook c: CookBYexpertise)
				str=c.toString()+"\n";


			others.MyMethods.infoMessage("Cooks by Expertise: ", str);
		}

		if(expertise.getValue().equals("Italien")) {
			ArrayList<Cook> CookBYexpertise=Model.Restaurant.getInstance().GetCooksByExpertise(Expertise.Italien);
			String str="";

			for(Cook c: CookBYexpertise)
				str=c.toString()+"\n";


			others.MyMethods.infoMessage("Cooks by Expertise: ", str);
		}

		if(expertise.getValue().equals("Indian")) {
			ArrayList<Cook> CookBYexpertise=Model.Restaurant.getInstance().GetCooksByExpertise(Expertise.Indian);
			String str="";

			for(Cook c: CookBYexpertise)
				str=c.toString()+"\n";


			others.MyMethods.infoMessage("Cooks by Expertise: ", str);
		}

		if(expertise.getValue().equals("Asian")) {
			ArrayList<Cook> CookBYexpertise=Model.Restaurant.getInstance().GetCooksByExpertise(Expertise.Asian);
			String str="";

			for(Cook c: CookBYexpertise)
				str=c.toString()+"\n";


			others.MyMethods.infoMessage("Cooks by Expertise: ", str);
		}

		if(expertise.getValue().equals("French")) {
			ArrayList<Cook> CookBYexpertise=Model.Restaurant.getInstance().GetCooksByExpertise(Expertise.French);
			String str="";

			for(Cook c: CookBYexpertise)
				str=c.toString()+"\n";


			others.MyMethods.infoMessage("Cooks by Expertise: ", str);
		}



	}

	@FXML
	void editProfile(ActionEvent event) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					parent = FXMLLoader.load(getClass().getResource("/view/EditProfile.fxml"));
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
	void menu(ActionEvent event) {
//		HashMap<Integer, Dish> returned = Model.Restaurant.getInstance().getDishes();
//		String str="";
//		for(Integer index: returned.keySet())
//			str+=returned.get(index).toString()+"\n";
//		others.MyMethods.infoMessage("The Menu is: ", str);
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					parent = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
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
	void popularcomponent(ActionEvent event) {

		LinkedList<Component> returned = Model.Restaurant.getInstance().getPopularComponents();
		String str="";

		for(int i=0;i<returned.size();i++)
		{
			str+=returned.get(i).getComponentName();
		}

		others.MyMethods.infoMessage("The Popular Components is: ", str);

	}

	@FXML
	void relevantdishlist(ActionEvent event) {

		    	Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							parent = FXMLLoader.load(getClass().getResource("/view/getRelevantDishList.fxml"));
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
		expertise.getItems().addAll(Types);

		//	        AllComponents = Model.Restaurant.getInstance().getComponenets();
		//			for(Component comp: AllComponents.values())
		//			{
		//				components.getItems().add(comp);
		//			}
		//			
		//			AllDishes = Model.Restaurant.getInstance().getDishes();
		//			for(Dish d: AllDishes.values())
		//			{
		//				dishes.getItems().add(d);
		//			}
		
	}

}
