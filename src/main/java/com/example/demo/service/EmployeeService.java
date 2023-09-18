package com.example.demo.service;

import com.example.demo.Dto.EmployeeDto;
import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getAllEmployees(Long companyId, Long departmentId);
    EmployeeDto getEmployeeById(Long companyId, Long employeeId);
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(EmployeeDto employeeDto);
    void deleteEmployee(Long employeeId);
}
