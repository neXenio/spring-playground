package com.example.posts;

import com.example.GroupsApplication;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = GroupsApplication.class)
final class PostsAdapterTest {
    @Autowired
    private PostsAdapter postsAdapter;

    @Test
    void postsAdapter_getPost_passes() {
        Post post1 = postsAdapter.getPost(1);
        assertThat(post1.getId()).isEqualTo(1);
        assertThat(post1.getTitle()).isEqualTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        assertThat(post1.getUserId()).isEqualTo(1);
        assertThat(post1.getBody()).startsWith("quia et suscipit");
    }
}
