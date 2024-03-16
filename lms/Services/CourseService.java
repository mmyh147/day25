package com.example.lms.Services;

import com.example.lms.Model.Course;
import com.example.lms.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class CourseService {



    ArrayList<Course> courseList = new ArrayList<>();
    private final UserService userService;

    public ArrayList<Course> getAllCourses() {
        return courseList;
    }

    public void addCourse(Course course) {

        courseList.add(course);

    }

    public boolean updateCourse(String id, Course updatedCourse){
        for (Course course : courseList) {
            if(course.getId().equals(id)){
                courseList.set(courseList.indexOf(course), updatedCourse);
                return true;
            }

        }
        return false;


    }

    public boolean deleteCourse(String id){
        for (Course course : courseList) {
            if(course.getId().equals(id)){
                courseList.remove(courseList.indexOf(course));
                return true;
            }

        }
        return false;
    }


//add user to the course and increase total hours for the user
    public boolean enrollUser(String courseID, String userID) {
        for (Course course : courseList) {
            if (course.getId().equals(courseID)) {
                for (User user : userService.userList) {
                    if (user.getId().equals(userID)) {
                        course.enrollUser(userService.userList.get(userService.userList.indexOf(userID)),course.getHours());
                        return true;
                    }
                }
            }
        }
        return false;

    }

    //get courses by category
    public ArrayList<Course> categoryList(String category){
        ArrayList<Course>  categoryList= new ArrayList<>();
        for (Course course : courseList) {
            if (course.getCategory().equals(category)) {
                categoryList.add(course);
            }

        }
        return categoryList;
    }





}
