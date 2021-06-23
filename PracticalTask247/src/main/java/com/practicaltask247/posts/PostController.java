package com.practicaltask247.posts;

import com.practicaltask247.config.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public CommonResponse getPost(Authentication authentication,
                                  HttpServletResponse httpServletResponse) throws URISyntaxException, IOException, InterruptedException {
        return postService.getPost(authentication.getName());
    }

    @GetMapping("/comments")
    public CommonResponse getPostsComments(Authentication authentication,
                                           HttpServletResponse httpServletResponse) throws URISyntaxException, IOException, InterruptedException {
        return postService.getComments(authentication.getName());
    }

}
