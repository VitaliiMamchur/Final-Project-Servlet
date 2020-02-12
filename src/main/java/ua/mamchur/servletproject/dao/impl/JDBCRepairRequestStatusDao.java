package ua.mamchur.servletproject.dao.impl;

import ua.mamchur.servletproject.dao.RepairRequestStatusDao;
import ua.mamchur.servletproject.model.RepairRequestStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBCRepairRequestStatusDao implements RepairRequestStatusDao {

    private final ConnectionPoolHolder connectionPoolHolder;

    public JDBCRepairRequestStatusDao(final ConnectionPoolHolder connectionPoolHolder) {
        this.connectionPoolHolder = connectionPoolHolder;
    }

    public String SQL_FIND_BY_STATUS = "SELECT * FROM statuses WHERE status = ?";
    public String SQL_FIND_BY_ID = "SELECT * FROM statuses WHERE id = ?";

    @Override
    public void save(RepairRequestStatus entity) {

    }

    @Override
    public RepairRequestStatus findByStatus(String status) {
        try (Connection connection = connectionPoolHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_STATUS)) {
            statement.setString(1, status);
            ResultSet resultSet = statement.executeQuery();
            RepairRequestStatus result;
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String currentStatus = resultSet.getString("status");
                result = new RepairRequestStatus(id, currentStatus);
                return result;
            }
            return null;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<RepairRequestStatus> findById(Long id) {
        try (Connection connection = connectionPoolHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            RepairRequestStatus result;
            if (resultSet.next()) {
                String status = resultSet.getString("status");
                result = new RepairRequestStatus(id, status);
                return Optional.of(result);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<RepairRequestStatus> findAll() {
        return null;
    }

    @Override
    public void update(RepairRequestStatus entity) {

    }

    @Override
    public void delete(int id) {

    }
}
