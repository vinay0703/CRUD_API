package com.example.demo.dao.impl;

import com.example.demo.Dto.CompanyDto;
import com.example.demo.dao.CompanyDao;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CompanyMapper;
import com.example.demo.respository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CompanyDaoImpl implements CompanyDao {
    @Autowired
    private final CompanyRepository companyRepository;

    @Autowired
    private final CompanyMapper companyMapper;

    @Override
    public List<CompanyDto> getAllCompanies() {
        return companyRepository.findAll().stream().map(companyMapper::mapToCompanyDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDto getCompanyById(Long companyId) {
        return companyMapper.mapToCompanyDto(
                companyRepository.findById(companyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Company not exists with id: " + companyId))
        );
    }

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        return companyMapper.mapToCompanyDto(
                companyRepository.save(
                        companyMapper.mapToCompanyEntity(companyDto)
                )
        );
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto) {
        companyRepository.findById(companyDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not exists with id: " + companyDto.getId()));
        return companyMapper.mapToCompanyDto(
                companyRepository.save(
                        companyMapper.mapToCompanyEntity(companyDto)
                )
        );
    }

    @Override
    public void deleteCompany(Long companyId) {
        companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not exists with id: " + companyId));
        companyRepository.deleteById(companyId);
    }
}
