package ua.mamchur.servletproject.service.impl;

import ua.mamchur.servletproject.dao.DaoFactory;
import ua.mamchur.servletproject.dao.UserDao;
import ua.mamchur.servletproject.service.UtilityService;

import javax.servlet.http.HttpSession;

public class UtilityServiceImpl implements UtilityService {

    UserDao userDao = DaoFactory.getInstance().createUserDao();

    @Override
    public void login() {
    }
}
