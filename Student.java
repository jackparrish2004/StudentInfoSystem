/*
 * CS 280
 * Jack Parrish
 * Mario Medeles
 * Project 1
 * 2/27
 */

import java.util.HashMap;

public class Student {
    String name;
    String ID;
    //The hashmaps have a Course as a key and a letter grade as the value
    HashMap<Course, String> coursesCompleted = new HashMap<Course, String>();
    HashMap<Course, String> coursesEnrolledIn = new HashMap<Course, String>();
    String yearInSchool;
    float cumulativeGPA;

    //This constructor assumes student is an incoming freshman with no completed credits
    Student(String name, String ID){
        this.name = name;
        this.ID = ID;
        this.yearInSchool = "Freshman";

    }

    //This constructor is used if a student is a transfer student with completed credits
    Student(String name, String ID, String yearInSchool, HashMap<Course, String> coursesCompleted){
        this.name = name;
        this.ID = ID;
        this.yearInSchool = yearInSchool;
        this.coursesCompleted = coursesCompleted;
    }
}
