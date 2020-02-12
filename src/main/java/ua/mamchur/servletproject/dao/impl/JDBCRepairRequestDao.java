package ua.mamchur.servletproject.dao.impl;

import ua.mamchur.servletproject.dao.RepairRequestDao;
import ua.mamchur.servletproject.model.RepairRequest;
import ua.mamchur.servletproject.model.RepairRequestStatus;
import ua.mamchur.servletproject.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCRepairRequestDao implements RepairRequestDao {

    private final ConnectionPoolHolder connectionPoolHolder;

    public JDBCRepairRequestDao(final ConnectionPoolHolder connectionPoolHolder) {
        this.connectionPoolHolder = connectionPoolHolder;
    }

    public String SQL_CREATE = "INSERT INTO repair_request (theme, description, feedback, price, reason, active, user_id, status_id) VALUES (?, ?, null, null, null, ?, ?, ?)";
    public String SQL_FIND_BY_ID =
            "select repair_request.*, status, username, password from repair_request " +
                    "left join statuses on repair_request.status_id = statuses.id " +
                    "left join usr on repair_request.user_id = usr.id where repair_request.id = ?";
    public String SQL_FIND_ALL_BY_STATUS_ID =
            "select repair_request.*, status, username, password from repair_request " +
                    "left join statuses on repair_request.status_id = statuses.id " +
                    "left join usr on repair_request.user_id = usr.id where repair_request.status_id = ?";
    ;
    public String SQL_FIND_ALL_BY_USER_ID =
            "select repair_request.*, status, username, password from repair_request " +
                    "left join statuses on repair_request.status_id = statuses.id " +
                    "left join usr on repair_request.user_id = usr.id where repair_request.user_id = ?";
    ;
    public String SQL_UPDATE_BY_ID = "UPDATE repair_request SET status_id = ? WHERE id = ?";
    public String SQL_ADD_FEEDBACK = "UPDATE repair_request SET feedback = ? WHERE id = ?";
    public String SQL_UPDATE_BY_MANAGER = "UPDATE repair_request SET status_id = ?, price = ? WHERE id = ?";

    @Override
    public void save(RepairRequest repairRequest) {
        try (PreparedStatement statement = connectionPoolHolder.getConnection().prepareStatement(SQL_CREATE)) {
            statement.setString(1, repairRequest.getTheme());
            statement.setString(2, repairRequest.getDescription());
            statement.setBoolean(3, repairRequest.isActive());
            statement.setLong(4, repairRequest.getRequestCreator().getId());
            statement.setLong(5, repairRequest.getStatus().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<RepairRequest> findById(Long id) {
        try (PreparedStatement statement = connectionPoolHolder.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            RepairRequest result;
            if (resultSet.next()) {
                String theme = resultSet.getString("theme");
                String description = resultSet.getString("description");
                boolean active = resultSet.getBoolean("active");
                Integer price = resultSet.getInt("price");
                String feedback = resultSet.getString("feedback");
                Long userId = resultSet.getLong("user_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Long statusId = resultSet.getLong("status_id");
                String statusName = resultSet.getString("status");
                User requestCreator = new User(userId, username, password);
                RepairRequestStatus status = new RepairRequestStatus(statusId, statusName);
                result = new RepairRequest(id, theme, description, active, price, feedback, requestCreator, status);
                return Optional.of(result);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<RepairRequest> findAll() {
        return null;
    }

    @Override
    public List<RepairRequest> findAllByStatusId(Long statusId) {
        try (PreparedStatement statement = connectionPoolHolder.getConnection().prepareStatement(SQL_FIND_ALL_BY_STATUS_ID)) {
            List<RepairRequest> repairRequests = new ArrayList<>();
            statement.setLong(1, statusId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String theme = resultSet.getString("theme");
                boolean active = resultSet.getBoolean("active");
                String description = resultSet.getString("description");
                Integer price = resultSet.getInt("price");
                String feedback = resultSet.getString("feedback");
                Long userId = resultSet.getLong("user_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String statusName = resultSet.getString("status");
                User requestCreator = new User(userId, username, password);
                RepairRequestStatus status = new RepairRequestStatus(statusId, statusName);
                RepairRequest repairRequest = new RepairRequest(id, theme, description, active, price, feedback, requestCreator, status);
                repairRequests.add(repairRequest);
            }

            return repairRequests;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<RepairRequest> findAllByUserId(Long userId) {
        try (PreparedStatement statement = connectionPoolHolder.getConnection().prepareStatement(SQL_FIND_ALL_BY_USER_ID)) {
            List<RepairRequest> repairRequests = new ArrayList<>();
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String theme = resultSet.getString("theme");
                boolean active = resultSet.getBoolean("active");
                String description = resultSet.getString("description");
                Integer price = resultSet.getInt("price");
                String feedback = resultSet.getString("feedback");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Long statusId = resultSet.getLong("status_id");
                String statusName = resultSet.getString("status");
                User requestCreator = new User(userId, username, password);
                RepairRequestStatus status = new RepairRequestStatus(statusId, statusName);

                RepairRequest repairRequest = new RepairRequest(id, theme, description, active, price, feedback, requestCreator, status);
                repairRequests.add(repairRequest);
            }

            return repairRequests;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void updateById(Long id, Long statusId) {
        try (PreparedStatement statement = connectionPoolHolder.getConnection().prepareStatement(SQL_UPDATE_BY_ID)) {
            statement.setLong(1, statusId);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateByManagerAccept(Long id, Long statusId, Integer price) {
        try (PreparedStatement statement = connectionPoolHolder.getConnection().prepareStatement(SQL_UPDATE_BY_MANAGER)) {
            statement.setLong(1, statusId);
            statement.setInt(2, price);
            statement.setLong(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addFeedback(Long requestID, String feedback) {
        try (PreparedStatement statement = connectionPoolHolder.getConnection().prepareStatement(SQL_ADD_FEEDBACK)) {
            statement.setString(1, feedback);
            statement.setLong(2, requestID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(RepairRequest entity) {

    }

    @Override
    public void delete(int id) {

    }

}
