package no.cmarker.frontend.controllers;

import no.cmarker.backend.entities.Category;
import no.cmarker.backend.entities.Post;
import no.cmarker.backend.services.CategoryService;
import no.cmarker.backend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.util.List;

/**
 * @author Christian Marker on 15/05/2018 at 22:36.
 */
@Named
@SessionScoped
public class PostController {
	
	@Autowired private PostService postService;
	@Autowired private CategoryService categoryService;
	
	private long selectedCategoryId;
	
	public String openPostPage(long selectedCategoryId){
		this.selectedCategoryId = selectedCategoryId;
		return "posts.xhtml?faces-redirect=true";
	}
	
	public Category getSelectedCategory(){
		return categoryService.getCategory(selectedCategoryId);
	}
	
	public List<Post> getAllPosts() {
		return postService.getAllPosts();
	}
	
	public List<Post> getAllPostsInCategory() {
		return postService.getPostInCategory(selectedCategoryId);
	}
	
	public Post getPost(int id) {
		return postService.getPost(id);
	}
	
	public long getSelectedCategoryId() {
		return selectedCategoryId;
	}
}
