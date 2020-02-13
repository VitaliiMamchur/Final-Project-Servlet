package ua.mamchur.servletproject.service;

import ua.mamchur.servletproject.model.RepairRequest;

import java.util.List;

public interface RepairRequestService {

    void create(String theme, String description, String username);

    void acceptRequestByManager(Long requestId, Integer price);

    boolean addFeedbackByUser(Long requestID, String feedback);

    void declineRequestByManager(Long requestID);

    void closeRequestByMaster(Long requestID);

    List<RepairRequest> showManagerList();

    List<RepairRequest> showMasterList();

    List<RepairRequest> showUserList(String username);
}
