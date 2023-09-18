package com.example.demo.service.impl;

import com.example.demo.Dto.EmployeeDto;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.EmployeeService;
import com.example.demo.validation.EmployeeValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private final EmployeeDao employeeDao;

    @Autowired
    private final EmployeeValidator employeeValidator;

    @Override
    public List<EmployeeDto> getAllEmployees(Long companyId, Long departmentId) {
        if(departmentId != null){
            if(!employeeValidator.validateEmployeeByDepartmentAndCompanyId(departmentId, companyId))
                throw  new ResourceNotFoundException("No department with id = " + departmentId + " and with company id = " + companyId);
            return employeeDao.getAllEmployeesByDepartmentId(departmentId);
        } else{
            if(!employeeValidator.validateEmployeeByCompanyId(companyId))
                throw new ResourceNotFoundException("No company with id = " + companyId);
            return employeeDao.getAllEmployeesByCompanyId(companyId);
        }
    }

    @Override
    public EmployeeDto getEmployeeById(Long companyId, Long employeeId) {
        try {
            return employeeDao.getEmployeeById(companyId, employeeId);
        }
        catch (Exception e){
            throw new ResourceNotFoundException("No company with id = " + companyId);
        }
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        return employeeDao.createEmployee(employeeDto);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        return employeeDao.updateEmployee(employeeDto);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeDao.deleteEmployee(employeeId);
    }
}
