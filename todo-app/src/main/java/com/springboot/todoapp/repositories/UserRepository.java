package com.springboot.todoapp.repositories;


import org.springframework.data.repository.CrudRepository;

import com.springboot.todoapp.models.User;



public interface UserRepository extends CrudRepository<User, Long>{

}
