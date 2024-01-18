package com.amaris.employees.api;

import com.amaris.employees.exception.employee.EmployeeApiException;
import com.amaris.employees.exception.employee.EmployeeNotFoundException;
import com.amaris.employees.models.Employee;

import java.util.List;

public interface IEmployeeAPIConsumer {

    List<Employee> fetchEmployeesFromApi() throws EmployeeApiException.ApiException;

    Employee fetchEmployeeFromApi(String employeeId) throws EmployeeApiException.ApiException, EmployeeNotFoundException;
}