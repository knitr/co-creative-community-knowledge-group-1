package com.cardiff.controller;

import com.cardiff.entity.Community;
import com.cardiff.repository.CommunityRepository;
import com.cardiff.service.CommunityService;
import com.cardiff.service.FragmentService;
import com.cardiff.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;


@Controller
public class CommunityController {

    private final CommunityRepository communityRepository;
    private CommunityService communityService;
    private FragmentService fragmentService;


    public CommunityController(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    @Autowired
    public void setFragmentService(FragmentService fragmentService) {

        this.fragmentService = fragmentService;
    }

    @Autowired
    public void setCommunityService(CommunityService communityService) {

        this.communityService = communityService;
    }

    @GetMapping("/home/NewCommunity")
    public String showCreateCommunityForm(WebRequest request, Model model) {
        model.addAttribute("community", new Community());
        model.addAttribute("communityList", fragmentService.getAllCommunitiesForNavigation());
        return "NewCommunity";

    }


    @PostMapping("/home/NewCommunity")
    public ModelAndView registerCommunity(@ModelAttribute("community") @Valid Community community, HttpServletRequest request, Errors errors,
                                          @RequestParam("image") MultipartFile multipartFile) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        community.setPhoto(fileName);
        Community savedCommunity = communityRepository.save(community);
        String uploadDir = "/src/main/resources/static/images/" + savedCommunity.getId();
        try {
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ModelAndView mav = new ModelAndView("community");
        Community registered = null;
        try {
            registered = communityService.createCommunity(community);
            mav.addObject("message",
                    "Community registered successfully");

        } catch (Exception e) {
            mav.addObject("message", "A community with that name already exists.");
            return mav;
        }
        return new ModelAndView("redirect:/community/" + registered.getId());
    }


    @GetMapping("/home")
    public String viewCommunityList(WebRequest request, Model model) {
        model.addAttribute("communityList", fragmentService.getAllCommunitiesForNavigation());
        return "home";
    }

    @GetMapping("/")
    public String redirectToHome(WebRequest request, Model model) {
        model.addAttribute("communityList", fragmentService.getAllCommunitiesForNavigation());
        return "home";
    }

    /**
     * This method is to populate community details in the model and navigate to community page
     *
     * @param request
     * @param model
     * @param id      Community ID
     * @return
     */
    @GetMapping("/community/{id}")
    public String getCommunityById(WebRequest request, Model model, @PathVariable Long id) {

        //fetch community by primary key
        Community communityById = communityService.getCommunityById(id);

        if (communityById != null) {
            model.addAttribute("community", communityById);
        }
        model.addAttribute("communityList", fragmentService.getAllCommunitiesForNavigation());
        return "community";
    }
}
