package com.example.lms.Model;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "ID must be not null")
    private String id;
    @NotEmpty(message = "name must be not null")
    private String name;
    @NotEmpty(message = "email must be not null")
    private String email;
    private Integer totalHours;
    private ArrayList<Course>courseEnrolled ;

}
