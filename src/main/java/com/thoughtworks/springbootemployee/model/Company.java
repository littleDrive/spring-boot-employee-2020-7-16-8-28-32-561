package com.thoughtworks.springbootemployee.model;

import java.util.List;

/**
 * @author ozm
 * @date 2020/7/28 - 20:09
 */
public class Company {

    private String companyName;
    private int employeesNumer;
    private List<Employee> employees;

    public Company(String companyName, int employeesNumer, List<Employee> employees) {
        this.companyName = companyName;
        this.employeesNumer = employeesNumer;
        this.employees = employees;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEmployeesNumer() {
        return employeesNumer;
    }

    public void setEmployeesNumer(int employeesNumer) {
        this.employeesNumer = employeesNumer;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
