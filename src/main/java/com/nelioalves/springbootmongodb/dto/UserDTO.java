package com.nelioalves.springbootmongodb.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.nelioalves.springbootmongodb.entities.Post;
import com.nelioalves.springbootmongodb.entities.User;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String email;
	private List<PostDTO> postsDto = new ArrayList<>();

	public UserDTO() {

	}

	public UserDTO(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public UserDTO(User entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
	}

	public UserDTO(User entity, List<Post> posts) {
		this(entity);
		
		posts.forEach(post -> this.postsDto.add(new PostDTO(post)));
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PostDTO> getPostsDto() {
		return postsDto;
	}

}
