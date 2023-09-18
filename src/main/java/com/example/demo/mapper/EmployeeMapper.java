package com.example.demo.mapper;

import com.example.demo.Dto.EmployeeDto;
import com.example.demo.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeMapper {
    // To convert employee JPA entity into employee dto
    @Autowired
    private final AddressMapper addressMapper;
    @Autowired
    private final DepartmentMapper departmentMapper;

    public EmployeeDto mapToEmployeeDto(EmployeeEntity employeeEntity){
        return new EmployeeDto(
                employeeEntity.getId(),
                employeeEntity.getName(),
                employeeEntity.getGender(),
                addressMapper.mapToAddressDto(employeeEntity.getAddress()),
                departmentMapper.mapToDepartmentDto(employeeEntity.getDepartment())
        );
    }

    // To convert employee dto into employee JPA entity
    public EmployeeEntity mapToEmployeeEntity(EmployeeDto employeeDto){
        return new EmployeeEntity(
                employeeDto.getId(),
                employeeDto.getName(),
                employeeDto.getGender(),
                addressMapper.mapToAddressEntity(employeeDto.getAddress()),
                departmentMapper.mapToDepartmentEntity(employeeDto.getDepartment())
        );
    }
}
