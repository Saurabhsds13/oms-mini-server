package in.dmart.oms.response;

import java.util.List;

import in.dmart.oms.models.Orders;

public class OrderResponse {

	private String message;
	private List<Orders> orders;

	// Constructor for when there are no orders
	public OrderResponse(String message) {
		this.message = message;
		this.orders = List.of();
	}

	// Constructor for when there are orders
	public OrderResponse(String message, List<Orders> orders) {
		this.message = message;
		this.orders = orders;
	}

	public String getMessage() {
		return message;
	}

	public List<Orders> getOrder() {
		return orders;
	}
}
