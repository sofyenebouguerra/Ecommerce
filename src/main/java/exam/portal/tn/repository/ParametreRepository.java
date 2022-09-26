package exam.portal.tn.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import exam.portal.tn.entities.Parametre;

@Repository
public interface ParametreRepository extends JpaRepository<Parametre, Long>{


}
