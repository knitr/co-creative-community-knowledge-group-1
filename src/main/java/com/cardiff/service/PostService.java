package com.cardiff.service;

import com.cardiff.entity.Comment;
import com.cardiff.entity.Community;
import com.cardiff.entity.Post;
import com.cardiff.entity.User;
import com.cardiff.repository.CommentRepository;
import com.cardiff.repository.PostRepository;
import com.cardiff.repository.UserRepository;
import com.cardiff.service.iface.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {

    private PostRepository postRepository;

    private UserRepository userRepository;

    private CommentRepository commentRepository;

    @Override
    public List<Post> findAll() {

        return postRepository.findAll(Sort.by("creationDate").descending());
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post createPost(Post post, String userName) {
        User loggedInUser = userRepository.findByEmail(userName);
        if (loggedInUser != null) {
            post.setUser(loggedInUser);
            Community community = new Community();
            community.setId(post.getCommunityId());
            post.setCommunity(community);
        }
        return postRepository.save(post);
    }

    public Comment createComment(Comment comment, String userName, Long postId) {
        User loggedInUser = userRepository.findByEmail(userName);
        if (loggedInUser != null) {
            comment.setUser(loggedInUser);

        }
        Post post = new Post();
        post.setId(postId);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    public List<Post> findByCommunityId(Long id) {
        return postRepository.findByCommunityId(id);
    }
}
