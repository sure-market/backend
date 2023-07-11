package com.techeer.suremarket.service;

import com.techeer.suremarket.domain.like.PostLike;
import com.techeer.suremarket.domain.like.PostLikeRepository;
import com.techeer.suremarket.domain.posts.Posts;
import com.techeer.suremarket.domain.posts.PostsRepository;
import com.techeer.suremarket.domain.security.JwtProvider;
import com.techeer.suremarket.controller.DTO.PostResponseDto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final PostLikeRepository postLikeRepository;
    private final PostsRepository postsRepository;
    private final JwtProvider jwtProvider;

    public List<PostResponseDto> posts(HttpServletRequest request){
        String accessToken = jwtProvider.resolveToken(request);
        return postsRepository.findAllByUserIdAndIsDeletedFalse(jwtProvider.getId(accessToken)).get()
                .stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    public List<PostResponseDto> likePosts(HttpServletRequest request){

        String accessToken = jwtProvider.resolveToken(request);
        Optional<List<PostLike>> postLike = postLikeRepository.findAllByUserIdAndIsDeletedFalse(jwtProvider.getId(accessToken));
        return postLike.get()
                .stream().map(post -> postsRepository.findByPostId(post.getPostLikeId())).collect(Collectors.toList())
                .stream().map(PostResponseDto::new).collect(Collectors.toList());

    }
}
