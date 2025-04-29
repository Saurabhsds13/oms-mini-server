package in.dmart.oms.response;

import java.util.List;

import in.dmart.oms.models.Inventory;

public class InventoryResponse {

	public String message;
	public List<Inventory> inventory;
	

	public InventoryResponse(String message) {
		this.message = message;
		this.inventory = List.of();
	}

	public InventoryResponse(String message, List<Inventory> inventory) {
		this.message = message;
		this.inventory = inventory;
	}

	public List<Inventory> getInventory() {
		return inventory;
	}

	public String getMessage() {
		return message;
	}

}
