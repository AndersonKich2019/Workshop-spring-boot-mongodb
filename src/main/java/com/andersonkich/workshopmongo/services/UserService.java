package com.andersonkich.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersonkich.workshopmongo.domain.User;
import com.andersonkich.workshopmongo.repositories.UserRepository;
import com.andersonkich.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	/*Explicação:
	 * return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	 * 
	 * return obj                                                   = retorne um objeto do tipo usuario
	 * orElseThrow                                                  = Caso não encontre o objeto ou ele não exista
	 * (() -> new ObjectNotFoundException("Objeto não encontrado")) = Através de um callback disparar a exception personalizada
	 */
}
