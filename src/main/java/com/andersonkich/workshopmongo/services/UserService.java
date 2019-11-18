package com.andersonkich.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersonkich.workshopmongo.domain.User;
import com.andersonkich.workshopmongo.dto.UserDTO;
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
	

	public void delete(String id) {
		repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));//Aproveita para usar a exception do metodo findById caso não exista id
		repository.deleteById(id);
	}
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public User update(User obj) {                               //Recebe o usuario com os novos dados
		Optional<User> newObj = repository.findById(obj.getId());
		User obj1 = new User(newObj.get().getId(), newObj.get().getName(), newObj.get().getEmail());//Convert o OPTIONAL	
		updateData(obj1, obj);                                 //Atualiza o usuario com os novos dados
		return repository.save(obj1);                   //Salva no db
		
	}
	
	private void updateData(User obj1, User obj) {//Atualiza o usuario com os novos dados
		obj1.setName(obj.getName());
		obj1.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDTO) {//Converte um usuarioDto em usuario
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
	
}
