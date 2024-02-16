import java.util.ArrayList;

public class Student {
    String name;
    String ID;
    ArrayList<Course> coursesCompleted = new ArrayList<Course>();
    ArrayList<Course> coursesEnrolledIn = new ArrayList<Course>();
    String yearInSchool;
    float cumulativeGPA;

    //This constructor assumes student is an incoming freshman with no completed credits
    Student(String name, String ID){
        this.name = name;
        this.ID = ID;
        this.yearInSchool = "Freshman";

    }

    //This constructor is used if a student is a transfer student with completed credits
    Student(String name, String ID, String yearInSchool, ArrayList<Course> coursesCompleted){
        this.name = name;
        this.ID = ID;
        this.yearInSchool = yearInSchool;
        this.coursesCompleted = coursesCompleted;
    }
}
