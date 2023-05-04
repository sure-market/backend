package com.techeer.suremarket.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    public Posts findByPostId(Long postId);
    public Posts findByPostIdAndIsDeleted(Long postId, Boolean isDeleted);

}
