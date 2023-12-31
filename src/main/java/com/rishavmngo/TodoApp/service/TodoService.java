package com.rishavmngo.TodoApp.service;

import java.util.List;
import java.util.Optional;

import com.rishavmngo.TodoApp.domain.entities.TodoEntity;

public interface TodoService {
	TodoEntity create(TodoEntity todoEntity);

	Optional<TodoEntity> getById(Long id);

	List<TodoEntity> getAll();

	Boolean isExist(Long id);

	TodoEntity partialUpdate(Long id, TodoEntity todoEntity);

	void delete(Long id);

}
