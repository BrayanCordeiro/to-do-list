package br.com.segsat.todolist.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 80)
	private String username;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<UserToDo> todo;

	public User() {}

	public User(Long id, String username) {
		super();
		this.id = id;
		this.username = username;
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

	public List<UserToDo> getTodo() {
		return todo;
	}

	public void setTodo(List<UserToDo> todo) {
		this.todo = todo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, todo, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id) && Objects.equals(todo, other.todo)
				&& Objects.equals(username, other.username);
	}
	
}
