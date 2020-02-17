package ua.mamchur.servletproject.service;

import ua.mamchur.servletproject.model.User;

public interface UserService {

    Long ROLE_USER_ID = 0L;
    Long ROLE_MANAGER_ID = 1L;
    Long ROLE_MASTER_ID = 2L;


    User create(String username, String password);

    String login(String username, String password);
}
