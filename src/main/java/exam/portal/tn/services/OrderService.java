package exam.portal.tn.services;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import exam.portal.tn.entities.Order;



@Validated
public interface OrderService {
	@NotNull
	Iterable<Order> getAllOrders();
	
	Order create(@NotNull(message = "The order cannot be null.") @Valid Order order);
	void update (@NotNull(message = "The order cannot be null.") @Valid Order order);
}
