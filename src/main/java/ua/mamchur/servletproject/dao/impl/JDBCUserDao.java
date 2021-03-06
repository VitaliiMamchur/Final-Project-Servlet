package ua.mamchur.servletproject.dao.impl;

import ua.mamchur.servletproject.dao.UserDao;
import ua.mamchur.servletproject.model.Role;
import ua.mamchur.servletproject.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao implements UserDao {

    private final ConnectionPoolHolder connectionPoolHolder;

    public JDBCUserDao(final ConnectionPoolHolder connectionPoolHolder) {
        this.connectionPoolHolder = connectionPoolHolder;
    }

    public String SQL_UPDATE = "UPDATE usr SET username = ? , password = ? , WHERE id = ?";
    public String SQL_CREATE = "INSERT INTO usr (username, password, active, role_id) VALUES (?, ?, ?, ?)";
    public String SQL_CHECK_USER = "SELECT * FROM usr WHERE username = ? AND password = ?";
    public String SQL_FIND_BY_ID = "SELECT * FROM usr WHERE id = ?";
    public String SQL_FIND_BY_USERNAME =
            "SELECT usr.*, role FROM usr LEFT JOIN roles ON usr.role_id = roles.id where usr.username = ?";
    public String SQL_FIND_ALL = "SELECT * FROM usr";
    public String SQL_DELETE = "DELETE FROM usr WHERE id = ?";

    @Override
    public void save(User user) {
        try (Connection connection = connectionPoolHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.isActive());
            statement.setLong(4, user.getRole().getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (Connection connection = connectionPoolHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            User result;
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                String username = resultSet.getString("username");
                result = new User(id, username, password);
                return Optional.of(result);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findByUsername(String userName) {
        try (Connection connection = connectionPoolHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USERNAME)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            User result;
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String password = resultSet.getString("password");
                String username = resultSet.getString("username");
                Long roleId = resultSet.getLong("role_id");
                String roleName = resultSet.getString("role");
                Role role = new Role(roleId, roleName);
                result = new User(id, username, password, role);
                return Optional.of(result);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean checkUser(String username, String password) {
        boolean exist = false;
        try (Connection connection = connectionPoolHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CHECK_USER)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            exist = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exist;
    }


    @Override
    public List<User> findAll() {
        try (Connection connection = connectionPoolHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {
            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String password = resultSet.getString("password");
                String username = resultSet.getString("username");

                User user = new User(username, password);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = connectionPoolHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setLong(3, user.getId());
            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = connectionPoolHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setLong(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
