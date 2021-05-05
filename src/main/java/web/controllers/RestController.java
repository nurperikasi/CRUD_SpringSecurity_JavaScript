package web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

        private final UserService userService;

        public RestController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping("")
        public List<User> list() {
            return userService.allUsers();
        }

        @GetMapping("/{id}")
        public User getById(@PathVariable int id) {
            return userService.getById(id);
        }

        @PutMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void edit(@RequestBody User user) {
            userService.update(user);
        }

        @DeleteMapping("")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void delete(@RequestBody User user) {
            userService.delete(user);
        }

        @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public User add(@RequestBody User user) {
            userService.add(user);
            return userService.getById(user.getId());
        }

    @GetMapping("/addedUser")
    public User addedUser() {
        return userService.addedUser();
    }

    @GetMapping("/user")
    public User userPage() {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            return userService.getByName(loggedInUser.getName());
        }
}
