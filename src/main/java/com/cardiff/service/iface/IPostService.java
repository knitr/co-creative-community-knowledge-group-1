package com.cardiff.service.iface;

import com.cardiff.entity.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {

    List<Post> findAll();

    Optional<Post> findById(Long id);

    Post save(Post post);

    Post createPost(Post post, String userName);
}
