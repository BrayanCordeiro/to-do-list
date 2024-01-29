package br.com.segsat.todolist.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_todo")
public class UserToDo implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserToDoKey id;
	
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@MapsId("toDoId")
	@JoinColumn(name = "todo_id")
	private ToDo toDo;

	public UserToDo() {}
	
	public UserToDo(UserToDoKey id, User user, ToDo toDo) {
		super();
		this.id = id;
		this.user = user;
		this.toDo = toDo;
	}

	public UserToDoKey getId() {
		return id;
	}

	public void setId(UserToDoKey id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ToDo getToDo() {
		return toDo;
	}

	public void setToDo(ToDo toDo) {
		this.toDo = toDo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, toDo, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserToDo other = (UserToDo) obj;
		return Objects.equals(id, other.id) && Objects.equals(toDo, other.toDo) && Objects.equals(user, other.user);
	}
	
}
