package no.cmarker.backend.entities;

import no.cmarker.backend.entities.annotations.ValidPassword;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

/**
 * @author Christian Marker on 14/05/2018 at 18:44.
 */
@Entity
@Table(name = "USERS")
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	@Size(min = 2, max = 128)
	private String firstName;
	
	@NotBlank
	@Size(min = 2, max = 128)
	private String surName;
	
	@NotNull
	private Boolean enabled;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> roles;
	
	@NotNull
	@Column(unique = true)
	@Email
	private String email;
	
	//@ValidPassword
	private String password;
	
	@NotNull
	@OneToMany(mappedBy = "parentUser", cascade = ALL)
	private List<Post> postList = new ArrayList<>();
	
	@NotNull
	@OneToMany(mappedBy = "parentUser", cascade = ALL)
	private List<Comment> commentList = new ArrayList<>();
	
	private int counter;
	
	public User() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getSurName() {
		return surName;
	}
	
	public void setSurName(String surName) {
		this.surName = surName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Post> getPostList() {
		return postList;
	}
	
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
	
	public List<Comment> getCommentList() {
		return commentList;
	}
	
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	public int getCounter() {
		return counter;
	}
	
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public Set<String> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}
