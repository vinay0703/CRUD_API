package com.example.demo.service;

import com.example.demo.Dto.CompanyDto;
import java.util.List;

public interface CompanyService {
    List<CompanyDto> getAllCompanies();
    CompanyDto getCompanyById(Long companyId);
    CompanyDto createCompany(CompanyDto companyDto);
    CompanyDto updateCompany(CompanyDto companyDto);
    void deleteCompany(Long companyId);
}
