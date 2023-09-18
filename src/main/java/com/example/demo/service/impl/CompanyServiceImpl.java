package com.example.demo.service.impl;

import com.example.demo.Dto.CompanyDto;
import com.example.demo.dao.CompanyDao;
import com.example.demo.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private final CompanyDao companyDao;

    @Override
    public List<CompanyDto> getAllCompanies() {
        return companyDao.getAllCompanies();
    }

    @Override
    public CompanyDto getCompanyById(Long companyId) {
        return companyDao.getCompanyById(companyId);
    }

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        return companyDao.createCompany(companyDto);
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto) {
        return companyDao.updateCompany(companyDto);
    }

    @Override
    public void deleteCompany(Long companyId) {
        companyDao.deleteCompany(companyId);
    }
}
