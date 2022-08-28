package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Exceptions.ErrorMessage;
import Exceptions.NothingIsSelectedException;
import Model.Component;
import Model.Dish;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ManagerMainPageController implements Initializable, Serializable{

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
	private Button add;

	@FXML
	private Button numberofdeliverybyperson;

	@FXML
	private ComboBox<String> choosefunction;

	@FXML
	private Button remove;

	@FXML
	private Button deliverybyperson;

	@FXML
	private Button relevantdishlist;

	@FXML
	private Button cookbyexpert;

	@FXML
	private Button popularcomponent;

	@FXML
	private Button deliver;

	@FXML
	private Button edit;

	@FXML
	private Button revenuefromexpressdelivery;

	@FXML
	private Button profitrelation;

	@FXML
	private Button AImachine;

	@FXML
	private Button logout;

	@FXML
	private Parent parent;
	
	@FXML
    private Button numberofdeliveriesbyperson;

    @FXML
    private Button orderbydeliveryarea;

    @FXML
    private Button orderbycustomer;


	@FXML
	void AddRemoveEdit(MouseEvent event) throws NothingIsSelectedException {

		
		if(choosefunction.getValue()!=null) {
			switch(choosefunction.getValue()) {

			case "Component": 
			{
				if(add.isPressed()) 
				{

					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/AddComponent.fxml"));
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

				else if(remove.isPressed()) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/RemoveComponent.fxml"));
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

				else if(edit.isPressed()) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/EditComponents.fxml"));
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

				break;

			}

			case "Cook": 
			{
				if(add.isPressed()) 
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/AddCook.fxml"));
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

				else if(remove.isPressed()) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {	
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/RemoveCook.fxml"));
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

				else if(edit.isPressed()) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/EditCooks.fxml"));
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

				break;

			}

			case "Customer": 
			{
				if(add.isPressed()) 
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
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

				else if(remove.isPressed()) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {	
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/RemoveCustomer.fxml"));
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

				else if(edit.isPressed()) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/EditCustomer.fxml"));
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

				break;

			}
			
			case "Blacklist Customer": 
			{
				if(add.isPressed()) 
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/EditBlacklist.fxml"));
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

				else if(remove.isPressed()) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {	
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/EditBlacklist.fxml"));
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

				else if(edit.isPressed()) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/EditBlacklist.fxml"));
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

				break;

			}
			
			case "Delivery Area": 
			{
				if(add.isPressed()) 
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/AddDeliveryArea.fxml"));
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

				else if(remove.isPressed()) 
				{
					Platform.runLater(new Runnable() {	
						@Override
						public void run() {	
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/RemoveDeliveryArea.fxml"));
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

				else if(edit.isPressed()) 
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/EditDeliveryArea.fxml"));
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

				break;

			}

			case "Delivery": 
			{
				if(add.isPressed()) 
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/AddDelivery.fxml"));
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

				else if(remove.isPressed()) 
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/RemoveDelivery.fxml"));
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

				else if(edit.isPressed()) 
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/EditDeliveries.fxml"));
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

				break;

			}

			case "Delivery Person": 
			{
				if(add.isPressed()) 
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/AddDeliveryPerson.fxml"));
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

				else if(remove.isPressed()) 
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/RemoveDeliveryPerson.fxml"));
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

				else if(edit.isPressed()) 
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/EditDeliveryPerson.fxml"));
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

				break;

			}

			case "Dish": 
			{
				if(add.isPressed()) 
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/AddDish.fxml"));
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

				else if(remove.isPressed()) 
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/RemoveDish.fxml"));
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

				else if(edit.isPressed()) 
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/EditDish.fxml"));
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

				break;

			}

			case "Order": 
			{
				if(add.isPressed()) 
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/AddOrder.fxml"));
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

				else if(remove.isPressed()) 
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/RemoveOrder.fxml"));
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

				else if(edit.isPressed()) 
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								parent = FXMLLoader.load(getClass().getResource("/view/EditOrder.fxml"));
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

				break;

			}
			
			
			}
		}
		
		else throw new NothingIsSelectedException();
		


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
	void revenuefromexpressdelivery(ActionEvent event) {

		double revenue=Model.Restaurant.getInstance().revenueFromExpressDeliveries();
		String str=revenue+"";
		others.MyMethods.infoMessage("Revenue From Express Delivery", str);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> functions = FXCollections.observableArrayList("Component","Cook","Customer","Blacklist Customer","Delivery Area","Delivery","Delivery Person","Dish","Order");
		choosefunction.getItems().addAll(functions);
	}

    
    @FXML
    void AImachine(ActionEvent event) {
    	
    	Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					parent = FXMLLoader.load(getClass().getResource("/view/CreateAImachine.fxml"));
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

    	Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					parent = FXMLLoader.load(getClass().getResource("/view/getCooksByExpertise.fxml"));
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
    void deliver(ActionEvent event) {

    	Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					parent = FXMLLoader.load(getClass().getResource("/view/deliver.fxml"));
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
    void deliverybyperson(ActionEvent event) {
    	
    	Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					parent = FXMLLoader.load(getClass().getResource("/view/getDeliveriesByPerson.fxml"));
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
    void numberofdeliveriesbyperson(ActionEvent event) {

    	HashMap<String,Integer> returned = Model.Restaurant.getInstance().getNumberOfDeliveriesPerType();
    	String str="";
    	
    	for(String s: returned.keySet())
    	{
    		str+=" " + returned.get(s)+"\n";
    	}
    	
    	others.MyMethods.infoMessage("Number of deliveries by person", str);
    }

    @FXML
    void orderbycustomer(ActionEvent event) {
    	Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					parent = FXMLLoader.load(getClass().getResource("/view/GetOrderByCustomer.fxml"));
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
    void orderbydeliveryarea(ActionEvent event) {
    	
    	Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					parent = FXMLLoader.load(getClass().getResource("/view/GetOrderByDeliveryArea.fxml"));
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
    void popularcomponent(ActionEvent event) throws ErrorMessage {

    	LinkedList<Component> returned = Model.Restaurant.getInstance().getPopularComponents();
    	
    	if(!returned.isEmpty())
    	{
    		for(int i=0;i<returned.size();i++)
    		{
    			others.MyMethods.infoMessage(returned.toString(), ".");
    		}
    	}
    	
    	else throw new ErrorMessage(null);
    }

    @FXML
    void profitrelation(ActionEvent event) throws ErrorMessage {

    	TreeSet<Dish> returned = Model.Restaurant.getInstance().getProfitRelation();
    	
    	if(!returned.isEmpty())
    	{
    		for(int i=0;i<returned.size();i++)
    		{
    			others.MyMethods.infoMessage(returned.toString(), ".");
    		}
    	}
    	
    	else throw new ErrorMessage(null);
    }

    

}

