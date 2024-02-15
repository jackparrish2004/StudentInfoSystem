public class Admin {
    String name;
    String ID;
    String email;
    
    //Parameter admin constructor
    Admin(String name, String ID, String email){
        this.name = name;
        this.ID = ID;
        this.email = email;
    }

    //No args admin constructor
    Admin(){
        this.name = "Admin";
        this.ID = "1111";
        this.email = "admin@uni.edu";
    }

    void addStudent(){
        /*Takes Student class as argument and adds them to system */
    }

    void removeStudent(){
        /*Deletes a given student from the system */
    }

    void modifyStudentInfo(){
        /*Updates given student info */
    }

    String searchForStudentName(String name){
        /*Returns a student class with the given name */
        return "Student name was searched for";
    }

    String searchForStudentID(String ID){
        /*Returns a student class with the given ID */
        return "Student ID was searched for";
    }

    void addCourse(){
        /*Takes a Course class as an argument and adds it to the system */
    }

    void removeCourse(){
        /*Deletes a given course from the system */
    }

    void modifyCourseInfo(){
        /*Updates given course info */
    }

    String searchForCourseName(String name){
        /*Returns a course class with the given name */
        return "Course name was searched for";
    }

    String searchForCourseID(String ID){
        /*Returns a course class with the given name */
        return "Course ID was searched for";
    }

    void updateCourseGrade(String grade){
        /*Updates the course grade for a given student and course */
    }

    void viewCourseHistory(){
        /*Prints out a student's course history and grades */
    }

    void viewStudentGPA(){
        /*Prints out a student's given GPA */
    }

    void addInstructor(){
        /*Adds a given instructor to the system */
    }

    void removeInstructor(){
        /*Removes a given instructor from the system */
    }

    void modifyInstructorInfo(){
        /*Updates given instructor info */
    }
}
