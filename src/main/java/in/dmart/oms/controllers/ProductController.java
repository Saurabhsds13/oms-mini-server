package in.dmart.oms.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dmart.oms.models.Product;
import in.dmart.oms.services.ProductServiceImpl;

@RestController
@RequestMapping("api/product")
public class ProductController {

	private final ProductServiceImpl productService;

	public ProductController(ProductServiceImpl productService) {
		this.productService = productService;
	}

	@GetMapping("/getAllproducts")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{productId}")
	public Product getByID(@PathVariable int productId) {
		return productService.getProductById(productId);
	}

	@PostMapping("/saveProduct")
	public Product saveProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}

	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
		boolean isDeleted = productService.deleteProduct(productId);

		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body("Item with Product ID " + productId + " has been successfully deleted.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Item with Product ID " + productId + " not found.");
		}
	}

	@GetMapping("/customer/{customer_id}/products")
	public ResponseEntity<?> getProductByCustomerLocation(@PathVariable int customer_id) {

		try {
			List<Product> products = productService.getProductsByCustomerLocation(customer_id);
			if (products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Product is Found for Customer Location");
			} else
				return ResponseEntity.ok(products);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}