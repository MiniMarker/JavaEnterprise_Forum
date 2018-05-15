package no.cmarker.frontend.controllers;

import no.cmarker.backend.entities.Comment;
import no.cmarker.backend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.util.List;

/**
 * @author Christian Marker on 15/05/2018 at 22:36.
 */
@Named
@SessionScoped
public class CommentsController {
	
	private String commentText;
	private long selectedPostId;
	
	@Autowired
	private CommentService commentService;
	
	public String openPostDetailsPage(long selectedPostId){
		this.selectedPostId = selectedPostId;
		return "post_details.xhtml?faces-redirect=true";
	}
	
	public List<Comment> getAllComments() {
		return commentService.getAllComments();
	}
	
	public List<Comment> getAllCommentsInPost() {
		return commentService.commentsInPost(selectedPostId);
	}
	
	public void createComment() {
		commentService.createComment(commentText, selectedPostId, 2);
		setCommentText("");
	}
	
	public String getCommentText() {
		return commentText;
	}
	
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	public long getSelectedPostId() {
		return selectedPostId;
	}
}
