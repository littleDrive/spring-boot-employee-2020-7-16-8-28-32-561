package com.thoughtworks.springbootemployee.vo;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RequestCompany {

    private Integer id;
    private String companyName;
    private Integer employeesNumber;
    private List<RequestEployee> employees;

    public RequestCompany() {
    }

    public RequestCompany(Integer id, String companyName, Integer employeesNumber, List<RequestEployee> employees) {
        this.id = id;
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(Integer employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public List<RequestEployee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<RequestEployee> employees) {
        this.employees = employees;
    }
}
