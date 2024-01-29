package br.com.segsat.todolist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.segsat.todolist.domain.UserToDo;
import br.com.segsat.todolist.domain.UserToDoKey;
import br.com.segsat.todolist.repository.UserToDoRepository;
import br.com.segsat.todolist.services.exceptions.DataBaseException;

@Service
public class UserToDoService {
	
	@Autowired
	private UserToDoRepository userToDoRepository;
	
	public List<UserToDo> findAll(){
		return userToDoRepository.findAll();
	}
	
	public UserToDo getById(UserToDoKey id) {
		return userToDoRepository.findById(id).orElseThrow(() -> new DataBaseException("Id not found"));
	}
	
	public List<UserToDo> getByUserId(Long id){
		return userToDoRepository.findByUserId(id);
	}
	
	public List<UserToDo> getByToDoId(Long id){
		return userToDoRepository.findByToDoId(id);
	}
	
	public UserToDo createUserToDo(UserToDo utd) {
		return userToDoRepository.save(utd);
	}
	
	public UserToDo updateUserToDo(UserToDo utd) {
		var entity = userToDoRepository.findById(utd.getId()).orElseThrow(() -> new DataBaseException("Id not found"));
		
		entity.setToDo(utd.getToDo());
		entity.setUser(utd.getUser());
		
		return userToDoRepository.save(entity);
	}
	
	public void deleteUserToDo(UserToDoKey id) {
		var entity = userToDoRepository.findById(id).orElseThrow(() -> new DataBaseException("Id not found"));
		userToDoRepository.delete(entity);
	}
	
}
