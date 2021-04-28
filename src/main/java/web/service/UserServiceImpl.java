package web.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import web.Dao.RoleDao;
import web.Dao.UserDao;
import web.models.Role;
import web.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleDao roleDao;

    private final UserDao userDao;

    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, UserDao userDao, RoleDao roleDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles= new ArrayList<>();
        roles.add(roleDao.getById(1));
        roles.add(roleDao.getById(2));
        return  roles;
    }
    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void update(User user) {
        if (!userDao.getById(user.getId()).getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
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
