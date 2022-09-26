package exam.portal.tn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import exam.portal.tn.entities.Product;
import exam.portal.tn.entities.User;
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	Product findByProductName(String productName);
	
	
	
}
