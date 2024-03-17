package com.example.lms.Services;

import com.example.lms.Model.Course;
import com.example.lms.Model.Instructor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class InstructorService {



    ArrayList<Instructor> instructorList = new ArrayList<>();
    private final CourseService courseService;

    public ArrayList<Instructor> getAllUsers() {
        return instructorList;
    }

    public void addInstructor(Instructor instructor) {

        instructorList.add(instructor);

    }

    public boolean updateInstructor(String id, Instructor updatedInstructor){
        for (Instructor instructor : instructorList) {
            if(instructor.getId().equals(id)){
                instructorList.set(instructorList.indexOf(instructor), updatedInstructor);
                return true;
            }

        }
        return false;


    }

    public boolean deleteInstructor(String id){
        for (Instructor instructor : instructorList) {
            if(instructor.getId().equals(id)){
                instructorList.remove(instructorList.indexOf(instructor));
                return true;
            }

        }
        return false;
    }


    //search by name
    public Instructor search(String name){
        for (Instructor instructor : instructorList) {
            if (instructor.getName().equals(name)) {
                return instructor;
            }

        }
        return null;
    }

//return the courses for the instructor
    public ArrayList<Course> getAllTheCourses(String id){
        ArrayList<Course> allCourses = new ArrayList<>();
        for (Course course : courseService.courseList){
            if (course.getInstructor().getId().equals(id)){
                allCourses.add(course);
            }
        }
        return allCourses;
    }


    //return number of courses for the instructor
    public int getNumberOfCourses(String id){
        int sum =0;
        for (Course course : courseService.courseList){
            if (course.getInstructor().getId().equals(id)){
                sum++;
            }
        }
        return sum;
    }


    //change instructor
    public boolean changeInstructor(String courseId, String instructorID){



        for (Course course : courseService.courseList){
            if (course.getId().equals(courseId)) {


                for (Instructor instructor : instructorList) {

                    if (instructor.getId().equals(instructorID)) {
                        courseService.courseList.get(courseService.courseList.indexOf(courseId)).setInstructor(instructorList.get(instructorList.indexOf(instructorID)));
                        return true;
                    }
                }
            }}
        return false;
    }

}
