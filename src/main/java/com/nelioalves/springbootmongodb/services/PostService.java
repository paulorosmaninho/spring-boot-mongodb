package com.nelioalves.springbootmongodb.services;

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

	
}
