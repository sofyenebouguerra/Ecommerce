package exam.portal.tn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exam.portal.tn.entities.Order;



public interface OrderRepository extends JpaRepository<Order, Long> {

}
