package com.techeer.suremarket.service;


import com.techeer.suremarket.controller.DTO.LoginResponseDto;
import com.techeer.suremarket.controller.DTO.UserCreateRequestDto;
import com.techeer.suremarket.controller.DTO.LoginRequestDto;
import com.techeer.suremarket.controller.DTO.UserResponseDto;
import com.techeer.suremarket.domain.security.JwtProvider;
import com.techeer.suremarket.domain.user.Authority;
import com.techeer.suremarket.domain.user.User;
import com.techeer.suremarket.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;


@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public LoginResponseDto login(LoginRequestDto requestDto) throws Exception {
        User user = userRepository.findByName(requestDto.getName()).orElseThrow(() ->
                new BadCredentialsException("잘못된 계정정보입니다."));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("잘못된 계정정보입니다.");
        }

        return LoginResponseDto.builder()
                .roles(user.getRoles())
                .accessToken(jwtProvider.createToken(user.getName(), user.getRoles()))
                .refreshToken("")
                .build();
    }

    public boolean register(UserCreateRequestDto requestDto) throws Exception {
        try {
            User user = User.builder()
                    .name(requestDto.getName())
                    .password(passwordEncoder.encode(requestDto.getPassword()))
                    .email(requestDto.getEmail())
                    .build();

            user.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));

            userRepository.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }

    public UserResponseDto getUser(String name) throws Exception {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));
        return new UserResponseDto(user);
    }
}
