package com.thoughtworks.springbootemployee.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Database {

    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        int i = 1;
        while (i <= 5) {
            employees.add(new Employee(i, "alibaba"+i, 22, "male", 5000));
            employees.add(new Employee(i+5, "alibaba"+i*2, 25, "female", 7000));
            i++;

        }
        return employees;
    }

    public static List<Company> getCompanies() {

        List<Company> companies = new ArrayList<>();
        int i = 1;
        while (i <= 5) {
            companies.add(new Company(i,"alibaba", 10, getEmployees()));
            companies.add(new Company(i+5,"tengxun", 10, getEmployees()));
            i++;
        }
        return companies;
    }
}
