/*
 * CS 280
 * Jack Parrish
 * Mario Medeles
 * Project 1
 * 2/27
 */

/*
 * We currently have a rough CLI in place, but there is some room to polish it
 * Student, Professor, and Course classes are all defined with necessary data
 * Most Admin methods related to Student classes are done
 * Some Admin methods related to Course classes are done
 * No Admin methods related to Professor classes are done
 * All methods in the class diagram are defined, but do not all have code written
 * Some currently written methods may be re-examined for efficiency and readability
 * There are a few methods that have been added that were not included in the original diagram
 * 
 * printStudentInfo: displays student information on the console
 * printCourseInfo: displays course information on the console
 * enrollCourse: adds a course to a students enrolled courses
 * updateStudentGPA: calculates a students cumulative gpa based on completed and enrolled courses
 * 
 */

import java.util.Scanner;

public class StudentInfoSystem{
    static int getUserInput(Scanner keyboard){
        System.out.print("Enter a number to execute the command: ");
        int userInput = keyboard.nextInt();
        return userInput;
    }
    static void printOptions(){
        /*This functions prints option categories and prompts the user to enter a number */
        System.out.println("1. View Student Options\n2. View Course Options\n3. View Instructor Options");
    }

    static void printStudentOptions(){
        /*This function prints all options associated with the student class */
        System.out.println("1. Add Student");
        System.out.println("2. Remove Student");
        System.out.println("3. Update Student Info");
        System.out.println("4. Print Student Info");
        System.out.println("5. Enroll student in a course");
        System.out.println("6. Update Course Grade");
        System.out.println("7. View Course History");
        System.out.println("8. View Cumulative GPA");
        System.out.println("9. Return to previous menu");
    }

    static void printCourseOptions(){
        /*This function prints all options associated with the course class */
        System.out.println("1. Add Course");
        System.out.println("2. Remove Course");
        System.out.println("3. Update Course Info");
        System.out.println("4. Print Course Info");
        System.out.println("5. Return to previous menu");
    }

