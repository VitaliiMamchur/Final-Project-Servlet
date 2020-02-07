package ua.mamchur.servletproject.dao.implementation;

import ua.mamchur.servletproject.dao.RepairRequestStatusDao;
import ua.mamchur.servletproject.model.RepairRequestStatus;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBCRepairRequestStatusDao implements RepairRequestStatusDao {
    Connection connection;

    public String SQL_FIND_BY_STATUS = "SELECT * FROM statuses WHERE status = ?";
    public String SQL_FIND_BY_ID = "SELECT * FROM statuses WHERE id = ?";


    public JDBCRepairRequestStatusDao(DataSource dataSource) {

        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void save(RepairRequestStatus entity) {

    }

    @Override
    public RepairRequestStatus findByStatus(String status) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_STATUS);
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
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
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

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
