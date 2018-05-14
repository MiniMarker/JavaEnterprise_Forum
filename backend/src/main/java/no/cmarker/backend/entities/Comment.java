package no.cmarker.backend.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Christian Marker on 14/05/2018 at 18:43.
 */
@Entity
public class Comment {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@ManyToOne
	private Post parentPost;
	
	@NotNull
	@ManyToOne
	private User parentUser;
	
	@NotNull
	@Size(max=128)
	private String text;
	
	public Comment() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Post getParentPost() {
		return parentPost;
	}
	
	public void setParentPost(Post post_id) {
		this.parentPost = post_id;
	}
	
	public User getParentUser() {
		return parentUser;
	}
	
	public void setParentUser(User user_id) {
		this.parentUser = user_id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
