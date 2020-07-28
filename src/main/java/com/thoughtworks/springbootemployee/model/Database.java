package com.thoughtworks.springbootemployee.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Database {

    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        int i = 0;
        while (i < 5) {
            employees.add(new Employee(i, "alibaba"+i, 22, "male", 5000));
            employees.add(new Employee(i*2, "alibaba"+i*2, 25, "female", 7000));

        }
        return employees;
    }

    public static List<Company> getCompanies() {

        List<Company> companies = new ArrayList<>();
        int i = 0;
        while (i < 5) {
            companies.add(new Company("alibaba", 10, getEmployees()));
            companies.add(new Company("tengxun", 10, getEmployees()));

        }
        return companies;
    }
}
