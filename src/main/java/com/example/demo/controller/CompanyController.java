package com.example.demo.controller;

import com.example.demo.Dto.CompanyDto;
import com.example.demo.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        System.out.println();
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable("id") Long companyId) {
        return ResponseEntity.ok(companyService.getCompanyById(companyId));
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
        return new ResponseEntity<>(companyService.createCompany(companyDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CompanyDto> updateCompany(@RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(companyService.updateCompany(companyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") Long companyId) {
        try {
            companyService.deleteCompany(companyId);
            return ResponseEntity.ok("Deleted company with id = " + companyId);
        }catch (Exception e){
            return new ResponseEntity<>("Cannot delete company since it contains departments", HttpStatus.BAD_REQUEST);
        }
    }
}
