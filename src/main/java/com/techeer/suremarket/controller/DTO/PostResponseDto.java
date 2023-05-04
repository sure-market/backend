package com.techeer.suremarket.controller.DTO;

import com.techeer.suremarket.domain.posts.Posts;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponseDto {

    private Long userId;
    private Long postId;
    private List<String> image;
    private String title;
    private Long price;
    private String region;
    private LocalDateTime updatedAt;
    //private Integer stars; 나중에 찜 갯수 넣어야함

    @Builder
    public PostResponseDto(Posts post) {
        this.userId = post.getUserId();
        this.postId = post.getPostId();
        this.image = post.getImages().stream()
                .map(images -> "https://team-is-image.s3.ap-northeast-2.amazonaws.com/" + images.getUuid())
                .collect(Collectors.toList());
        this.title = post.getTitle();
        this.price = post.getPrice();
        this.region = post.getRegion();
        this.updatedAt = post.getUpdatedAt();
    }
}
