package br.com.segsat.todolist.dto;

import java.io.Serializable;

public class UserToDoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private Long todoId;
	
	public UserToDoDTO() {}
	
	public UserToDoDTO(Long userId, Long todoId) {
		super();
		this.userId = userId;
		this.todoId = todoId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTodoId() {
		return todoId;
	}

	public void setTodoId(Long todoId) {
		this.todoId = todoId;
	}
	
}
