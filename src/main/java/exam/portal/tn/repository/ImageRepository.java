package exam.portal.tn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import exam.portal.tn.entities.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
	Optional<ImageModel> findByName(String name);
}