package com.example.demo.service;


import com.example.demo.Dto.DepartmentDto;
import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> getAllDepartments(Long companyId);
    DepartmentDto getDepartmentById(Long companyId, Long departmentId);
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto updateDepartment(DepartmentDto departmentDto);
    void deleteDepartment(Long departmentId);
}
