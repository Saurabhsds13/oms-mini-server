package in.dmart.oms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import in.dmart.oms.models.OrderDetails;
import in.dmart.oms.repository.IOrderDetailRepository;

@Service
public class OrderDetailsServiceImpl implements IOrderDetailsService {

	private final IOrderDetailRepository iorderDetailRepository;

	public OrderDetailsServiceImpl(IOrderDetailRepository iorderDetailRepository) {
		this.iorderDetailRepository = iorderDetailRepository;
	}

	public List<OrderDetails> getAllOrderDetails() {
		return iorderDetailRepository.findAll();
	}
}
