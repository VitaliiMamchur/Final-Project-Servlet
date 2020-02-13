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

    UserDao userDao = DaoFactory.getInstance().createUserDao();
    RepairRequestDao repairRequestDao = DaoFactory.getInstance().createRepairRequestDao();
    RepairRequestStatusDao repairRequestStatusDao = DaoFactory.getInstance().createRepairRequestStatusDao();

    @Override
    public List<RepairRequest> showManagerList() {
        Long statusId = 0L;
        List<RepairRequest> repairRequests = repairRequestDao.findAllByStatusId(statusId);
        return repairRequests;
    }

    @Override
    public List<RepairRequest> showMasterList() {
        Long statusId = 1L;
        List<RepairRequest> repairRequests = repairRequestDao.findAllByStatusId(statusId);
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
        Long statusId = 1L;
        repairRequestDao.updateByManagerAccept(requestId, statusId, price);
    }

    @Override
    public void closeRequestByMaster(Long requestID) {
        Long statusId = 2L;
        repairRequestDao.updateById(requestID, statusId);
    }

    @Override
    public void declineRequestByManager(Long requestID) {
        Long statusId = 3L;
        repairRequestDao.updateById(requestID, statusId);
    }

    @Override
    public boolean addFeedbackByUser(Long requestID, String feedback) {
        String requestStatus = repairRequestDao.findById(requestID).get().getStatus().getStatus();
        if (requestStatus.equals("CLOSED_REQUEST")) {
            repairRequestDao.addFeedback(requestID, feedback);
            return true;
        }
        return false;
    }

    @Override
    public void create(String theme, String description, String username) {
        Long currentStatus = 0L;
        User user = userDao.findByUsername(username).get();
        RepairRequestStatus repairRequestStatus = repairRequestStatusDao.findById(currentStatus).get();

        RepairRequest repairRequest = new RepairRequest();
        repairRequest.setTheme(theme);
        repairRequest.setDescription(description);
        repairRequest.setActive(true);
        repairRequest.setStatus(repairRequestStatus);
        repairRequest.setRequestCreator(user);
        repairRequestDao.save(repairRequest);
    }
}
