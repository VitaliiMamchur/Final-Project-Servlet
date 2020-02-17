package ua.mamchur.servletproject.service.impl;

import ua.mamchur.servletproject.dao.DaoFactory;
import ua.mamchur.servletproject.dao.RepairRequestDao;
import ua.mamchur.servletproject.dao.RepairRequestStatusDao;
import ua.mamchur.servletproject.dao.UserDao;
import ua.mamchur.servletproject.model.RepairRequest;
import ua.mamchur.servletproject.model.RepairRequestStatus;
import ua.mamchur.servletproject.model.User;
import ua.mamchur.servletproject.service.RepairRequestService;

import java.util.List;

public class RepairRequestServiceImpl implements RepairRequestService {

    private UserDao userDao = DaoFactory.getInstance().createUserDao();
    private RepairRequestDao repairRequestDao = DaoFactory.getInstance().createRepairRequestDao();
    private RepairRequestStatusDao repairRequestStatusDao = DaoFactory.getInstance().createRepairRequestStatusDao();

    @Override
    public List<RepairRequest> showManagerList() {
        List<RepairRequest> repairRequests = repairRequestDao.findAllByStatusId(CURRENT_REQUEST_STATUS_ID);
        return repairRequests;
    }

    @Override
    public List<RepairRequest> showMasterList() {
        List<RepairRequest> repairRequests = repairRequestDao.findAllByStatusId(ACCEPTED_REQUEST_STATUS_ID);
        return repairRequests;
    }

    @Override
    public List<RepairRequest> showUserList(String username) {
        Long userId = userDao.findByUsername(username).get().getId();
        List<RepairRequest> repairRequests = repairRequestDao.findAllByUserId(userId);
        return repairRequests;
    }

    @Override
    public void acceptRequestByManager(Long requestId, Integer price) {
        repairRequestDao.updateByManagerAccept(requestId, ACCEPTED_REQUEST_STATUS_ID, price);
    }

    @Override
    public void closeRequestByMaster(Long requestID) {
        repairRequestDao.updateById(requestID, CLOSED_REQUEST_STATUS_ID);
    }

    @Override
    public void declineRequestByManager(Long requestID) {
        repairRequestDao.updateById(requestID, DECLINED_REQUEST_STATUS_ID);
    }

    @Override
    public boolean addFeedbackByUser(Long requestID, String feedback) {
        String requestStatus = repairRequestDao.findById(requestID).get().getStatus().getStatus();
        if (requestStatus.equals(CLOSED_REQUEST)) {
            repairRequestDao.addFeedback(requestID, feedback);
            return true;
        }
        return false;
    }

    @Override
    public void create(String theme, String description, String username) {
        User user = userDao.findByUsername(username).get();
        RepairRequestStatus repairRequestStatus = repairRequestStatusDao.findById(CURRENT_REQUEST_STATUS_ID).get();

        RepairRequest repairRequest = new RepairRequest();
        repairRequest.setTheme(theme);
        repairRequest.setDescription(description);
        repairRequest.setActive(true);
        repairRequest.setStatus(repairRequestStatus);
        repairRequest.setRequestCreator(user);
        repairRequestDao.save(repairRequest);
    }
}
