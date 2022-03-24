package com.nelioalves.springbootmongodb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.springbootmongodb.dto.UserDTO;
import com.nelioalves.springbootmongodb.entities.User;
import com.nelioalves.springbootmongodb.repositories.UserRepository;
import com.nelioalves.springbootmongodb.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<UserDTO> findAll() {

		List<User> listEntity = userRepository.findAll();

		List<UserDTO> listUserDTO = new ArrayList<>();

		listEntity.forEach(entityElement -> listUserDTO.add(new UserDTO(entityElement)));

		return listUserDTO;
	}

	public UserDTO findById(String id) {

		Optional<User> objOptional = userRepository.findById(id);

		User entity = objOptional.orElseThrow(() -> new ResourceNotFoundException("Usuário " + id + " não encontrado"));

		return new UserDTO(entity);
	}

	public UserDTO findByIdPosts(String id) {
		
		Optional<User> objOptional = userRepository.findById(id);
		
		User entity = objOptional.orElseThrow(() -> new ResourceNotFoundException("Usuário " + id + " não encontrado"));
		
		return new UserDTO(entity, entity.getPosts());
	}
	
	public UserDTO insert(UserDTO userDTO) {

		User entity = new User();

		copyDtoToEntity(userDTO, entity);

		userRepository.insert(entity);

		return new UserDTO(entity);
	}

	public UserDTO update(UserDTO userDTO, String id) {
		
		//Recupera o registro da base de dados
		UserDTO oldObj = findById(id);
		
		User entity = new User();
		
		//Se achou o registro copia dados para entidade 
		copyDtoToEntity(oldObj, entity);
		
		//Atualiza entidade com informações do userDTO
		updateEntity(userDTO, entity);
		
		entity = userRepository.save(entity);
		
		return new UserDTO(entity);
		
	}
	
	public void deleteById(String id) {

		findById(id);
		userRepository.deleteById(id);

	}

	private void copyDtoToEntity(UserDTO userDTO, User entity) {

		entity.setId(userDTO.getId());
		entity.setName(userDTO.getName());
		entity.setEmail(userDTO.getEmail());

	}

	private void updateEntity(UserDTO userDTO, User entity) {
		
		entity.setName(userDTO.getName());
		entity.setEmail(userDTO.getEmail());
		
	}
	
}
