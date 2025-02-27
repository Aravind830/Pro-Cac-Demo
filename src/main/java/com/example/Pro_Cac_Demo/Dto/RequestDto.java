package com.example.Pro_Cac_Demo.Dto;

import com.example.Pro_Cac_Demo.Entity.Projects;
import java.util.List;

public class RequestDto {

    private String employeeName;
    private String password;
    private List<Projects> projectsList;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Projects> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(List<Projects> projectsList) {
        this.projectsList = projectsList;
    }
}
