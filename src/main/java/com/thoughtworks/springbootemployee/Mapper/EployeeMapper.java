package com.thoughtworks.springbootemployee.Mapper;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.vo.RequestEployee;
import org.springframework.stereotype.Component;

@Component
public class EployeeMapper {

    public static Employee getEmployeeByRequestEmployee(RequestEployee requestEployee) {

        Employee employee = new Employee();
        employee.setId(requestEployee.getId());
        employee.setName(requestEployee.getName());
        employee.setAge(requestEployee.getAge());
        employee.setGender(requestEployee.getGender());
        employee.setSalary(requestEployee.getSalary());
        employee.setCompanyId(requestEployee.getCompanyId());
        return employee;
    }

    public static RequestEployee getRequestEmployeeByEmployee(Employee employee) {

        RequestEployee requestEployee = new RequestEployee();
        requestEployee.setId(employee.getId());
        requestEployee.setAge(employee.getAge());
        requestEployee.setName(employee.getName());
        requestEployee.setGender(employee.getGender());
        requestEployee.setSalary(employee.getSalary());
        requestEployee.setCompanyId(employee.getCompanyId());
        return requestEployee;
    }
}
