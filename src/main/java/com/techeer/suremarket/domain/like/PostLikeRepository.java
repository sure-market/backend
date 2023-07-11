package com.techeer.suremarket.domain.like;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository  extends JpaRepository<PostLike, Long> {
    public PostLike save(PostLike postLike);
    public Optional<PostLike> findByPostLikeIdAndUserId(Long postId, Long userId);
    public Optional<List<PostLike>> findAllByUserIdAndIsDeletedFalse(Long userId);
}
