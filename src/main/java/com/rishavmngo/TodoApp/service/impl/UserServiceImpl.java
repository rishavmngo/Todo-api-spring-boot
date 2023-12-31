package com.rishavmngo.TodoApp.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rishavmngo.TodoApp.domain.entities.UserEntity;
import com.rishavmngo.TodoApp.repository.UserRepository;
import com.rishavmngo.TodoApp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserEntity save(UserEntity user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<UserEntity> getByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<UserEntity> getById(Long id) {
		return userRepository.findById(id);
	}
}
