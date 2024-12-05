package in.dmart.oms.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dmart.oms.dto.ProductPurchaseDTO;
import in.dmart.oms.models.Orders;
import in.dmart.oms.services.IOrderService;
import in.dmart.oms.services.OrderServiceImpl;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final IOrderService orderService;

	public OrderController(OrderServiceImpl orderService) {
		this.orderService = orderService;
	}

	@PutMapping("/purchase/{id}")
	public ResponseEntity<Orders> purchaseProducts(@RequestBody List<ProductPurchaseDTO> productPurchaseDTOs,
			@PathVariable int id) {

		Orders order = orderService.buyProducts(productPurchaseDTOs, id);
		if (order != null) {
			return new ResponseEntity<>(order, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}