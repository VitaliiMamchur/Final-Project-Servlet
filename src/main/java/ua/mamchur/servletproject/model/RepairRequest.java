package ua.mamchur.servletproject.model;

public class RepairRequest {
    private Long id;
    private String theme;
    private String description;
    private Integer price;
    private boolean active;
    private String feedback;
    private User requestCreator;
    private RepairRequestStatus status;

    public RepairRequest() {
    }

    public RepairRequest(Long id, String theme, String description, Integer price, boolean active, User requestCreator, RepairRequestStatus status) {
        this.theme = theme;
        this.description = description;
        this.price = price;
        this.active = active;
        this.requestCreator = requestCreator;
        this.status = status;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public User getRequestCreator() {
        return requestCreator;
    }

    public void setRequestCreator(User requestCreator) {
        this.requestCreator = requestCreator;
    }

    public RepairRequestStatus getStatus() {
        return status;
    }

    public void setStatus(RepairRequestStatus status) {
        this.status = status;
    }

    public String statusToString()
    {
        String stringStatus;
        switch (this.status.getId().intValue())
        {
            case 0:
                stringStatus = "Please wait till manager accept your repair request.";
                return stringStatus;
            case 1:
                stringStatus = "Your repair request is accepted. Please wait.";
                return stringStatus;
            case 2:
                stringStatus = "Your request has been done. Thank you!";
                return stringStatus;
            case 3:
                stringStatus = "Your repair request is declined.";
                return stringStatus;
        }
        return "Error";
    }
}
