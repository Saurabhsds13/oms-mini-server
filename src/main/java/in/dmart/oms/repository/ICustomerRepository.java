package in.dmart.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.dmart.oms.models.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

}
