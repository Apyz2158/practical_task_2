package com.practicaltask247.posts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Comment implements Serializable {

    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;

}
