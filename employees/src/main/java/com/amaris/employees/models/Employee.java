package com.amaris.employees.models;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Employee{

    @SerializedName("id")
    private Long id;

    @SerializedName("employee_salary")
    private Double salary;

    @SerializedName("employee_name")
    private String name;

    @SerializedName("employee_age")
    private Integer age;

    @SerializedName("profile_image")
    private String profileImage;
}