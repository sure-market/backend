package com.techeer.suremarket.controller.DTO;

import com.techeer.suremarket.domain.posts.Posts;
import javax.persistence.Column;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsRequestDto {
    private Long userId;

    @Column(length = 30)
    private String title;
    private Long price;
    private String category;
    private String region;
    private Boolean isDeleted;
    private String content;
    private String status;

    @Builder
    public PostsRequestDto(Long userId, String title, Long price, String category, String image,
            String region, String content, String status) {
        this.userId = userId;
        this.title = title;
        this.price = price;
        this.category = category;
        this.region = region;
        this.content = content;
        this.status = status;
    }
    public Posts toEntity(){
        return Posts.builder()
                .userId(userId)
                .title(title)
                .price(price)
                .category(category)
                .region(region)
                .status("T")
                .content(content)
                .build();
    }
}
