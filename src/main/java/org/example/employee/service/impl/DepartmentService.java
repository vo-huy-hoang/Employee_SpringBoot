package org.example.employee.service.impl;

import org.example.employee.model.Department;
import org.example.employee.repository.IDepartmentRepository;
import org.example.employee.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartmentService implements IDepartmentService {
    @Autowired
    private IDepartmentRepository departmentRepository;
    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
}
