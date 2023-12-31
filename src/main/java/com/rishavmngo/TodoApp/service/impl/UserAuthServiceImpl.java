package com.rishavmngo.TodoApp.service.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rishavmngo.TodoApp.domain.entities.UserAuth;
import com.rishavmngo.TodoApp.domain.entities.UserEntity;
import com.rishavmngo.TodoApp.repository.UserRepository;
import com.rishavmngo.TodoApp.service.UserAuthService;

@Service
public class UserAuthServiceImpl implements UserAuthService {

	private UserRepository userRepository;

	public UserAuthServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetailsService userDetailsService() {

		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
				System.out.println("test:::");
				Optional<UserEntity> user = userRepository.findByEmail(email);

				System.out.println(user.isEmpty());
				if (user.isEmpty()) {
					throw new UsernameNotFoundException("User not found");
				}

				System.out.println(user.get());
				return new UserAuth(user.get());
			}
		};
	}

}
