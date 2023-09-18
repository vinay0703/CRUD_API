package com.example.demo.validation.impl;

import com.example.demo.dao.CompanyDao;
import com.example.demo.dao.DepartmentDao;
import com.example.demo.validation.EmployeeValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeValidatorImpl implements EmployeeValidator {

    @Autowired
    private final DepartmentDao departmentDao;

    @Autowired
    private final CompanyDao companyDao;

    // check if the department exits with given id and company
    @Override
    public Boolean validateEmployeeByDepartmentAndCompanyId(Long departmentId, Long companyId){
        return departmentDao.getDepartmentById(companyId, departmentId) != null && departmentDao.getDepartmentById(companyId, departmentId).getCompany().getId() == companyId;
    }

    // Check if the company exists with given id
    @Override
    public Boolean validateEmployeeByCompanyId(Long companyId){
        return companyDao.getCompanyById(companyId) != null;
    }
}
