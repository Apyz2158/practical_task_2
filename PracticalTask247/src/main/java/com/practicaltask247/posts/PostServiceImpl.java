package com.practicaltask247.posts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practicaltask247.config.CommonResponse;
import com.practicaltask247.utilities.ResponseUtilities;
import com.practicaltask247.user.Users;
import com.practicaltask247.user.UsersRepository;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private UsersRepository usersRepository;

    @Autowired
    public PostServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    @Override
    public CommonResponse<Post> getPost(String email) throws URISyntaxException, IOException, InterruptedException {
        Users user = usersRepository.findByEmail(email);
        String url = "http://jsonplaceholder.typicode.com/posts/" + user.getId().toString();
        String data = this.getDataFromURL(url);
        ObjectMapper mapper = new ObjectMapper();
        Post post = mapper.readValue(data, Post.class);
        CommonResponse response = new CommonResponse(post);
        return ResponseUtilities.createSuccessResponse(response);
    }

    @Override
    public CommonResponse getComments(String email) throws URISyntaxException, IOException, InterruptedException {
        Users user = usersRepository.findByEmail(email);
        String url = "http://jsonplaceholder.typicode.com/posts/" + user.getId().toString() + "/comments";
        String data = this.getDataFromURL(url);
        ObjectMapper mapper = new ObjectMapper();
        List<Comment> commentList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(data);
        for (Object obj : jsonArray) {
            Comment comment = mapper.readValue(obj.toString(), Comment.class);
            commentList.add(comment);
        }
        CommonResponse response = new CommonResponse(commentList);
        return ResponseUtilities.createSuccessResponse(response);
    }

    private String getDataFromURL(String url) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
