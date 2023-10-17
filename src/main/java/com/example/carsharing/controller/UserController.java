package com.example.carsharing.controller;

import com.example.carsharing.domain.User;
import com.example.carsharing.domain.UserDto;
import com.example.carsharing.mapper.UserMapper;
import com.example.carsharing.service.UserDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserDbService dBService;

    @GetMapping
    public ResponseEntity<List<UserDto>> fetchAllUsers() {
    log.info("Fetching all USERS");
    List<User>users = dBService.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto>fetchUserById(@PathVariable Long id){
        log.info("fetching User with id="+id);
        User user  = dBService.getUserById(id);
        log.info("User fetched!");
        return ResponseEntity.ok(userMapper.mapUserToUserDto(user));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>createUser(@RequestBody UserDto userDto){
        log.info("Saving user...");
        User user = userMapper.mapUserDtoToUser(userDto);
        dBService.saveUser(user);
        log.info("Successful saved User with id="+user.getId()+", name="+user.getName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteUser(@PathVariable Long id){
        log.info("Deleting User");
        dBService.deleteUser(id);
        log.info("Successful deleted !");
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto>editUser(@PathVariable Long id, @RequestBody UserDto userDto){
        log.info("Editing User with id="+id);
        User updatedUser = userMapper.mapUserDtoToUser(userDto);
        User savedUser = dBService.updateUser(id, updatedUser);
        log.info("User with id="+id+" successfully updated!");
        return ResponseEntity.ok(userMapper.mapUserToUserDto(savedUser));
    }

}
