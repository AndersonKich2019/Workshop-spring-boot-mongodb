package com.andersonkich.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersonkich.workshopmongo.domain.Post;
import com.andersonkich.workshopmongo.repositories.PostRepository;
import com.andersonkich.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text){
		return repository.searchTitle(text);//Usando Query Methods no repository
	}
	
	
	public List<Post> fullSearch(String text,Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);//Adicionando 24 horas na data maxima
		return repository.fullSearch(text, minDate, maxDate);
	}
	
	
}
