package com.example.demo.validation.impl;

import com.example.demo.dao.CompanyDao;
import com.example.demo.validation.DepartmentValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DepartmentValidatorImpl implements DepartmentValidator {

    @Autowired
    private final CompanyDao companyDao;

    // check if the company exits or not
    @Override
    public Boolean validateDepartmentByCompanyId(Long companyId) {
        return companyDao.getCompanyById(companyId) != null;
    }
}
