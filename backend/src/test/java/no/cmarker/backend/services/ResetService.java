package no.cmarker.backend.services;

import no.cmarker.backend.entities.Category;
import no.cmarker.backend.entities.Comment;
import no.cmarker.backend.entities.Post;
import no.cmarker.backend.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Christian Marker on 14/05/2018 at 18:56.
 */
@Service
@Transactional
public class ResetService {
	
	@PersistenceContext
	private EntityManager em;
	
	public void resetDatabase() {
		//Have to use native SQL for ElementCollection
		Query query = em.createNativeQuery("delete from user_roles");
		query.executeUpdate();
		deleteEntity(
				Comment.class,
				Post.class,
				Category.class,
				User.class);
	}
	
	private void deleteEntity(Class<?>... entities) {
		
		/*
		 * We use Class<?> to avoid SQL-Injection!
		 */
		
		for (Class<?> entity : entities) {
			String name = entity.getSimpleName();
			Query query = em.createQuery("delete from " + name);
			query.executeUpdate();
		}
	}
}