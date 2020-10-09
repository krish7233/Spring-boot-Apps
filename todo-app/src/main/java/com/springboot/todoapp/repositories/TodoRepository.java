package com.springboot.todoapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.todoapp.models.Todo;



public interface TodoRepository extends JpaRepository<Todo, Long>{

}
