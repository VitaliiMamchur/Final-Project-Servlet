package ua.mamchur.servletproject.dao.implementation;

import ua.mamchur.servletproject.dao.DaoFactory;
import ua.mamchur.servletproject.dao.RoleDao;
import ua.mamchur.servletproject.dao.UserDao;
import ua.mamchur.servletproject.model.Role;
import ua.mamchur.servletproject.model.User;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    DaoFactory daoFactory = new JDBCDaoFactory();
    RoleDao roleDao = daoFactory.createRoleDao();

    public String SQL_UPDATE = "UPDATE usr SET username = ? , password = ? , WHERE id = ?";
    public String SQL_CREATE = "INSERT INTO usr (username, password, active, role_id) VALUES (?, ?, ?, ?)";
    public String SQL_CHECK_USER = "SELECT * FROM usr WHERE username = ? AND password = ?";
    public String SQL_FIND_BY_ID = "SELECT * FROM usr WHERE id = ?";
    public String SQL_FIND_BY_USERNAME = "SELECT * FROM usr WHERE username = ?";
    public String SQL_FIND_ALL = "SELECT * FROM usr";
    public String SQL_DELETE = "DELETE FROM usr WHERE id = ?";



    public JDBCUserDao(DataSource dataSource) {

        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE);

            statement.setString(1 , user.getUsername());
            statement.setString(2 , user.getPassword());
            statement.setBoolean(3 , user.isActive());
            statement.setLong(4, user.getRole().getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            User result;
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                String username = resultSet.getString("username");
                result = new User(id, username, password);
                return Optional.of(result);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findByUsername(String userName) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USERNAME);
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            User result;
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String password = resultSet.getString("password");
                String username = resultSet.getString("username");
                Long roleId = resultSet.getLong("role_id");
                Role role = roleDao.findById(roleId).get();
                result = new User(id, username, password, role);
                return Optional.of(result);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean checkUser(String username, String password) {
        boolean exist = false;
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_CHECK_USER);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            exist = rs.next();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return exist;
    }


    @Override
    public List<User> findAll() {
        try {
            List<User> users = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Long id = resultSet.getLong("id");
                //boolean active = resultSet.getBoolean("active");
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
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1 , user.getUsername());
            statement.setString(2 , user.getPassword());
            statement.setLong(3, user.getId());
            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setLong(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
