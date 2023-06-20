package com.techeer.suremarket.service;

import com.techeer.suremarket.controller.DTO.PostIdResponseDto;
import com.techeer.suremarket.controller.DTO.PostResponseDto;
import com.techeer.suremarket.controller.DTO.PostsRequestDto;
import com.techeer.suremarket.domain.like.PostLike;
import com.techeer.suremarket.domain.like.PostLikeRepository;
import com.techeer.suremarket.domain.posts.Posts;
import com.techeer.suremarket.domain.posts.PostsRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final PostLikeRepository postLikeRepository;
    private final S3Service s3Service;

    @Transactional
    public PostIdResponseDto savePost(PostsRequestDto requestDto, List<MultipartFile> files){
        PostIdResponseDto postIdResponseDto = PostIdResponseDto.builder()
                .postId(postsRepository.save(requestDto.toEntity()).getPostId())
                .build();
        s3Service.uploadFiles(postIdResponseDto.getPostId(), files);
        return postIdResponseDto;
    }

    @Transactional
    public void deletePost(Long postId){
        Posts post = postsRepository.findByPostIdAndIsDeleted(postId, false);
        post.deletePosts(post);
    }

    @Transactional
    public Long updatePost(Long postId, PostsRequestDto requestDto) {
        postsRepository.findByPostId(postId).updatePost(requestDto);
        return postId;
    }

    @Transactional
    public Long updatePostStatus(Long postId, String status){
        postsRepository.findByPostId(postId).updateStatus(status);
        return postId;
    }

    public PostResponseDto getPost(Long postId){
        return PostResponseDto.builder()
                .post(postsRepository.findByPostIdAndIsDeleted(postId, false))
                .build();
    }

    public List<PostResponseDto> getPostList(){
        return postsRepository.findAll().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveLike(Integer postId,Integer userId){
        postLikeRepository.save(
                PostLike.builder()
                        .postLikeId(postId)
                        .userId(userId)
                        .idDeleted(false)
                        .build());
    }
}
