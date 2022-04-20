package com.cardiff.controller;

import com.cardiff.domain.UserDto;
import com.cardiff.entity.User;
import com.cardiff.exception.UserAlreadyExistException;
import com.cardiff.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * This method Populate empty user DTO object to get data from the form
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }


    /**
     * This method will create a new user if the user has not already been registered
     *
     * @param userDto
     * @param request
     * @param errors
     * @return
     */
    @PostMapping("/user/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, HttpServletRequest request, Errors errors) {
        ModelAndView mav = new ModelAndView("registration");
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(userDto);
            mav.addObject("message",
                    "User registered successfully");

        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }
        return new ModelAndView("successRegister", "user", userDto);

    }

}
