package exam.portal.tn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import exam.portal.tn.entities.Product;
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	
	
	
	
}
