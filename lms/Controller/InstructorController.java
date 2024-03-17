package com.example.lms.Controller;

import com.example.lms.ApiResponse.ApiResponse;
import com.example.lms.Model.Course;
import com.example.lms.Model.Instructor;
import com.example.lms.Model.User;
import com.example.lms.Services.InstructorService;
import com.example.lms.Services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/instructor")
@AllArgsConstructor
public class InstructorController {




        private final InstructorService instructorService;


        @GetMapping("get")
        public ResponseEntity getAllInstructor() {

            ArrayList<Instructor> UserList = instructorService.getAllUsers();
            return ResponseEntity.status(200).body(UserList);
        }

        @PostMapping("post")
        public ResponseEntity addInstructor(@RequestBody @Valid Instructor instructor, Errors errors) {
            if (errors.hasErrors()) {
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(new ApiResponse(message));
            }
            instructorService.addInstructor(instructor);
            return ResponseEntity.status(200).body(new ApiResponse("Instructor added successfully"));
        }

        @PutMapping("update/{id}")
        public ResponseEntity updateUser(@PathVariable String id, @RequestBody Instructor instructor, Errors errors) {
            if (errors.hasErrors()) {
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(new ApiResponse(message));
            }

            boolean isUpdated = instructorService.updateInstructor(id, instructor);
            if (isUpdated) {
                return ResponseEntity.status(400).body(new ApiResponse("Instructor successfully updated"));

            } else {
                return ResponseEntity.status(200).body(new ApiResponse("Instructor not found"));
            }
        }


        @DeleteMapping("delete/{id}")
        public ResponseEntity deleteInstructor(@PathVariable String id) {

            boolean isDeleted = instructorService.deleteInstructor(id);
            if (isDeleted) {
                return ResponseEntity.status(200).body(new ApiResponse("Instructor deleted"));

            } else {
                return ResponseEntity.status(400).body(new ApiResponse("Instructor not found"));

            }
        }


    @GetMapping("search/{name}")
    public ResponseEntity getInstructorByName(@PathVariable String name){

        Instructor instructor = instructorService.search(name);

        if (instructor == null){
            return ResponseEntity.status(400).body(new ApiResponse("instructor not found"));
        }

        return ResponseEntity.status(200).body(instructor);
    }


    @GetMapping("get-all-courses/{id}")
    public ResponseEntity getAllICourses(@PathVariable String id) {

        ArrayList<Course> courseList = instructorService.getAllTheCourses(id);
        if (courseList.isEmpty()){
            return ResponseEntity.status(400).body("not found");

        }
        return ResponseEntity.status(200).body(courseList);
    }


    @GetMapping("get-num-courses/{id}")
    public ResponseEntity getNumOfCourses(@PathVariable String id) {

        int num = instructorService.getNumberOfCourses(id);
        if (num == 0){
            return ResponseEntity.status(400).body("not found");

        }
        return ResponseEntity.status(200).body(num);
    }

    @PutMapping("change-instructor/{courseId}/{instructorID}")
    public ResponseEntity changeInstructor(@PathVariable String courseId,@PathVariable String instructorID) {

            boolean isChanged = instructorService.changeInstructor(courseId, instructorID);

            if (isChanged){
                return ResponseEntity.status(200).body("instructor has been changed");
            }else{
                return ResponseEntity.status(400).body("not found");
            }


    }


}
