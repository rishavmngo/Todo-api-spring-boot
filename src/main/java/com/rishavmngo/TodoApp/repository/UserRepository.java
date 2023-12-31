package com.rishavmngo.TodoApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rishavmngo.TodoApp.domain.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	// @Query("Select u from users u where u.email = ?1")
	Optional<UserEntity> findByEmail(String email);
}
