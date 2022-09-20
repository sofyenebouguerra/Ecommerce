package exam.portal.tn.services;

import java.util.List;

import exam.portal.tn.entities.Product;

public interface IProductServices {

	public Product addProduct(Product pro);
	public List<Product> GetAllPro();
	public void DeleteProDetail(Long productId);
	public Product GetProduct(Long productId);
}
