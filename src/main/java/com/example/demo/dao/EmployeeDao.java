package com.example.demo.dao;

import com.example.demo.Dto.EmployeeDto;

import java.util.List;

public interface EmployeeDao {
    List<EmployeeDto> getAllEmployeesByCompanyId(Long companyId);
    List<EmployeeDto> getAllEmployeesByDepartmentId(Long departmentId);
    EmployeeDto getEmployeeById(Long companyId, Long employeeId);
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(EmployeeDto employeeDto);
    void deleteEmployee(Long employeeId);
}
