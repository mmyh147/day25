package com.example.lms.Services;

import com.example.lms.Model.Course;
import com.example.lms.Model.Instructor;
import com.example.lms.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@AllArgsConstructor
@Service
public class UserService {


    ArrayList<User> userList = new ArrayList<>();
    private final CourseService courseService;

    public ArrayList<User> getAllUsers() {
        return userList;
    }

    public void addUser(User user) {

        userList.add(user);

    }

    public boolean updateUser(String id, User updatedUser){
        for (User user : userList) {
            if(user.getId().equals(id)){
                userList.set(userList.indexOf(user), updatedUser);
                return true;
            }

        }
        return false;


    }

    public boolean deleteUser(String id){
        for (User user : userList) {
            if(user.getId().equals(id)){
                userList.remove(userList.indexOf(user));
                return true;
            }

        }
        return false;
    }


//get users list based on hours it will return equal or greater list
    public ArrayList<User> getHoursList(int hours){
        ArrayList<User> hoursList = new ArrayList<>();
        for (User user : userList) {
            if (hours <= user.getTotalHours()) {
                hoursList.add(user);
            }
        }
        return hoursList;
    }


    //search for user by email
    public User search(String email){
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return user;
            }

        }
        return null;
    }

    //get all the course that the user enroll
    public ArrayList<Course> getAllCoursesEnrolled(String id){
        ArrayList<Course> courseList = new ArrayList<>();
        for (Course course : courseService.courseList){
            for (User user : course.getEnrolledUsers()){
                if (user.getId().equals(id)){
                    courseList.add(course);
                }
            }
        }
        return courseList;
    }


    //get number of courses the user enrolled
    public int getNumOfCourses(String id){
        int sum = 0;
        for (Course course : courseService.courseList){
            for (User user : course.getEnrolledUsers()){
                if (user.getId().equals(id)){
                    sum++;
                }
            }
        }
        return sum;
    }




    }
