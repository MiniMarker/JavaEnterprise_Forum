package no.cmarker.backend.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

/**
 * @author Christian Marker on 14/05/2018 at 18:43.
 */
@Entity
public class Post {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Category category;
	
	@NotNull
	@ManyToOne
	private User parentUser;
	
	@NotNull
	@Size(max=64)
	private String title;
	
	@NotNull
	@Size(max=128)
	private String text;
	
	@NotNull
	private Date date;
	
	@NotNull
	@OneToMany(mappedBy = "parentPost", cascade = ALL)
	private List<Comment> commentList = new ArrayList<>();
	
	public Post() {
		date = new Date();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public User getParentUser() {
		return parentUser;
	}
	
	public void setParentUser(User user_id) {
		this.parentUser = user_id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Date getDate() {
		return date;
	}
	
	public List<Comment> getCommentList() {
		return commentList;
	}
	
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	//SimpleDateFormat dateFormat = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
}
