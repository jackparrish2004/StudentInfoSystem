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
import java.util.Scanner;

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
        /*Takes Student information as arguments and adds a student class to the list*/
        Student student = new Student(studentName, studentID);
        students.add(student);
        System.out.printf("\n%s has been added to the system\n", studentName);
    }

    void removeStudent(String inputID){
        /*Deletes a given student from the system */
        int studentIndex = searchForStudentID(inputID);

        if (studentIndex == -1){
            System.out.println("Student ID not found");
        } else {
           Student removedStudent = students.get(studentIndex);

           // Remove the student from enrolled courses
           for (Course course : courses) {
               ArrayList<String> studentsInCourse = course.enrolled;
               studentsInCourse.remove(removedStudent.ID); // I did reverse logic here - TRY accessing student courses and removing courses through student object
           }

           // Remove the student from the list of students
           students.remove(studentIndex);

           System.out.println("Student '" + removedStudent.name+ "' removed");
       }
    }

    void modifyStudentInfo(Scanner keyboard, String studentID){
        /*Updates given student info */
        int studentIndex = searchForStudentID(studentID);
        if (studentIndex == -1){
            System.out.println("Student ID not found");
        } else {
            Student student = students.get(studentIndex);
            System.out.println("1. Student name");
            System.out.println("2. Student ID");
            System.out.print("Enter a number to change the value: ");
            int userInput = keyboard.nextInt();
            keyboard.nextLine(); //clears input buffer
            
            switch (userInput) {
                case 1:
                    System.out.print("Enter new student name: ");
                    student.name = keyboard.nextLine();
                    break;
                case 2:
                    System.out.print("Enter new student ID: ");
                    String newID = keyboard.nextLine();
                    if (searchForStudentID(newID) > -1){
                        System.out.println("ID already belongs to a student");
                        System.out.println("Try again with a different ID");
                    } else {
                        student.ID = newID;
                    }
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
                
            }
        }

    }

    int searchForStudentID(String inputID){
        /*Returns an integer indicating the student's location in the list */
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
            System.out.println("\nStudent ID not found");
        } else {
            Student student = students.get(studentIndex);

            System.out.printf("\nStudent Name:   %s\n", student.name);
            System.out.printf("Student ID:     %s\n", student.ID);
            System.out.printf("Year in School: %s\n", student.yearInSchool);
        }
    }

    void enrollCourse (String studentID, String courseID){
        /*This method enrolls a student in a given course */
        int studentIndex = searchForStudentID(studentID);
        int courseIndex = searchForCourseID(courseID);

        if (studentIndex == -1){
            System.out.println("\nStudent ID not found");
        } else if (courseIndex == -1){
            System.out.println("\nCourse ID not found");
        } else {
            Student student = students.get(studentIndex);
            Course course = courses.get(courseIndex);
            if (course.enrolled.size() > course.maxStudents){
                System.out.println("\nThis course is currently full. You are being added to the waitlist");
                course.waitlist.add(studentID);
            } else {
                //I put N/A assuming that there is no grade to receive in the course yet
                student.coursesEnrolledIn.put(course, "N/A");
                course.enrolled.add(studentID);
                System.out.printf("\n%s has been enrolled in %s\n", student.name, course.name);
            }
        }
    }

    void dropCourse(String studentID, String courseID){
        int studentIndex = searchForStudentID(studentID);
        int courseIndex = searchForCourseID(courseID);

        if (studentIndex == -1){
            System.out.println("\nStudent ID not found");
        } else if (courseIndex == -1){
            System.out.println("\nCourse ID not found");
        } else {
            Student student = students.get(studentIndex);
            Course course = courses.get(courseIndex);
            student.coursesEnrolledIn.remove(course);
            System.out.printf("\n%s has been unenrolled from %s\n", student.name, course.name);
        }
    }

    void addCourse(String name, String ID, String professorID, int credits, int maxStudents){
        /*Takes Course information as arguments and adds it to the system */
        int professorIndex = searchForProfessorID(professorID);
        if (professorIndex == -1){
            System.out.println("\nProfessor not found");
            System.out.println("Course not added");
        } else {
            //New course instance is created
            Course course = new Course(name, ID, credits, maxStudents);
            //taughtBy is set to the professor instance with a matchin ID
            Professor professor = professors.get(professorIndex);
            course.taughtBy = professor;
            professor.coursesTaught.add(ID);

            courses.add(course);
            System.out.printf("\n%s added to course catalog\n", course.name);
        }
    }

    void removeCourse(String inputID){
        /*Deletes a given course from the system */
        int courseIndex = searchForCourseID(inputID);

        if (courseIndex == -1){
            System.out.println("Course ID not found");
        } else {
            Course removedCourse = courses.get(courseIndex);
            courses.remove(courseIndex);

            // Remove the professor associated with the course
            Professor professor = removedCourse.taughtBy;
            if (professor != null) {
                professor.coursesTaught.remove(removedCourse.ID);
            }

            // Remove students from the course
            for (String studentID : removedCourse.enrolled){
                Student student = students.get(searchForStudentID(studentID));
                student.coursesEnrolledIn.remove(removedCourse);
            }

            System.out.println("Course '" + removedCourse.name + "' removed");
        }
    }

    void modifyCourseInfo(Scanner keyboard, String courseID){
        /*Updates given course info */
        int courseIndex = searchForCourseID(courseID);
        if (courseIndex == -1){
            System.out.println("\nCourse ID not found");
        } else {
            Course course = courses.get(courseIndex);
            System.out.println("1. Course Name");
            System.out.println("2. Course ID");
            System.out.println("3. Number of credits");
            System.out.println("4. Professor teaching course");
            System.out.println("5. Max students");
            System.out.print("Enter a number to change the value: ");
            int userInput = keyboard.nextInt();
            keyboard.nextLine();
            switch (userInput) {
                case 1:
                    System.out.print("Enter new course name: ");
                    course.name = keyboard.nextLine();
                    break;
                case 2:
                    System.out.print("Enter new course ID: ");
                    String newID = keyboard.nextLine();
                    if (searchForCourseID(courseID) > -1){
                        System.out.println("That ID already belongs to a course");
                        System.out.println("Try again with a new ID");
                    } else {
                        course.ID = newID;
                    }
                    break;
                case 3:
                    System.out.print("Enter new credit amount: ");
                    course.credits = keyboard.nextInt();
                    keyboard.nextLine();
                    break;
                case 4:
                    System.out.print("Enter ID of new professor: ");
                    String professorID = keyboard.nextLine();
                    int professorIndex = searchForProfessorID(professorID);
                    if (professorIndex == -1){
                        System.out.println("Professor not found");
                    } else {
                        course.taughtBy = professors.get(professorIndex);
                    }
                    break;
                case 5:
                    System.out.print("Enter new max students: ");
                    course.maxStudents = keyboard.nextInt();
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
                }
            }
        
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
            System.out.println("\nCourse ID not found");
        } else {
            Course course = courses.get(courseIndex);
            System.out.printf("\nCourse Name:     %s\n", course.name);
            System.out.printf("Course ID:       %s\n", course.ID);
            System.out.printf("Credits Offered: %d\n", course.credits);
            if (course.taughtBy.name != null){
                System.out.printf("Professor:       %s\n", course.taughtBy.name);
            } else {
                System.out.print("Professor:       N/A\n");
            }
            
        }
    }

    void displayEnrolledStudents(String courseID){
        /*This method prints out all students enrolled in a given course
         *If a waitlist exists, this method prints that too*/

        int courseIndex = searchForCourseID(courseID);
        if (courseIndex == -1){
            System.out.println("\nCourse ID not found");
        } else {
            Course course = courses.get(courseIndex);
            System.out.printf("\nStudents enrolled in %s:\n", course.name);
            for (String student : course.enrolled){
                System.out.printf("%s\n", student);
            }
            if (course.waitlist.size() > 0){
                System.out.printf("Students waitlisted for %s:\n", course.name);
                for (String student : course.waitlist){
                    System.out.printf("%s\n", student);
                }
            } 
        }
        
    }

    void updateCourseGrade(String studentID, String courseID, String grade){
        /*Updates the course grade for a given student and course */
        int studentIndex = searchForStudentID(studentID);
        int courseIndex = searchForCourseID(courseID);

        if (studentIndex == -1){
            System.out.println("\nStudent ID not found");
        } else if (courseIndex == -1){
            System.out.println("\nCourse ID not found");
        } else {
            Student student = students.get(studentIndex);
            Course course = courses.get(courseIndex);

            student.coursesEnrolledIn.put(course, grade);

            System.out.printf("\n%s now has a %s in the course %s\n", student.name, grade, course.name);
        }
        updateStudentGPA("1111");
    }

    //this method is called whenever a student grade is updated
    void updateStudentGPA(String inputID){
        int studentIndex = searchForStudentID(inputID);
        if (studentIndex == -1){
            System.out.println("\nStudent ID not found");
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
                        break;
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
            System.out.println("\nStudent not found");
        } else {
            Student student = students.get(studentIndex);

            //separate print loops are used to indicate what courses a student has completed and courses they are currently taking
            if (student.coursesCompleted.size() == 0){
                System.out.println("\nStudent has not completed any courses");
            } else {
                System.out.println("\nCourses completed:");
                for (Course course : student.coursesCompleted.keySet()){
                    System.out.printf("%s : %s\n", course.name, student.coursesCompleted.get(course));
                }
            }

            if (student.coursesEnrolledIn.size() == 0){
                System.out.println("\nStudent is not currently enrolled in any courses");
            } else {
                System.out.println("\nCourses enrolled in:");
                for (Course course : student.coursesEnrolledIn.keySet()){
                    System.out.printf("%s : %s\n", course.name, student.coursesEnrolledIn.get(course));
                }
            }
        }
    }

    void viewStudentGPA(String studentID){
        /*Prints out a student's given GPA */
        int studentIndex = searchForStudentID(studentID);
        if (studentIndex == -1){
            System.out.println("\nStudent not found");
        } else {
            Student student = students.get(studentIndex);
            System.out.printf("\n%s GPA: %.2f\n", student.name, student.cumulativeGPA);
        }
    }

    void addProfessor(String name, String ID, String email, String department){
        /*Adds a given professor to the system */
        Professor professor = new Professor(name, ID, email, department);
        professors.add(professor);
        System.out.printf("\n%s added to the system\n", professor.name);
    }

    void removeProfessor(String inputID){
        /*Removes a given professor from the system */
        int professorIndex = searchForProfessorID(inputID);
        if (professorIndex == -1){
            System.out.println("Professor not found");

        } else {
            Professor removedProfessor = professors.get(professorIndex);
            professors.remove(professorIndex);

            // Remove courses associated with the professor
            for (String courseID : removedProfessor.coursesTaught){
                courses.get(searchForCourseID(courseID)).taughtBy = null;
            }

            System.out.println("Professor '" + removedProfessor.name + "' removed");
            }

    }

    void modifyProfessorInfo(Scanner keyboard, String professorID){
        /*Updates given professor info */
        int professorIndex = searchForProfessorID(professorID);
        if (professorIndex == -1){
            System.out.println("\nProfessor not found");
        } else {
            Professor professor = professors.get(professorIndex);
            System.out.println("1. Professor name");
            System.out.println("2. Professor ID");
            System.out.println("3. Professor email");
            System.out.println("4. Professor department");
            int userInput = keyboard.nextInt();
            switch (userInput) {
                case 1:
                    System.out.print("Enter new professor name: ");
                    professor.name = keyboard.nextLine();
                    break;
                case 2:
                    System.out.print("Enter new professor ID: ");
                    String newID = keyboard.nextLine();
                    if (searchForProfessorID(newID) > -1){
                        System.out.println("That ID already belongs to a professor");
                        System.out.println("Try again with a new ID");
                    } else {
                        professor.ID = newID;
                    }
                    break;
                case 3:
                    System.out.print("Enter new professor email: ");
                    professor.email = keyboard.nextLine();
                    break;
                case 4:
                    System.out.print("Enter new professor department: ");
                    professor.department = keyboard.nextLine();
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }

        }
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
            System.out.println("\nProfessor not found");
        } else {
            Professor professor = professors.get(professorIndex);
            System.out.printf("\nProfessor Name:       %s", professor.name);
            System.out.printf("Professor ID:         %s", professor.ID);
            System.out.printf("Professor Email:      %s", professor.email);
            System.out.printf("Professor Department: %s", professor.department);
        }
    }
}
