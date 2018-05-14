package no.cmarker.backend.entities;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

/**
 * @author Christian Marker on 14/05/2018 at 18:42.
 */
@Entity
public class Category {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	@Size(max=128)
	@Column(unique=true)
	private String name;
	
	@NotNull
	@OneToMany(mappedBy = "category", cascade = ALL)
	private List<Post> postList = new ArrayList<>();
	
	public Category() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Post> getPostList() {
		return postList;
	}
	
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
}
