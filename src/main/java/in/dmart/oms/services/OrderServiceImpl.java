package in.dmart.oms.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.dmart.oms.dto.ProductPurchaseDTO;
import in.dmart.oms.exception.ProductNotFoundException;
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
@Transactional
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

		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer Not Found"));
		String location = customer.getLocation();

		double totalAmount = 0.0;
		List<OrderDetails> orderDetailsList = new ArrayList<>();

		for (ProductPurchaseDTO dto : productPurchaseDTO) {

			Inventory inventory = inventoryRepository.findByProductIdAndLocation(dto.getProductId(), location);
			if (inventory == null || inventory.getAvailable() < dto.getQuantity()) {
				throw new RuntimeException("Insufficient stock for product ID: " + dto.getProductId());
			}
			// Update inventory
			inventory.setDemand(inventory.getDemand() + dto.getQuantity());
			inventory.setAvailable(inventory.getAvailable() - dto.getQuantity());
			inventory.setSupply(inventory.getSupply() - dto.getQuantity());
			inventoryRepository.save(inventory);

			// Fetch product
			Product product = productRepository.findById(dto.getProductId())
					.orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
			// Calculate order details and amount
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setProduct(product);
			orderDetails.setQuantity(dto.getQuantity());
			orderDetails.setUnit_price(product.getPrice());
			orderDetailsList.add(orderDetails);

			totalAmount += product.getPrice() * dto.getQuantity();
		}

		// Create Order (only one record per transaction)
		Orders order = new Orders();
		order.setAmount(totalAmount);
		order.setLocation(location);
		order.setCustomer(customer);
		order = orderRepository.save(order);

		// Save order details (multiple records per transaction)
		for (OrderDetails orderDetails : orderDetailsList) {
			orderDetails.setOrder(order);
			orderDetailRepository.save(orderDetails);
		}

		return order;
	}

}
