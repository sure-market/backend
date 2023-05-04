package com.techeer.suremarket.domain.posts;

import com.techeer.suremarket.controller.DTO.PostsRequestDto;
import com.techeer.suremarket.domain.BaseTimeEntity;
import com.techeer.suremarket.domain.image.Images;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private Long userId;

    @Column(length = 30)
    private String title;

    private Long price;

    private String category;
    private String region;

    private String content;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    private String status; // Default 판매중

    @OneToMany(mappedBy = "postId")
    private List<Images> images = new ArrayList<>();

    @Builder
    public Posts(Long userId, String title, Long price, String category,
            String region, String content, String status) {
        this.userId = userId;
        this.title = title;
        this.price = price;
        this.category = category;
        this.isDeleted = false;
        this.region = region;
        this.content = content;
        this.status = status;
    }

    @Builder

    public void deletePosts(Posts posts){
        this.isDeleted = true;
        posts.getImages().stream().map(img -> img.removeImage());
    }

    public void updatePost(PostsRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.price = requestDto.getPrice();
        this.category = requestDto.getCategory();
        this.isDeleted = requestDto.getIsDeleted();
        this.region = requestDto.getRegion();
        this.content = requestDto.getContent();
        this.status = requestDto.getStatus();
    }

    public void updateStatus(String status){
        this.status = status;
    }
}
