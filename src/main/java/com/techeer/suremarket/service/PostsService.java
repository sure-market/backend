package com.techeer.suremarket.service;

import com.techeer.suremarket.controller.DTO.PostIdResponseDto;
import com.techeer.suremarket.controller.DTO.PostLikeRequestDto;
import com.techeer.suremarket.controller.DTO.PostResponseDto;
import com.techeer.suremarket.controller.DTO.PostsRequestDto;
import com.techeer.suremarket.domain.like.PostLike;
import com.techeer.suremarket.domain.like.PostLikeRepository;
import com.techeer.suremarket.domain.posts.Posts;
import com.techeer.suremarket.domain.posts.PostsRepository;
import com.techeer.suremarket.domain.security.JwtProvider;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
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
    private final JwtProvider jwtProvider;

    @Transactional
    public PostIdResponseDto savePost(PostsRequestDto requestDto, List<MultipartFile> files){
        Posts post = requestDto.toEntity();
        PostIdResponseDto postIdResponseDto = PostIdResponseDto.builder()
                .postId(postsRepository.save(post).getPostId())
                .build();
        s3Service.uploadFiles(post, files);
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
    public void saveLike(PostLikeRequestDto postLikeRequestDto, HttpServletRequest request){
        String accessToken = jwtProvider.resolveToken(request);
         Optional<PostLike> postLike = postLikeRepository.findByPostIdAndUserId(postLikeRequestDto.getPostId(),
                Long.parseLong(jwtProvider.getAuthentication(accessToken).getName()));
        if(postLike.isPresent()) { // 좋아요 삭제 혹은 재생성
            boolean status = postLike.get().getIdDeleted();
            postLike.get().setIdDeleted(!status);
        }
        else { // 좋아요 새로 생성
            postLikeRepository.save(
                    PostLike.builder()
                            .postLikeId(postLikeRequestDto.getPostId())
                            .userId(Long.parseLong(
                                    jwtProvider.getAuthentication(accessToken).getName()))
                            .idDeleted(false)
                            .build());
        }
    }

}
