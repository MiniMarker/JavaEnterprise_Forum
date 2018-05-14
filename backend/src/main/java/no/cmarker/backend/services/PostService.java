package no.cmarker.backend.services;

import no.cmarker.backend.entities.Category;
import no.cmarker.backend.entities.Post;
import no.cmarker.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Christian Marker on 14/05/2018 at 18:53.
 */
@Service
@Transactional
public class PostService {
	
	@Autowired
	private EntityManager em;
	@Autowired private UserService userService;
	
	
	public long createPost(@NotNull String title,
	                       @NotNull String text,
	                       @NotNull long parentUserId,
	                       @NotNull long parentCateoryId){
		
		Category parentCategory = em.find(Category.class, parentCateoryId);
		User parentUser = em.find(User.class, parentUserId);
		
		Post post = new Post();
		post.setTitle(title);
		post.setText(text);
		post.setCategory(parentCategory);
		post.setParentUser(parentUser);
		
		em.persist(post);
		
		parentCategory.getPostList().add(post);
		parentUser.getPostList().add(post);
		
		userService.updateCounter(parentUser);
		
		return post.getId();
	}
	
	public Post getPost(long id){
		return em.find(Post.class, id);
	}
	
	public List<Post> getPostInCategory(long categoryId){
		
		TypedQuery<Post> query = em.createQuery("SELECT p FROM Post p WHERE p.category.id=?1 ORDER BY p.date DESC", Post.class);
		query.setParameter(1, categoryId);
		
		return query.getResultList();
	}
	
	public List<Post> getAllPosts(){
		TypedQuery<Post> query = em.createQuery("SELECT p FROM Post p ORDER BY p.category.id", Post.class);
		return query.getResultList();
	}
	
	public boolean deletePost(long id){
		Post post = getPost(id);
		
		if (post != null){
			em.remove(post);
			return true;
		}
		
		return false;
	}
	
}
