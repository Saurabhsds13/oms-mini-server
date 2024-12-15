package in.dmart.oms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_details_id;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Orders order;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	private int quantity;
	private double unit_price;

	public int getOrder_details_id() {
		return order_details_id;
	}

	public void setOrder_details_id(int order_details_id) {
		this.order_details_id = order_details_id;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	@Override
	public String toString() {
		return "OrderDetails [order_details_id=" + order_details_id + ", order=" + order + ", product=" + product
				+ ", quantity=" + quantity + ", unit_price=" + unit_price + "]";
	}

	public OrderDetails(int order_details_id, Orders order, Product product, int quantity, double unit_price) {
		super();
		this.order_details_id = order_details_id;
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.unit_price = unit_price;
	}

	public OrderDetails() {
		super();
	}

}