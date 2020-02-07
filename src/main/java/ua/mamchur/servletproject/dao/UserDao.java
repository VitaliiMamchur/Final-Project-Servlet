package ua.mamchur.servletproject.dao;

import ua.mamchur.servletproject.model.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByUsername(String username);
    boolean checkUser(String username, String password);

}
