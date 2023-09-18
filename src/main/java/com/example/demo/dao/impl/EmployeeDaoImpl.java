package com.example.demo.dao.impl;

import com.example.demo.Dto.DepartmentDto;
import com.example.demo.Dto.EmployeeDto;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.respository.DepartmentRepository;
import com.example.demo.respository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao {
    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private final DepartmentRepository departmentRepository;

    @Autowired
    private final EmployeeMapper employeeMapper;
    @Autowired
    private final DepartmentMapper departmentMapper;

    @Override
    public List<EmployeeDto> getAllEmployeesByCompanyId(Long companyId) {
        return employeeRepository.findAll().stream().map(employeeMapper::mapToEmployeeDto)
                .filter(employee -> employee.getDepartment().getCompany().getId().equals(companyId))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getAllEmployeesByDepartmentId(Long departmentId) {
        return employeeRepository.findAll().stream().map(employeeMapper::mapToEmployeeDto)
                .filter(employee -> employee.getDepartment().getId().equals(departmentId)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long companyId, Long employeeId){
        return employeeMapper.mapToEmployeeDto(
                employeeRepository.findById(employeeId).stream()
                        .filter(employee -> employee.getDepartment().getCompany().getId().equals(companyId))
                        .collect(Collectors.toList()).get(0)
        );
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        employeeDto = setDepartmentDetails(employeeDto);
        return employeeMapper.mapToEmployeeDto(
                employeeRepository.save(employeeMapper.mapToEmployeeEntity(employeeDto))
        );
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        EmployeeDto finalEmployeeDto = employeeDto;
        employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exists with id = " + finalEmployeeDto.getId()));
        employeeDto = setDepartmentDetails(employeeDto);
        return employeeMapper.mapToEmployeeDto(
                employeeRepository.save(employeeMapper.mapToEmployeeEntity(employeeDto))
        );
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exists with id = " + employeeId));
        employeeRepository.deleteById(employeeId);
    }

    public EmployeeDto setDepartmentDetails(EmployeeDto employeeDto){
        Long departmentId = employeeDto.getDepartment().getId();
        DepartmentDto department = departmentMapper.mapToDepartmentDto(departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id = " + departmentId)));
        employeeDto.getDepartment().setName(department.getName());
        employeeDto.getDepartment().setCompany(department.getCompany());
        employeeDto.setDepartment(department);
        return employeeDto;
    }
}
