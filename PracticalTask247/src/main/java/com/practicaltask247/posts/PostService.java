package com.practicaltask247.posts;

import com.practicaltask247.config.CommonResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public interface PostService {

    CommonResponse getPost(String username) throws URISyntaxException, IOException, InterruptedException;

    CommonResponse getComments(String username) throws URISyntaxException, IOException, InterruptedException;

}
