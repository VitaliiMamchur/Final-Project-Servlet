package ua.mamchur.servletproject.dao;

import ua.mamchur.servletproject.model.RepairRequestStatus;
import ua.mamchur.servletproject.model.User;

public interface RepairRequestStatusDao extends GenericDao<RepairRequestStatus> {
    RepairRequestStatus findByStatus(String status);
}
