package com.practicaltask247.posts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Post implements Serializable {

    private Long id;
    private Long userId;
    private String title;
    private String body;

}
