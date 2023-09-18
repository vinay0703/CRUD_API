package com.example.demo.dao;

import com.example.demo.Dto.DepartmentDto;

import java.util.List;

public interface DepartmentDao {
    List<DepartmentDto> getAllDepartments(Long companyId);
    DepartmentDto getDepartmentById(Long companyId, Long departmentId);
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto updateDepartment(DepartmentDto departmentDto);
    void deleteDepartment(Long departmentId);
}
