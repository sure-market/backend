package com.techeer.suremarket.controller.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto {

    private String name;
    private String email;
    private String password;

}
