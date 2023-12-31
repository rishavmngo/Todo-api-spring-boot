package com.rishavmngo.TodoApp.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.rishavmngo.TodoApp.domain.dtos.UserDto;
import com.rishavmngo.TodoApp.domain.entities.UserEntity;

@Component
public class UserMapper {

	private ModelMapper modelMapper;

	public UserMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;

	}

	public UserDto mapTo(UserEntity userEntity) {
		return modelMapper.map(userEntity, UserDto.class);
	}

	public UserEntity mapFrom(UserDto userDto) {
		return modelMapper.map(userDto, UserEntity.class);
	}
}
