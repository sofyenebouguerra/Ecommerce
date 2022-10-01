package exam.portal.tn.services;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import exam.portal.tn.entities.OrderProduct;
import exam.portal.tn.repository.OrderProductRepository;



@Transactional
@Component
public class OrderProductServiceImpl implements OrderProductService {
	
	@Autowired
	private OrderProductRepository orderProductRepository;

	@Override
	public OrderProduct create(
			@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct) {
		return this.orderProductRepository.save(orderProduct);
	}

}
