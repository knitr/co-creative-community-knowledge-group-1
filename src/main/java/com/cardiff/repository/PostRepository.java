package com.cardiff.repository;

import com.cardiff.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p where p.community.id = ?1")
    List<Post> findByCommunityId(Long id);
}
