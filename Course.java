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
    String taughtBy;
    boolean isAvailable = false;
    ArrayList<String> enrolled = new ArrayList<String>();
    int maxStudents;
    ArrayList<String> waitlist = new ArrayList<String>();

    Course(String name, String ID, int credits, String taughtBy, int maxStudents){
        this.name = name;
        this.ID = ID;
        this.credits = credits;
        this.taughtBy = taughtBy;
        this.maxStudents = maxStudents;
    }
}
