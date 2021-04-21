package web.service;

import web.models.Role;
import web.models.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    Set<Role> getAllRoles();
    List<User> allUsers();
    void add(User user);
    void delete(User user);
    void update(User user);
    User getById(int id);
    User getByName(String name);
}
