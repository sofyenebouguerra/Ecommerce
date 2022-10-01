package exam.portal.tn.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import exam.portal.tn.entities.Comment;
import exam.portal.tn.entities.Product;
import exam.portal.tn.repository.CommentRepository;
import exam.portal.tn.repository.ProductRepository;

@Transactional
@Component
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Comment addCommentToProduct(Comment comment, long idProduct) {
		Product product = productRepository.findById(idProduct).orElse(null);
		comment.setAddedAt(new Date());
		product.addCommentToProduct(comment);
		return commentRepository.save(comment);
	}

	@Override
	public Comment editComment(Comment comment, long id) {
		Comment existComponent = commentRepository.findById(id).orElse(null);
		existComponent.setTitle(comment.getTitle());
		existComponent.setMessage(comment.getMessage());
		existComponent.setAddedAt(new Date());
		existComponent.setAddedBy(comment.getAddedBy());
		return commentRepository.save(existComponent);
	}

	@Override
	public Comment findCommentById(long id) {
		return commentRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteComment(long id) {
		commentRepository.deleteById(id);
	}

	@Override
	public List<Comment> findCommentsForProduct(long idProduct) {
		Product product = productRepository.findById(idProduct).orElse(null);      
		return product.getComments();
	}

	@Override
	public List<Comment> findAllComments() {
		return commentRepository.findAll();
	}
	

}
