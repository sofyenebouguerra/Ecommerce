package exam.portal.tn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exam.portal.tn.entities.Product;
import exam.portal.tn.entities.User;
import exam.portal.tn.repository.ProductRepository;
@Service
public class ProductServiceImpl implements IProductServices {

	@Autowired
	ProductRepository productRepository;
	
	
	@Override
	public Product addProduct(Product pro) {
		return productRepository.save(pro);
	}


	@Override
	public List<Product> GetAllPro() {
		
		return  (List<Product>) productRepository.findAll();
	}


	@Override
	public void DeleteProDetail(Long productId) {
		productRepository.deleteById(productId);
		
	}


	@Override
	public Product GetProduct(Long productId) {
		
		return productRepository.findById(productId).get();
	}


	@Override
	public Product getProductName(String productName) {
		// TODO Auto-generated method stub
		return productRepository.findByProductName(productName);
	}


	@Override
	public Product updatePro(Product product) {
		return productRepository.save(product);
	}

}
