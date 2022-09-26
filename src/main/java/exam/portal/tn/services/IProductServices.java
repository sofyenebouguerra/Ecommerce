package exam.portal.tn.services;

import java.io.FileNotFoundException;
import java.util.List;

import exam.portal.tn.entities.Product;
import exam.portal.tn.entities.User;
import net.sf.jasperreports.engine.JRException;

public interface IProductServices {

	public Product addProduct(Product pro);
	public List<Product> GetAllPro();
	public void DeleteProDetail(Long productId);
	public Product GetProduct(Long productId);
	 public Product getProductName(String productName);
	 public Product updatePro(Product product);
	 public String exportReport() throws JRException, FileNotFoundException;
}
