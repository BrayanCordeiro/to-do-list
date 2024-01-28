package br.com.segsat.todolist.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserToDoKey implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "todo_id")
	private Long toDoId;

	public UserToDoKey() {}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getToDoId() {
		return toDoId;
	}

	public void setToDoId(Long toDoId) {
		this.toDoId = toDoId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(toDoId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserToDoKey other = (UserToDoKey) obj;
		return Objects.equals(toDoId, other.toDoId) && Objects.equals(userId, other.userId);
	}
	
}
