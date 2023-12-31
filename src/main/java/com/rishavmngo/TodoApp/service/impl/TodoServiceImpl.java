package com.rishavmngo.TodoApp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.rishavmngo.TodoApp.domain.entities.TodoEntity;
import com.rishavmngo.TodoApp.repository.TodoRepository;
import com.rishavmngo.TodoApp.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {
	private TodoRepository todoRepository;

	public TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Override
	public TodoEntity create(TodoEntity todoEntity) {
		TodoEntity todoEntitySaved = todoRepository.save(todoEntity);

		return todoEntitySaved;

	}

	@Override
	public Optional<TodoEntity> getById(Long id) {
		return todoRepository.findById(id);
	}

	@Override
	public List<TodoEntity> getAll() {

		return StreamSupport.stream(todoRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public Boolean isExist(Long id) {
		return todoRepository.existsById(id);
	}

	@Override
	public TodoEntity partialUpdate(Long id, TodoEntity todoEntity) {

		todoEntity.setId(id);
		return todoRepository.findById(id).map(existingTodo -> {
			Optional.ofNullable(todoEntity.getName()).ifPresent(existingTodo::setName);
			return todoRepository.save(existingTodo);
		}).orElseThrow(() -> new RuntimeException("todo does't exist"));
	}

	@Override
	public void delete(Long id) {
		todoRepository.deleteById(id);
	}

}
