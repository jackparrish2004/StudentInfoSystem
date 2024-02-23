/*
 * CS 280
 * Jack Parrish
 * Mario Medeles
 * Project 1
 * 2/27
 */

/*
 * For the viewCumulativeGPA method:
 * I think there should be a method that updates the GPA whenever a course is added or a grade is changed
 * I don't think that the GPA should be calculated every time it needs to be displayed
 */

import java.util.ArrayList;

public class Admin {
    String name;
    String ID;
    String email;

    ArrayList<Student> students = new ArrayList<Student>();
    ArrayList<Course> courses = new ArrayList<Course>();
    ArrayList<Professor> professors = new ArrayList<Professor>();
    
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

    void addStudent(String studentName, String studentID){
        /*Takes Student class as argument and adds them to system */
        
        Student student = new Student(studentName, studentID);
        students.add(student);
    }

    void removeStudent(String inputID){
        /*Deletes a given student from the system */
        int studentIndex = searchForStudentID(inputID);
        if (studentIndex == -1){
            System.out.println("Student ID not found");
        } else {
            students.remove(studentIndex);
            System.out.println("Student removed");
        }
    }

    //I don't know if this should ask for all info at once, or if it should only ask for info that should be changed
    void modifyStudentInfo(){
        /*Updates given student info */
        System.out.println("Student info modified");

    }

    //This method is exclusively used internally to find the index of a given student
    int searchForStudentID(String inputID){
        /*Returns a student class with the given ID */
        for (int i = 0; i < students.size(); i++){
            if ((students.get(i).ID).compareTo(inputID) == 0){
                return i;
            }
        }
        return -1;
    }

    void printStudentInfo(String inputID){
        int studentIndex = searchForStudentID(inputID);
        if (studentIndex == -1){
            System.out.println("Student ID not found");
        } else {
            Student student = students.get(studentIndex);

            System.out.printf("Student Name:   %s\n", student.name);
            System.out.printf("Student ID:     %s\n", student.ID);
            System.out.printf("Year in School: %s\n", student.yearInSchool);
        }
    }

    void enrollCourse (String studentID, String courseID){
        /*This method enrolls a student in a given course */
        int studentIndex = searchForStudentID(studentID);
        int courseIndex = searchForCourseID(courseID);

        if (studentIndex == -1){
            System.out.println("Student ID not found");
        } else if (courseIndex == -1){
            System.out.println("Course ID not found");
        } else {
            Student student = students.get(studentIndex);
            Course course = courses.get(courseIndex);
            if (course.enrolled.size() > course.maxStudents){
                System.out.println("This course is currently full. You are being added to the waitlist");
                course.waitlist.add(studentID);
            } else {
                //I put N/A assuming that there is no grade to receive in the course yet
                student.coursesEnrolledIn.put(course, "N/A");
                course.enrolled.add(studentID);
                System.out.printf("\n%s has been enrolled in %s\n", student.name, course.name);
            }
        }
    }

    void addCourse(String name, String ID, String professorID, int credits, int maxStudents){
        /*Takes Course information as arguments and adds it to the system */
        int professorIndex = searchForProfessorID(professorID);
        if (professorIndex == -1){
            System.out.println("Professor not found");
            System.out.println("Course not added");
        } else {
            //New course instance is created
            Course course = new Course(name, ID, credits, maxStudents);
            //taughtBy is set to the professor instance with a matchin ID
            course.taughtBy = professors.get(professorIndex);
            courses.add(course);
            System.out.println("Course added");
        }
    }

    void removeCourse(String inputID){
        /*Deletes a given course from the system */
        int courseIndex = searchForCourseID(inputID);
        if (courseIndex == -1){
            System.out.println("Student ID not found");
        } else {
            courses.remove(courseIndex);
            System.out.println("Course removed");
        }
    }

    void modifyCourseInfo(){
        /*Updates given course info */
        System.out.println("Course info modified");
    }

    int searchForCourseID(String inputID){
        /*Returns a course class with the given name */
        for (int i = 0; i < courses.size(); i++){
            if ((courses.get(i).ID).compareTo(inputID) == 0){
                return i;
            }
        }
        return -1;
    }

    void printCourseInfo(String inputID){
        
        int courseIndex = searchForCourseID(inputID);
        if (courseIndex == -1){
            System.out.println("Course ID not found");
        } else {
            Course course = courses.get(courseIndex);
            System.out.printf("Course Name:     %s\n", course.name);
            System.out.printf("Course ID:       %s\n", course.ID);
            System.out.printf("Credits Offered: %d\n", course.credits);
            System.out.printf("Professor:       %s\n", course.taughtBy);
        }
    }

    //-----------------------------------------------------
    //I think there should be methods for displaying what students are enrolled in a course
    //-----------------------------------------------------

