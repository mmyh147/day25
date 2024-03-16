package com.example.lms.Controller;

import com.example.lms.ApiResponse.ApiResponse;
import com.example.lms.Model.Course;
import com.example.lms.Model.User;
import com.example.lms.Services.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/Course")
@AllArgsConstructor
public class CourseController {




        private final CourseService courseService;


        @GetMapping("get")
        public ResponseEntity getAllCourses() {

            ArrayList<Course> studentsList = courseService.getAllCourses();
            return ResponseEntity.status(200).body(studentsList);
        }

        @PostMapping("post")
        public ResponseEntity addCourse(@RequestBody @Valid Course course, Errors errors) {
            if (errors.hasErrors()) {
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(new ApiResponse(message));
            }
            courseService.addCourse(course);
            return ResponseEntity.status(200).body(new ApiResponse("Course added successfully"));
        }

        @PutMapping("update/{id}")
        public ResponseEntity updateCourse(@PathVariable String id, @RequestBody Course course, Errors errors) {
            if (errors.hasErrors()) {
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(new ApiResponse(message));
            }

            boolean isUpdated = courseService.updateCourse(id, course);
            if (isUpdated) {
                return ResponseEntity.status(400).body(new ApiResponse("course successfully updated"));

            } else {
                return ResponseEntity.status(200).body(new ApiResponse("course not found"));
            }
        }


        @DeleteMapping("delete/{id}")
        public ResponseEntity deleteCourse(@PathVariable String id) {

            boolean isDeleted = courseService.deleteCourse(id);
            if (isDeleted) {
                return ResponseEntity.status(200).body(new ApiResponse("Course deleted"));

            } else {
                return ResponseEntity.status(400).body(new ApiResponse("Course not found"));

            }
        }

    @GetMapping("category/{category}")
    public ResponseEntity getMajorList(@PathVariable String category){

        ArrayList<Course> majorList = courseService.categoryList(category);

        if (majorList.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no courses in provided category"));
        }
        return ResponseEntity.status(200).body(majorList);
    }


    @PutMapping("enroll-user/{courseID/{userId}")
    public ResponseEntity enrollUser(@PathVariable String userId,@PathVariable String courseID) {

          boolean userEnrolled = courseService.enrollUser(courseID, userId);

        if (userEnrolled) {
            return ResponseEntity.status(400).body(new ApiResponse("User successfully added to course"));

        } else {
            return ResponseEntity.status(200).body(new ApiResponse("User or course not found"));
        }
    }


}
