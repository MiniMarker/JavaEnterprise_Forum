package no.cmarker.backend.services;

import no.cmarker.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * @author Christian Marker on 14/05/2018 at 18:54.
 */
@Service
@Transactional
public class UserService {
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public long createUser(@NotNull String firstName,
	                       @NotNull String surName,
	                       @NotNull String email,
	                       @NotNull String password){
		
		String hashedPassword = passwordEncoder.encode(password);
		
		User user = new User();
		user.setFirstName(firstName);
		user.setSurName(surName);
		user.setEmail(email);
		user.setPassword(hashedPassword);
		user.setEnabled(true);
		user.setRoles(Collections.singleton("USER"));
		
		em.persist(user);
		
		return user.getId();
	}
	
	public List<User> getTopUsers(int numberOfUsers){
		
		TypedQuery<User> result = em.createQuery("SELECT u FROM User u ORDER BY u.counter DESC", User.class);
		result.setMaxResults(numberOfUsers);
		
		return result.getResultList();
	}
	
	public User getUser(long id){
		return em.find(User.class, id);
	}
	
	public void updateCounter(User user){
		user.setCounter(user.getPostList().size() + user.getCommentList().size());
	}
	
	public List<User> getTopUsersUsingCounter(int numberOfUsers){
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u ORDER BY u.counter DESC", User.class);
		query.setMaxResults(numberOfUsers);
		
		return query.getResultList();
	}
	
	public List<User> getAllUsers(){
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}
	
	public boolean deleteUser(long id){
		User user = getUser(id);
		
		if (user != null){
			em.remove(user);
			return true;
		}
		
		return false;
	}
}
