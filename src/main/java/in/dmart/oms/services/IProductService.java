package in.dmart.oms.services;

import java.util.List;

import in.dmart.oms.models.Product;

public interface IProductService {
	Product getProductById(int productId);

	List<Product> getAllProducts();

	Product saveProduct(Product product);

	Product updateProduct(int productId, Product product);

	boolean deleteProduct(int productId);

	// other than CRUD
	List<Product> getProductsByCustomerLocation(int customerId);
}
