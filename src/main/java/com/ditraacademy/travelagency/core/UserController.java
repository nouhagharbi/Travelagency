package com.ditraacademy.travelagency.core;

import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserResposotory userResposotory;

    @PostMapping("/user")
    public  ResponseEntity<?> createUser(@RequestBody User user)
    {
        if (user.getName() ==null)
            return new  ResponseEntity<>(new ErrorResponseModel("user name required"),HttpStatus.BAD_REQUEST);

        if (user.getName().length() < 3)
            return new  ResponseEntity<>(new ErrorResponseModel("Wrong user name"),HttpStatus.BAD_REQUEST);

        if (user.getAge() ==null)
            return new  ResponseEntity<>(new ErrorResponseModel("user age required"),HttpStatus.BAD_REQUEST);

        if (user.getAge() <= 0)
            return new  ResponseEntity<>(new ErrorResponseModel("Wrong user age"),HttpStatus.BAD_REQUEST);

        User userdatabase= userResposotory.save(user);
        return new ResponseEntity<>(userdatabase,HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        List<User> userList = userResposotory.findAll();
        return userList;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserId (@PathVariable int id ){
        Optional<User> userOptional=userResposotory.findById(id);

        if (!userOptional.isPresent()){
            ErrorResponseModel errorResponseModel=new ErrorResponseModel("wrong user id");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        User user= userOptional.get();
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updatedUser (@PathVariable int id, @RequestBody User updatedUser)
    {
        Optional<User> userOptional=userResposotory.findById(id);
        User userDatabase=userOptional.get();

        userDatabase.setName(updatedUser.getName());
        userDatabase.setAge(updatedUser.getAge());

        if (userDatabase.getName() ==null)
            return new  ResponseEntity<>(new ErrorResponseModel("user name required"),HttpStatus.BAD_REQUEST);

        if (userDatabase.getName().length() < 3)
            return new  ResponseEntity<>(new ErrorResponseModel("Wrong user name"),HttpStatus.BAD_REQUEST);

        if (userDatabase.getAge() ==null)
            return new  ResponseEntity<>(new ErrorResponseModel("user age required"),HttpStatus.BAD_REQUEST);

        if (userDatabase.getAge() <= 0)
            return new  ResponseEntity<>(new ErrorResponseModel("Wrong user age"),HttpStatus.BAD_REQUEST);




        User userupdated = userResposotory.save(userDatabase);
        return new ResponseEntity<>(userupdated,HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public void removeUser(@PathVariable int id){

        userResposotory.deleteById(id);
    }
}
