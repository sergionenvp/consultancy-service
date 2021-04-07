package com.consultancyservices.registration.controllers;

import com.consultancyservices.registration.models.UserRequest;
import com.consultancyservices.registration.models.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    ModelMapper modelMapper;

    List<UserResponse> findAll() {
        //TODO
        return null;
    }
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    UserResponse newUser(@Valid @RequestBody UserRequest userRequest) {
        //maps field of different objects
        UserResponse userResponse = modelMapper.map(userRequest,UserResponse.class);
        userResponse.setId(1L);
        return userResponse;
    }

}