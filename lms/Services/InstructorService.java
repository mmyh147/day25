package com.example.lms.Services;

import com.example.lms.Model.Instructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InstructorService {



    ArrayList<Instructor> instructorList = new ArrayList<>();

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
}
