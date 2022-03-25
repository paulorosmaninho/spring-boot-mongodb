package com.nelioalves.springbootmongodb.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.springbootmongodb.controllers.util.URL;
import com.nelioalves.springbootmongodb.dto.PostDTO;
import com.nelioalves.springbootmongodb.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

	@Autowired
	private PostService postService;


	@GetMapping(value = "/{id}")
	public ResponseEntity<PostDTO> findById(@PathVariable String id) {

		PostDTO postDTO = postService.findById(id);

		return ResponseEntity.ok().body(postDTO);
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<PostDTO>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		
		text = URL.decodeParam(text);
		
		List<PostDTO> postsDTO = postService.findByTitle(text);
		
		return ResponseEntity.ok().body(postsDTO);
	}

	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<PostDTO>> fullSearch(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate){
		
		text = URL.decodeParam(text);
		
		//Converte a data mínima em string para Date. Se não for informada
		//gera a data mínima do Java com new Date(0L)
		Date min = URL.convertDate(minDate, new Date(0L));

		//Converte a data máxima em string para Date. Se não for informada
		//gera a data máxima do Java com new Date()
		Date max = URL.convertDate(maxDate, new Date());
		
		List<PostDTO> postsDTO = postService.fullSearch(text, min, max);
		
		return ResponseEntity.ok().body(postsDTO);
	}
	
}
