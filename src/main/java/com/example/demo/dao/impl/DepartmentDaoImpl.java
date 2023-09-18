package com.example.demo.dao.impl;

import com.example.demo.Dto.CompanyDto;
import com.example.demo.Dto.DepartmentDto;
import com.example.demo.dao.DepartmentDao;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CompanyMapper;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.respository.CompanyRepository;
import com.example.demo.respository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DepartmentDaoImpl implements DepartmentDao {
    @Autowired
    private final DepartmentRepository departmentRepository;

    @Autowired
    private final CompanyRepository companyRepository;

    @Autowired
    private final DepartmentMapper departmentMapper;

    @Autowired
    private final CompanyMapper companyMapper;
    @Override
    public List<DepartmentDto> getAllDepartments(Long companyId) {
        return departmentRepository.findAll().stream().map(departmentMapper::mapToDepartmentDto)
                .filter(department -> department.getCompany().getId().equals(companyId))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentById(Long companyId, Long departmentId) {
        return departmentMapper.mapToDepartmentDto(
                departmentRepository.findById(departmentId).stream()
                        .filter(department -> department.getCompany().getId().equals(companyId))
                        .collect(Collectors.toList()).get(0)
        );
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        departmentDto = setCompanyDetails(departmentDto);
        return departmentMapper.mapToDepartmentDto(
                departmentRepository.save(
                        departmentMapper.mapToDepartmentEntity(departmentDto)
                )
        );
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        DepartmentDto finalDepartmentDto = departmentDto;
        departmentRepository.findById(departmentDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not exists with id = " + finalDepartmentDto.getId()));
        departmentDto = setCompanyDetails(departmentDto);
        return departmentMapper.mapToDepartmentDto(
                departmentRepository.save(
                        departmentMapper.mapToDepartmentEntity(departmentDto)
                )
        );
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not exists with id = " + departmentId));
        departmentRepository.deleteById(departmentId);
    }

    public DepartmentDto setCompanyDetails(DepartmentDto departmentDto){
        Long companyId = departmentDto.getCompany().getId();
        CompanyDto company = companyMapper.mapToCompanyDto(
                companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company not found with id = " + companyId))
        );
        departmentDto.getCompany().setName(company.getName());
        departmentDto.getCompany().setAddress(company.getAddress());
        departmentDto.setCompany(company);
        return departmentDto;
    }
}
