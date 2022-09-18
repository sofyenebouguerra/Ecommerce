package exam.portal.tn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exam.portal.tn.entities.Product;
import exam.portal.tn.repository.ProductRepository;
@Service
public class ProductServiceImpl implements IProductServices {

	@Autowired
	ProductRepository productRepository;
	
	
	@Override
	public Product addProduct(Product pro) {
		return productRepository.save(pro);
	}

}
