package br.com.segsat.todolist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.segsat.todolist.domain.User;
import br.com.segsat.todolist.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User getById(Long id) {
		return userRepository.findById(id).get();
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User updateUser(User user) {
		var entity = userRepository.findById(user.getId()).get();
		
		entity.setId(user.getId());
		entity.setUsername(user.getUsername());
		entity.setTodo(user.getTodo());
		
		return userRepository.save(entity);
	}
	
	public void deleteUser(Long id) {
		var entity = userRepository.findById(id).get();
		userRepository.delete(entity);
	}

}
