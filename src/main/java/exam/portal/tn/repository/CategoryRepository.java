package exam.portal.tn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exam.portal.tn.entities.Category;



public interface CategoryRepository extends JpaRepository<Category, Long>  {

}
