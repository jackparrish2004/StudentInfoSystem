public class Course {
    String name;
    String ID;
    int credits;
    String taughtBy;
    boolean isAvailable = false;
    String[] enrolled = new String[20];
    int maxStudents;
    String[] waitlist = new String[20];

    Course(String name, String ID, int credits, String taughtBy, int maxStudents){
        this.name = name;
        this.ID = ID;
        this.credits = credits;
        this.taughtBy = taughtBy;
        this.maxStudents = maxStudents;
    }
}
