package org.example.employee.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.employee.dto.EmployeeSearchDTO;
import org.example.employee.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IEmployeeService {
    Page<Employee> search(EmployeeSearchDTO employeeSearchDTO, Pageable pageable);

    Employee findById(int id);

    void save(Employee employee);
    void delete(Integer id);


    void validate(HttpServletRequest request, Map<String, String> massageError);

    void validateNumberPhoneExists(HttpServletRequest request, HttpServletResponse response);
}
