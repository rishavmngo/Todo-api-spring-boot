package com.rishavmngo.TodoApp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAuthService {
	UserDetailsService userDetailsService();
}
