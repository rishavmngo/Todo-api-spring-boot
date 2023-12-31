package com.rishavmngo.TodoApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rishavmngo.TodoApp.domain.entities.TodoEntity;

@Repository
public interface TodoRepository extends CrudRepository<TodoEntity, Long> {
}
