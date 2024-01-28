package br.com.segsat.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.segsat.todolist.domain.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long>{}
