package ua.mamchur.servletproject.dao.implementation;

import ua.mamchur.servletproject.dao.RepairRequestDao;
import ua.mamchur.servletproject.model.RepairRequest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBCRepairRequestDao implements RepairRequestDao {
    private Connection connection;

    public String SQL_CREATE = "INSERT INTO repair_request (theme, description, feedback, price, reason, active, user_id, status_id) VALUES (?, ?, null, null, null, ?, ?, ?)";


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
        return null;
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
