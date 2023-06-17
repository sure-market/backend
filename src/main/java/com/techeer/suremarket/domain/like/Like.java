package com.techeer.suremarket.domain.like;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Like extends BaseTimeEntity {
    @Id
    @Column(name = "like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @Column(name = "post_like_id")
    private Long postLikeId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "is_deleted")
    private String idDeleted;
}