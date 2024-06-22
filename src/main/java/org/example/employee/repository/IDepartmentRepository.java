package org.example.employee.repository;

import org.example.employee.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findAll();
}
