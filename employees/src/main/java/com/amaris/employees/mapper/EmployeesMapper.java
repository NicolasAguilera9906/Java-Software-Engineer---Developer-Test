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

    public static Employee mapToEmployee(EmployeeDto employeeDto, Employee employee) {
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setSalary(employeeDto.getSalary());
        employee.setAge(employeeDto.getAge());
        employee.setProfileImage(employeeDto.getProfileImage());
        return employee;
    }

}