package ua.mamchur.servletproject.dao.implementation;

import ua.mamchur.servletproject.dao.DaoFactory;
import ua.mamchur.servletproject.dao.RepairRequestDao;
import ua.mamchur.servletproject.dao.RepairRequestStatusDao;
import ua.mamchur.servletproject.dao.UserDao;
import ua.mamchur.servletproject.model.RepairRequest;
import ua.mamchur.servletproject.model.RepairRequestStatus;
import ua.mamchur.servletproject.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCRepairRequestDao implements RepairRequestDao {
    private Connection connection;

    DaoFactory daoFactory = new JDBCDaoFactory();
    UserDao userDao = daoFactory.createUserDao();
    RepairRequestStatusDao repairRequestStatusDao = daoFactory.createRepairRequestStatusDao();

    public String SQL_CREATE = "INSERT INTO repair_request (theme, description, feedback, price, reason, active, user_id, status_id) VALUES (?, ?, null, null, null, ?, ?, ?)";
    public String SQL_FIND_ALL = "SELECT * FROM repair_request";
    public String SQL_UPDATE_BY_ID = "UPDATE repair_request SET status_id = 2 WHERE id = ?";

    public JDBCRepairRequestDao(DataSource dataSource) {

        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(RepairRequest repairRequest) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE);

            statement.setString(1 , repairRequest.getTheme());
            statement.setString(2 , repairRequest.getDescription());
            statement.setBoolean(3 , repairRequest.isActive());
            statement.setLong(4, repairRequest.getRequestCreator().getId());
            statement.setLong(5, repairRequest.getStatus().getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<RepairRequest> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<RepairRequest> findAll() {
        try {
            List<RepairRequest> repairRequests = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String theme = resultSet.getString("theme");
                boolean active = resultSet.getBoolean("active");
                String description = resultSet.getString("description");
                Integer price = resultSet.getInt("price");
                Long userId = resultSet.getLong("user_id");
                Long statusId = resultSet.getLong("status_id");
                User requestCreator = userDao.findById(userId).get();
                RepairRequestStatus status = repairRequestStatusDao.findById(statusId).get();

                RepairRequest repairRequest = new RepairRequest(id, theme, description, price, active, requestCreator, status);
                repairRequests.add(repairRequest);
            }

            return repairRequests;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void updateById(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(RepairRequest entity) {

    }

    @Override
    public void delete(int id) {

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
