package home.sabapathy.pm.api.controller;

import home.sabapathy.pm.api.mapper.UserMapper;
import home.sabapathy.pm.api.model.UserRequest;
import home.sabapathy.pm.api.model.UserResponse;
import home.sabapathy.pm.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j

@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> add(@Valid @RequestBody UserRequest userRequest) {
        log.debug("Add an user... {}", userRequest);
        return new ResponseEntity(userMapper.toUserResponse(userService.add(userMapper.toUser(userRequest))), HttpStatus.CREATED);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<List<UserResponse>> edit(@Valid @RequestBody UserRequest userRequest) {
        log.debug("Edit the user... {}", userRequest);
        return new ResponseEntity(userMapper.toUserResponse(userService.edit(userMapper.toUser(userRequest))), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> delete(@Valid @PathVariable long userId) {
        log.debug("Delete the user with UserID: {}", userId);
        userService.delete(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> get(@PathVariable long userId) {
        log.debug("Get the user with UserID: {}", userId);
        return new ResponseEntity(userMapper.toUserResponse(userService.get(userId)), HttpStatus.OK);
    }

    @GetMapping("/managers/{projectId}")
    public ResponseEntity<List<UserResponse>> getManagers(@PathVariable long projectId) {
        log.debug("Get all managers for projectId {}", projectId);
        return new ResponseEntity(userMapper.toUserResponse(userService.getManagers(projectId)), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers() {
        log.debug("Get all users...{}");
        return new ResponseEntity(userMapper.toUserResponse(userService.getAllWithUniqueEmployeeId()), HttpStatus.OK);
    }
}
