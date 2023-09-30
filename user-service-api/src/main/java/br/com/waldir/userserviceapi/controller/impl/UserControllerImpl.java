package br.com.waldir.userserviceapi.controller.impl;

import br.com.waldir.userserviceapi.controller.UserController;
import br.com.waldir.userserviceapi.entity.User;
import br.com.waldir.userserviceapi.service.UserService;
import lombok.RequiredArgsConstructor;
import models.requests.CreateUserRequest;
import models.requests.UpdateUserRequest;
import models.responses.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;
    @Override
    public ResponseEntity<UserResponse> findById(String id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @Override
    public ResponseEntity<Void> save(CreateUserRequest createUserRequest) {
        userService.save(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @Override
    public ResponseEntity<UserResponse> update(final String id, final UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok().body(userService.update(id, updateUserRequest));
    }
}

