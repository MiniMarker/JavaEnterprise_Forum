package no.cmarker.frontend.controllers;

import no.cmarker.backend.entities.Category;
import no.cmarker.backend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.util.List;

/**
 * @author Christian Marker on 15/05/2018 at 22:39.
 */
@Named
@SessionScoped
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	public String selectCategory(int categoryId) {
		return "posts.xhtml?category=" + categoryId + "&faces-redirect=true";
	}
	
	public Category getCategory(int id) {
		return categoryService.getCategory(id);
	}
	
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}
	
}
