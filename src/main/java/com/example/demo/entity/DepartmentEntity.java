package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;
    @Column(name = "department_name")
    private String name;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.ALL})
    @JoinColumn(name = "company_fk_id")
    @JsonIgnoreProperties({"name", "address"})
    private CompanyEntity company;
}
