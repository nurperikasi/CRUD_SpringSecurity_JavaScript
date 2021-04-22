package web.service;

import web.models.Role;
import web.models.User;

import java.util.List;

public interface UserService {

    List<Role> getAllRoles();
    List<User> allUsers();
    void add(User user);
    void delete(User user);
    void update(User user, String[] roleList);
    User getById(int id);
    User getByName(String name);
}
