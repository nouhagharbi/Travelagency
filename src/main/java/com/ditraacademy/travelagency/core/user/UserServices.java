package com.ditraacademy.travelagency.core.user;

import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import com.ditraacademy.travelagency.utils.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;




@Service
public class UserServices {
    @Autowired
    Mailer mailer;

    @Autowired
    UserResposotory userResposotory;

    public ResponseEntity<?> createUser( User user) {
        if (user.getName() ==null)
            return new  ResponseEntity<>(new ErrorResponseModel("user name required"), HttpStatus.BAD_REQUEST);

        if (user.getName().length() < 3)
            return new  ResponseEntity<>(new ErrorResponseModel("Wrong user name"),HttpStatus.BAD_REQUEST);

        if (user.getAge() ==null)
            return new  ResponseEntity<>(new ErrorResponseModel("user age required"),HttpStatus.BAD_REQUEST);

        if (user.getAge() <= 0)
            return new  ResponseEntity<>(new ErrorResponseModel("Wrong user age"),HttpStatus.BAD_REQUEST);



        String destination= user.getEmail();
        String subject = "Welcome";
        String text ="Hello MR" +user.getName();

        mailer.senEmail(destination,subject,text);
        User userdatabase= userResposotory.save(user);
        return new ResponseEntity<>(userdatabase,HttpStatus.OK);
    }


    public List<User> getAllUsers(){
        List<User> userList = userResposotory.findAll();
        return userList;
    }

    public ResponseEntity<?> getUserId ( int id ){
        Optional<User> userOptional=userResposotory.findById(id);

        if (!userOptional.isPresent()){
            ErrorResponseModel errorResponseModel=new ErrorResponseModel("wrong user id");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        User user= userOptional.get();
        return new ResponseEntity<>(user, HttpStatus.OK);

    }


    public ResponseEntity<?> updatedUser ( int id,  User updatedUser) {
        Optional<User> userOptional=userResposotory.findById(id);

        if (!userOptional.isPresent()){
            ErrorResponseModel errorResponseModel=new ErrorResponseModel("wrong user id");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        User userDatabase=userOptional.get();

        if(updatedUser.getName() !=null){
            if (updatedUser.getName().length() < 3) {
                return new ResponseEntity<>(new ErrorResponseModel("Wrong user name"), HttpStatus.BAD_REQUEST);
            }
            userDatabase.setName(updatedUser.getName());
        }

        if(updatedUser.getAge() !=null){
            if (updatedUser.getAge() < 0) {
                return new ResponseEntity<>(new ErrorResponseModel("Wrong user age"), HttpStatus.BAD_REQUEST);
            }
            userDatabase.setAge(updatedUser.getAge());
        }

        userResposotory.save(userDatabase);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public ResponseEntity<?> removeUser( int id){
        Optional<User> userOptional=userResposotory.findById(id);

        if (!userOptional.isPresent()){
            ErrorResponseModel errorResponseModel=new ErrorResponseModel("wrong user id");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        userResposotory.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
