package br.com.waldir.userserviceapi.controller;

import br.com.waldir.userserviceapi.controller.exceptions.StandardError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import models.requests.CreateUserRequest;
import models.responses.UserResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UserController", description = "Controller responsible for user operations")
@RequestMapping("/api/users")
public interface UserController {

    @Operation(summary = "Find user by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User found"),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = StandardError.class
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = StandardError.class
                                    )
                            )

                    )
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> findById(
            @Parameter(description = "User id", required = true, example = "651200791bc25078e991c4d1")
            @PathVariable(name = "id") String id);

    @Operation(summary = "Save new user")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "User created"),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = StandardError.class
                                    )
                            )
                    )
                    ,
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = StandardError.class
                                    )
                            )
                    )
            }
    )
    @PostMapping
    ResponseEntity<Void> save(@Valid @RequestBody final CreateUserRequest createUserRequest);

}
