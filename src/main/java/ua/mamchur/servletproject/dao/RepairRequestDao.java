package ua.mamchur.servletproject.dao;

import ua.mamchur.servletproject.model.RepairRequest;
import java.util.List;

public interface RepairRequestDao extends GenericDao<RepairRequest> {
    void updateById(Long id, String status);
    void updateByManagerAccept(Long id, String status, Integer price);
    List<RepairRequest> findAllByStatusId(Long statusId);
    List<RepairRequest> findAllByUserId(Long userId);
    void addFeedback(Long requestID, String feedback);
}
