package com.techeer.suremarket.controller;

import com.techeer.suremarket.controller.DTO.UserCreateRequestDto;
import com.techeer.suremarket.controller.DTO.UserResponseDto;
import com.techeer.suremarket.domain.user.UserRepository;
import com.techeer.suremarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping(value = "/api/v1/auth/users/login")
    public ResponseEntity<UserResponseDto> signin(@RequestBody UserCreateRequestDto requestDto) throws Exception {
        return new ResponseEntity<>(userService.login(requestDto), HttpStatus.OK);
    }

    @PostMapping(value = "/api/v1/auth/users/signup")
    public ResponseEntity<Boolean> signup(@RequestBody UserCreateRequestDto requestDto) throws Exception {
        return new ResponseEntity<>(userService.register(requestDto), HttpStatus.OK);
    }

    @GetMapping(value = "/api/v1/users/{name}")
    public ResponseEntity<UserResponseDto> getUser(@RequestParam String name) throws Exception {
        return new ResponseEntity<>(userService.getUser(name), HttpStatus.OK);
    }

    @GetMapping(value = "/api/v1/users/admin")
    public ResponseEntity<UserResponseDto> getUserForAdmin(@RequestParam String name) throws Exception {
        return new ResponseEntity<>(userService.getUser(name), HttpStatus.OK);
    }
}
