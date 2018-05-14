package no.cmarker.backend.services;

import no.cmarker.backend.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Christian Marker on 14/05/2018 at 18:52.
 */
@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private EntityManager em;
	
	public CategoryService() {
	}
	
	public long createCategory(@NotNull String name) {
		Category category = new Category();
		category.setName(name);
		
		em.persist(category);
		
		return category.getId();
	}
	
	public Category getCategory(long id) {
		return em.find(Category.class, id);
	}
	
	public List<Category> getAllCategories() {
		TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c", Category.class);
		return query.getResultList();
	}
	
	public boolean deleteCategory(long id){
		Category category = getCategory(id);
		
		if (category != null){
			em.remove(category);
			return true;
		}
		
		return false;
	}
}
