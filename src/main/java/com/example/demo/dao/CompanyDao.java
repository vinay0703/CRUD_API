package com.example.demo.dao;

import com.example.demo.Dto.CompanyDto;

import java.util.List;

public interface CompanyDao {
    List<CompanyDto> getAllCompanies();
    CompanyDto getCompanyById(Long companyId);
    CompanyDto createCompany(CompanyDto companyDto);
    CompanyDto updateCompany(CompanyDto companyDto);
    void deleteCompany(Long companyId);
}
