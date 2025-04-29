package in.dmart.oms.services;

import java.util.List;

import in.dmart.oms.models.OrderDetails;

public interface IOrderDetailsService {

	public List<OrderDetails> getAllOrderDetails();

	public OrderDetails getOrderDetailById(int orderId);
}
