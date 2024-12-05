package in.dmart.oms.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dmart.oms.models.Inventory;
import in.dmart.oms.services.InventoryServiceImpl;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {

	InventoryServiceImpl inventoryService;

	public InventoryController(InventoryServiceImpl inventoryService) {
		this.inventoryService = inventoryService;
	}

	@GetMapping("/getAllInventory")
	public List<Inventory> getAllInventory() {
		return inventoryService.getAllInventories();
	}
}