package com.techeer.suremarket.controller;

import com.techeer.suremarket.controller.DTO.PostResponseDto;
import com.techeer.suremarket.domain.posts.Posts;
import com.techeer.suremarket.service.ProfileService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("api/v1/profile/posts")
    public List<PostResponseDto> getMyPosts(HttpServletRequest request){
        return profileService.posts(request);
    }


    @GetMapping("api/v1/profile/likes")
    public List<Posts> getMyLikes(HttpServletRequest request){
        return profileService.likePosts(request);
    }
}
