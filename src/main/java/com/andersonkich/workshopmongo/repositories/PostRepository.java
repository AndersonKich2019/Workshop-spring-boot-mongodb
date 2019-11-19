package com.andersonkich.workshopmongo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.andersonkich.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	//Query Methods ficam no repository
	List<Post> findByTitleContaining(String text);
		
	//Usando notação @Query para buscas;
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	@Query("{ $and: [ {'data': {$gte: ?1}}, { 'data': { $lte: ?2} }, { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'list.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text,Date minDate, Date maxDate);
	
	
	
	/*Expressões JSon
	 * expressão ou = { $or: [ { <expression1> }, { <expression2> }, ... , { <expressionN> } ] }
	 * expressão and = { $and: [ { <expression1> }, { <expression2> } , ... , { <expressionN> } ] }
	 * expressao >=  = {field: {$gte: value} }
	 * expressão <=  = { field: { $lte: value} }
	 * 
	 */
	
	
	
	
	
	
}
