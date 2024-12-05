package in.dmart.oms.services;

import java.util.List;

import in.dmart.oms.models.Inventory;

public interface IInventoryService {

	Inventory getInventoryById(int inventoryId);

	List<Inventory> getAllInventories();

	Inventory saveInventory(Inventory inventory);

	Inventory updateInventory(int inventoryId, Inventory inventory);

	void deleteInventory(int inventoryId);
}