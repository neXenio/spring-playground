package com.example.posts;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class PostsAdapter {

    private final RestTemplate restTemplate = new RestTemplate();

    public Post getPost(int postId) {
        final var uri =
            fromHttpUrl("https://jsonplaceholder.typicode.com/")
                .path("posts")
                .query("id=" + postId)
                .build()
                .toUri();

        ResponseEntity<Post[]> response = restTemplate.getForEntity(uri, Post[].class);
        return response.getBody()[0];
    }
}
