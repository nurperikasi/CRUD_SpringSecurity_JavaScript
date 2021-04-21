package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import web.Dao.RoleDao;
import web.Dao.UserDao;
import web.models.Role;
import web.models.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    RoleDao roleDao;

    final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Set<Role> getAllRoles() {
        Set<Role> roles = new HashSet<>();
        Role role1 = new Role("ADMIN");
        role1.setId(56L);
        roles.add(role1);
        Role role2 = new Role("USER");
        role2.setId(85l);
        roles.add(role2);
        return  roles;
    }
    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

}
