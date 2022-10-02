package exam.portal.tn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import exam.portal.tn.entities.Product;
import exam.portal.tn.entities.User;
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	Product findByProductName(String productName);
	
    @Query("SELECT p FROM Product p WHERE p.productName LIKE :n")
    List<Product> findByProductName1(@Param("n") String productName);
	
}
