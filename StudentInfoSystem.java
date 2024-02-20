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
 * All methods in the class diagram are defined, but do not necessarily have code written
 * Some currently written methods may be re-examined for efficiency and readability
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
        System.out.println("5. Update Course Grade");
        System.out.println("6. View Course History");
        System.out.println("7. View Cumulative GPA");
        System.out.println("8. Return to previous menu");
    }

    static void printCourseOptions(){
        /*This function prints all options associated with the course class */
        System.out.println("1. Add Course");
        System.out.println("2. Remove Course");
        System.out.println("3. Update Course Info");
        System.out.println("4. Search for Course");
        System.out.println("5. Return to previous menu");
    }

    static void printProfessorOptions(){
        /*This function prints all options associated with the professor class */
        System.out.println("1. Add Professor");
        System.out.println("2. Remove Professor");
        System.out.println("3. Update Professor Info");
        System.out.println("4. Return to previous menu");
    }
    public static void main(String[] args){

        //I just have a no-args admin for now
        //We can add some sort of login process later
        //But this will let us test stuff for now
        Admin admin = new Admin();

        Scanner keyboard = new Scanner(System.in);

        admin.addStudent("Jack Parrish", "1111");
        admin.addStudent("Mario Medeles", "2222");

        admin.addCourse("Object Oriented Programming", "CS 280", 3, "Fatima Kuehn", 0);
        admin.addCourse("Tools and Techniques", "CS 266", 3, "John Nordlie", 20);

        admin.enrollCourse("1111", "CS 280");
        admin.enrollCourse("2222", "CS 280");

        //admin.updateCourseGrade("1111", "CS 280", "A");
        //admin.updateCourseGrade("1111", "CS 266", "B");

        //admin.viewStudentGPA("1111");
        
        /*

        printOptions();
        //I put -1 as the exit value, but it can be easily rewritten to be q or quit or something else
        System.out.print("Enter a number to execute the command, enter -1 to quit: ");
        int userInput = keyboard.nextInt();

        while (userInput != -1){
            if (userInput == 1){
                printStudentOptions();
                userInput = getUserInput(keyboard);
                switch (userInput) {
                    case 1:
                        
                        String studentName;
                        String studentID;
            
                        //nextLine is needed to clear the buffer after reading in an int
                        keyboard.nextLine();

                        //Values for student instance are read
                        System.out.println("Enter the student's name: ");
                        studentName = keyboard.nextLine();
                        System.out.println("Enter the student's ID number: ");
                        studentID = keyboard.nextLine();

                        //Student is instantiated and added to arraylist to track
                        admin.addStudent(studentName, studentID);
                        
                        break;
                    case 2:
                        String inputID;
                        keyboard.nextLine();
                        System.out.print("Enter student ID: ");
                        inputID = keyboard.nextLine();


                        admin.removeStudent(inputID);
                        break;
                    case 3:
                        admin.modifyStudentInfo();
                        break;
                    case 4:
                        int index = admin.searchForStudentID("1234");
                        break;
                    case 5:
                        admin.updateCourseGrade("A");
                        break;
                    case 6:
                        admin.viewCourseHistory(null);
                        break;
                    case 7:
                        admin.viewStudentGPA(null);
                        break;
                    default:
                        System.out.println("------------------\nReturning to previous menu\n------------------");
                        break;
                }

            } else if (userInput == 2){
                printCourseOptions();
                userInput = getUserInput(keyboard);
                switch (userInput) {
                    case 1:
                        admin.addCourse();
                        break;
                    case 2:
                        admin.removeCourse();
                        break;
                    case 3:
                        admin.modifyCourseInfo();
                        break;
                    case 4:
                        admin.searchForCourseName(null);
                        break;
                    default:
                        System.out.println("------------------\nReturning to previous menu\n------------------");
                        break;
                }

            } else if (userInput == 3){
                printProfessorOptions();
                userInput = getUserInput(keyboard);
                switch (userInput) {
                    case 1:
                        admin.addProfessor();
                        break;
                    case 2:
                        admin.removeProfessor();
                        break;
                    case 3:
                        admin.modifyProfessorInfo();
                        break;
                
                    default:
                        System.out.println("------------------\nReturning to previous menu\n------------------");
                        break;
                }
                
            }

            printOptions();
            userInput = getUserInput(keyboard);
        }

        */

        keyboard.close();
        System.out.println("Exiting the program");

    }
}