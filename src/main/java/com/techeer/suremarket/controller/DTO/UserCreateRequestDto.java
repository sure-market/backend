package com.techeer.suremarket.controller.DTO;

import lombok.*;

@Getter
@Setter
public class UserCreateRequestDto {

    private String name;
    private String email;
    private String password;

}
