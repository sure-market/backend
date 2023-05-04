package com.techeer.suremarket.controller.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseIdDto {
    private Long id;

    public static UserResponseIdDto fromEntity(Long id) {
        return UserResponseIdDto.builder()
                .id(id)
                .build();
    }
}
