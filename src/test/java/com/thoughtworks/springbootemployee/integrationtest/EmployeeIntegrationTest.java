package com.thoughtworks.springbootemployee.integrationtest;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void deleteData() {
        companyRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_employees_when_get_employees_given_nothing() throws Exception{
        //given
        Company company = new Company(1, "OOCL", 10000, null);
        Company savedCompany = companyRepository.save(company);

        Employee employee = new Employee(1,"user1", 18, "male", 1000.0, savedCompany.getId());
        employeeRepository.save(employee);

        //when
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(employee.getName()))
                .andExpect(jsonPath("$[0].gender").value(employee.getGender()))
                .andExpect(jsonPath("$[0].salary").value(employee.getSalary()))
                .andExpect(jsonPath("$[0].age").value(employee.getAge()));
    }

    @Test
    void should_return_employees_when_get_employees_given_id() throws Exception{
        //given
        Company company = new Company(1, "OOCL", 10000, null);
        Company savedCompany = companyRepository.save(company);

        Employee employee1 = new Employee(1,"user1", 18, "male", 1000.0, savedCompany.getId());
        Employee employee2 = new Employee(2,"user2", 19, "female", 9000.0, savedCompany.getId());
        employeeRepository.save(employee1);
        Employee savedEmployee = employeeRepository.save(employee2);

        //when
        mockMvc.perform(get("/employees/" + savedEmployee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedEmployee.getId()))
                .andExpect(jsonPath("$.name").value(employee2.getName()))
                .andExpect(jsonPath("$.gender").value(employee2.getGender()))
                .andExpect(jsonPath("$.salary").value(employee2.getSalary()))
                .andExpect(jsonPath("$.age").value(employee2.getAge()));
    }

    @Test
    void should_return_employees_when_get_employees_given_page_and_pageSize() throws Exception{
        //given
        Company company = new Company(1, "OOCL", 10000, null);
        Company savedCompany = companyRepository.save(company);

        Employee employee1 = new Employee(1,"user1", 25, "male", 8000.0, savedCompany.getId());
        Employee employee2 = new Employee(2,"user2", 22, "female", 9000.0, savedCompany.getId());
        Employee employee3 = new Employee(3,"user3", 21, "female", 9000.0, savedCompany.getId());
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        //when
        mockMvc.perform(get("/employees?page=1&pageSize=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(employee3.getName()))
                .andExpect(jsonPath("$[0].gender").value(employee3.getGender()))
                .andExpect(jsonPath("$[0].salary").value(employee3.getSalary()))
                .andExpect(jsonPath("$[0].age").value(employee3.getAge()));
    }

    @Test
    void should_return_employees_when_get_employees_given_gender() throws Exception{
        //given
        Company company = new Company(1, "OOCL", 10000, null);
        Company savedCompany = companyRepository.save(company);

        Employee employee1 = new Employee(1,"user1", 25, "male", 8000.0, savedCompany.getId());
        Employee employee2 = new Employee(2,"user2", 22, "female", 9000.0, savedCompany.getId());
        Employee employee3 = new Employee(3,"user3", 21, "female", 9000.0, savedCompany.getId());
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        //when
        mockMvc.perform(get("/employees?gender=female"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name").value(employee3.getName()))
                .andExpect(jsonPath("$[1].gender").value(employee3.getGender()))
                .andExpect(jsonPath("$[1].salary").value(employee3.getSalary()))
                .andExpect(jsonPath("$[1].age").value(employee3.getAge()));
    }

    @Test
    void should_return_employee_when_add_employee_given_employee() throws Exception{
        //given
        Company company = new Company(1, "OOCL", 10000, null);
        Company savedCompany = companyRepository.save(company);

        Employee employee = new Employee("Zero", 25, "male", 30000.0, savedCompany.getId());
        employeeRepository.save(employee);
        String employeeJSON = "{\n" +
                "    \"name\": \"Zero\",\n" +
                "    \"age\": 25,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 30000,\n" +
                "    \"companyId\" : " + savedCompany.getId()+"\n" +
                "}";

        //when
        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(employeeJSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(employee.getName()))
                .andExpect(jsonPath("$.gender").value(employee.getGender()))
                .andExpect(jsonPath("$.salary").value(employee.getSalary()))
                .andExpect(jsonPath("$.age").value(employee.getAge()));
    }

    @Test
    void should_return_employee_when_update_employee_given_employee() throws Exception{
        //given
        Company company = new Company(1, "OOCL", 10000, null);
        Company savedCompany = companyRepository.save(company);

        Employee zero = new Employee("Zero", 25, "male", 30000.0, savedCompany.getId());
        Employee zeroSaved = employeeRepository.save(zero);

        String employeeJSON = "{\n" +
                "    \"name\": \"Hans\",\n" +
                "    \"age\": 28,\n" +
                "    \"gender\": \"female\",\n" +
                "    \"salary\": 48000,\n" +
                "    \"companyId\" : " + savedCompany.getId()+"\n" +
                "}";

        //when
        mockMvc.perform(put("/employees/" + zeroSaved.getId()).contentType(MediaType.APPLICATION_JSON).content(employeeJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(zeroSaved.getId()))
                .andExpect(jsonPath("$.name").value("Hans"))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value(48000))
                .andExpect(jsonPath("$.age").value(28));
    }

    @Test
    void should_delete_employee_when_delete_employee_given_id() throws Exception{
        //given
        Company company = new Company(1, "OOCL", 10000, null);
        Company savedCompany = companyRepository.save(company);


        Employee employee2 = new Employee(2,"user2", 19, "female", 9000.0, savedCompany.getId());
        Employee employeeSave = employeeRepository.save(employee2);

        //when
        mockMvc.perform(delete("/employees/" + employeeSave.getId()))
                .andExpect(status().isOk());
        assertEquals(false, employeeRepository.findById(employeeSave.getId()).isPresent());

    }
}
