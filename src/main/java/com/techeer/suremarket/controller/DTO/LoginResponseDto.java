package com.techeer.suremarket.controller.DTO;

import com.techeer.suremarket.domain.user.Authority;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class LoginResponseDto {

    private String accessToken; // 애플리케이션이 Google API 요청을 승인하기 위해 보내는 토큰
    private String refreshToken;    // 새 액세스 토큰을 얻는 데 사용할 수 있는 토큰
    private List<Authority> roles = new ArrayList<>();
}
