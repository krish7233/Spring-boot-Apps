package com.springboot.todoapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.todoapp.models.Todo;
import com.springboot.todoapp.models.User;
import com.springboot.todoapp.repositories.TodoRepository;
import com.springboot.todoapp.repositories.UserRepository;




@RestController
@RequestMapping("/users")
public class UserServices {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TodoRepository todoRepository;
	
	//adding new user to db
	
	@PostMapping("/add-user")
	public User addNewUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	//getting all users from db
	
	@GetMapping("/get-users")
	public Iterable<User> getUsers(){
		return userRepository.findAll();
	}
	
	//adding new todo for particular user
	
	@PostMapping("/add-todo")
	public Todo addNewTodo(@RequestParam(value = "userId") String userId, @RequestBody Todo todo) {
		
		Optional<User> opUser = userRepository.findById(Long.parseLong(userId));
		User user = null;
		if(opUser.isPresent())
			user = opUser.get();
		
		
		todo.setUser(user);
		return todoRepository.save(todo);
	}
	
	//delte todo
	@DeleteMapping("/delete-todo/{id}")
	public void deleteTodo(@PathVariable("id") String todoId) {
		todoRepository.deleteById(Long.parseLong(todoId));
	}
	
	//update todo
	@PutMapping("/update-todo/{id}")
	public void updateTodo(@PathVariable() String id, @RequestBody Todo newTodo) {
		todoRepository.findById(Long.parseLong(id))
		.map(todo -> {
			todo.setText(newTodo.getText());
			return todoRepository.save(todo);
		}).orElseGet(() -> {
			return todoRepository.save(newTodo);
		});
	}
	
	
}
