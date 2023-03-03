package com.booking.userservice.controller;

import com.booking.domain.exception.EntityNotFoundException;
import com.booking.userservice.dto.UserDto;
import com.booking.userservice.service.userservice.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get an user by its email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found the user", response = UserDto.class),
            @ApiResponse(code = 404, message = "User not found", response = EntityNotFoundException.class)
    })
    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        UserDto userDto = userService.getUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @Operation(summary = "Create user")
    @ApiResponse(code = 201, message = "Property created", response = UserDto.class)
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User updated successfully", response = UserDto.class),
            @ApiResponse(code = 404, message = "User not found", response = EntityNotFoundException.class)
    })
    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestPart("user") String userDto,
                                              @RequestPart(value = "image", required = false) MultipartFile image) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userDto, image));
    }
}