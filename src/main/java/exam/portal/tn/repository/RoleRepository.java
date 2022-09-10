package exam.portal.tn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import exam.portal.tn.entities.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
