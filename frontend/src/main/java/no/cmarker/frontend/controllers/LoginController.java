package no.cmarker.frontend.controllers;

import no.cmarker.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;

/**
 * @author Christian Marker on 14/05/2018 at 21:57.
 */
@Named
@SessionScoped
public class LoginController implements Serializable {
	
	private String loginUserEmail;
	private String loginUserPassword;
	
	@Autowired
	private EntityManager em;
	
	public String logInUser(){
		
		User redirectUser = findUserInDb();
		
		if(redirectUser != null){
			return "success_redirect.xhtml&faces-redirect=true";
		}
		
		return null;
	}
	
	private User findUserInDb(){
		TypedQuery query =  em.createQuery("SELECT u FROM User u WHERE u.email LIKE ?1 AND u.password LIKE ?2", User.class);
		query.setParameter(1, loginUserEmail);
		query.setParameter(2, loginUserPassword);
		
		User user = (User) query.getSingleResult();
		
		if(user != null){
			return user;
		}
		
		return null;
	}
	
	public String getLoginUserEmail() {
		return loginUserEmail;
	}
	
	public void setLoginUserEmail(String loginUserEmail) {
		this.loginUserEmail = loginUserEmail;
	}
	
	public String getLoginUserPassword() {
		return loginUserPassword;
	}
	
	public void setLoginUserPassword(String loginUserPassword) {
		this.loginUserPassword = loginUserPassword;
	}
}
