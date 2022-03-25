package com.nelioalves.springbootmongodb.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.springbootmongodb.dto.PostDTO;
import com.nelioalves.springbootmongodb.entities.Post;
import com.nelioalves.springbootmongodb.repositories.PostRepository;
import com.nelioalves.springbootmongodb.services.exceptions.ResourceNotFoundException;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;


	public PostDTO findById(String id) {

		Optional<Post> objOptional = postRepository.findById(id);

		Post entity = objOptional.orElseThrow(() -> new ResourceNotFoundException("Post " + id + " não encontrado"));

		return new PostDTO(entity);
	}
	
//	public List<PostDTO> findByTitle(String text) {
//		
//		List<Post> posts = postRepository.findByTitleContainingIgnoreCase(text);
//		List<PostDTO> postsDto = new ArrayList<>();
//		
//		posts.forEach(post -> postsDto.add(new PostDTO(post)));
//		
//		return postsDto;
//	}

	public List<PostDTO> findByTitle(String text) {
		
		List<Post> posts = postRepository.searchTitle(text);
		List<PostDTO> postsDto = new ArrayList<>();
		
		posts.forEach(post -> postsDto.add(new PostDTO(post)));
		
		return postsDto;
	}
	
	public List<PostDTO> fullSearch(String text, Date minDate, Date maxDate) {
		
		//Soma 24 horas em maxDate para buscar todos os posts até o final do dia
		//É preciso converter às 24 horas em milissegundos: 24 * 60 * 60 * 1000
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		
		List<Post> posts = postRepository.fullSearch(text, minDate, maxDate);
		List<PostDTO> postsDto = new ArrayList<>();
		
		posts.forEach(post -> postsDto.add(new PostDTO(post)));
		
		return postsDto;
	}
	
	
	
}
