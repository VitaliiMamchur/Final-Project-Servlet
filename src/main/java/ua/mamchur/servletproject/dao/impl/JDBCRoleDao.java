package ua.mamchur.servletproject.dao.impl;

import ua.mamchur.servletproject.dao.RoleDao;
import ua.mamchur.servletproject.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCRoleDao implements RoleDao {

    private final ConnectionPoolHolder connectionPoolHolder;

    public JDBCRoleDao(final ConnectionPoolHolder connectionPoolHolder) {
        this.connectionPoolHolder = connectionPoolHolder;
    }

    public String SQL_FIND_BY_ID = "SELECT * FROM roles WHERE id = ?";
    public String SQL_FIND_BY_ROLE = "SELECT * FROM roles WHERE role = ?";
    public String SQL_FIND_ALL = "SELECT * FROM roles";

    @Override
    public void save(Role entity) {

    }

    @Override
    public Optional<Role> findById(Long id) {
        try (Connection connection = connectionPoolHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Role result;
            if (resultSet.next()) {
                Long roleId = resultSet.getLong("id");
                String roleName = resultSet.getString("role");
                result = new Role(roleId, roleName);
                return Optional.of(result);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Role findByRole(String role) {
        try (Connection connection = connectionPoolHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ROLE)) {
            statement.setString(1, role);
            ResultSet resultSet = statement.executeQuery();
            Role result;
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String currentRole = resultSet.getString("role");
                result = new Role(id, currentRole);
                return result;
            }
            return null;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Role> findAll() {
        try (Connection connection = connectionPoolHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {
            List<Role> roles = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String roleName = resultSet.getString("role");

                Role role = new Role(roleName);

                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(Role entity) {

    }

    @Override
    public void delete(int id) {

    }
}
