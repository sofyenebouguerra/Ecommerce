package exam.portal.tn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import exam.portal.tn.entities.Category;
import exam.portal.tn.entities.User;
import exam.portal.tn.repository.CategoryRepository;
import exam.portal.tn.repository.UserRepository;

@Transactional
@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Category addCategoryToUser(Category category, long idUser) {
		User user = userRepository.findById(idUser).orElse(null);
		user.addCategoryToUser(category);
		return categoryRepository.save(category);
	}

	@Override
	public Category editCategory(Category category, long id) {
		Category existsCategory = categoryRepository.findById(id).orElse(null);
		existsCategory.setName(category.getName());
		return categoryRepository.save(existsCategory);
	}

	@Override
	public Category findCategoryById(long id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteCategory(long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public List<Category> findCategoriesForUser(long id) {
		User user = userRepository.findById(id).orElse(null);
		return user.getCategories();
	}

}
