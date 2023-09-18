package com.example.demo.mapper;

import com.example.demo.Dto.DepartmentDto;
import com.example.demo.entity.DepartmentEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DepartmentMapper {
    @Autowired
    private final CompanyMapper companyMapper;
    // To map department JPA entity into department dto
    public DepartmentDto mapToDepartmentDto(DepartmentEntity departmentEntity){
        return new DepartmentDto(
                departmentEntity.getId(),
                departmentEntity.getName(),
                companyMapper.mapToCompanyDto(departmentEntity.getCompany())
        );
    }

    // To map department dto into department JPA entity
    public DepartmentEntity mapToDepartmentEntity(DepartmentDto departmentDto){
        return new DepartmentEntity(
                departmentDto.getId(),
                departmentDto.getName(),
                companyMapper.mapToCompanyEntity(departmentDto.getCompany())
        );
    }
}
