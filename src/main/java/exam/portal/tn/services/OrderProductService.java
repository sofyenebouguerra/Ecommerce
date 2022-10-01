package exam.portal.tn.services;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import exam.portal.tn.entities.OrderProduct;



@Validated
public interface OrderProductService {
  OrderProduct create(@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct);
}
