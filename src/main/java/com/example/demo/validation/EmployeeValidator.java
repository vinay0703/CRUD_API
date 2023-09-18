package com.example.demo.validation;

public interface EmployeeValidator {
    Boolean validateEmployeeByCompanyId(Long companyId);
    Boolean validateEmployeeByDepartmentAndCompanyId(Long departmentId, Long companyId);
}
