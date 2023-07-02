package com.techeer.suremarket.controller;

import com.techeer.suremarket.controller.DTO.LoginResponseDto;
import com.techeer.suremarket.controller.DTO.UserCreateRequestDto;
import com.techeer.suremarket.controller.DTO.LoginRequestDto;
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
    private final UserService userService;

    @PostMapping(value = "/auth/users/login")
    public ResponseEntity<LoginResponseDto> signin(@RequestBody LoginRequestDto requestDto) throws Exception {
        return new ResponseEntity<>(userService.login(requestDto), HttpStatus.OK);
    }

    @PostMapping(value = "/auth/users/signup")
    public ResponseEntity<Boolean> signup(@RequestBody UserCreateRequestDto requestDto) throws Exception {
        return new ResponseEntity<>(userService.register(requestDto), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{name}")
    public ResponseEntity<UserResponseDto> getUser(@RequestParam String name) throws Exception {
        return new ResponseEntity<>(userService.getUser(name), HttpStatus.OK);
    }

    @GetMapping(value = "/users/admin")
    public ResponseEntity<UserResponseDto> getUserForAdmin(@RequestParam String name) throws Exception {
        return new ResponseEntity<>(userService.getUser(name), HttpStatus.OK);
    }
}
