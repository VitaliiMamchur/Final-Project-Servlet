package ua.mamchur.servletproject.service;

import ua.mamchur.servletproject.model.RepairRequest;

import java.util.List;

public interface RepairRequestService {

    Long CURRENT_REQUEST_STATUS_ID = 0L;
    Long ACCEPTED_REQUEST_STATUS_ID = 1L;
    Long CLOSED_REQUEST_STATUS_ID = 2L;
    Long DECLINED_REQUEST_STATUS_ID = 3L;
    String CURRENT_REQUEST = "CURRENT_REQUEST";
    String ACCEPTED_REQUEST = "ACCEPTED_REQUEST";
    String CLOSED_REQUEST = "CLOSED_REQUEST";
    String DECLINED_REQUEST = "DECLINED_REQUEST";


    void create(String theme, String description, String username);

    void acceptRequestByManager(Long requestId, Integer price);

    boolean addFeedbackByUser(Long requestID, String feedback);

    void declineRequestByManager(Long requestID);

    void closeRequestByMaster(Long requestID);

    List<RepairRequest> showManagerList();

    List<RepairRequest> showMasterList();

    List<RepairRequest> showUserList(String username);
}
