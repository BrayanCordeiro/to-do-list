package br.com.segsat.todolist.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.segsat.todolist.domain.Status;
import br.com.segsat.todolist.domain.ToDo;

public class ToDoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String tittle;
	private String description;
	private Status status;
	private Boolean completed;
	private Date creationDate;
	private Date conclusionForecast;
	private Date conclusionDate;
	private List<UserDTO> user;
	
	public ToDoDTO() {}
	
	public ToDoDTO(ToDo toDo) {
        this.id = toDo.getId();
        this.tittle = toDo.getTittle();
        this.description = toDo.getDescription();
        this.status = toDo.getStatus();
        this.completed = toDo.getCompleted();
        this.creationDate = toDo.getCreationDate();
        this.conclusionForecast = toDo.getConclusionForecast();
        this.conclusionDate = toDo.getConclusionDate();
        
        if(toDo.getUser() != null) {
        	this.user = toDo.getUser().stream().map(utd -> new UserDTO(utd.getUser())).collect(Collectors.toList());
        }else {
        	this.user = new ArrayList<>();
        }
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getConclusionForecast() {
		return conclusionForecast;
	}

	public void setConclusionForecast(Date conclusionForecast) {
		this.conclusionForecast = conclusionForecast;
	}

	public Date getConclusionDate() {
		return conclusionDate;
	}

	public void setConclusionDate(Date conclusionDate) {
		this.conclusionDate = conclusionDate;
	}

	public List<UserDTO> getUser() {
		return user;
	}

	public void setUser(List<UserDTO> user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(completed, conclusionDate, conclusionForecast, creationDate, description, id, status,
				tittle, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDoDTO other = (ToDoDTO) obj;
		return Objects.equals(completed, other.completed) && Objects.equals(conclusionDate, other.conclusionDate)
				&& Objects.equals(conclusionForecast, other.conclusionForecast)
				&& Objects.equals(creationDate, other.creationDate) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && status == other.status && Objects.equals(tittle, other.tittle)
				&& Objects.equals(user, other.user);
	}
	
}
