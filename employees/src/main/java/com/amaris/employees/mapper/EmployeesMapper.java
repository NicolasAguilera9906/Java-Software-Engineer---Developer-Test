package com.amaris.employees.mapper;

import com.amaris.employees.dto.EmployeeDto;
import com.amaris.employees.models.Employee;

public class EmployeesMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee, EmployeeDto employeeDto) {
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setAge(employee.getAge());
        employeeDto.setProfileImage(employee.getProfileImage());
        return employeeDto;
    }

}