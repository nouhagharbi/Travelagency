package com.ditraacademy.travelagency.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping("/user")
    public  ResponseEntity<?> createUser(@RequestBody User user) {
        return userServices.createUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return  userServices.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserId (@PathVariable int id ){
        return  userServices.getUserId(id);

    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updatedUser (@PathVariable int id, @RequestBody User updatedUser) {
        return  userServices.updatedUser(id,updatedUser);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> removeUser(@PathVariable int id){
        return  userServices.removeUser(id);
    }
}
