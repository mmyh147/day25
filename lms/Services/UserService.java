package com.example.lms.Services;

import com.example.lms.Model.Course;
import com.example.lms.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> userList = new ArrayList<>();

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




    }
