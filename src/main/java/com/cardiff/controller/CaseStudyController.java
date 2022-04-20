package com.cardiff.controller;

import com.cardiff.entity.CaseStudy;
import com.cardiff.entity.Community;
import com.cardiff.service.CaseStudyService;
import com.cardiff.service.FragmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CaseStudyController {

    private FragmentService fragmentService;
    private CaseStudyService caseStudyService;


    @Autowired
    public void setFragmentService(FragmentService fragmentService) {
        this.fragmentService = fragmentService;
    }

    @Autowired
    public void setCaseStudyService(CaseStudyService caseStudyService) {
        this.caseStudyService = caseStudyService;
    }
    //display the existing case study
    @GetMapping("/viewCaseStudy/{id}")//URL
    public String showCaseStudyPage(WebRequest request, Model model, @PathVariable Long id) {
        /*
            transfer attributes from database to HTML to display
         */
        model.addAttribute("casestudy", caseStudyService.findById(id));
        model.addAttribute("communityList", fragmentService.getAllCommunitiesForNavigation());
        return "ViewCaseStudy";//Return to the HTML page
    }

    //see the case study the user just created
    @GetMapping("/createCaseStudy/{id}")//URL
    public String showCreateCaseStudyPage(WebRequest request, Model model, @PathVariable Long id) {
        CaseStudy caseStudy = new CaseStudy();
        Community community = new Community();
        community.setId(id);
        caseStudy.setCommunity(community);
/*
    add attribute from case study table and send to the front end
 */
        model.addAttribute("casestudy", caseStudy);
        model.addAttribute("communityList", fragmentService.getAllCommunitiesForNavigation());
        return "createCaseStudy";
    }

    @PostMapping("/createCaseStudy")
    public ModelAndView saveCaseStudyForm(@ModelAttribute("caseStudy") CaseStudy caseStudy, HttpServletRequest request) {
        //create a object mav
        ModelAndView mav = new ModelAndView("redirect:/viewCaseStudy/");
        //try catch
        try {
            Community community = new Community();//new community object
            community.setId(caseStudy.getCommunityId());//set the id to make sure the id in community table is equal to the community id in case study table
            caseStudy.setCommunity(community);
            CaseStudy savedCaseStudy = caseStudyService.saveCaseStudy(caseStudy);
            mav.addObject("message",
                    "CaseStudy create successfully");
            Long caseStudyId = savedCaseStudy.getId();
            mav.setViewName(mav.getViewName() + caseStudyId);
        } catch (ExpressionException e) {//report system error
            mav.addObject("message", "error");
            return mav;
        }

        return mav;

    }

}
