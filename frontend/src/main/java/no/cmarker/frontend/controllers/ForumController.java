package no.cmarker.frontend.controllers;

import no.cmarker.backend.entities.Category;
import no.cmarker.backend.entities.Comment;
import no.cmarker.backend.entities.Post;
import no.cmarker.backend.entities.User;
import no.cmarker.backend.services.CategoryService;
import no.cmarker.backend.services.CommentService;
import no.cmarker.backend.services.PostService;
import no.cmarker.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Christian Marker on 14/05/2018 at 21:57.
 */
@Named
@SessionScoped
public class ForumController implements Serializable {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	
	public List<Post> postsInCategory;
	private Post selectedPost;
	private String commentText = "";
	private int commentUser;
	public long postId = 1;
	
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}
	
	public List<Post> getAllPosts() {
		return postService.getAllPosts();
	}
	
	public List<Comment> getAllComments() {
		return commentService.getAllComments();
	}
	
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	public List<Post> getAllPostsInCategory(int categoryId) {
		return postService.getPostInCategory(categoryId);
	}
	
	public List<Comment> getAllCommentsInPost(int postId) {
		return commentService.commentsInPost(postId);
	}
	
	public Post getPost(int id) {
		return postService.getPost(id);
	}
	
	public Category getCategory(int id) {
		return categoryService.getCategory(id);
	}
	/*
	public void createComment() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		
		postId = Long.parseLong(params.get("postId"));
		
		commentService.createComment(commentText, postId, 2);
		setCommentText("");
	}
	*/
	public String createComment(String postId) {
		commentService.createComment(commentText, Integer.parseInt(postId), 2);
		setCommentText("");
		return "post_details.xhtml?post=" + postId + "&faces-redirect=true";
	}
	
	public String selectPost(Integer postId) {
		return "post_details.xhtml?post=" + postId + "&faces-redirect=true";
	}
	
	public String selectCategory(int categoryId) {
		return "posts.xhtml?category=" + categoryId + "&faces-redirect=true";
	}
	
	
	public String getCommentText() {
		return commentText;
	}
	
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	public int getCommentUser() {
		return commentUser;
	}
	
	public void setCommentUser(int commentUser) {
		this.commentUser = commentUser;
	}
}
