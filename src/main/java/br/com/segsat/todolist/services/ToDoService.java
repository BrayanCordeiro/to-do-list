package br.com.segsat.todolist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.segsat.todolist.domain.ToDo;
import br.com.segsat.todolist.domain.UserToDo;
import br.com.segsat.todolist.dto.ToDoDTO;
import br.com.segsat.todolist.repository.ToDoRepository;
import br.com.segsat.todolist.services.exceptions.DataBaseException;

@Service
public class ToDoService {
	
	@Autowired
	private ToDoRepository toDoRepository;
	
	@Autowired
	private UserToDoService userToDoService;
	
	public List<ToDo> findAll(){
		return toDoRepository.findAll();
	}
	
	public ToDo getById(Long id) {
		return toDoRepository.findById(id)
				.orElseThrow(() -> new DataBaseException("ToDo not found"));
	}
	
	public ToDo createToDo(ToDo toDo) {
		return toDoRepository.save(toDo);
	}
	
	public ToDo updateToDo(ToDo toDo) {
		var entity = toDoRepository.findById(toDo.getId())
				.orElseThrow(() -> new DataBaseException("ToDo not found"));;
		
		entity.setTittle(toDo.getTittle());
		entity.setDescription(toDo.getDescription());
		entity.setStatus(toDo.getStatus());
		entity.setCompleted(toDo.getCompleted());
		entity.setCreationDate(toDo.getCreationDate());
		entity.setConclusionForecast(toDo.getConclusionForecast());
		entity.setConclusionDate(toDo.getConclusionDate());
		
		ToDo updatedToDo = toDoRepository.save(entity);
		return updatedToDo;
	}
	
	public void deleteTodo(Long id) {
		var entity = toDoRepository.findById(id)
				.orElseThrow(() -> new DataBaseException("ToDo not found"));
				
		for(UserToDo utd : entity.getUser()) {
			userToDoService.deleteUserToDo(utd.getId());
		}
		toDoRepository.delete(entity);
	}
	
	public ToDo fromDTO(ToDoDTO dto) {
		return new ToDo(
					dto.getId(),
					dto.getTittle(),
					dto.getDescription(),
					dto.getStatus(),
					dto.getCompleted(),
					dto.getCreationDate(),
					dto.getConclusionForecast(),
					dto.getConclusionDate());
	}

}
