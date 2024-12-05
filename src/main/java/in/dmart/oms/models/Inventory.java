package in.dmart.oms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inventory_id;
	private int demand;
	private int supply;
	private int available;
	private String location;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public int getInventory_id() {
		return inventory_id;
	}

	public void setInventory_id(int inventory_id) {
		this.inventory_id = inventory_id;
	}

	public int getDemand() {
		return demand;
	}

	public void setDemand(int demand) {
		this.demand = demand;
	}

	public int getSupply() {
		return supply;
	}

	public void setSupply(int supply) {
		this.supply = supply;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Inventory(int inventory_id, Product product_id, int demand, int supply, int available, String location) {
		super();
		this.inventory_id = inventory_id;
		this.demand = demand;
		this.supply = supply;
		this.available = available;
		this.location = location;
	}

	public Inventory() {
		super();
	}

	@Override
	public String toString() {
		return "Inventory [inventory_id=" + inventory_id + ", Product_id=" + ", demand=" + demand + ", supply=" + supply
				+ ", available=" + available + ", location=" + location + "]";
	}

}
