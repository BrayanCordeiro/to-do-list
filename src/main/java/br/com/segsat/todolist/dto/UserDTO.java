package br.com.segsat.todolist.dto;

import java.io.Serializable;

import br.com.segsat.todolist.domain.User;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	
	public UserDTO() {}
	
	public UserDTO(User user) {	
		this.id = user.getId();
		this.username = user.getUsername();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
