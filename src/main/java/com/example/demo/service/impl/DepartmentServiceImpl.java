package com.example.demo.service.impl;

import com.example.demo.Dto.DepartmentDto;
import com.example.demo.dao.DepartmentDao;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.DepartmentService;
import com.example.demo.validation.DepartmentValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private final DepartmentDao departmentDao;

    @Autowired
    private final DepartmentValidator departmentValidator;

    @Override
    public List<DepartmentDto> getAllDepartments(Long companyId) {
        if(!departmentValidator.validateDepartmentByCompanyId(companyId))
            throw new ResourceNotFoundException("No company with id = " + companyId);
        return departmentDao.getAllDepartments(companyId);
    }

    @Override
    public DepartmentDto getDepartmentById(Long companyId, Long departmentId) {
        try {
            return departmentDao.getDepartmentById(companyId, departmentId);
        }
        catch (Exception e){
            throw new ResourceNotFoundException("No company with id = " + companyId);
        }
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        return departmentDao.createDepartment(departmentDto);
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        return departmentDao.updateDepartment(departmentDto);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        departmentDao.deleteDepartment(departmentId);
    }
}
