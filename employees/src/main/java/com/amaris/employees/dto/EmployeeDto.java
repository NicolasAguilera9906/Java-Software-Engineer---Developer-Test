package com.amaris.employees.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
@Schema(name = "Employee", description = "Schema to hold Employee information")
public class EmployeeDto {

    @Schema(description = "Name of the employee")
    @NotEmpty(message = "Name can not be null or empty")
    private String name;

    @Schema(description = "Age of the employee")
    @NotNull(message = "Age can not be null")
    private Integer age;

    @Schema(description = "Salary of the employee")
    @NotNull(message = "Salary can not be null")
    private Double salary;

    @Schema(description = "ID of the employee")
    @NotNull(message = "ID can not be null")
    private Long id;

    @Schema(description = "Profile image URL of the employee")
    @NotNull(message = "ID can not be null")
    private String profileImage;

    @Schema(description = "Annual Salary of the employee")
    @NotNull(message = "annual salary can not be null")
    private Double annualSalary;
}
