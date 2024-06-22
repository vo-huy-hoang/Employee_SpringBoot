package org.example.employee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tự động
    @Column(name = "id") // tên cho cột ở trong table
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "birth_day")
    private LocalDate birthDay;
    @Column(name = "gender")
    private boolean gender;
    @Column(name = "salary")
    private double salary;
    @Column(name = "phone_number")
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
