package exam.portal.tn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import exam.portal.tn.dto.OrderProductDto;
import exam.portal.tn.entities.Order;
import exam.portal.tn.entities.OrderProduct;
import exam.portal.tn.entities.OrderStatus;
import exam.portal.tn.helper.ResourceNotFoundException;
import exam.portal.tn.services.IProductServices;
import exam.portal.tn.services.OrderProductService;
import exam.portal.tn.services.OrderService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	IProductServices productService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderProductService orderProductService;
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public @NotNull Iterable<Order> list(){
		return this.orderService.getAllOrders();
	}
	
	@PostMapping("/orders")
	public ResponseEntity<Order> create(@RequestBody OrderForm form){
		List<OrderProductDto> formDtos = form.getpOrderProductDtos();
		validateProductsExistance(formDtos);
		Order order = new Order();
		order.setStatus(OrderStatus.PAID.name());
		order = this.orderService.create(order);
		
		List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
		for(OrderProductDto dto: formDtos) {
			orderProducts.add(orderProductService.create(new OrderProduct(order, productService.getProduct(
					dto.getProduct().getProductId()), dto.getQuantity())));
		}
		order.setOrderProducts(orderProducts);
		this.orderService.update(order);
		
		String uri = ServletUriComponentsBuilder
				.fromCurrentServletMapping()
				.path("/orders/{id}")
				.buildAndExpand(order.getId())
				.toString();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", uri);
		
		return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
	
	}

	private void validateProductsExistance(List<OrderProductDto> orderProducts) {
		List<OrderProductDto> list = orderProducts
				.stream()
				.filter(op -> Objects.isNull(productService.getProduct(op.getProduct().getProductId())))
				.collect(Collectors.toList());
		if (!CollectionUtils.isEmpty(list)) {
			new ResourceNotFoundException("Product not found");
			
		}
		
		
	}
	
	public  static class OrderForm {
		private List<OrderProductDto> pOrderProductDtos;

		public List<OrderProductDto> getpOrderProductDtos() {
			return pOrderProductDtos;
		}

		public void setpOrderProductDtos(List<OrderProductDto> pOrderProductDtos) {
			this.pOrderProductDtos = pOrderProductDtos;
		}
		
	}
	

}
