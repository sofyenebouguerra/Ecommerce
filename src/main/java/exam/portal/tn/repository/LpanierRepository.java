package exam.portal.tn.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import exam.portal.tn.entities.Lpanier;

@Repository
public interface LpanierRepository extends JpaRepository<Lpanier, Long>{

}
