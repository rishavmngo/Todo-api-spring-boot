package com.rishavmngo.TodoApp.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.rishavmngo.TodoApp.domain.dtos.TodoDto;
import com.rishavmngo.TodoApp.domain.entities.TodoEntity;

@Component
public class TodoMapper {
	private ModelMapper modelMapper;

	public TodoMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public TodoDto mapTo(TodoEntity todoEntity) {
		return modelMapper.map(todoEntity, TodoDto.class);
	}

	public TodoEntity mapFrom(TodoDto todoDto) {
		return modelMapper.map(todoDto, TodoEntity.class);
	}
}
