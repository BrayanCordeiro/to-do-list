package br.com.segsat.todolist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.segsat.todolist.domain.ToDo;
import br.com.segsat.todolist.repository.ToDoRepository;

@Service
public class ToDoService {
	
	@Autowired
	private ToDoRepository toDoRepository;
	
	public List<ToDo> findAll(){
		return toDoRepository.findAll();
	}
	
	public ToDo getById(Long id) {
		return toDoRepository.findById(id).get();
	}
	
	public ToDo createToDo(ToDo toDo) {
		return toDoRepository.save(toDo);
	}
	
	public ToDo updateToDo(ToDo toDo) {
		var entity = toDoRepository.findById(toDo.getId()).get();
		
		entity.setId(toDo.getId());
		entity.setTittle(toDo.getTittle());
		entity.setDescription(toDo.getDescription());
		entity.setStatus(toDo.getStatus());
		entity.setCompleted(toDo.getCompleted());
		entity.setCreationDate(toDo.getCreationDate());
		entity.setConclusionForecast(toDo.getConclusionForecast());
		entity.setConclusionDate(toDo.getConclusionDate());
		entity.setUser(toDo.getUser());
		
		return toDoRepository.save(entity);
	}
	
	public void deleteTodo(Long id) {
		var entity = toDoRepository.findById(id).get();
		toDoRepository.delete(entity);
	}

}
