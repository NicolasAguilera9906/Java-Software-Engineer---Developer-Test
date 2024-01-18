package com.amaris.employees.services;

import com.amaris.employees.dto.EmployeeDto;

import java.util.List;

public interface IEmployeeService {

    List<EmployeeDto> fetchEmployees();
    EmployeeDto fetchEmployee(String employeeId);
}
