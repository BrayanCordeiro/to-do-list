package br.com.segsat.todolist.controller;

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
import br.com.segsat.todolist.domain.User;
import br.com.segsat.todolist.dto.UserDTO;
import br.com.segsat.todolist.services.UserService;

@RestController
@RequestMapping("/todolist/api/user")
public class UserController {
	
    private String apiToken = "20be54ff-66b2-49e6-857a-eaa70b29b145";
	
	@Autowired
	private UserService userService;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<UserDTO> findAll(@RequestHeader("Authorization") String authorization) throws AuthenticationException{
		if(authorization.equals(apiToken) ) {
			List<User> users = userService.findAll();
			List<UserDTO> usersDTO = users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
			
			return usersDTO;
		}
		
		throw new AuthenticationException("Invalid token!");
	}
	
	@GetMapping(
			value = "/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public UserDTO getById(@PathVariable Long id, @RequestHeader("Authorization") String authorization){
		if(authorization.equals(apiToken)) {
			User user = userService.getById(id);
			return new UserDTO(user);
		}
		
		throw new AuthenticationException("Invalid token!");
	}
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public UserDTO createUser(@RequestBody UserDTO dto, @RequestHeader("Authorization") String authorization) {
		if(authorization.equals(apiToken)) {
			User user = userService.createUser(userService.fromDTO(dto));
			return new UserDTO(user);
		}
		
		throw new AuthenticationException("Invalid token!");
	}
	
	@PutMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public UserDTO updateUser(@RequestBody UserDTO dto, @RequestHeader("Authorization") String authorization) {
		if(authorization.equals(apiToken)) {
			User user = userService.updateUser(userService.fromDTO(dto));
			return new UserDTO(user);
		}
		
		throw new AuthenticationException("Invalid token!");
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id, @RequestHeader("Authorization") String authorization){
		if(authorization.equals(apiToken)) {
			userService.deleteUser(id);
			return ResponseEntity.noContent().build();
		}
		
		throw new AuthenticationException("Invalid token!");
	}
	
}
