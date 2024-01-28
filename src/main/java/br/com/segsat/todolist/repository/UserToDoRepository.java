package br.com.segsat.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.segsat.todolist.domain.UserToDo;
import br.com.segsat.todolist.domain.UserToDoKey;

@Repository
public interface UserToDoRepository extends JpaRepository<UserToDo, UserToDoKey>{

	@Query("select utd from UserToDo utd where utd.id.userId = :id")
	List<UserToDo> findByUserId(@Param(value = "id") Long id);
	
	@Query("select utd from UserToDo utd where utd.id.toDoId = :id")
	List<UserToDo> findByToDoId(@Param(value = "id") Long id);

}
