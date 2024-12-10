package in.dmart.oms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import in.dmart.oms.exception.CustomerNotFoundException;
import in.dmart.oms.models.Customer;
import in.dmart.oms.models.Product;
import in.dmart.oms.repository.ICustomerRepository;
import in.dmart.oms.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

	private final IProductRepository productRepository;
	private final ICustomerRepository customerRepository;

	ProductServiceImpl(IProductRepository productRepository, ICustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;

	}

	@Override
	public Product getProductById(int productId) {
		return productRepository.findById(productId).orElse(null);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(int productId, Product product) {

		Optional<Product> optionalProduct = productRepository.findById(productId);

		if (optionalProduct.isPresent()) {
			Product existingProduct = optionalProduct.get();
			existingProduct.setName(product.getName());
			existingProduct.setDescription(product.getDescription());
			existingProduct.setPrice(product.getPrice());
			return productRepository.save(existingProduct);
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteProduct(int productId) {
		Optional<Product> product = productRepository.findById(productId);

		if (product.isPresent()) {
			productRepository.deleteById(productId);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Product> getProductsByCustomerLocation(int customerId) {

		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

		if (optionalCustomer.isPresent()) {
			Customer customer = optionalCustomer.get();
			String location = customer.getLocation();
			return productRepository.findProductsByCustomerLocation(location);

		} else {
			throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
		}
	}

}
