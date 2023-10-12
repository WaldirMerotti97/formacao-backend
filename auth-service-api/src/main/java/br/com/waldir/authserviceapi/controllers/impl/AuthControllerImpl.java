package br.com.waldir.authserviceapi.controllers.impl;

import br.com.waldir.authserviceapi.controllers.AuthController;
import models.requests.AuthenticateRequest;
import models.responses.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {
    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticateRequest authenticateRequest) {
        return ResponseEntity.ok(AuthenticationResponse.builder()
                .type("Bearer")
                .token("token")
                .build());
    }
}
