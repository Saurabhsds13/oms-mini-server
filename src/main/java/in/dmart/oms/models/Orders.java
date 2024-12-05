package in.dmart.oms.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	private Date order_date;
	private Double amount;
	private String location;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Orders(int order_id, Customer customer, Date order_date, Double amount, String location) {
		super();
		this.order_id = order_id;
		this.customer = customer;
		this.order_date = order_date;
		this.amount = amount;
		this.location = location;
	}

	public Orders() {
		super();
	}

	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", customer=" + customer + ", order_date=" + order_date + ", amount="
				+ amount + ", location=" + location + "]";
	}

}
