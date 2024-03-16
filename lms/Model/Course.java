package com.example.lms.Model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Course {
    @NotEmpty(message = "ID must be not null")
    private String id;
    @NotEmpty(message = "title must be not null")
    private String title;
    @NotEmpty(message = "category must not be empty")
    private String category;
    @NotEmpty
    private Integer hours;
    private ArrayList<User> enrolledUsers;
    private Instructor instructor;

    public void enrollUser(User user, int hours) {
        user.setTotalHours(user.getTotalHours() + hours);
        enrolledUsers.add(user);
    }

}
