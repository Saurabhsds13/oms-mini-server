package in.dmart.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.dmart.oms.models.Inventory;

@Repository
public interface IInventoryRepository extends JpaRepository<Inventory, Integer> {

	@Query(value = "select * from inventory where product_id =:productId and location =:location", nativeQuery = true)
	Inventory findByProductIdAndLocation(@Param("productId") int productId, @Param("location") String location);
}