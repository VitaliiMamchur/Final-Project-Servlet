package ua.mamchur.servletproject.dao;

import ua.mamchur.servletproject.model.RepairRequestStatus;

public interface RepairRequestStatusDao extends GenericDao<RepairRequestStatus> {
    RepairRequestStatus findByStatus(String status);
}
