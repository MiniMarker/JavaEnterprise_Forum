package no.cmarker.backend.services;

import no.cmarker.backend.StubApplication;
import no.cmarker.backend.entities.Comment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Christian Marker on 14/05/2018 at 18:55.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StubApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CommentServiceTest {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired private PostService postService;
	@Autowired private UserService userService;
	@Autowired private CommentService commentService;
	
	@Autowired private ResetService resetService;
	
	@Before
	public void init(){
		resetService.resetDatabase();
	}
	
	@Test
	public void testCreateComment(){
		String commentText = "DON'T PANIC";
		
		String postTitle = "Foo";
		String postText = "Bar";
		
		long userId = userService.createUser("Christian", "Marker", "christian@marker.no", "x64*E2bs94!");
		long categoryId = categoryService.createCategory("Math");
		long postId = postService.createPost(postTitle, postText, userId, categoryId);
		
		long commentId = commentService.createComment(commentText, postId, userId);
		assertNotNull(commentId);
	}
	
	@Test
	public void testGetComment(){
		String commentText = "DON'T PANIC";
		
		String postTitle = "Foo";
		String postText = "Bar";
		
		long userId = userService.createUser("Christian", "Marker", "christian@marker.no", "x64*E2bs94!");
		long categoryId = categoryService.createCategory("Math");
		long postId = postService.createPost(postTitle, postText, userId, categoryId);
		
		long commentId = commentService.createComment(commentText, postId, userId);
		Comment comment = commentService.getComment(commentId);
		
		assertEquals(commentText, comment.getText());
	}
}
