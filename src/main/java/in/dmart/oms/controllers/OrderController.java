package in.dmart.oms.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dmart.oms.dto.ProductPurchaseDTO;
import in.dmart.oms.models.Orders;
import in.dmart.oms.response.OrderResponse;
import in.dmart.oms.services.IOrderService;
import in.dmart.oms.services.OrderServiceImpl;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final IOrderService orderService;

	public OrderController(OrderServiceImpl orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/purchase/{id}")
	public ResponseEntity<Orders> purchaseProducts(@RequestBody List<ProductPurchaseDTO> productPurchaseDTOs,
			@PathVariable int id) {

		Orders order = orderService.buyProducts(productPurchaseDTOs, id);
		if (order != null) {
			return new ResponseEntity<>(order, HttpStatus.CREATED);
		} else {
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getAllOrders")
	public ResponseEntity<OrderResponse> getAllOrders() {

		List<Orders> orders = orderService.getAllOrders();

		if (orders.isEmpty()) {
			return ResponseEntity.ok(new OrderResponse("No Orders Found.", List.of()));

		} else
			return ResponseEntity.ok(new OrderResponse("All Order's Retrive Succesfully", orders));
	}
}