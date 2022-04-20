package com.cardiff.controller;

import com.cardiff.entity.Comment;
import com.cardiff.entity.Post;
import com.cardiff.service.FragmentService;
import com.cardiff.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/forum")
public class PostController {

    private PostService postService;
    private FragmentService fragmentService;

    @Autowired
    public void setFragmentService(FragmentService fragmentService) {
        this.fragmentService = fragmentService;
    }

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }


    /**
     * This method will populate empty POST object for create post form, the list of all posts, empty Comment Object to add comment form
     * and community list for the navbar
     *
     * @param model
     * @return
     */
    @GetMapping("/home")
    public String showForumHome(Model model) {

        model.addAttribute("post", new Post());
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("comment", new Comment());
        model.addAttribute("communityList", fragmentService.getAllCommunitiesForNavigation());
        return "forum";
    }


    /**
     * This method will populate post related to the community that user has selected using the path variable id
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/sortByCommunityId/{id}")
    public String showForumPostsByCommunityId(Model model, @PathVariable Long id) {

        model.addAttribute("post", new Post());
        model.addAttribute("posts", postService.findByCommunityId(id));
        model.addAttribute("comment", new Comment());
        model.addAttribute("communityList", fragmentService.getAllCommunitiesForNavigation());
        return "forum";
    }


    /**
     * This method will create a post in to a database and return data back to the form home page
     *
     * @param post
     * @param request
     * @param errors
     * @return
     */
    @PostMapping("/createPost")
    public ModelAndView createPost(@ModelAttribute("post") @Valid Post post, HttpServletRequest request, Errors errors) {

        ModelAndView mav = new ModelAndView("redirect:/forum/home");
        try {

            //get the logged-in user from Security context
            String userName = request.getUserPrincipal().getName();

            postService.createPost(post, userName);
            mav.addObject("post", new Post());

        } catch (Exception ex) {
            mav.addObject("post", new Post());
            mav.addObject("message", "Unable to create Post");
            System.err.println(ex.getMessage());
        }

        mav.addObject("posts", postService.findAll());
        return mav;

    }


    /**
     * This is method will add the comment to the related pos using the id parameter
     *
     * @param comment
     * @param id      (post ID)
     * @param request
     * @param errors
     * @return
     */
    @PostMapping("/addComment/{id}")
    public ModelAndView addComment(@ModelAttribute("comment") @Valid Comment comment, @PathVariable Long id, HttpServletRequest request, Errors errors) {
        ModelAndView mav = new ModelAndView("redirect:/forum/home");
        try {

            //get the logged-in user from Security context
            String userName = request.getUserPrincipal().getName();

            //save the comment object
            Comment comment1 = postService.createComment(comment, userName, id);
            mav.addObject("post", new Post());

        } catch (Exception ex) {
            mav.addObject("post", new Post());
            mav.addObject("message", "Unable to add comment");
            System.err.println(ex.getMessage());
        }
        mav.addObject("posts", postService.findAll());
        return mav;

    }

}
