package no.cmarker.backend.services;

import no.cmarker.backend.StubApplication;
import no.cmarker.backend.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Christian Marker on 14/05/2018 at 18:57.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StubApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest {
	
	
	@Autowired
	private UserService userService;
	@Autowired private CategoryService categoryService;
	@Autowired private PostService postService;
	@Autowired private CommentService commentService;
	
	@Autowired private ResetService resetService;
	
	@Before
	public void init(){
		resetService.resetDatabase();
	}
	
	@Test
	public void testCreateUser(){
		long userId = userService.createUser("Martin", "Marker", "hello@marker.no", "hy*4kaePEpU-");
		assertNotNull(userId);
	}
	
	@Test
	public void testGetUser(){
		String firstName = "Christian";
		long userId = userService.createUser(firstName, "Marker", "christian@marker.no", "hy*4kaePEpU-");
		User user = userService.getUser(userId);
		
		assertEquals(firstName, user.getFirstName());
	}
	
	@Test
	public void testGetTopUsersUsingCounter(){
		long user1Id = userService.createUser("Christian", "Marker", "chrstian@gmail.no", "hy*4kaePEpU-");
		long user2Id = userService.createUser("Martin", "Marker", "martin@gmail.no", "hy*4kaePEpU-");
		long user3Id = userService.createUser("Knut", "Marker", "knut@gmail.com", "x52Udd@42");
		
		long cat1Id = categoryService.createCategory("Math");
		long cat2Id = categoryService.createCategory("General");
		long cat3Id = categoryService.createCategory("Gaming");
		long cat4Id = categoryService.createCategory("Cooking");
		
		long post1Id = postService.createPost("Pi", "What is the defenition of pi?", user1Id, cat1Id);
		long post2Id = postService.createPost("Cake", "Is the cake real?", user2Id, cat3Id);
		long post3Id = postService.createPost("Pizza","What is your fav pizza?", user3Id, cat4Id);
		
		long com1Id = commentService.createComment("3.14", post1Id, user2Id);
		long com2Id = commentService.createComment("3.15", post1Id, user1Id);
		long com3Id = commentService.createComment("3.12", post1Id, user3Id);
		
		long com4Id = commentService.createComment("THE CAKE IS A LIE!", post2Id, user2Id);
		long com5Id = commentService.createComment("YES, the cake is at GLAdOS", post2Id, user1Id);
		
		long com6Id = commentService.createComment("Grandiosa", post3Id, user1Id);
		long comm7Id = commentService.createComment("BigOne", post3Id, user2Id);
		
		List<User> resultList = userService.getTopUsers(3);
		assertEquals(3, resultList.size());
		
	}
}
