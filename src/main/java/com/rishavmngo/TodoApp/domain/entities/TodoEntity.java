package com.rishavmngo.TodoApp.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class TodoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_id_seq")
	private Long id;

	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "owner_id")
	private UserEntity owner;

}
