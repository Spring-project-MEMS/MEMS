package com.fixit.areas.users.controllers;

import com.fixit.abstractions.controller.BaseController;
import com.fixit.areas.users.models.binding.UsersRegisterBindingModel;
import com.fixit.areas.users.models.service.UsersServiceModel;
import com.fixit.areas.users.services.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UsersController extends BaseController {

    private final UsersService usersService;

    private final ModelMapper modelMapper;

    @Autowired
    public UsersController(UsersService userService, ModelMapper modelMapper) {
        this.usersService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute UsersRegisterBindingModel usersRegisterBindingModel) {
        return super.view("views/user/register", "Register");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute UsersRegisterBindingModel usersRegisterBindingModel,
                                        BindingResult bindingResult,
                                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return super.view("views/user/register", "Register");
        }

        UsersServiceModel userServiceModel = this.modelMapper.map(usersRegisterBindingModel, UsersServiceModel.class);
        //what will happen with role
        this.usersService.createUser(userServiceModel);
        return super.redirect("/login");
    }

    @GetMapping("/users/create")
    public ModelAndView createUser(@ModelAttribute UsersRegisterBindingModel usersRegisterBindingModel) {
        return super.view("views/user/create-user", "Create User");
    }

    @PostMapping("/users/create")
    public ModelAndView createUserConfirm(@Valid @ModelAttribute UsersRegisterBindingModel usersRegisterBindingModel,
                                        BindingResult bindingResult,
                                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return super.view("views/user/create-user", "Create User");
        }

        UsersServiceModel userServiceModel = this.modelMapper.map(usersRegisterBindingModel, UsersServiceModel.class);
        //what will happen with role
        this.usersService.createUser(userServiceModel);
        return super.redirect("/");
    }

//    @GetMapping("/user/{username}")
//    public ModelAndView userProfile(@PathVariable String username) {
//        UsersServiceModel userServiceModel = this.userService.findByUsername(username);
//        UserProfileViewModel userProfileViewModel = this.modelMapper.map(userServiceModel, UserProfileViewModel.class);
//        return super.view("/views/user/profile", userProfileViewModel);
//    }

    @GetMapping("/login")
    public ModelAndView login(String error, ModelAndView mav) {
        mav.addObject("viewName", "/views/user/login");
        mav.setViewName("layout");
        if (error != null) {
            mav.addObject("error", "Wrong username or password");
        }
        return mav;
    }

}
