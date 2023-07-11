package com.techeer.suremarket.domain.posts;

import com.techeer.suremarket.domain.like.PostLike;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    public Posts findByPostId(Long postId);
    public Posts findByPostIdAndIsDeleted(Long postId, Boolean isDeleted);
    public Optional<List<Posts>> findAllByUserIdAndIsDeletedFalse(Long userId);
}