    static void printProfessorOptions(){
        /*This function prints all options associated with the professor class */
        System.out.println("1. Add Professor");
        System.out.println("2. Remove Professor");
        System.out.println("3. Update Professor Info");
        System.out.println("4. Print Professor Info");
        System.out.println("5. Return to previous menu");
    }
    public static void main(String[] args){

        //I just have a no-args admin for now
        //We can add some sort of login process later
        //But this will let us test stuff for now
        Admin admin = new Admin();

        Scanner keyboard = new Scanner(System.in);

        //initializing input variables so there aren't duplicate instances in control structure
        String studentID;
        String studentName;

        String courseID;
        String courseName;
        int credits;
        int maxStudents;
        String grade;
        
        String professorID;
        String professorName;
        String professorEmail;
        String department;

        printOptions();
        //I put -1 as the exit value, but it can be easily rewritten to be q or quit or something else
        System.out.print("Enter a number to execute the command, enter -1 to quit: ");
        int userInput = keyboard.nextInt();

        while (userInput != -1){
            if (userInput == 1){
                printStudentOptions();
                userInput = getUserInput(keyboard);
                //nextLine is needed to clear the buffer after reading an int
                keyboard.nextLine();
                switch (userInput) {
                    case 1:

                        //Values for student instance are read
                        System.out.print("Enter the student's name: ");
                        studentName = keyboard.nextLine();
                        System.out.print("Enter the student's ID number: ");
                        studentID = keyboard.nextLine();

                        //Student is instantiated and added to arraylist to track
                        admin.addStudent(studentName, studentID);
                        
                        break;
                    case 2:
                        System.out.print("Enter the student's ID number: ");
                        studentID = keyboard.nextLine();
                        admin.removeStudent(studentID);
                        break;
                    case 3:
                        admin.modifyStudentInfo();
                        break;
                    case 4:
                        System.out.print("Enter the student's ID number: ");
                        studentID = keyboard.nextLine();
                        admin.printStudentInfo(studentID);
                        break;
                    case 5:
                        System.out.print("Enter the student's ID number: ");
                        studentID = keyboard.nextLine();
                        System.out.print("Enter the course ID: ");
                        courseID = keyboard.nextLine();
                        admin.enrollCourse(studentID, courseID);
                        break;
                    case 6:
                        System.out.print("Enter the student's ID number: ");
                        studentID = keyboard.nextLine();
                        System.out.print("Enter the course ID: ");
                        courseID = keyboard.nextLine();
                        System.out.print("Enter the letter grade: ");
                        grade = keyboard.nextLine();
                        admin.updateCourseGrade(studentID, courseID, grade);
                        break;
                    case 7:
                        System.out.print("Enter the student's ID number: ");
                        studentID = keyboard.nextLine();
                        admin.viewCourseHistory(studentID);
                        break;
                    case 8:
                        System.out.print("Enter the student's ID number: ");
                        studentID = keyboard.nextLine();
                        admin.viewStudentGPA(studentID);
                        break;
                    default:
                        System.out.println("------------------\nReturning to previous menu\n------------------");
                        break;
                }

            } else if (userInput == 2){
                printCourseOptions();
                userInput = getUserInput(keyboard);
                keyboard.nextLine();
                switch (userInput) {
                    case 1:
                        if (admin.professors.size() == 0) {
                            System.out.println("There are no professors to teach this course.");
                            System.out.println("Please add a professor to the system before adding a course");
                        } else {
                            System.out.print("Enter the course name: ");
                            courseName = keyboard.nextLine();
                            System.out.print("Enter the course ID: ");
                            courseID = keyboard.nextLine();
                            System.out.print("Enter the professor ID: ");
                            professorID = keyboard.nextLine();

                            System.out.print("Enter the number of credits offered: ");
                            credits = keyboard.nextInt();
                            keyboard.nextLine();

                            System.out.print("Enter the max number of students that can enroll in this course: ");
                            maxStudents = keyboard.nextInt();
                            keyboard.nextLine();

                            admin.addCourse(courseName, courseID, professorID, credits, maxStudents);
                        }
                        break;
                    case 2:
                        System.out.print("Enter the course ID: ");
                        courseID = keyboard.nextLine();
                        admin.removeCourse(courseID);
                        break;
                    case 3:
                        admin.modifyCourseInfo();
                        break;
                    case 4:
                        System.out.print("Enter the course ID");
                        courseID = keyboard.nextLine();
                        admin.printCourseInfo(courseID);
                        break;
                    default:
                        System.out.println("------------------\nReturning to previous menu\n------------------");
                        break;
                }

            } else if (userInput == 3){
                printProfessorOptions();
                userInput = getUserInput(keyboard);
                keyboard.nextLine();
                switch (userInput) {
                    case 1:
                        System.out.print("Enter the professor name: ");
                        professorName = keyboard.nextLine();

                        System.out.print("Enter the professor ID: ");
                        professorID = keyboard.nextLine();

                        System.out.print("Enter the professor email: ");
                        professorEmail = keyboard.nextLine();

                        System.out.print("Enter the professor department: ");
                        department = keyboard.nextLine();

                        admin.addProfessor(professorName, professorID, professorEmail, department);
                        break;
                    case 2:
                        System.out.print("Enter the professor ID: ");
                        professorID = keyboard.nextLine();
                        admin.removeProfessor(professorID);
                        break;
                    case 3:
                        admin.modifyProfessorInfo();
                        break;
                    case 4:
                        System.out.print("Enter the professor ID: ");
                        professorID = keyboard.nextLine();
                        admin.printProfessorInfo(professorID);
                        break;
                    default:
                        System.out.println("------------------\nReturning to previous menu\n------------------");
                        break;
                }
                
            }

            printOptions();
            userInput = getUserInput(keyboard);
        }

        keyboard.close();
        System.out.println("Exiting the program");

    }
}