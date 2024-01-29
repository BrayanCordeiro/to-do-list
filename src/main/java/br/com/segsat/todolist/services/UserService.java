package br.com.segsat.todolist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.segsat.todolist.domain.User;
import br.com.segsat.todolist.domain.UserToDo;
import br.com.segsat.todolist.dto.UserDTO;
import br.com.segsat.todolist.repository.UserRepository;
import br.com.segsat.todolist.services.exceptions.DataBaseException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserToDoService userToDoService;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User getById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new DataBaseException("User not found"));
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User updateUser(User user) {
		var entity = userRepository.findById(user.getId())
				.orElseThrow(() -> new DataBaseException("User not found"));;
		
		entity.setUsername(user.getUsername());
		entity.setTodo(user.getTodo());
		
		return userRepository.save(entity);
	}
	
	public void deleteUser(Long id) {
		var entity = userRepository.findById(id)
				.orElseThrow(() -> new DataBaseException("User not Found"));
		
		for(UserToDo utd : userToDoService.getByUserId(id)) {
			userToDoService.deleteUserToDo(utd.getId());
		}
				
		userRepository.delete(entity);
	}
	
	public User fromDTO(UserDTO dto) {
		return new User(dto.getId(), dto.getUsername());
	}

}
