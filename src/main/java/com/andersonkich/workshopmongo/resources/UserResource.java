package com.andersonkich.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		
		@PostMapping
		public ResponseEntity<Object> insert(@RequestBody UserDTO objDTO){//Usar o OBJECT no lugar do void
			User obj = service.fromDTO(objDTO); // convert um UserDTO em User
			obj = service.insert(obj);//inserindo no db
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
			
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<Object> delete(@PathVariable String id) {//Usar o OBJECT no lugar do void
			service.delete(id);
			return ResponseEntity.noContent().build();//Para retornar vazio com o codigo 204
			
		}
		
	
}
