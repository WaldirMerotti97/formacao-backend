package br.com.waldir.userserviceapi.controller.impl;

import br.com.waldir.userserviceapi.controller.UserController;
import br.com.waldir.userserviceapi.entity.User;
import br.com.waldir.userserviceapi.service.UserService;
import lombok.RequiredArgsConstructor;
import models.requests.CreateUserRequest;
import models.responses.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}

