package ua.mamchur.servletproject.dao;

import ua.mamchur.servletproject.model.Role;

public interface RoleDao extends GenericDao<Role> {
    Role findByRole(String role);
}
