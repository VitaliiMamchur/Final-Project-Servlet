package ua.mamchur.servletproject.dao;

import ua.mamchur.servletproject.dao.implementation.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract RoleDao createRoleDao();
    public abstract RepairRequestDao createRepairRequestDao();
    public abstract RepairRequestStatusDao createRepairRequestStatusDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}