package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import Exceptions.EmptyFieldException;
import Exceptions.ErrorMessage;
import Exceptions.UserNameNotFoundException;
import Exceptions.wrongPasswordException;
import Model.Customer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RestaurantController implements Initializable, Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private Parent parent;


	@FXML
	private MediaView mediaView;

	@FXML
	private Button close;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Button login;

	@FXML
	private Button signup;

	@FXML
	private AnchorPane screen;

	@FXML
	private Button result;

	private Media media;
	private MediaPlayer backgroundvideo;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (backgroundvideo == null) {//starts the background video
			media = new Media(getClass().getResource("/Background-Video.mp4").toExternalForm());

			backgroundvideo = new MediaPlayer(media);
			mediaView.setMediaPlayer(backgroundvideo);

			backgroundvideo.setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					backgroundvideo.seek(Duration.ZERO);
					backgroundvideo.play();
				}
			});
			backgroundvideo.play();
		}
		mediaView.toBack();
	}

	@FXML
	void login(ActionEvent event) throws EmptyFieldException {//this method check the user is a manager or a customer 
		//the method checks for exceptions first

		try {
			if (username.getText()==null || password.getText()==null)
				throw new EmptyFieldException();
			
			if (username.getText().equals("manager")&& password.getText().equals("manager"))
			{
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

			else if(username.getText().equals("manager")&& !password.getText().equals("manager"))
				throw new wrongPasswordException();

			else if(!username.getText().equals("manager")) {
				for(Customer cust: Model.Restaurant.getInstance().getCustomers().values())
				{
					if(cust!=null)
					{
						

						if(cust.getUserName().equals(username.getText())&& cust.getPassword().equals(password.getText()))
						{
							others.MyMethods.setCustomer(cust);
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									try {
										parent = FXMLLoader.load(getClass().getResource("/view/CustomerMainPage.fxml"));
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

						if(cust.getUserName().equals(username.getText())&& !cust.getPassword().equals(password.getText()))
							throw new wrongPasswordException();

						else if(!cust.getUserName().equals(username.getText()))
							throw new UserNameNotFoundException();
					}
				}
			}

		} catch (EmptyFieldException e) {
			e.setTitle("Error");
		}catch (wrongPasswordException e) {
			e.getMessage();
		}catch (UserNameNotFoundException e) {
			e.getMessage();
		}catch (ErrorMessage e) {
			e.getMessage();
		}

	}


	@FXML
	void signup(ActionEvent event) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					parent = FXMLLoader.load(getClass().getResource("/view/Signup.fxml"));
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

}
