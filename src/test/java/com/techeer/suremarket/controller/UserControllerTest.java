package com.techeer.suremarket.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techeer.suremarket.controller.DTO.LoginRequestDto;
import com.techeer.suremarket.controller.DTO.LoginResponseDto;
import com.techeer.suremarket.controller.DTO.UserCreateRequestDto;
import com.techeer.suremarket.service.UserService;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(MockitoExtension.class)
class UserControllerTest{

    @InjectMocks    // mock 혹은 spy로 만들어진 가짜 객체를 주입시켜줌
    private UserController userController;
    @Mock // 가짜 객체를 생성해줌
    private UserService userService;

     // @WebMvcTest 어노테이션이 자동으로 객체 생성해줌
    private MockMvc mockMvc;    // controller 라서 api 통신을 통한 테스트를 진행해야 하는데 api 요청을 처리해줌

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    @DisplayName("회원가입")
    void signUpSuccess() throws Exception {
        // given
        UserCreateRequestDto requestDto = userCreateRequest();

        doReturn(true).when(userService)
                .register(any(UserCreateRequestDto.class));
            // "true"를 리턴하는데 userService의 register 함수가 실행되었을 경우
        // When
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/auth/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)));
        // Then
        resultActions.andExpect(status().isOk());
    }

    @DisplayName("로그인")
    @Test
    void loginTest() throws Exception{
        LoginRequestDto loginRequest = loginRequset();
        LoginResponseDto loginResponse = loginResponse();

        doReturn(loginResponse).when(userService)
                .login(any(LoginRequestDto.class));

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/auth/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("accessToken", loginResponse.getAccessToken()).exists())
                .andExpect(jsonPath("refreshToken", loginResponse.getRefreshToken()).exists())
                .andExpect(jsonPath("roles", loginResponse.getRoles()).exists());

    }

    private LoginResponseDto loginResponse() {
        return LoginResponseDto.builder()
                .roles(new ArrayList<>())
                .accessToken("")
                .refreshToken("")
                .build();
    }

    private LoginRequestDto loginRequset() {
        return LoginRequestDto.builder()
                .name("tester")
                .password("test123")
                .build();
    }

    private UserCreateRequestDto userCreateRequest() {
        return UserCreateRequestDto.builder()
                .name("tester")
                .email("test@test.test")
                .password("test123")
                .build();
    }
}
