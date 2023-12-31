package com.rishavmngo.TodoApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rishavmngo.TodoApp.domain.dtos.UserDto;
import com.rishavmngo.TodoApp.domain.entities.UserAuth;
import com.rishavmngo.TodoApp.domain.entities.UserEntity;
import com.rishavmngo.TodoApp.mappers.impl.UserMapper;
import com.rishavmngo.TodoApp.service.JwtService;
import com.rishavmngo.TodoApp.service.UserService;

@RestController
public class UserController {

	private UserMapper userMapper;

	private UserService userService;

	private AuthenticationManager manager;
	private PasswordEncoder passwordEncoder;
	private JwtService jwtService;

	public UserController(UserMapper userMapper, UserService userService, AuthenticationManager manager,
			PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.userMapper = userMapper;
		this.userService = userService;
		this.manager = manager;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;

	}

	@PostMapping("/auth/register")
	public ResponseEntity<UserDto> RegisterUser(@RequestBody UserEntity user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		UserEntity savedUser = userService.save(user);

		return new ResponseEntity<>(userMapper.mapTo(savedUser), HttpStatus.CREATED);
	}

	@PostMapping("/auth/login")
	public ResponseEntity<String> LoginUser(@RequestBody UserEntity user) {

		Authentication request = UsernamePasswordAuthenticationToken.unauthenticated(user.getEmail(),
				user.getPassword());

		this.manager.authenticate(request);

		var jwt = this.jwtService.generateToken(new UserAuth(user));

		return new ResponseEntity<>(jwt, HttpStatus.OK);
	}
}
