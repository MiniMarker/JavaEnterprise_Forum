package no.cmarker.frontend.controllers;

import no.cmarker.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author Christian Marker on 14/05/2018 at 21:58.
 */
@Named
@RequestScoped
public class SignUpController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private String email;
	private String password;
	private String firstname;
	private String lastname;
	
	public String signUpUser(){
		
		Long registered = null;
		try {
			registered = userService.createUser(firstname, lastname, email, password);
		}catch (Exception e){
			//nothing to do
		}
		
		if(registered != null){
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(email);
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					userDetails,
					password,
					userDetails.getAuthorities());
			
			authenticationManager.authenticate(token);
			
			if (token.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(token);
			}
			
			return "/success_redirect.jsf?faces-redirect=true";
		} else {
			return "/signup.jsf?faces-redirect=true&error=true";
		}
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
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
