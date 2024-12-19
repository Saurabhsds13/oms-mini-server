package in.dmart.oms.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dmart.oms.models.OrderDetails;
import in.dmart.oms.services.IOrderDetailsService;

import java.util.List;	

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/orderDetails")
public class OrderDetailsController {

	@Autowired
	IOrderDetailsService iorderDetailsService;

	public OrderDetailsController(IOrderDetailsService iorderDetailsService) {
		this.iorderDetailsService = iorderDetailsService;
	}

	@GetMapping("/getAllOrderDetails")
	public List<OrderDetails> getAllOrderDetails() {
		return iorderDetailsService.getAllOrderDetails();
	}

}
