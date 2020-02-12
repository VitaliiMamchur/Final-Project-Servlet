package ua.mamchur.servletproject.dao;

import ua.mamchur.servletproject.dao.impl.ConnectionPoolHolder;
import ua.mamchur.servletproject.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();

    public abstract RoleDao createRoleDao();

    public abstract RepairRequestDao createRepairRequestDao();

    public abstract RepairRequestStatusDao createRepairRequestStatusDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    DaoFactory temp = new JDBCDaoFactory(ConnectionPoolHolder.poolHolder());
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
