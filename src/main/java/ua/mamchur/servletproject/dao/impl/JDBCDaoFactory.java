package ua.mamchur.servletproject.dao.impl;

import ua.mamchur.servletproject.dao.*;

public class JDBCDaoFactory extends DaoFactory {

    private final ConnectionPoolHolder connectionPoolHolder;

    public JDBCDaoFactory(ConnectionPoolHolder connectionPoolHolder) {
        this.connectionPoolHolder = connectionPoolHolder;
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(connectionPoolHolder);
    }

    @Override
    public RoleDao createRoleDao() {
        return new JDBCRoleDao(connectionPoolHolder);
    }

    @Override
    public RepairRequestDao createRepairRequestDao() {
        return new JDBCRepairRequestDao(connectionPoolHolder);
    }

    @Override
    public RepairRequestStatusDao createRepairRequestStatusDao() {
        return new JDBCRepairRequestStatusDao(connectionPoolHolder);
    }
}
