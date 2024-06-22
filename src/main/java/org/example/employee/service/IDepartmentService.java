package org.example.employee.service;

import org.example.employee.model.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> findAll();
}
