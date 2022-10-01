package exam.portal.tn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import exam.portal.tn.entities.Product;
import exam.portal.tn.entities.Tag;
import exam.portal.tn.repository.ProductRepository;
import exam.portal.tn.repository.TagRepository;

@Transactional
@Component
public class TagServiceeImpl implements TagService {
	
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void addTagToProduct(long idProduct, long idTag) {
		Product product = productRepository.findById(idProduct).orElse(null);
		Tag tag = tagRepository.findById(idTag).orElse(null);
		tag.addProductToTag(product);
		product.addTag(tag);
		
	}

	@Override
	public List<Tag> findTagsForProduct(long idProduct) {
		Product product = productRepository.findById(idProduct).orElse(null);
		return product.getTags();
	}

	@Override
	public void deleteTagFromProduct(long idTag, long idProduct) {
		Product product = productRepository.findById(idProduct).orElse(null);
		Tag tag = tagRepository.findById(idTag).orElse(null);
		product.getTags().remove(tag);
		
	}

	@Override
	public Tag addTag(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public void deleteTag(long id) {
		tagRepository.deleteById(id);
	}

	@Override
	public Tag findTagById(long id) {
		return tagRepository.findById(id).orElse(null);
	}

	@Override
	public List<Tag> findAllTags() {
		return tagRepository.findAll();
	}

	@Override
	public List<Product> findProductsForTag(long idTag) {
		Tag tag = tagRepository.findById(idTag).orElse(null);
		return tag.getProducts();
	}
	

}