    void updateCourseGrade(String studentID, String courseID, String grade){
        /*Updates the course grade for a given student and course */
        int studentIndex = searchForStudentID(studentID);
        int courseIndex = searchForCourseID(courseID);

        if (studentIndex == -1){
            System.out.println("Student ID not found");
        } else if (courseIndex == -1){
            System.out.println("Course ID not found");
        } else {
            Student student = students.get(studentIndex);
            Course course = courses.get(courseIndex);

            student.coursesEnrolledIn.put(course, grade);

            System.out.println("Course grade updated");
        }
        updateStudentGPA("1111");
    }

    //this method is called whenever a student grade is updated
    void updateStudentGPA(String inputID){
        int studentIndex = searchForStudentID(inputID);
        if (studentIndex == -1){
            System.out.println("Student ID not found");
        } else {
            Student student = students.get(studentIndex);
            float totalCredits = 0;
            float gradePoints = 0;
            for (Course course : student.coursesCompleted.keySet()){
                totalCredits += course.credits;
                switch (student.coursesCompleted.get(course)) {
                    case "A":
                        gradePoints += (course.credits * 4);
                        break;
                    case "B":
                        gradePoints += (course.credits * 3);
                        break;
                    case "C":
                        gradePoints += (course.credits * 2);
                        break;
                    case "D":
                        gradePoints += (course.credits * 1);
                        break;
                    default:
                        gradePoints += (course.credits * 0);
                        break;
                }
            }
            for (Course course : student.coursesEnrolledIn.keySet()){
                totalCredits += course.credits;
                switch (student.coursesEnrolledIn.get(course)) {
                    case "A":
                        gradePoints += (course.credits * 4);
                        break;
                    case "B":
                        gradePoints += (course.credits * 3);
                        break;
                    case "C":
                        gradePoints += (course.credits * 2);
                        break;
                    case "D":
                        gradePoints += (course.credits * 1);
                        break;
                    case "N/A":
                        //In this case, the class cannot be used to calculate gpa, so the total credits are removed 
                        totalCredits -= course.credits;
                    default:
                        gradePoints += (course.credits * 0);
                        break;
                }
            }

            //both for loops are done and gpa can be calculated
            student.cumulativeGPA = gradePoints / totalCredits;

        }
    }

    void viewCourseHistory(String studentID){
        /*Prints out a student's course history and grades */
        int studentIndex = searchForStudentID(studentID);
        if (studentIndex == -1){
            System.out.println("Student not found");
        } else {
            Student student = students.get(studentIndex);

            //separate print loops are used to indicate what courses a student has completed and courses they are currently taking
            if (student.coursesCompleted.size() == 0){
                System.out.println("Student has not completed any courses");
            } else {
                System.out.println("Courses completed:");
                for (Course course : student.coursesCompleted.keySet()){
                    System.out.printf("%s : %s\n", course.name, student.coursesCompleted.get(course));
                }
            }

            if (student.coursesEnrolledIn.size() == 0){
                System.out.println("Student is not currently enrolled in any courses");
            } else {
                System.out.println("Courses enrolled in:");
                for (Course course : student.coursesEnrolledIn.keySet()){
                    System.out.printf("%s : %s\n", course.name, student.coursesEnrolledIn.get(course));
                }
            }
        }
    }

    void viewStudentGPA(String inputID){
        /*Prints out a student's given GPA */
        int studentIndex = searchForStudentID(inputID);
        if (studentIndex == -1){
            System.out.println("Student not found");
        } else {
            Student student = students.get(studentIndex);
            System.out.printf("Student GPA: %.2f\n", student.cumulativeGPA);
        }
    }

    void addProfessor(String name, String ID, String email, String department){
        /*Adds a given professor to the system */
        Professor professor = new Professor(name, ID, email, department);
        professors.add(professor);
        System.out.println("Professor added");
    }

    void removeProfessor(String inputID){
        /*Removes a given professor from the system */
        int professorIndex = searchForProfessorID(inputID);
        if (professorIndex == -1){
            System.out.println("Professor not found");
        } else {
            professors.remove(professorIndex);
            System.out.println("Professor removed");
        }

    }

    void modifyProfessorInfo(){
        /*Updates given professor info */
        System.out.println("Professor info modified");
    }

    int searchForProfessorID(String inputID){
        for (int i = 0; i < professors.size(); i++){
            if ((professors.get(i).ID).compareTo(inputID) == 0){
                return i;
            }
        }
        return -1;
    }

    void printProfessorInfo(String inputID){
        int professorIndex = searchForProfessorID(inputID);
        if (professorIndex == -1){
            System.out.println("Professor not found");
        } else {
            Professor professor = professors.get(professorIndex);
            System.out.printf("Professor Name:       %s", professor.name);
            System.out.printf("Professor ID:         %s", professor.ID);
            System.out.printf("Professor Email:      %s", professor.email);
            System.out.printf("Professor Department: %s", professor.department);
        }
    }
}
