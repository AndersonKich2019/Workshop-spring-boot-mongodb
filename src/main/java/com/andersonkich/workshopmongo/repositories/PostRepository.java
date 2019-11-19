package com.andersonkich.workshopmongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.andersonkich.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	//Query Methods ficam no repository
	List<Post> findByTitleContaining(String text);
		
	
	
	
}
