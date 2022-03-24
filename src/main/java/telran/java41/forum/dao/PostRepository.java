package telran.java41.forum.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java41.forum.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {

	Stream<Post> findPostsByAuthorIgnoreCase(String author);
	
	Stream<Post> findPostsByTagsIgnoreCase(List<String> tags);
	
	Stream<Post> findPostsByDateCreatedBetween(LocalDate from, LocalDate to);

		
}
