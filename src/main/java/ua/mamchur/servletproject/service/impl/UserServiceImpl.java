package ua.mamchur.servletproject.service.impl;

import ua.mamchur.servletproject.dao.DaoFactory;
import ua.mamchur.servletproject.dao.RoleDao;
import ua.mamchur.servletproject.dao.UserDao;
import ua.mamchur.servletproject.model.Role;
import ua.mamchur.servletproject.model.User;
import ua.mamchur.servletproject.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDao userDao = DaoFactory.getInstance().createUserDao();
    private RoleDao roleDao = DaoFactory.getInstance().createRoleDao();

    @Override
    public User create(String username, String password) {
        User user = new User();
        Optional<User> userFromDB = userDao.findByUsername(username);
        if (userFromDB.isPresent()) {
           return null;
        } else {
            Role role = roleDao.findById(ROLE_USER_ID).get();
            user.setUsername(username);
            user.setPassword(password);
            user.setActive(true);
            user.setRole(role);
            userDao.save(user);
            return user;
        }
    }

    @Override
    public String login(String username, String password) {
        if (userDao.checkUser(username, password)) {
            return userDao.findByUsername(username).get().getRole().getRole();
        } else {
            return null;
        }
    }
}
