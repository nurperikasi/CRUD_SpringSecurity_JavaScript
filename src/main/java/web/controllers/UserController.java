package web.controllers;


import com.sun.org.apache.xpath.internal.operations.Mod;
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

//    @PersistenceContext
//    ModelAndView modelAndView;


    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginPage");
        modelAndView.addObject("userForm", new User());
        return modelAndView;
    }
//
//    @PostMapping("/login")
//    public ModelAndView login(@ModelAttribute("user") User user, BindingResult bindingResult){
//        modelAndView.setViewName("/user");
//        userValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors()){
//            modelAndView.setViewName("redirect/login");
//        }
//        userService.add(user);
//        securityService.autologin(user.getUsername(),user.getConfirmPassword());
//        return modelAndView;
//    }
//
    @GetMapping("/admin")
    public ModelAndView admin(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> list = userService.allUsers();
        modelAndView.setViewName("admin");
        modelAndView.addObject("allUsers", list);
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user'sPage");
//        modelAndView.addObject("allUsers", list);
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updatePage(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(id);
        modelAndView.addObject("user", userService.getById(id));
        System.out.println(userService.getById(id));
        modelAndView.setViewName("updatePage");
        return modelAndView;
    }

    @PostMapping("/update")
    public  ModelAndView updateUser(@ModelAttribute("user") User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/user");
        userService.update(user);
        return modelAndView;
    }
    @GetMapping("/addPage")
    public ModelAndView addPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("addPage");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addUser(@ModelAttribute("user") User user){
        ModelAndView modelAndView = new ModelAndView();
        userService.add(user);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getById(id);
        userService.delete(user);
        modelAndView.setViewName("redirect:/user");
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView deleteUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginPage");
        return modelAndView;
    }
}
