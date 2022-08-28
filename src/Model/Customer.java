package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import Utils.Gender;
import Utils.Neighberhood;

public class Customer extends Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int idCounter = 1;
	private Neighberhood neighberhood;
	private boolean isSensitiveToLactose;
	private boolean isSensitiveToGluten;
	private String userName="";
	private String password="";
	
	/* all the dishes that the customer ordered*/
	private ArrayList<Dish> dishes;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer(String firstName, String lastName, LocalDate birthDay, Gender gender,
			Neighberhood neighberhood,	boolean isSensitiveToLactose, boolean isSensitiveToGluten) {
		super(idCounter++, firstName, lastName, birthDay, gender);
		this.neighberhood = neighberhood;
		this.isSensitiveToLactose = isSensitiveToLactose;
		this.isSensitiveToGluten = isSensitiveToGluten;
	}
	
	public Customer(String firstName, String lastName, LocalDate birthDay, Gender gender,
			Neighberhood neighberhood,	boolean isSensitiveToLactose, boolean isSensitiveToGluten, String userName, String password) {
		super(idCounter++, firstName, lastName, birthDay, gender);
		this.neighberhood = neighberhood;
		this.isSensitiveToLactose = isSensitiveToLactose;
		this.isSensitiveToGluten = isSensitiveToGluten;
		this.userName=userName;
		this.password=password;
	}
	
	public Customer(int id) {
		super(id);
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Customer.idCounter = idCounter;
	}

	public Neighberhood getNeighberhood() {
		return neighberhood;
	}

	public void setNeighberhood(Neighberhood neighberhood) {
		this.neighberhood = neighberhood;
	}

	public boolean isSensitiveToLactose() {
		return isSensitiveToLactose;
	}

	public void setSensitiveToLactose(boolean isSensitiveToLactose) {
		this.isSensitiveToLactose = isSensitiveToLactose;
	}

	public boolean isSensitiveToGluten() {
		return isSensitiveToGluten;
	}

	public void setSensitiveToGluten(boolean isSensitiveToGluten) {
		this.isSensitiveToGluten = isSensitiveToGluten;
	}

	@Override
	public String toString() {
		return super.toString()+" Customer [isSensitiveToLactose=" + isSensitiveToLactose + ", isSensitiveToGluten=" + isSensitiveToGluten
				+ "]";
	}

	public ArrayList<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(ArrayList<Dish> dishes) {
		this.dishes = dishes;
	}
}
