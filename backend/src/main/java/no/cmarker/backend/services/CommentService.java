package no.cmarker.backend.services;

import no.cmarker.backend.entities.Comment;
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
 * @author Christian Marker on 14/05/2018 at 18:52.
 */
@Service
@Transactional
public class CommentService {
	
	@Autowired
	private EntityManager em;
	@Autowired private UserService userService;
	
	public long createComment(@NotNull String text,
	                          @NotNull long parentPostId,
	                          @NotNull long parentUserId){
		
		Post parentPost = em.find(Post.class, parentPostId);
		User parentUser = em.find(User.class, parentUserId);
		
		Comment comment = new Comment();
		comment.setText(text);
		comment.setParentPost(parentPost);
		comment.setParentUser(parentUser);
		
		em.persist(comment);
		
		parentPost.getCommentList().add(comment);
		parentUser.getCommentList().add(comment);
		
		userService.updateCounter(parentUser);
		
		return comment.getId();
	}
	
	public Comment getComment(long id){
		return em.find(Comment.class, id);
	}
	
	public List<Comment> commentsInPost(long postId){
		TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c WHERE c.parentPost.id = ?1", Comment.class);
		query.setParameter(1, postId);
		return query.getResultList();
	}
	
	public List<Comment> getAllComments(){
		TypedQuery<Comment> query = em.createQuery("SELECT c from Comment c ORDER BY c.parentPost.id", Comment.class);
		return query.getResultList();
	}
	
	public boolean deleteComment(long id){
		Comment comment = getComment(id);
		
		if (comment != null){
			em.remove(comment);
			return true;
		}
		
		return false;
	}
	
}
