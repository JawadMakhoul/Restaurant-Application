package Model;

import java.time.LocalDate;

public class ExpressDelivery extends Delivery {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Order order;
	private double postage;
	
	public ExpressDelivery(DeliveryPerson deliveryPerson, DeliveryArea area,
			boolean isDelivered , Order order , double postage, LocalDate deliveredDate) {
		super(deliveryPerson, area, isDelivered, deliveredDate);
		this.order = order;
		this.postage = postage;
	}
	
	public ExpressDelivery(DeliveryPerson deliveryPerson, DeliveryArea area,
			boolean isDelivered , Order order, LocalDate deliveredDate) {
		super(deliveryPerson, area, isDelivered,deliveredDate);
		this.order = order;
		this.postage = 5.0;
	}
	
	public ExpressDelivery(int id) {
		super(id);
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public double getPostage() {
		return postage;
	}
	
	public void setPostage(double postage) {
		this.postage = postage;
	}
	
	@Override
	public String toString() {
		return "Express delivery [id=" + this.getId() + ", deliveryPerson=" + this.getDeliveryPerson() + ", area=" + this.getArea() + ", isDelivered="
				+ this.isDelivered()  + " postage=" + postage + "]";
	}	
}
