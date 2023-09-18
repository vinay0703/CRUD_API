package com.example.demo.mapper;

import com.example.demo.Dto.AddressDto;
import com.example.demo.entity.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    // To convert address jpa entity into address dto
    public AddressDto mapToAddressDto(AddressEntity addressEntity){
        return new AddressDto(
                addressEntity.getId(),
                addressEntity.getCity(),
                addressEntity.getCountry(),
                addressEntity.getStreet()
        );
    }

    // To convert address dto into address jpa entity
    public AddressEntity mapToAddressEntity(AddressDto addressDto){
        return new AddressEntity(
                addressDto.getId(),
                addressDto.getCity(),
                addressDto.getCountry(),
                addressDto.getStreet()
        );
    }
}
