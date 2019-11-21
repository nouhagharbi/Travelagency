package com.ditraacademy.travelagency.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserResposotory userResposotory;

    @PostMapping("/user")
    public  void createUser(@RequestBody User user){
        userResposotory.save(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        List<User> userList = userResposotory.findAll();
        return userList;
    }

    @GetMapping("/users/{id}")
    public User getUserId (@PathVariable int id ){
        Optional<User> userOptional=userResposotory.findById(id);
        User user= userOptional.get();
        return user;

    }

    @PutMapping("/user/{id}")
    public void updatedUser (@PathVariable int id, @RequestBody User updatedUser)
    {
        Optional<User> userOptional=userResposotory.findById(id);
        User userDatabase=userOptional.get();

        userDatabase.setName(updatedUser.getName());
        userDatabase.setAge(updatedUser.getAge());

        userResposotory.save(userDatabase);
    }
}
