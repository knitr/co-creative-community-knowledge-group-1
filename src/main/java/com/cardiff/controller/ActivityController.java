package com.cardiff.controller;


import com.cardiff.entity.Activity;
import com.cardiff.entity.Community;
import com.cardiff.service.ActivityService;
import com.cardiff.service.CommunityService;
import com.cardiff.service.FragmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class ActivityController {

    private ActivityService activityService;
    private CommunityService communityService;

    private FragmentService fragmentService;

    @Autowired
    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Autowired
    public void setCommunityService(CommunityService communityService) {
        this.communityService = communityService;
    }

    @Autowired
    public void setFragmentService(FragmentService fragmentService) {
        this.fragmentService = fragmentService;
    }

    @GetMapping("/community/activity/{id}")
    public String showCreateActivityPage(WebRequest request, Model model, @PathVariable Long id) {

        Activity activity = new Activity();
        Community community = new Community();
        community.setId(id);
        activity.setCommunity(community);
        model.addAttribute("activity", activity);
        model.addAttribute("communityList", fragmentService.getAllCommunitiesForNavigation());
        return "activity";
    }


    @PostMapping("/activity/create")
    public ModelAndView createActivity(@ModelAttribute("Activity") @Valid Activity activity, HttpServletRequest request, Errors errors) {
        ModelAndView mav = new ModelAndView("redirect:/community/" + activity.getCommunityId());
        Activity saved = null;
        try {

            Community community = new Community();
            community.setId(activity.getCommunityId());
            activity.setCommunity(community);
            saved = activityService.createActivity(activity);
            mav.addObject("message",
                    "Activity saved successfully");

        } catch (Exception ex) {
            mav.addObject("message", "Error occurred while saving Activity");
            return mav;
        }
        return mav;
    }
}
