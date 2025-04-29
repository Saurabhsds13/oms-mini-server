package in.dmart.oms.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dmart.oms.models.Inventory;
import in.dmart.oms.response.InventoryResponse;
import in.dmart.oms.services.InventoryServiceImpl;

@RestController
@RequestMapping("api/inventory")
@CrossOrigin(origins = "http://localhost:3000")
public class InventoryController {

	InventoryServiceImpl inventoryService;

	public InventoryController(InventoryServiceImpl inventoryService) {
		this.inventoryService = inventoryService;
	}

	@GetMapping("/getAllInventory")
	public ResponseEntity<InventoryResponse> getAllInventory() {
		List<Inventory> inventory = inventoryService.getAllInventories();

		if (ObjectUtils.isEmpty(inventory)) {
			return ResponseEntity.ok(new InventoryResponse("Inventory Not Found", List.of()));
		} else {
			return ResponseEntity.ok(new InventoryResponse("All Item's Retrive Succesfully", inventory));
		}
	}
}