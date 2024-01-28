package br.com.segsat.todolist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.segsat.todolist.domain.UserToDo;
import br.com.segsat.todolist.domain.UserToDoKey;
import br.com.segsat.todolist.repository.UserToDoRepository;

@Service
public class UserToDoService {
	
	@Autowired
	private UserToDoRepository userToDoRepository;
	
	public List<UserToDo> findAll(){
		return userToDoRepository.findAll();
	}
	
	public UserToDo getById(UserToDoKey id) {
		return userToDoRepository.findById(id).get();
	}
	
	public List<UserToDo> getByUserId(Long id){
		return userToDoRepository.findByUserId(id);
	}
	
	public List<UserToDo> getByToDoId(Long id){
		return userToDoRepository.findByToDoId(id);
	}
	
	public UserToDo updateUserToDo(UserToDo utd) {
		var entity = userToDoRepository.findById(utd.getId()).get();
		
		entity.setToDo(utd.getToDo());
		entity.setUser(utd.getUser());
		
		return userToDoRepository.save(entity);
	}
	
	public void deleteUserToDo(UserToDoKey id) {
		var entity = userToDoRepository.findById(id).get();
		userToDoRepository.delete(entity);
	}
}
