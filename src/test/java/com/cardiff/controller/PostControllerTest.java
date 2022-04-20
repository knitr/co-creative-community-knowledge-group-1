package com.cardiff.controller;

import com.cardiff.entity.Post;
import com.cardiff.entity.User;
import com.cardiff.repository.PostRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        // add principal object to SecurityContextHolder
        User user = new User();
        user.setEmail("test@test.com");
        user.setFirstName("tester");
        user.setId(100L);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(auth);


    }


    @Test
    void showForumHome() {
    }

    @Test
    void createPost() throws Exception {


        Post post1 = new Post();
        post1.setId(10L);
        post1.setTitle("My First post");
        post1.setBody("Body");

       // Mockito.when(this.postRepository.save(post1)).thenReturn((post1));

//        this.mockMvc.perform(post("/forum/createPost").param("post",post1)).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Body")));

    }

    @Test
    void addComment() {
    }
}