package web.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import web.Dao.RoleDao;
import web.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {

    private final RoleDao roleDao;

    private final UserService userService;

    public UserController(RoleDao roleDao, UserService userService) {
        this.roleDao = roleDao;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ModelAndView admin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");

//        getting current user
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        modelAndView.addObject("loggedInUserName", username);

//        For all roles list
        modelAndView.addObject("AllRolesList", userService.getAllRoles());

//        for new user
        modelAndView.addObject("newUser", new User());

        List<User> list = userService.allUsers();
        modelAndView.addObject("allUsers", list);

//        for current users roles
        modelAndView.addObject("userRoles", userService.getByName(username).getRoles1());
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView user() {
        ModelAndView modelAndView = new ModelAndView();

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        modelAndView.addObject("user", userService.getByName(username));

        modelAndView.setViewName("user'sPage");
        return modelAndView;
    }

    @RequestMapping("/getUser/{id}")
    @ResponseBody
    public Optional<User> updatePage(@PathVariable("id") int id){
        return Optional.ofNullable(userService.getById(id));
    }

   @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST} )
    public  ModelAndView updateUser(@ModelAttribute("user") User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        userService.update(user);
        return modelAndView;
    }


    @PostMapping("/add")
    public ModelAndView addUser(@ModelAttribute("user") User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        userService.add(user);
        return modelAndView;
    }


    @RequestMapping(value = "/delete", method = {RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST} )
    public ModelAndView deleteUser(@ModelAttribute("user") User user){
        ModelAndView modelAndView = new ModelAndView();
        userService.delete(user);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }
}
