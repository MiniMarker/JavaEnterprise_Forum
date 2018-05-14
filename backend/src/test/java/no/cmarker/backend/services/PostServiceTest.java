package no.cmarker.backend.services;

import no.cmarker.backend.StubApplication;
import no.cmarker.backend.entities.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Christian Marker on 14/05/2018 at 18:56.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StubApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PostServiceTest {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired private PostService postService;
	@Autowired private UserService userService;
	
	@Autowired private ResetService resetService;
	
	@Before
	public void init(){
		resetService.resetDatabase();
	}
	
	@Test
	public void testCreatePost(){
		String postTitle = "Foo";
		String postText = "Bar";
		
		long userId = userService.createUser("Christian", "Marker", "christian@marker.no", "x64*E2bs94!");
		long categoryId = categoryService.createCategory("Math");
		
		long postId = postService.createPost(postTitle, postText, userId, categoryId);
		
		assertNotNull(postId);
	}
	
	@Test
	public void testGetPost(){
		String postTitle = "Foo";
		String postText = "Bar";
		
		long userId = userService.createUser("Christian", "Marker", "christian@marker.no", "x64*E2bs94!");
		long categoryId = categoryService.createCategory("Math");
		
		long postId = postService.createPost(postTitle, postText, userId, categoryId);
		Post post = postService.getPost(postId);
		
		assertEquals(postTitle, post.getTitle());
	}
	
}
