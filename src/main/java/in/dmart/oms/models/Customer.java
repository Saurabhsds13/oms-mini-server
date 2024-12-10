package in.dmart.oms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customer_id;
	private String name;
	private String email;
	private long phone;
	private String address;
	private String location;

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Customer(int customer_id, String name, String email, long phone, String address, String location) {
		super();
		this.customer_id = customer_id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.location = location;
	}

	public Customer() {
		super();

	}

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", location=" + location + "]";
	}

}
