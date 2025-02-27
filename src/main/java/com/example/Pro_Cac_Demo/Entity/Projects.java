package com.example.Pro_Cac_Demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    private String projectName;
//    private Double costOfProject;

    @ManyToMany(mappedBy = "projects",fetch = FetchType.LAZY)
    private List<Employee> employee;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

//    public Double getCostOfProject() {
//        return costOfProject;
//    }
//
//    public void setCostOfProject(Double costOfProject) {
//        this.costOfProject = costOfProject;
//    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
}
