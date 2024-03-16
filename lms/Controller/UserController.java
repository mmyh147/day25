package com.example.lms.Controller;

import com.example.lms.ApiResponse.ApiResponse;
import com.example.lms.Model.Course;
import com.example.lms.Model.User;
import com.example.lms.Services.CourseService;
import com.example.lms.Services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor

public class UserController {



        private final UserService userService;


        @GetMapping("get")
        public ResponseEntity getAllUsers() {

            ArrayList<User> UserList = userService.getAllUsers();
            return ResponseEntity.status(200).body(UserList);
        }

        @PostMapping("post")
        public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
            if (errors.hasErrors()) {
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(new ApiResponse(message));
            }
            userService.addUser(user);
            return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
        }

        @PutMapping("update/{id}")
        public ResponseEntity updateUser(@PathVariable String id, @RequestBody User user, Errors errors) {
            if (errors.hasErrors()) {
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(new ApiResponse(message));
            }

            boolean isUpdated = userService.updateUser(id, user);
            if (isUpdated) {
                return ResponseEntity.status(400).body(new ApiResponse("User successfully updated"));

            } else {
                return ResponseEntity.status(200).body(new ApiResponse("User not found"));
            }
        }


        @DeleteMapping("delete/{id}")
        public ResponseEntity deleteUser(@PathVariable String id) {

            boolean isDeleted = userService.deleteUser(id);
            if (isDeleted) {
                return ResponseEntity.status(200).body(new ApiResponse("User deleted"));

            } else {
                return ResponseEntity.status(400).body(new ApiResponse("User not found"));

            }
        }

    @GetMapping("hours/{hours}")
    public ResponseEntity getHours(@PathVariable int hours){

        ArrayList<User> hourList = userService.getHoursList(hours);

        if (hourList.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("list not found"));
        }
        return ResponseEntity.status(200).body(hourList);
    }


}
