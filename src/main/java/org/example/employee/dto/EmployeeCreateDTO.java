package org.example.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.employee.model.Department;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreateDTO implements Validator {
    private String name;
    private String birthDay;
    private String gender;
    private String salary;
    private String phoneNumber;
    private Department department;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeCreateDTO employeeCreateDTO = (EmployeeCreateDTO) target;
    }
}
