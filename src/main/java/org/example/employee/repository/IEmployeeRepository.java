package org.example.employee.repository;

import org.example.employee.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "from Employee as e where (:name = '' or :name is null or e.name like concat('%', :name, '%'))" +
                    "and (:fromBirthDay is null or e.birthDay >= :fromBirthDay )" +
                    "and (:toBirthDay is null or e.birthDay <= :toBirthDay )" +
                    "and (:gender is null or e.gender = :gender)" +
                    "and (:toSalary is null or (e.salary <= :toSalary))" +
                    "and (:fromSalary is null or (e.salary >= :fromSalary))" +
                    "and (:phoneNumber = '' or :phoneNumber is null or e.phoneNumber like concat('%', :phoneNumber, '%'))" +
                    "and (:departmentId is null or e.department.id = :departmentId )")
    Page<Employee> search(@Param("name") String name, @Param("fromBirthDay") LocalDate fromBirthDay, @Param("toBirthDay") LocalDate toBirthDay,
                          @Param("gender") Boolean gender, @Param("toSalary") Double toSalary, @Param("fromSalary") Double fromSalary, @Param("phoneNumber") String phoneNumber,
                          @Param("departmentId") Integer departmentId, Pageable pageable);

}
