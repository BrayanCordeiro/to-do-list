package br.com.segsat.todolist.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.segsat.todolist.controller.exceptions.AuthenticationException;
import br.com.segsat.todolist.domain.ToDo;
import br.com.segsat.todolist.domain.User;
import br.com.segsat.todolist.domain.UserToDo;
import br.com.segsat.todolist.domain.UserToDoKey;
import br.com.segsat.todolist.dto.ToDoDTO;
import br.com.segsat.todolist.dto.UserDTO;
import br.com.segsat.todolist.dto.UserToDoDTO;
import br.com.segsat.todolist.services.ToDoService;
import br.com.segsat.todolist.services.UserService;
import br.com.segsat.todolist.services.UserToDoService;

@RestController
@RequestMapping("/todolist/api/todo")
public class ToDoController {
	
    private String apiToken = "20be54ff-66b2-49e6-857a-eaa70b29b145";
	
	@Autowired
	ToDoService toDoService;
	
	@Autowired
	UserToDoService userToDoService;
	
	@Autowired
	UserService userService;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<ToDoDTO> findAll(@RequestHeader("Authorization") String authorization){
		if(authorization.equals(apiToken)) {
			List<ToDo> todoList = toDoService.findAll();
			List<ToDoDTO> todoDTOList = todoList.stream().map(x -> new ToDoDTO(x)).collect(Collectors.toList());
			return todoDTOList;
		}
		
		throw new AuthenticationException("Invalid token!");	
	}
	
	@GetMapping(value = "/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ToDoDTO getById(@PathVariable Long id, @RequestHeader("Authorization") String authorization) {
		if(authorization.equals(apiToken)) {
			ToDo todo = toDoService.getById(id);
			return new ToDoDTO(todo);
		}
		
		throw new AuthenticationException("Invalid token!");
	}
	
	@PostMapping(
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ToDoDTO createToDo(@RequestBody ToDoDTO dto, @RequestHeader("Authorization") String authorization) {
		if(authorization.equals(apiToken)) {
			List<User> users = new ArrayList<>();
			for(UserDTO u : dto.getUser()) {
				users.add(userService.getById(u.getId()));
			}
			
			ToDo todo = toDoService.createToDo(toDoService.fromDTO(dto));
			
			List<UserToDo> utdList = new ArrayList<>();
			for(User u : users) {
				UserToDo utd = new UserToDo();
				utd.setId(new UserToDoKey(u.getId(), todo.getId()));
				utd.setUser(u);
				utd.setToDo(todo);
				utdList.add(userToDoService.createUserToDo(utd));
			}
			todo.setUser(utdList);
			return new ToDoDTO(todo);
		}
		
		throw new AuthenticationException("Invalid token!");
	}
	
	@PostMapping(
			value = "/vinculate",
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> vinculateUser(@RequestBody UserToDoDTO utdDTO,
			@RequestHeader("Authorization") String authorization) {
		if(authorization.equals(apiToken)) {
			User u = userService.getById(utdDTO.getUserId());
			ToDo td = toDoService.getById(utdDTO.getTodoId());
			UserToDo utd = new UserToDo();
			utd.setId(new UserToDoKey(u.getId(), td.getId()));
			utd.setUser(u);
			utd.setToDo(td);
			userToDoService.createUserToDo(utd);
			return ResponseEntity.created(null).build();
		}
		
		throw new AuthenticationException("Invalid token!");
	}
	
	@PutMapping(
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ToDoDTO updateToDo(@RequestBody ToDoDTO dto, @RequestHeader("Authorization") String authorization) {
		if(authorization.equals(apiToken)) {
			ToDo todo = toDoService.updateToDo(toDoService.fromDTO(dto));
			return new ToDoDTO(todo);
		}
		
		throw new AuthenticationException("Invalid token!");
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteToDo(@PathVariable Long id, @RequestHeader("Authorization") String authorization){
		if(authorization.equals(apiToken)) {
			toDoService.deleteTodo(id);
			return ResponseEntity.noContent().build();
		}
		
		throw new AuthenticationException("Invalid token!");
	}
	
	@DeleteMapping(value = "/desvinculate")
	public ResponseEntity<?> desvinculateUser(
			@RequestBody UserToDoDTO utdDTO,
			@RequestHeader("Authorization") String authorization){
		if(authorization.equals(apiToken)) {
			userToDoService.deleteUserToDo(new UserToDoKey(utdDTO.getUserId(), utdDTO.getTodoId()));
			return ResponseEntity.noContent().build();
		}
		
		throw new AuthenticationException("Invalid token!");
	}
}
