package com.amaris.employees.api;

import com.amaris.employees.exception.EmployeeApiException;
import com.amaris.employees.exception.ResourceNotFoundException;
import com.amaris.employees.models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IEmployeeAPIConsumer {

    List<Employee> fetchEmployeesFromApi() throws EmployeeApiException.ApiException;

    Employee fetchEmployeeFromApi(String employeeId) throws EmployeeApiException.ApiException, ResourceNotFoundException;
}