package exam.portal.tn.services;

import java.util.List;

import exam.portal.tn.entities.Product;
import exam.portal.tn.entities.User;

public interface IProductServices {

	public Product addProduct(Product pro);
	public List<Product> GetAllPro();
	public void DeleteProDetail(Long productId);
	public Product GetProduct(Long productId);
	 public Product getProductName(String productName);
	 public Product updatePro(Product product);
}
