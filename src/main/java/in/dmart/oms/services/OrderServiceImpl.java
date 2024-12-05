package in.dmart.oms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import in.dmart.oms.dto.ProductPurchaseDTO;
import in.dmart.oms.models.Customer;
import in.dmart.oms.models.Inventory;
import in.dmart.oms.models.OrderDetails;
import in.dmart.oms.models.Orders;
import in.dmart.oms.models.Product;
import in.dmart.oms.repository.ICustomerRepository;
import in.dmart.oms.repository.IInventoryRepository;
import in.dmart.oms.repository.IOrderDetailRepository;
import in.dmart.oms.repository.IOrderRepository;
import in.dmart.oms.repository.IProductRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	private final IInventoryRepository inventoryRepository;
	private final IOrderRepository orderRepository;
	private final ICustomerRepository customerRepository;
	private final IProductRepository productRepository;
	private final IOrderDetailRepository orderDetailRepository;

	public OrderServiceImpl(IInventoryRepository inventoryRepository, IOrderRepository orderRepository,
			ICustomerRepository customerRepository, IProductRepository productRepositoryl,
			IOrderDetailRepository orderDetailRepository) {
		this.inventoryRepository = inventoryRepository;
		this.orderRepository = orderRepository;
		this.customerRepository = customerRepository;
		this.productRepository = productRepositoryl;
		this.orderDetailRepository = orderDetailRepository;
	}

	@Override
	public Orders buyProducts(List<ProductPurchaseDTO> productPurchaseDTO, int customerId) {

		boolean flag = false;
		double amount = 0.0;
		String location = null;

		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

		if (optionalCustomer.isPresent()) {
			Customer customer = optionalCustomer.get();
			location = customer.getLocation();
		}

		for (ProductPurchaseDTO dto : productPurchaseDTO) {

			Inventory inventory = inventoryRepository.findByProductIdAndLocation(dto.getProductId(), location);
			if (inventory != null && inventory.getAvailable() >= dto.getQuantity()) {

				flag = true;
				// to change the demand quantity
				int newDmand = inventory.getDemand() + dto.getQuantity();
				inventory.setDemand(newDmand);

				// to change available quantity
				int newAvailable = inventory.getSupply() - newDmand;
				inventory.setAvailable(newAvailable);

				inventoryRepository.save(inventory);

				// to set amount
				Product newProduct = productRepository.findById(dto.getProductId())
						.orElseThrow(() -> new RuntimeException("Product Not Found"));

				Product product = newProduct;
				amount = product.getPrice() * dto.getQuantity();
			}
		}

		// Create Order
		Orders order = createOrder(amount, customerId, location);

		// Create Order Details
		if (flag) {
			for (ProductPurchaseDTO dto : productPurchaseDTO) {
				createOrderDetails(dto, order); // calls n time for every order line
			}
		}

		return order;
	}

	// for order creation (only one record per transaction)
	private Orders createOrder(double amount, int customerId, String location) {
		Orders order = new Orders();
		order.setAmount(amount);
		order.setLocation(location);

		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer Not Found"));
		order.setCustomer(customer);
		return order = orderRepository.save(order);

	}

	// for the orderDetails creations (multiple records per transaction)
	private OrderDetails createOrderDetails(ProductPurchaseDTO dto, Orders order) {

		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setQuantity(dto.getQuantity());

		Product product = productRepository.findById(dto.getProductId())
				.orElseThrow(() -> new RuntimeException("Product Not Found"));
		if (product != null) {
			orderDetails.setUnit_price(product.getPrice());
		} else {
			orderDetails.setUnit_price(0);
		}

		orderDetails.setProduct(product);
		orderDetails.setOrder(order);
		orderDetailRepository.save(orderDetails);
		return orderDetails;
	}
}
