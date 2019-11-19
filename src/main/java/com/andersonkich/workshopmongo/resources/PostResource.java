package com.andersonkich.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andersonkich.workshopmongo.domain.Post;
import com.andersonkich.workshopmongo.resources.util.URL;
import com.andersonkich.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/Posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> finById(@PathVariable String id){
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	

	@GetMapping("/titlesearch")
	public ResponseEntity<List<Post>> finByTitle(@RequestParam(value = "text", defaultValue = "") String text){
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	

	@GetMapping("/fullsearch")
	public ResponseEntity<List<Post>> fullsearch(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate){
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	
	
}
