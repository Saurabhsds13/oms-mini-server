package in.dmart.oms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.dmart.oms.models.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

	// Define custom query for getting products by customer location
	@Query(value = "select * from product WHERE product_id IN (select prodcut_id from inventory WHERE location = :location)", nativeQuery = true)
	List<Product> findProductsByCustomerLocation(@Param("location") String location);
}