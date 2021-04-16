package web.controllers;


import web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.service.SecurityService;
import web.service.UserService;
import web.validator.UserValidator;

import javax.persistence.PersistenceContext;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @PersistenceContext
    ModelAndView modelAndView;


    @GetMapping("/login")
    public ModelAndView login(){
        modelAndView.setViewName("loginPage");
        modelAndView.addObject("userForm", new User());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("user") User user, BindingResult bindingResult){
        modelAndView.setViewName("/user");
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()){
            modelAndView.setViewName("redirect/login");
        }
        userService.add(user);
        securityService.autologin(user.getUsername(),user.getConfirmPassword());
        return modelAndView;
    }

    @PostMapping("/admin")
    public ModelAndView admin(){
        modelAndView.setViewName("/admin");
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView allUsers() {
        List<User> list = userService.allUsers();
        modelAndView.setViewName("usersList");
        modelAndView.addObject("allUsers", list);
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updatePage(@PathVariable("id") int id){
        System.out.println(id);
        modelAndView.addObject("user", userService.getById(id));
        System.out.println(userService.getById(id));
        modelAndView.setViewName("updatePage");
        return modelAndView;
    }

    @PostMapping("/update")
    public  ModelAndView updateUser(@ModelAttribute("user") User user){
        modelAndView.setViewName("redirect:/user");
        userService.update(user);
        return modelAndView;
    }
    @GetMapping("/addPage")
    public ModelAndView addPage(){
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("addPage");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addUser(@ModelAttribute("user") User user){
        userService.add(user);
        modelAndView.setViewName("redirect:/user");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") int id){
        User user = userService.getById(id);
        userService.delete(user);
        modelAndView.setViewName("redirect:/user");
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView deleteUser(){
        modelAndView.setViewName("loginPage");
        return modelAndView;
    }
}
