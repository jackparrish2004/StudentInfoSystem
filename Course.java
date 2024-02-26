/*
 * CS 280
 * Jack Parrish
 * Mario Medeles
 * Project 1
 * 2/27
 */
import java.util.ArrayList;

public class Course {
    String name;
    String ID;
    int credits;
    Professor taughtBy;
    boolean isAvailable = false;
    ArrayList<String> enrolled = new ArrayList<String>(); //this holds student ID values
    int maxStudents;
    ArrayList<String> waitlist = new ArrayList<String>(); //this holds student ID values

    Course(String name, String ID, int credits, int maxStudents){
        this.name = name;
        this.ID = ID;
        this.credits = credits;
        this.maxStudents = maxStudents;
    }
}
