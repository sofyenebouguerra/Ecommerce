package exam.portal.tn.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import exam.portal.tn.entities.Category;
import exam.portal.tn.entities.Product;
import exam.portal.tn.entities.User;
import exam.portal.tn.repository.CategoryRepository;
import exam.portal.tn.repository.ProductRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ProductServiceImpl implements IProductServices {
	


	@Autowired
	private CategoryRepository categoryRepository;

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
		
		return productRepository.findByProductName(productName);
	}


	@Override
	public Product updatePro(Product product) {
		return productRepository.save(product);
	}


	@Override
	public String exportReport() throws JRException, FileNotFoundException {
	
		
		        String path = "C:\\Users\\BAZINFO\\JaspersoftWorkspace\\MyReports";
		        List<Product> products =  (List<Product>) productRepository.findAll() ;
		        //load file and compile it
		        File file = ResourceUtils.getFile("classpath:Prod.jrxml");
		        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(products);
		        Map<String, Object> parameters = new HashMap<>();
		        parameters.put("createdBy", "Sofyene Bouguerra");
		        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		       /* if (reportFormat.equalsIgnoreCase("html")) {
		            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\questions.html");
		        }*/
		    
		            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Prod.pdf");
		        

		        return "report generated in path : " + path;
		    }


	@Override
	public Product addProductToCategory(Product product, long idCategory) {
		Category category = categoryRepository.findById(idCategory).orElse(null);
		category.addProductToCategory(product);
		return productRepository.save(product);
	}



	@Override
	public Product editProduct(Product product, long id) {
		Product existProduct = productRepository.findById(id).orElse(null);
		existProduct.setProductName(product.getProductName());
		existProduct.setProductDescription(product.getProductDescription());
		existProduct.setFileName(product.getFileName());
		existProduct.setProductActualPrice(product.getProductActualPrice());
		existProduct.setProductDiscountPrice(product.getProductDiscountPrice());
		return productRepository.save(existProduct);
	}

	@Override
	public Product findProductById(long productId) {
		return productRepository.findById(productId).orElse(null);
	}

	@Override
	public void deleteProduct(long id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<Product> findAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public List<Product> findProductsForCategory(long idCategory) {
		Category category = categoryRepository.findById(idCategory).orElse(null);
		return category.getProducts();
	}

	@Override
	public Product getProduct(Long id) {
		return productRepository.findById(id).orElse(null);
	}

			
}
