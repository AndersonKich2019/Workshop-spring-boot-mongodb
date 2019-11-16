package com.andersonkich.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andersonkich.workshopmongo.domain.User;
import com.andersonkich.workshopmongo.dto.UserDTO;
import com.andersonkich.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/Users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
		
		/*Explicações:
		 List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList())
		 
		 list.stream()               = Converte uma lista para stream para usar expressões lambda.
		 map(x -> new UserDTO(x))    = Para item objeto x da lista seja criada uma nova listaDTO usando o Map.
		 collect(Collectors.toList() = Converte de steam para lista novamente.
		 
		*/
		}
			
		@GetMapping("/{id}")
		public ResponseEntity<UserDTO> finById(@PathVariable String id){
			User obj = service.findById(id);
			return ResponseEntity.ok().body(new UserDTO(obj));
		
		
		
	}
	
}
