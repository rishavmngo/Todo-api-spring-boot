package com.rishavmngo.TodoApp.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rishavmngo.TodoApp.domain.dtos.TodoDto;
import com.rishavmngo.TodoApp.domain.entities.TodoEntity;
import com.rishavmngo.TodoApp.domain.entities.UserEntity;
import com.rishavmngo.TodoApp.mappers.impl.TodoMapper;
import com.rishavmngo.TodoApp.service.TodoService;
import com.rishavmngo.TodoApp.service.UserService;

@RestController
@RequestMapping("/todos")
public class TodoController {
	private TodoService todoService;
	private UserService userService;
	private TodoMapper todoMapper;

	public TodoController(TodoService todoService, UserService userService,
			TodoMapper todoMapper) {
		this.todoService = todoService;
		this.userService = userService;
		this.todoMapper = todoMapper;
	}

	@PostMapping("/create")
	public ResponseEntity<TodoDto> CreateTodo(@RequestBody TodoDto todoDto) {
		UserEntity userentity = userService.getById(todoDto.getOwner().getId()).get();
		TodoEntity todoEntity = todoMapper.mapFrom(todoDto);
		todoEntity.setOwner(userentity);

		TodoEntity createdTodo = todoService.create(todoEntity);
		return new ResponseEntity<>(todoMapper.mapTo(createdTodo), HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId) {
		Optional<TodoEntity> todo = todoService.getById(todoId);
		if (todo.isEmpty()) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(todoMapper.mapTo(todo.get()), HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<TodoDto>> getAllTodos() {
		List<TodoEntity> todos = todoService.getAll();

		List<TodoDto> todoDtos = todos.stream().map(todoMapper::mapTo).collect(Collectors.toList());

		return new ResponseEntity<>(todoDtos, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<TodoDto> fullUpdate(@PathVariable("id") Long todoId, @RequestBody TodoDto todoDto) {
		if (!todoService.isExist(todoId)) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		UserEntity userentity = userService.getById(todoDto.getOwner().getId()).get();
		TodoEntity todoEntity = todoMapper.mapFrom(todoDto);
		todoEntity.setId(todoId);
		todoEntity.setOwner(userentity);

		TodoEntity createdTodo = todoService.create(todoEntity);
		return new ResponseEntity<>(todoMapper.mapTo(createdTodo), HttpStatus.OK);
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<TodoDto> partialUpdate(@PathVariable("id") Long todoId, @RequestBody TodoDto todoDto) {
		if (!todoService.isExist(todoId)) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		TodoEntity todoEntity = todoMapper.mapFrom(todoDto);

		TodoEntity updatedTodo = todoService.partialUpdate(todoId, todoEntity);
		return new ResponseEntity<>(todoMapper.mapTo(updatedTodo), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<TodoDto> partialUpdate(@PathVariable("id") Long todoId) {
		if (!todoService.isExist(todoId)) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		TodoEntity todoEntity = todoService.getById(todoId).get();
		TodoDto deletedTodo = todoMapper.mapTo(todoEntity);
		todoEntity.setOwner(null);
		todoService.create(todoEntity);
		todoService.delete(todoId);
		return new ResponseEntity<>(deletedTodo, HttpStatus.OK);
	}
}
