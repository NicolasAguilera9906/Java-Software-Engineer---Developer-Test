package com.amaris.employees.services.impl;


import com.amaris.employees.api.impl.EmployeeAPIConsumer;
import com.amaris.employees.dto.EmployeeDto;
import com.amaris.employees.mapper.EmployeesMapper;
import com.amaris.employees.models.Employee;
import com.amaris.employees.services.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl  implements IEmployeeService {

    private EmployeeAPIConsumer employeeAPIConsumer;

    @Override
    public List<EmployeeDto> fetchEmployeesFromApi() {
        List<Employee> employees = employeeAPIConsumer.fetchEmployeesFromApi();
        return employees.stream()
                .map(employee -> {
                    EmployeeDto employeeDto = EmployeesMapper.mapToEmployeeDto(employee, new EmployeeDto());
                    double annualSalary = employee.getSalary() * 12;
                    employeeDto.setAnnualSalary(annualSalary);
                    return employeeDto;
                })
                .collect(Collectors.toList());
    }

}