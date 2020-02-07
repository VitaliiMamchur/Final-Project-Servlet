package ua.mamchur.servletproject.dao.implementation;

import ua.mamchur.servletproject.dao.*;

public class JDBCDaoFactory extends DaoFactory {

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(ConnectionPoolHolder.getDataSource());
    }

    @Override
    public RoleDao createRoleDao() {
        return new JDBCRoleDao(ConnectionPoolHolder.getDataSource());
    }

    @Override
    public RepairRequestDao createRepairRequestDao() {
        return new JDBCRepairRequestDao(ConnectionPoolHolder.getDataSource());
    }

    @Override
    public RepairRequestStatusDao createRepairRequestStatusDao() {
        return new JDBCRepairRequestStatusDao(ConnectionPoolHolder.getDataSource());
    }
}
