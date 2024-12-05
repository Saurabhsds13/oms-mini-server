package in.dmart.oms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import in.dmart.oms.models.Inventory;
import in.dmart.oms.repository.IInventoryRepository;

@Service
public class InventoryServiceImpl implements IInventoryService {

	private final IInventoryRepository inventoryRepository;

	public InventoryServiceImpl(IInventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}

	@Override
	public Inventory getInventoryById(int inventoryId) {
		return inventoryRepository.findById(inventoryId).orElseThrow();
	}

	@Override
	public List<Inventory> getAllInventories() {
		return inventoryRepository.findAll();
	}

	@Override
	public Inventory saveInventory(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	@Override
	public Inventory updateInventory(int inventoryId, Inventory inventory) {
		Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryId);
		if (optionalInventory.isPresent()) {

			Inventory existingInventory = optionalInventory.get();
			existingInventory.setDemand(inventory.getDemand());
			existingInventory.setAvailable(inventory.getAvailable());
			existingInventory.setLocation(inventory.getLocation());
			existingInventory.setSupply(inventory.getSupply());

			return inventoryRepository.save(existingInventory);
		} else
			return null;
	}

	@Override
	public void deleteInventory(int inventoryId) {
		inventoryRepository.deleteById(inventoryId);
		System.out.println("Article" + inventoryId + " is Deleted Successfully");
	}
}