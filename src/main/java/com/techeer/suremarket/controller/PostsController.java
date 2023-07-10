package com.techeer.suremarket.controller;

import com.techeer.suremarket.controller.DTO.PostIdResponseDto;
import com.techeer.suremarket.controller.DTO.PostLikeRequestDto;
import com.techeer.suremarket.controller.DTO.PostResponseDto;
import com.techeer.suremarket.controller.DTO.PostsRequestDto;
import com.techeer.suremarket.domain.security.JwtProvider;
import com.techeer.suremarket.service.PostsService;
import java.util.List;

import com.techeer.suremarket.service.S3Service;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class PostsController {
    private final PostsService postsService;
    private final S3Service s3Service;
    private final JwtProvider jwtProvider;

    @PostMapping("/api/v1/articles")
    public PostIdResponseDto save(
            @RequestPart(value = "postDto") PostsRequestDto requestDto,
            @RequestPart(value = "files") List<MultipartFile> files) {
        return postsService.savePost(requestDto, files);
    }

    @DeleteMapping("/api/v1/articles/{postId}")
    public void delete(@PathVariable("postId") Long postId) {
        postsService.deletePost(postId);
    }

    @GetMapping("/api/v1/articles/{postId}")
    public PostResponseDto getPost(@PathVariable("postId") Long postId) {
        return postsService.getPost(postId);
    }

    @GetMapping("/api/v1/articles")
    public List<PostResponseDto> getPostList() {
        return postsService.getPostList();
    }

    @PostMapping("/api/v1/articles/{postId}")
    public Long updatePost(@PathVariable("postId") Long postId,
            @RequestBody PostsRequestDto requestDto){
        return postsService.updatePost(postId, requestDto);
    }

//    @PostMapping("/api/v1/articles/uploads")
//    public void uploadFiles(
//            @RequestParam(value = "postId") Long postId,
//            @RequestPart(value = "files") List<MultipartFile> multipartFiles) {
//        s3Service.uploadFiles(postId, multipartFiles);
//    }

    @PostMapping("/api/v1/articles/like")
    public void like(@RequestBody PostLikeRequestDto postLikeRequestDto,
            HttpServletRequest request) {
        postsService.saveLike(postLikeRequestDto, request);
    }
}
