package com.example.lms.Model;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Instructor {

    @NotEmpty(message = "ID must be not null")
    private String id;
    @NotEmpty(message = "name must be not null")
    private String name;
    @NotEmpty(message = "email must be not null")
    private String email;



}
