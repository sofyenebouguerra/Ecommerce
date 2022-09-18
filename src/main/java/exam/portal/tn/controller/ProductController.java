package exam.portal.tn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exam.portal.tn.entities.Product;
import exam.portal.tn.services.IProductServices;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {
	
	@Autowired
	IProductServices iProductServices;
	
	@PostMapping("/")
	public Product addProduct(@RequestBody Product pro) {
		return iProductServices.addProduct(pro);
		
	}
}
