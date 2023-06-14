package com.techeer.suremarket.controller.DTO;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequestDto {
    private String name;
    private String password;
}
