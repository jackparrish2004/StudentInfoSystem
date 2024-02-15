public class Student {
    String name;
    String ID;
    String[] coursesCompleted = new String[1];
    String[] coursesEnrolledIn = new String[1];
    String yearInSchool;
    float cumulativeGPA;

    Student(String name, String ID){
        this.name = name;
        this.ID = ID;
        this.yearInSchool = "Freshman";

    }
}
