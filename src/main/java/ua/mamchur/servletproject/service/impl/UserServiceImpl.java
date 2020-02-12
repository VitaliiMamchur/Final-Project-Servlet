package ua.mamchur.servletproject.service.impl;

import ua.mamchur.servletproject.dao.DaoFactory;
import ua.mamchur.servletproject.dao.RoleDao;
import ua.mamchur.servletproject.dao.UserDao;
import ua.mamchur.servletproject.model.Role;
import ua.mamchur.servletproject.model.User;
import ua.mamchur.servletproject.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    UserDao userDao = DaoFactory.getInstance().createUserDao();
    RoleDao roleDao = DaoFactory.getInstance().createRoleDao();

    @Override
    public User create(String username, String password) {
        User user = new User();
        Long roleId = 0L;
        Optional<User> userFromDB = userDao.findByUsername(username);
        if (userFromDB.isPresent()) {
            Role role = roleDao.findById(roleId).get();
            user.setUsername(username);
            user.setPassword(password);
            user.setActive(true);
            user.setRole(role);
            userDao.save(user);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public String login(String username, String password) {
        if (userDao.checkUser(username, password)) {
            String role = userDao.findByUsername(username).get().getRole().getRole();
            return role;
        } else {
            return null;
        }
    }
}
