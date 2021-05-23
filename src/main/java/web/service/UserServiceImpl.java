package web.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import web.Dao.RoleDao;
import web.Dao.UserDao;
import web.models.Role;
import web.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    User user1 = new User();

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
        List<Role> list = new ArrayList();
        for (Role role : user.getRoles()) {
            list.add(roleDao.getByName(role.getRole()));
        }
        user.setRoles(list);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
        user1 = userDao.getByName(user.getName());
    }

    @Override
    public User addedUser(){
        return user1;
    }

    @Override
    public void delete(User user) {
        List<Role> list = new ArrayList<>();
        user.setRoles(list);
        userDao.delete(user);
    }

    @Override
    public void update(User user) {
        List<Role> list = new ArrayList();
        for (Role role : user.getRoles()) {
            list.add(roleDao.getByName(role.getRole()));
        }
        user.setRoles(list);
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
