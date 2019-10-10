package com.apisero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.apisero.model.UserDTO;
import com.apisero.service.UserService;

@RestController
@RequestMapping(value={"/user"})
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        UserDTO user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }

    @PostMapping(value="/create",headers="Accept=application/json")
    public ResponseEntity<Void> createUser(@RequestBody UserDTO user, UriComponentsBuilder ucBuilder){
        System.out.println("Creating User "+user.getName());
        userService.createUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value="/get", headers="Accept=application/json")
    public List<UserDTO> getAllUser() {
        List<UserDTO> tasks=userService.getUser();
        return tasks;
    }

    @PutMapping(value="/update", headers="Accept=application/json")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO currentUser)
    {
        System.out.println("sd");
        UserDTO user = userService.findById(currentUser.getId());
        if (user==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        userService.update(currentUser, currentUser.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}", headers ="Accept=application/json")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") long id){
        UserDTO user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value="/{id}", headers="Accept=application/json")
    public ResponseEntity<UserDTO> updateUserPartially(@PathVariable("id") long id, @RequestBody UserDTO currentUser){
        UserDTO user = userService.findById(id);
        if(user ==null){
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }
        UserDTO usr = userService.updatePartially(currentUser, id);
        return new ResponseEntity<UserDTO>(usr, HttpStatus.OK);
    }
}