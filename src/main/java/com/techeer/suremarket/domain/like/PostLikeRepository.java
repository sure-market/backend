package com.techeer.suremarket.domain.like;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository  extends JpaRepository<PostLike, Long> {
    public Optional<PostLike> findByPostIdAndUserId(Long postId,Long userId);
}
