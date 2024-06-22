package org.example.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDTO {
    private Integer id;
    private String name;
    private LocalDate birthDay;
    private boolean gender;
    private Double salary;
    private String phoneNumber;
    private Integer departmentId;
    private String departmentName;

}
