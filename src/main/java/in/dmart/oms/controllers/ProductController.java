package in.dmart.oms.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dmart.oms.models.Product;
import in.dmart.oms.services.ProductServiceImpl;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

	@PostMapping
	public Product saveProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}

	
	@DeleteMapping("/{productId}")
	public void deleteProduct(@PathVariable int productId ) {
	productService.deleteProduct(productId);
	}
	
	@GetMapping("/customer/{customer_id}")
	public List<Product> getProductByCustomerLocation(@PathVariable int customer_id){
		return productService.getProductsByCustomerLocation(customer_id);
		
	}
}
