package com.example.demo.mapper;

import com.example.demo.Dto.CompanyDto;
import com.example.demo.entity.CompanyEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CompanyMapper {
    @Autowired
    private final AddressMapper addressMapper;
    // To convert company JPA entity into company dto
    public CompanyDto mapToCompanyDto(CompanyEntity companyEntity){
        return new CompanyDto(
                companyEntity.getId(),
                companyEntity.getName(),
                addressMapper.mapToAddressDto(companyEntity.getAddress())
        );
    }

    // To convert company dto into company JPA entity
    public CompanyEntity mapToCompanyEntity(CompanyDto companyDto){
        return new CompanyEntity(
                companyDto.getId(),
                companyDto.getName(),
                addressMapper.mapToAddressEntity(companyDto.getAddress())
        );
    }
}
