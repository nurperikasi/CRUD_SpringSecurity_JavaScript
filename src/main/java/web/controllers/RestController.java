package web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/rest")
class RestUserController {

        private final UserService userService;

        public RestUserController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping("")
        public ResponseEntity<List<User>> list() {
            List<User> list = userService.allUsers();

            return (list !=null && !list.isEmpty()) ?
                    new ResponseEntity<>(list, HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @GetMapping("/{id}")
        public ResponseEntity<User> getById(@PathVariable int id) {
            return (userService.getById(id) != null) ?
                    new ResponseEntity<>(userService.getById(id), HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @PutMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public ResponseEntity<?> edit(@RequestBody User user) {
            userService.update(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        @DeleteMapping("")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public ResponseEntity<?> delete(@RequestBody User user) {
            userService.delete(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public ResponseEntity<?> add(@RequestBody User user) {
            userService.add(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    @GetMapping("/addedUser")
    public ResponseEntity<User> addedUser() {
            return (userService.addedUser() != null) ?
                    new ResponseEntity<>(userService.addedUser(), HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    @GetMapping("/user")
    public ResponseEntity<User> userPage() {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        return (loggedInUser != null) ?
                new ResponseEntity<>(userService.getByName(loggedInUser.getName()), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
}
