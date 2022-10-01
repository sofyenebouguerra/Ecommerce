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
	
	


}
