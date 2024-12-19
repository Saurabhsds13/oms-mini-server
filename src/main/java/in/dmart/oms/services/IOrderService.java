package in.dmart.oms.services;

import java.util.List;

import in.dmart.oms.dto.ProductPurchaseDTO;
import in.dmart.oms.models.Orders;

public interface IOrderService {

	public Orders buyProducts(List<ProductPurchaseDTO> productPurchaseDTO, int customerId);

	public List<Orders> getAllOrders();
}
