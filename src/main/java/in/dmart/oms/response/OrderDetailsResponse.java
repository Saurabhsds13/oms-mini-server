package in.dmart.oms.response;

import java.util.List;

import in.dmart.oms.models.OrderDetails;

public class OrderDetailsResponse {

	public String message;
	public List<OrderDetails> orderDetails;
	public OrderDetails order;

	public OrderDetailsResponse(String message) {
		this.message = message;
		this.orderDetails = List.of();
	}

	public OrderDetailsResponse(String message, List<OrderDetails> orderDetails) {
		this.message = message;
		this.orderDetails = orderDetails;
	}

	public OrderDetailsResponse(String message, OrderDetails order) {
		super();
		this.message = message;
		this.order = order;
	}

	public String getMessage() {
		return message;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

}
