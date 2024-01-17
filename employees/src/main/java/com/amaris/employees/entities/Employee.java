package com.amaris.employees.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Employee{

    private Long salary;

    private String name;

    private Integer age;

    private String profile_image;
}