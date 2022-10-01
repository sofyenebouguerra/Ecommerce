package exam.portal.tn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exam.portal.tn.entities.OrderProduct;



public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

}
