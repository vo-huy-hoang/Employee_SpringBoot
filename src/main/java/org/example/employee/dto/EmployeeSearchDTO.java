package org.example.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSearchDTO {
    private String name="";
    private String fromBirthDay;
    private String toBirthDay;
    private String gender;
    private String salary;
    private String phoneNumber="";
    private String departmentId;
}
