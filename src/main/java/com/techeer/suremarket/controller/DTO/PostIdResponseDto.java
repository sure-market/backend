package com.techeer.suremarket.controller.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostIdResponseDto {
    private Long postId;

    @Builder
    public PostIdResponseDto(Long postId) {
        this.postId = postId;
    }
}
