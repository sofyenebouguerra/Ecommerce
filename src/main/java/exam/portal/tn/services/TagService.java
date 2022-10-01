package exam.portal.tn.services;

import java.util.List;

import exam.portal.tn.entities.Product;
import exam.portal.tn.entities.Tag;



public interface TagService {
	
	void addTagToProduct(long idProduct, long idTag);
	
	List<Tag> findTagsForProduct(long idProduct);
	
	List<Tag> findAllTags();
	
	void deleteTagFromProduct(long idTag, long idProduct);
	
	Tag addTag(Tag tag);
	
	void deleteTag(long id);

	
	 Tag findTagById(long id);
	 
	 List<Product> findProductsForTag(long idTag);

}
