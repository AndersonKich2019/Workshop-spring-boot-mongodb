package com.andersonkich.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.andersonkich.workshopmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
