package com.techeer.suremarket.controller.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostLikeRequestDto {
    private Long postId;
    private Boolean isDeleted;
}
