package org.example.employee.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.employee.dto.EmployeeDTO;
import org.example.employee.dto.EmployeeSearchDTO;
import org.example.employee.model.Employee;
import org.example.employee.repository.IEmployeeRepository;
import org.example.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;
    @Override
    public Page<Employee> search(EmployeeSearchDTO employeeSearchDTO, Pageable pageable) {
        Double fromSalary = null;
        Double toSalary = null;
        if (employeeSearchDTO.getSalary() != null) {
            if (employeeSearchDTO.getSalary().equals("lt5")) {
                toSalary = 5000000.0;
            } else if (employeeSearchDTO.getSalary().equals("5-10")) {
                fromSalary = 5000000.0;
                toSalary = 10000000.0;
            } else if (employeeSearchDTO.getSalary().equals("10-15")) {
                fromSalary = 10000000.0;
                toSalary = 15000000.0;
            } else {
                fromSalary = 15000000.0;
            }
        }

        return employeeRepository.search(employeeSearchDTO.getName(),
                employeeSearchDTO.getFromBirthDay() == null || employeeSearchDTO.getFromBirthDay().isEmpty() ? null : LocalDate.parse(employeeSearchDTO.getFromBirthDay()),
                employeeSearchDTO.getToBirthDay() == null || employeeSearchDTO.getToBirthDay().isEmpty() ? null : LocalDate.parse(employeeSearchDTO.getToBirthDay()),
                employeeSearchDTO.getGender() == null || employeeSearchDTO.getGender().isEmpty() ? null : Boolean.parseBoolean(employeeSearchDTO.getGender()),
                toSalary, fromSalary,
                employeeSearchDTO.getPhoneNumber(),
                employeeSearchDTO.getDepartmentId() == null || employeeSearchDTO.getDepartmentId().isEmpty() ? null : Integer.parseInt(employeeSearchDTO.getDepartmentId()), pageable);
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void validate(HttpServletRequest request, Map<String, String> massageError) {

        String nameStr = request.getParameter("name");
        String birthDayStr = request.getParameter("birthDay");
        String genderStr = request.getParameter("gender");
        String salaryStr = request.getParameter("salary");

        if (nameStr.trim().equals("")) {
            massageError.put("name", "Tên bắt buộc nhập");
        } else if(!nameStr.matches("[a-zA-ZÀ-ỹ\\s]+")) {
            massageError.put("name", "Tên chỉ chứa khoảng cách hoặc chữ cái");
        }
        if (birthDayStr.trim().equals("")) {
            massageError.put("birthDay", "Ngày sinh bắt buộc nhập");
        }
        if (genderStr.trim().equals("")) {
            massageError.put("gender", "Giới tính bắt buộc chọn");
        }
        if (salaryStr.trim().equals("")) {
            massageError.put("salary", "Lương bắt buộc nhập");
        }
    }

    @Override
    public void validateNumberPhoneExists(HttpServletRequest request, HttpServletResponse response) {

    }
}
