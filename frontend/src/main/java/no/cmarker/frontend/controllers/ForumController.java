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
	private UserService userService;
	
	public List<Post> postsInCategory;
	private Post selectedPost;
	private String commentText = "";
	private int commentUser;
	public long postId = 1;
	
	public List<User> getAllUsers() {
		return userService.getAllUsers();
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
