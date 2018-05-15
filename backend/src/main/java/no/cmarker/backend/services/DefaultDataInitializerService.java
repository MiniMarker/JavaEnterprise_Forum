package no.cmarker.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.function.Supplier;

/**
 * @author Christian Marker on 15/05/2018 at 23:18.
 */
@Service
public class DefaultDataInitializerService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	@Autowired
	private CommentService commentService;
	
	@PostConstruct
	public void init() {
		
		long mathCatId = categoryService.createCategory("Math");
		long movCatId = categoryService.createCategory("Movies");
		long gamCatId = categoryService.createCategory("Gaming");
		long cooCatId = categoryService.createCategory("Cooking");
		
		long chrUserId = userService.createUser("Christian", "Marker", "chrstian@gmail.no", "hy*4kaePEpU-");
		long marUserId = userService.createUser("Martin", "Marker", "martin@gmail.no", "hy*4kaePEpU-");
		long knutUserId = userService.createUser("Knut", "Marker", "knut@gmail.com", "x52Udd@42");
		
		long post1 = postService.createPost("Pi", "What is the defenition of pi?", chrUserId, mathCatId);
		long post2 = postService.createPost("Cake", "Is the cake real?", marUserId, gamCatId);
		long post3 = postService.createPost("Pizza", "What is your fav pizza?", knutUserId, cooCatId);
		long post4 = postService.createPost("Half Life 3", "When do hl3 come out?!?", knutUserId, gamCatId);
		long post5 = postService.createPost("Newest Tarantino movie","What do you think of DiCaprio in Tarantino's controversial Manson film?", chrUserId, movCatId);
		
		long comment1 = commentService.createComment("3.14", post1, marUserId);
		long comment2 = commentService.createComment("3.15", post1, chrUserId);
		long comment3 = commentService.createComment("3.12", post1, knutUserId);
		
		long comment4 = commentService.createComment("THE CAKE IS A LIE!", post2, marUserId);
		long comment5 = commentService.createComment("YES, the cake is at GLAdOS", post2, chrUserId);
		long comment6 = commentService.createComment("The cake is hidden in room 3", post2, knutUserId);
		
		long comment7 = commentService.createComment("Grandiosa", post3, chrUserId);
		long comment8 = commentService.createComment("BigOne", post3, knutUserId);
		long comment9 = commentService.createComment("Mammas", post3, marUserId);
		
		long comment10 = commentService.createComment("Never ever!", post4, chrUserId);
		long comment11 = commentService.createComment("If they just could tell us... SOON!", post4, marUserId);
		
		long comment12 = commentService.createComment("AWESOME!", post5, chrUserId);
		long comment13 = commentService.createComment("can't wait..!", post5, knutUserId);
		long comment14 = commentService.createComment("I dont like it...", post5, marUserId);
	}
	
	private <T> T attempt(Supplier<T> lambda) {
		try {
			return lambda.get();
		} catch (Exception e) {
			return null;
		}
	}
}
