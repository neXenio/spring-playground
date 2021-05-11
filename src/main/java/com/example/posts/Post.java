package com.example.posts;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Post {
    private int id;
    private int userId;
    private String title;
    private String body;
}
