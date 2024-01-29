package br.com.segsat.todolist.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "todo")
public class ToDo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 120)
	private String tittle;
	
	@Column(length = 250)
	private String description;
	
	@Enumerated(value = EnumType.STRING)
	private Status status;
	
	@Column(nullable = false)
	private Boolean completed;
	
	@Column(name = "creation_date")
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	
	@Column(name = "conclusion_forecast")
	@Temporal(TemporalType.DATE)
	private Date conclusionForecast;
	
	@Column(name = "conclusion_date")
	@Temporal(TemporalType.DATE)
	private Date conclusionDate;
	
	@OneToMany(mappedBy = "toDo", fetch = FetchType.EAGER)
	private List<UserToDo> user;

	public ToDo() {}

	public ToDo(Long id, String tittle, String description, Status status, Boolean completed, Date creationDate,
			Date conclusionForecast, Date conclusionDate) {
		super();
		this.id = id;
		this.tittle = tittle;
		this.description = description;
		this.status = status;
		this.completed = completed;
		this.creationDate = creationDate;
		this.conclusionForecast = conclusionForecast;
		this.conclusionDate = conclusionDate;
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

	public List<UserToDo> getUser() {
		return user;
	}

	public void setUser(List<UserToDo> user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(conclusionDate, conclusionForecast, creationDate, completed, description, id, status,
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
		ToDo other = (ToDo) obj;
		return Objects.equals(conclusionDate, other.conclusionDate)
				&& Objects.equals(conclusionForecast, other.conclusionForecast)
				&& Objects.equals(creationDate, other.creationDate) && Objects.equals(completed, other.completed)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& status == other.status && Objects.equals(tittle, other.tittle) && Objects.equals(user, other.user);
	}	
	
}
