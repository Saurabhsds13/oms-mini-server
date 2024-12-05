package in.dmart.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.dmart.oms.models.Orders;

@Repository
public interface IOrderRepository extends JpaRepository<Orders, Integer> {

}
