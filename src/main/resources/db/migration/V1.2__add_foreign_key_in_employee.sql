ALTER TABLE employee
ADD CONSTRAINT fk_company_employee
FOREIGN KEY (company_id)
REFERENCES company(id)