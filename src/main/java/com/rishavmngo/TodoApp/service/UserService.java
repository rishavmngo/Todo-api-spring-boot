package com.rishavmngo.TodoApp.service;

import java.util.Optional;

import com.rishavmngo.TodoApp.domain.entities.UserEntity;

public interface UserService {

	UserEntity save(UserEntity user);

	Optional<UserEntity> getByEmail(String email);

	Optional<UserEntity> getById(Long id);

}
