package com.thoughtworks.springbootemployee.integrationtest;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
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


import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void deleteData() {
        companyRepository.deleteAll();
    }

    @Test
    void should_return_companies_when_get_companies_given_nothing() throws Exception{
        //given
        Company company = new Company(1, "OOCL", 10000, null);
        Company savedCompany = companyRepository.save(company);


        //when
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(savedCompany.getId()))
                .andExpect(jsonPath("$[0].companyName").value(savedCompany.getCompanyName()))
                .andExpect(jsonPath("$[0].employeesNumber").value(savedCompany.getEmployeesNumber()));
    }

    @Test
    void should_return_companies_when_get_companies_given_id() throws Exception{
        //given
        Company company = new Company(1, "OOCL", 10000, null);
        Company savedCompany = companyRepository.save(company);

        //when
        mockMvc.perform(get("/companies/" + savedCompany.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedCompany.getId()))
                .andExpect(jsonPath("$.companyName").value(savedCompany.getCompanyName()))
                .andExpect(jsonPath("$.employeesNumber").value(savedCompany.getEmployeesNumber()));
    }

    @Test
    void should_return_employees_when_get_employees_given_company_id() throws Exception{
        //given
        Company company = new Company(1, "OOCL", 10000, new ArrayList<>());
        Company savedCompany = companyRepository.save(company);

        //when
        mockMvc.perform(get("/companies/" + savedCompany.getId() + "/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    void should_return_companyies_when_get_companyies_given_page_and_pageSize() throws Exception{
        //given
        Company oocl = new Company(1, "OOCL", 10000, null);
        Company tw = new Company(2, "TW", 10000, null);
        Company company = new Company(3, "company", 10000, null);

        Company savedOocl = companyRepository.save(oocl);
        Company savedTw = companyRepository.save(tw);
        Company savedCompany = companyRepository.save(company);


        //when
        mockMvc.perform(get("/companies?page=1&pageSize=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedCompany.getId()))
                .andExpect(jsonPath("$[0].companyName").value(savedCompany.getCompanyName()))
                .andExpect(jsonPath("$[0].employeesNumber").value(savedCompany.getEmployeesNumber()));
    }



    @Test
    void should_return_company_when_add_company_given_company() throws Exception{
        //given
        Company company = new Company(1, "OOCL", 10000, null);
        Company savedCompany = companyRepository.save(company);

        String companiesJson = "{\n" +
                "        \"companyName\": \"alibaba2\",\n" +
                "        \"employeesNumber\": 100,\n" +
                "        \"employees\": [\n" +
                "        ]\n" +
                "    }";

        //when
        mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON).content(companiesJson))
                .andExpect(status().isCreated());
    }

    @Test
    void should_return_company_when_update_company_given_company() throws Exception{
        //given
        Company company = new Company(1, "OOCL", 10000, null);
        Company savedCompany = companyRepository.save(company);

        String companiesJson = "{\n" +
                "        \"companyName\": \"alibaba2\",\n" +
                "        \"employeesNumber\": 100,\n" +
                "        \"employees\": [\n" +
                "        ]\n" +
                "    }";

        //when
        mockMvc.perform(put("/companies/" + savedCompany.getId()).contentType(MediaType.APPLICATION_JSON).content(companiesJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedCompany.getId()))
                .andExpect(jsonPath("$.companyName").value("alibaba2"))
                .andExpect(jsonPath("$.employeesNumber").value(100));
    }

    @Test
    void should_delete_company_when_delete_company_given_id() throws Exception{
        //given
        Company company = new Company(1, "OOCL", 10000, null);
        Company savedCompany = companyRepository.save(company);


        //when
        mockMvc.perform(delete("/companies/" + savedCompany.getId()))
                .andExpect(status().isOk());
        assertEquals(false, companyRepository.findById(savedCompany.getId()).isPresent());

    }



}
