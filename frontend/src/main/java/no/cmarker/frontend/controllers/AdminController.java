package no.cmarker.frontend.controllers;

import no.cmarker.backend.services.CategoryService;
import no.cmarker.backend.services.CommentService;
import no.cmarker.backend.services.PostService;
import no.cmarker.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Christian Marker on 14/05/2018 at 21:32.
 */
@Named
@SessionScoped
public class AdminController implements Serializable {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired private PostService postService;
	@Autowired private UserService userService;
	@Autowired private CommentService commentService;
	
	private String adminCategoryTextField;
	
	private String adminPostTitle;
	private String adminPostTextField;
	private int adminPostCategory;
	private int adminPostUser;
	
	private String adminCommentText;
	private int adminCommentUser;
	private int adminCommentPost;
	
	private String commentText;
	
	private String adminUserFirstName;
	private String adminUserSurName;
	private String adminUserEmail;
	private String adminUserPassword;
	
	public void createCategory(){
		categoryService.createCategory(adminCategoryTextField);
		setAdminCategoryTextField("");
	}
	
	public void createPost(){
		postService.createPost(adminPostTitle, adminPostTextField, adminPostUser, adminPostCategory);
		setAdminPostTitle("");
		setAdminPostTextField("");
	}
	
	public void createComment(){
		commentService.createComment(adminCommentText, adminCommentPost, adminCommentUser);
		setAdminCommentText("");
	}
	
	public String createUser(){
		userService.createUser(adminUserFirstName, adminUserSurName, adminUserEmail, adminUserPassword);
		setAdminUserFirstName("");
		setAdminUserSurName("");
		setAdminUserEmail("");
		setAdminUserPassword("");
		return "success_redirect.xhtml?registerUser=1&faces-redirect=true";
	}
	
	public void deleteCategory(int categoryId){
		categoryService.deleteCategory(categoryId);
	}
	
	public void deletePost(int postId){
		postService.deletePost(postId);
	}
	
	public void deleteUser(int userId){
		userService.deleteUser(userId);
	}
	
	public void deleteComment(int commentId){
		commentService.deleteComment(commentId);
	}
	
	public String getAdminCategoryTextField() {
		return adminCategoryTextField;
	}
	
	public void setAdminCategoryTextField(String adminCategoryTextField) {
		this.adminCategoryTextField = adminCategoryTextField;
	}
	
	public String getAdminPostTextField() {
		return adminPostTextField;
	}
	
	public void setAdminPostTextField(String adminPostTextField) {
		this.adminPostTextField = adminPostTextField;
	}
	
	public String getAdminPostTitle() {
		return adminPostTitle;
	}
	
	public void setAdminPostTitle(String adminPostTitle) {
		this.adminPostTitle = adminPostTitle;
	}
	
	public int getAdminPostCategory() {
		return adminPostCategory;
	}
	
	public void setAdminPostCategory(int adminPostCategory) {
		this.adminPostCategory = adminPostCategory;
	}
	
	public int getAdminPostUser() {
		return adminPostUser;
	}
	
	public void setAdminPostUser(int adminPostUser) {
		this.adminPostUser = adminPostUser;
	}
	
	public String getAdminCommentText() {
		return adminCommentText;
	}
	
	public void setAdminCommentText(String adminCommentText) {
		this.adminCommentText = adminCommentText;
	}
	
	public int getAdminCommentUser() {
		return adminCommentUser;
	}
	
	public void setAdminCommentUser(int adminCommentUser) {
		this.adminCommentUser = adminCommentUser;
	}
	
	public int getAdminCommentPost() {
		return adminCommentPost;
	}
	
	public void setAdminCommentPost(int adminCommentPost) {
		this.adminCommentPost = adminCommentPost;
	}
	
	public String getAdminUserFirstName() {
		return adminUserFirstName;
	}
	
	public void setAdminUserFirstName(String adminUserFirstName) {
		this.adminUserFirstName = adminUserFirstName;
	}
	
	public String getAdminUserSurName() {
		return adminUserSurName;
	}
	
	public void setAdminUserSurName(String adminUserSurName) {
		this.adminUserSurName = adminUserSurName;
	}
	
	public String getAdminUserEmail() {
		return adminUserEmail;
	}
	
	public void setAdminUserEmail(String adminUserEmail) {
		this.adminUserEmail = adminUserEmail;
	}
	
	public String getAdminUserPassword() {
		return adminUserPassword;
	}
	
	public void setAdminUserPassword(String adminUserPassword) {
		this.adminUserPassword = adminUserPassword;
	}
}
