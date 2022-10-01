package exam.portal.tn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import exam.portal.tn.entities.Cart;



public interface CartRepository extends JpaRepository<Cart, Long>{
   Optional<Cart> findByName(String name);
}
