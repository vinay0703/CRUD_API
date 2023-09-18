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
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;
    @Column(name = "employee_name")
    private String name;
    @Column(name = "employee_gender")
    private String gender;

    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "address_fk_id")
    private AddressEntity address;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "department_fk_id")
    @JsonIgnoreProperties({"name", "company"})
    private DepartmentEntity department;
}
