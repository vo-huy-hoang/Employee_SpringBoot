package org.example.employee.mapper;

import org.example.employee.dto.EmployeeCreateDTO;
import org.example.employee.dto.EmployeeEditDTO;
import org.example.employee.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component("employeeMapper")
public interface EmployeeMapper {
    @Mapping(source = "birthDay", target = "birthDay", dateFormat = "dd-MM-yyyy")
    Employee toEmployeeFromEmployeeCreateDTO(EmployeeCreateDTO employeeCreateDTO);
    Employee toEmployeeFromEmployeeEditDTO(EmployeeEditDTO employeeEditDTO);
    EmployeeEditDTO toEmployeeEditDTOFromEmployee(Employee employee);
}
