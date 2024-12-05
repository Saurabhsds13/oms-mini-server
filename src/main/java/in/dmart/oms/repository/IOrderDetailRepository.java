package in.dmart.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.dmart.oms.models.OrderDetails;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetails, Integer> {

}
