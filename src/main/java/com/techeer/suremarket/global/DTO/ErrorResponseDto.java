package com.techeer.suremarket.global.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {
    private String status;
    private String message;

    public static ErrorResponseDto fromEntity(String status, String message) {
        return ErrorResponseDto.builder()
                .status(status)
                .message(message)
                .build();
    }
}
