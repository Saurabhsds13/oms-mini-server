package in.dmart.oms.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dmart.oms.models.OrderDetails;
import in.dmart.oms.response.OrderDetailsResponse;
import in.dmart.oms.services.IOrderDetailsService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/orderDetails")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderDetailsController {

	IOrderDetailsService iorderDetailsService;

	public OrderDetailsController(IOrderDetailsService iorderDetailsService) {
		this.iorderDetailsService = iorderDetailsService;
	}

	@GetMapping("/getAllOrderDetails")
	public ResponseEntity<OrderDetailsResponse> getAllOrderDetails() {
		List<OrderDetails> orderDetails = iorderDetailsService.getAllOrderDetails();

		if (ObjectUtils.isEmpty(orderDetails)) {
			return ResponseEntity.ok(new OrderDetailsResponse("No Order Details Found ", List.of()));
		} else {

			return ResponseEntity.ok(new OrderDetailsResponse("All Order Detail's Retive Succesfully", orderDetails));
		}
	}

	@GetMapping("/{orderId}")
	public OrderDetails getOrderDetailsById(@PathVariable int orderId) {
		return iorderDetailsService.getOrderDetailById(orderId);
	}

}
