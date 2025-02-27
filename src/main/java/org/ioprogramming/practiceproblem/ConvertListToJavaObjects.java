package org.ioprogramming.practiceproblem;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

class User{
    public String name;
    public int age;
    public String mail;

    public User(String name , int age , String mail){
        this.name = name;
        this.age = age;
        this.mail = mail;

    }
}

public class ConvertListToJavaObjects {
    public static void main(String[] args) {
        try {
            List<User> user = Arrays.asList(
                    new User("Naveen", 18, "Naveen@123"),
                    new User("Nerraj", 34, "Neeraj@123")
            );

            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(user);

            System.out.println(s);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
