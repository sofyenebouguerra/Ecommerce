package exam.portal.tn.services;

import java.util.List;

import exam.portal.tn.entities.Comment;


public interface CommentService {
	
	Comment addCommentToProduct(Comment comment, long idProduct );
	
	Comment editComment(Comment comment, long id);
	
	Comment findCommentById(long id);
	
	void deleteComment(long id);
	
	List<Comment> findCommentsForProduct(long idProduct);
	
	List<Comment> findAllComments();	
	
}
