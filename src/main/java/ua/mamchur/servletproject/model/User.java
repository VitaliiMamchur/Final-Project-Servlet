package ua.mamchur.servletproject.model;

import java.util.Optional;
import java.util.Set;

public class User {
    private Long id;
    private String username;
    private String password;
    private boolean active;
    private Set<RepairRequest> repairRequests;
    private Role role;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<RepairRequest> getRepairRequests() {
        return repairRequests;
    }

    public void setRepairRequests(Set<RepairRequest> repairRequests) {
        this.repairRequests = repairRequests;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
