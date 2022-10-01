package exam.portal.tn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exam.portal.tn.entities.Comment;



public interface CommentRepository extends JpaRepository<Comment, Long> {

}
