package ua.mamchur.servletproject.service;

import ua.mamchur.servletproject.model.User;

public interface UserService {

    User create(String username, String password);

    String login(String username, String password);
}
