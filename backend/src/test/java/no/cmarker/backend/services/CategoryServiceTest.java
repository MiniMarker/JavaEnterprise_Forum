package no.cmarker.backend.services;

import no.cmarker.backend.StubApplication;
import no.cmarker.backend.entities.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Christian Marker on 14/05/2018 at 18:55.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StubApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CategoryServiceTest {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ResetService resetService;
	
	@Before
	public void init(){
		resetService.resetDatabase();
	}
	
	//CATEGORY
	@Test
	public void testCreateCategory(){
		String categoryName = "Math";
		Long categoryId = categoryService.createCategory(categoryName);
		assertNotNull(categoryId);
	}
	
	@Test
	public void testGetCategory(){
		String categoryName = "Math";
		long categoryId = categoryService.createCategory(categoryName);
		Category category = categoryService.getCategory(categoryId);
		
		assertEquals(categoryName, category.getName());
	}
	
	@Test
	public void testGetAllCategories(){
		long cat1Id = categoryService.createCategory("Foo");
		long cat2Id = categoryService.createCategory("Bar");
		
		List<Category> results = categoryService.getAllCategories();
		assertEquals(2, results.size());
	}
}
