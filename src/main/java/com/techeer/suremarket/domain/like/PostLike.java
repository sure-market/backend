package com.techeer.suremarket.domain.like;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "post_like")
@Builder
public class PostLike {
    @Id
    @Column(name = "like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @Column(name = "post_id_like")
    private Integer postLikeId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "is_deleted")
    private Boolean idDeleted;
}
