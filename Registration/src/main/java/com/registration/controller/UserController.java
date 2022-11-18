package com.registration.controller;

import com.registration.service.UserService;
import com.shop.core.model.dto.BaseResponse;
import com.shop.core.model.dto.UserDto;
import com.shop.core.model.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static com.shop.core.ApplicationConstants.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create User to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SUCCESS,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = BADREQUEST,
                    content = @Content),
            @ApiResponse(responseCode = "401", description = AUTHENTICATION, content = @Content)})
    @PostMapping("/")
    public ResponseEntity<BaseResponse<User>> createUser(@RequestBody @Valid UserDto userDto) {
        User user = userService.saveUser(userDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUserId()).toUri();
        return ResponseEntity.created(location).body(new BaseResponse<>(HttpStatus.CREATED.value(), SUCCESS));
    }

    @Operation(summary = "Query User by ID in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SUCCESS,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = BADREQUEST,
                    content = @Content),
            @ApiResponse(responseCode = "401", description = AUTHENTICATION, content = @Content),
            @ApiResponse(responseCode = "404", description = NOT_FOUND, content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<User>> findUserById(@PathVariable("id") String id) {
        if (userService.findUserByUserId(id).isPresent()) {
            return new ResponseEntity<>(new BaseResponse<>(HttpStatus.OK.value(), SUCCESS), HttpStatus.OK);
        } else
            return ResponseEntity.notFound().build();
    }
}
